package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ReportController;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import javax.swing.JEditorPane;

/**
 * Panel del login del programa
 *
 * @author dorian
 */
public class PanelReport extends javax.swing.JPanel {

    /**
     * Controlador del panel
     */
    private ReportController controller;

    /**
     * Creates new form PanelLogin
     */
    public PanelReport() {
        //inicializar componentes
        initComponents();
    }

    /**
     * establece un controlador al panel
     *
     * @param controller un LoginController como controlador
     */
    public void setController(ReportController controller) {
        this.controller = controller;
    }

    public void setReport(String report) {
        editorPane.setText(report);
    }

    public JEditorPane getPanelReport() {
        return editorPane;
    }

    /**
     * Inicializa los componentes
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    /**
     * Crea los componentes
     */
    private void createComponents() {
        buttonPrint = new javax.swing.JButton();
        buttonListFarms = new javax.swing.JButton();
        buttonFarm = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();
        buttonHelp = new javax.swing.JButton(
                new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
    }

    /**
     * Configura los componentes
     */
    private void configureComponents() {
        setLayout(new java.awt.GridBagLayout());

        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);

        buttonPrint.setText("Imprimir");
        buttonFarm.setText("Volver a la granja");
        buttonListFarms.setText("Volver al listado de granjas");

        editorPane.setContentType("text/html"); // NOI18N
        editorPane.setText("<html>\n  <head>\n\n  </head>\n  <body>\n<h1>Titulo formato</h1>\n    <p style=\"margin-top: 0\">\n      BEAAA!!! vamos a trolear un poco por si te da por mirar aquí... Muahahahahaha!!!\n    </p>\n<p>Y cómo coño establezco yo el tamaño de párrafos y páginas?!?!?! :(</p>\n  </body>\n</html>\n");
        editorPane.setMaximumSize(new java.awt.Dimension(700, 2147483647));
        editorPane.setMinimumSize(new java.awt.Dimension(1034, 720));
        scroll.setViewportView(editorPane);
    }

    /**
     * Añade los eventos pertinentes
     */
    private void addEvents() {
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.imprimir();
            }
        });

        buttonListFarms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.listFarms();
            }
        });

        buttonFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.farm();
            }
        });

        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpSystem.getInstance().seeHelp(HelpTheme.Report);
            }
        });
    }

    /**
     * Agrega todo al panel configurando el layout
     */
    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonPrint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonListFarms, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scroll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }

    private javax.swing.JButton buttonFarm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonListFarms;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JScrollPane scroll;
}
