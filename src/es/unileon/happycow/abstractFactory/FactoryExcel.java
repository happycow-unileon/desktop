package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.ExcelController;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelExcel;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Create the panel and controller to select farms and evaluations to export
 * @author dorian
 */
public class FactoryExcel implements FactoryWindows{
    /**
     * Concrete panel
     */
    private PanelExcel panel;
    /**
     * Concrete controller
     */
    private ExcelController controller;
    /**
     * List of users and their farms
     */
    private LinkedHashMap<User, LinkedList<Farm>> list;

    /**
     * Builder receive a list of users with their farms.
     * That list will be show in the window to select which farms and evaluation
     * can be selected
     * @param list a linkedhashmap with users and their farms
     */
    public FactoryExcel(LinkedHashMap<User, LinkedList<Farm>> list) {
        this.list=list;
    }
    
    /**
     * Builder that receive a single list of farm. This suppose that there
     * is an only user, the user logged
     * @param list a linkedlist of farms
     */
    public FactoryExcel(LinkedList<Farm> list){
        this.list=new LinkedHashMap<>();
        this.list.put(Database.getInstance().getUser(), list);
    }
    
    
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel() 
     */
    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new ExcelController(panel, list);
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelExcel(new LinkedList<>(list.keySet()));
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createElements() 
     */
    @Override
    public void createElements() {
        createPanel();
        createController();
    }
    
}
