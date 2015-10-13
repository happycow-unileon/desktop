package es.unileon.happycow.windows;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdHandler;
import javax.swing.JPanel;

/**
 * Abstract class for a window
 * @author dorian
 */
public abstract class IWindow {
    /**
     * Only an instance in the application?
     */
    private boolean unique;
    /**
     * Can be in this windows by returning from another window?
     */
    private final boolean back;
    /**
     * Title of window
     */
    private final String title;
    /**
     * Id of window
     */
    private final IdHandler id;
    
    /**
     * Factory of the window
     */
    private IFactory factory;
    /**
     * Controller of the window
     */
    private Controller controller;
    /**
     * Panel of the window
     */
    private JPanel panel;

    /**
     * Constructor
     * @param title the title of window
     * @param unique only an instance in the application?
     * @param back can be in this windows coming back from another?
     * @param id the id
     * @param factory the factory
     */
    public IWindow(String title,boolean unique, boolean back, IdHandler id, IFactory factory) {
        this.title=title;
        this.unique=unique;
        this.back=back;
        this.id = id;
        this.factory=factory;
    }
    
    /**
     * Get the type of the window
     * @return 
     */
    public abstract Window getType();

    /**
     * Get the factory of this window
     * @return 
     */
    public IFactory getFactory() {
        return factory;
    }

    /**
     * Get title
     * @return 
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Can back from another window?
     * @return 
     */
    public boolean isBack() {
        return back;
    }
    
    /**
     * Is unique in the application?
     * @return 
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Set unique
     * @param unique 
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * Get id
     * @return 
     */
    public IdHandler getId() {
        return id;
    }
    
    /**
     * Create the window
     */
    private void createWindow(){
        factory.createElements();
        this.panel=factory.getPanel();
        this.controller=factory.getController();
        factory=null;
    }
    
    /**
     * Get the controller
     * @return 
     */
    public Controller getController() {
        if(controller==null){
            createWindow();
        }
        return controller;
    }

    /**
     * get the panel
     * @return 
     */
    public JPanel getPanel() {
        if(panel==null){
            createWindow();
        }
        return panel;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IWindow){
            IWindow window=(IWindow)obj;
            return window.getId().compareTo(id)==0;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id.getValue().hashCode();
    }
    
    /**
     * Resume the window
     * @param parameters 
     */
    public void onResume(Parameters parameters){
        getController().onResume(parameters);
    };
    /**
     * Create the window (means the action's taken when the window is created
     * on queue
     * @param parameters 
     */
    public void onCreate(Parameters parameters){
        getController().onCreate(parameters);
    };
    /**
     * Action of destroy window
     */
    public void onDestroy(){
        getController().onDestroy();
    };
    
}
