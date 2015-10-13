package es.unileon.happycow.application;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.windows.factory.FactoryWindows;
import es.unileon.happycow.windows.IWindow;
import es.unileon.happycow.windows.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Principal JFrame where all windows will be displayed
 * @author dorian
 */
public class JFrame extends javax.swing.JFrame implements Observer {
    /**
     * Queue of windows, only showed the first of them
     */
    private GuiQueue queue;
    /**
     * Controller of the jframe
     */
    private JFrameController controller;
    /**
     * Menu options
     */
    private final JPanel menuBar;

    /**
     * Constructor
     */
    public JFrame() {
        //create the controller and add the jframe as an observer to the changes
        //of the controller
        controller = new JFrameController();
        controller.addObserver(this);
        //set the controller of the queue
        queue = new GuiQueue(controller);

        //init the components of the jframe
        initComponents();
        
        //Create the menu bar, get the panel and set the controller (same controller
        //as jframe)
        IFactory factory=FactoryWindows.create(Window.BAR_OPTIONS, new Parameters())
                .getFactory();
        factory.createElements();
        menuBar = factory.getPanel();
        factory.getController().setFrameController(controller);
        
        //control the closing of application
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                controller.exit();
            }
        });

        //create and start the login
        queue.addWindow(FactoryWindows.create(Window.LOGIN, new Parameters()));
        //set the state
        changePanel(queue.peek());

        //positioning window
        setLocationByPlatform(true);
        setVisible(true);
        
        
    }

    /**
     * Indica al jframe de cambiar un panel por otro.
     */
    private void changePanel(IWindow factory) {
        //cambio el titulo
        setTitle(factory.getTitle());

        //remove everything
        this.getContentPane().removeAll();
        //add the window created by the factory
        this.add(factory.getPanel(), BorderLayout.CENTER);
        //set the menu bar on jframe if necessary
        if (factory.getType() != Window.LOGIN) {
            this.add(menuBar, BorderLayout.NORTH);
        }
        
        //pack and give some more space
        pack();
        Dimension size = getSize();
        setSize((int) size.getWidth() + 20, (int) size.getHeight() + 20);
    }

    @Override
    public void update(Observable o, Object o1) {
        //changes of the state!
        switch (controller.getAction()) {
            case BACK:
                queue.back();
                controller.setState(queue.peek().getType());
                changePanel(queue.peek());
                break;

            case STATE:
                Window newState = controller.getState();
                Window actualState = queue.peek().getType();
                //control if i need to change
                if (newState != actualState) {
                    IWindow newWindow = FactoryWindows.create(newState, controller.getParameters());
                    queue.addWindow(newWindow);
                    changePanel(queue.peek());
                }
                break;
        }
    }

    private void initComponents() {
        //controlled by controllers
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("/images/icon.png")));
        pack();
    }
}
