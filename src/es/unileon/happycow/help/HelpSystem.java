package es.unileon.happycow.help;

import es.unileon.happycow.Init;
import java.awt.Color;
import java.net.URL;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import oracle.bali.ewt.olaf.OracleLookAndFeel;
import oracle.help.CSHManager;

import oracle.help.Help;
import oracle.help.library.Book;
import oracle.help.library.helpset.HelpSet;

/**
 * System of helps in the application
 * @author dorian
 */
public class HelpSystem {
    /**
     * Instance
     */
    private static HelpSystem instance = null;
    /**
     * Manager of help
     */
    private CSHManager manager;
    /**
     * Help object with the books
     */
    private oracle.help.Help _helpObject;

    /**
     * Constructor
     */
    private HelpSystem() {
//        _useOracleLookAndFeel();

        Vector books = new Vector();

        boolean combineBooks = false;
        boolean useLabelInfo = true;
        try {
            //get the file with the configuration
            URL fileURL = Init.class.getResource("/help/ohguide.hs");
            HelpSet aHelpSet = new HelpSet(fileURL);

            if (aHelpSet != null) {
                //add the book
                books.addElement(aHelpSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Set the whole help
        setHelp(books, combineBooks, useLabelInfo);
    }

    /**
     * Get the only instance of help application
     * @return 
     */
    public static HelpSystem getInstance() {
        if (instance == null) {
            instance = new HelpSystem();
        }
        return instance;
    }

    /**
     * Set the help system
     * @param books
     * @param combineBooks
     * @param useLabelInfo 
     */
    private void setHelp(Vector books, boolean combineBooks, boolean useLabelInfo) {
        int i;

        Class htmlBrowserClass = null;

        try {
            htmlBrowserClass = Class.forName("oracle.help.htmlBrowser.ICEBrowser");
        } catch (Exception e) {
            htmlBrowserClass = null;
            System.err.println("Failed to load ICEBrowser");
        }
        _helpObject = new Help(htmlBrowserClass, combineBooks, useLabelInfo, false);

        if (books != null) {
            for (i = 0; i < books.size(); i++) {
                Book aBook = (Book) books.elementAt(i);
                _helpObject.addBook(aBook);
            }
        }

        manager = new CSHManager(_helpObject);
    }

    /**
     * An oracle look and feel
     */
    static private void _useOracleLookAndFeel() {
        OracleLookAndFeel.setColorScheme(Color.cyan);

        // And install the look-and-feel
        try {
            UIManager.setLookAndFeel(new OracleLookAndFeel());
        } catch (UnsupportedLookAndFeelException ulafe) {
            // OracleLookAndFeel is always supported, so this won't
            // actually ever happen...
        }
    }

    /**
     * Start the help, show the index
     */
    public void start() {
        _helpObject.showNavigatorWindow();
    }

    /**
     * Show a concrete help's tophic
     * @param theme 
     */
    public void seeHelp(HelpTheme theme) {
        String topic = "";
        switch (theme) {
            case Backup:
                topic = "AyudaAdminBackup";
                break;
            case BarOptions:
                topic = "AyudaBarraOpciones";
                break;
            case EnableDisableFarm:
                topic = "AyudaHabilitarDeshabilitar";
                break;
            case EvaluationCow:
                topic = "AyudaEvaluacionVaca";
                break;
            case EvaluationCriterion:
                topic = "AyudaEvaluacionCriterio";
                break;
            case Excel:
                topic = "AyudaExcel";
                break;
            case ExcelAdmin:
                topic = "AyudaAdminExcel";
                break;
            case ListFarm:
                topic = "AyudaListaGranjas";
                break;
            case Login:
                topic = "AyudaLogin";
                break;
            case ManageFarm:
                topic = "AyudaGestionGranja";
                break;
            case ModifyFarm:
                topic = "AyudaModificarDatosGranja";
                break;
            case NewCriterion:
                topic = "AyudaAdminNuevoCriterio";
                break;
            case NewFarm:
                topic = "AyudaNuevaGranjaAnhadir";
                break;
            case NewUser:
                topic = "AyudaAdminNuevoUsuario";
                break;
            case RemoveCriterion:
                topic = "AyudaAdminEliminarCriterio";
                break;
            case RemoveUser:
                topic = "AyudaAdminEliminarUsuario";
                break;
            case Report:
                topic = "AyudaReporte";
                break;
        }
        manager.showTopic(topic);
    }
}
