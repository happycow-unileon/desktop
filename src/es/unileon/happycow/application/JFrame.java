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
    private final Options menuBar;

    public JFrame() {
        controller = new JFrameController();
        controller.addObserver(this);
        queue = new GuiQueue(controller);

        initComponents();
        menuBar = new Options(controller);
//        helpSet = new HelpSet();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                controller.exit();
            }
        });

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
        if (factory.getType() != Window.LOGIN) {
            this.add(menuBar, BorderLayout.NORTH);
        }
//        this.pack();
        //ajusta de nuevo el tamaño del jframe
        pack();
        Dimension size = getSize();
        setSize((int) size.getWidth() + 20, (int) size.getHeight() + 20);
    }

    @Override
    public void update(Observable o, Object o1) {
        switch (controller.getAction()) {
            case BACK:
                queue.back();
                changePanel(queue.peek());
                break;

            case STATE:
                Window newState = controller.getState();
                Window actualState = queue.peek().getType();
                
                if (newState != actualState) {
                    IWindow newWindow = FactoryWindows.create(newState, controller.getParameters());
                    queue.addWindow(newWindow);
                    changePanel(queue.peek());
                }
                break;
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("/images/icon.png")));
        pack();
    }
}
