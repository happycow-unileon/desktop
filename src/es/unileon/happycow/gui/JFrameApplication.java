package es.unileon.happycow.gui;

import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.abstractFactory.FactoryLogin;
import es.unileon.happycow.controller.JFrameController;
import es.unileon.happycow.help.HelpSet;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *
 * @author dorian
 */
public class JFrameApplication extends javax.swing.JFrame {
    private final es.unileon.happycow.help.HelpSet helpSet;
    
    private static JFrameApplication instance;
    private final Opciones menuBar;
    
    /**
     * Creates new form JFrameApplication
     * @param factory
     */
    private JFrameApplication() {
        initComponents();
        menuBar=new Opciones();
        helpSet = new HelpSet();
        this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing( WindowEvent evt ) { 
                JFrameController.getInstance().exit();
            } 
        } ); 
    }
    
    public static JFrameApplication getInstance(){
        if(instance==null){
            instance=new JFrameApplication();
        }
        return instance;
    }
    
    public static void start(){
        if(instance==null){
            JFrameApplication.getInstance();
        }else{
            instance.setVisible(false);
            instance.removeAll();
            instance=null;
        }
    }
    
    public void changePanel(FactoryWindows factory){
        this.getContentPane().removeAll();
        this.add(factory.getPanel(),BorderLayout.CENTER);
        if(!(factory instanceof FactoryLogin)){
            this.add(menuBar, BorderLayout.NORTH);
        }
        this.pack();
    }
    
    public void changeTitle(String title){
        String text=title.concat(" - HappyCow");
        this.setTitle(text);
    }

    
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("/images/icon.png")));
        pack();
    }
    
    public HelpSet getHelp() {
        return this.helpSet;
    }
}
