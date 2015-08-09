package es.unileon.happycow.application;

import es.unileon.happycow.factory.FactoryWindows;
import es.unileon.happycow.gui.IWindow;
import es.unileon.happycow.gui.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author dorian
 */
public class JFrame extends javax.swing.JFrame implements Observer {

    private GuiQueue queue;
    private JFrameController controller;
//    private final Opciones menuBar;

    public JFrame() {
        controller = new JFrameController();
        controller.addObserver(this);
        queue = new GuiQueue(controller);
        
        initComponents();
//        menuBar=new Opciones();
//        helpSet = new HelpSet();
        this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing( WindowEvent evt ) { 
                controller.exit();
            } 
        } );
        
        //inicializo el login
        queue.addWindow(FactoryWindows.create(Window.LOGIN, new HashMap<String, String>()));
        //seteo el estado
        changePanel(queue.peek());
        
        setLocationByPlatform(true);
        //setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Indica al jframe de cambiar un panel por otro.
     */
    private void changePanel(IWindow factory) {
        //cambio el titulo
        setTitle(factory.getTitle());
        
        this.getContentPane().removeAll();
        this.add(factory.getPanel(), BorderLayout.CENTER);
//        if (!(factory instanceof FactoryLogin)) {
//            this.add(menuBar, BorderLayout.NORTH);
//        }
//        this.pack();
        //ajusta de nuevo el tamaño del jframe
        pack();
        Dimension size=getSize();
        setSize((int)size.getWidth()+20, (int)size.getHeight()+20);
    }

    @Override
    public void update(Observable o, Object o1) {
        Window newState=controller.getState();
        Window actualState=queue.peek().getType();
        
        if(newState!=actualState){
            IWindow newWindow=FactoryWindows.create(newState, controller.getParameters());
            queue.addWindow(newWindow);
            changePanel(queue.peek());
        }
        //ir al queue y establecer el nuevo estado del jframe
        //él llama al factory para crear el windows
        //posteriormente establece el cambio de panel
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("/images/icon.png")));
        pack();
    }
}
