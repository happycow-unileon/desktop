package es.unileon.happycow.abstractFactory.admin;

import es.unileon.happycow.abstractFactory.FactoryExcel;
import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.controller.admin.AdminController;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.Administrator;
import es.unileon.happycow.gui.admin.RemoveAdmin;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Create panel and controller of the window to administration
 * @author dorian
 */
public class FactoryAdmin implements FactoryWindows{
    /**
     * Concrete controller
     */
    private AdminController controller;
    /**
     * Concrete panel
     */
    private Administrator panel;
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
        if(controller!=null){
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
        if(panel!=null){
            createController();
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
            controller=new AdminController();
            //set the new controller to the panel
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            //create the principal panel
            panel=new Administrator();
            //create the specific panels (tabs) to the principal panel
            panel.addTab("Nuevo Usuario", createNewUser().getPanel());
            panel.addTab("Nuevo Criterio", createNewCriterion().getPanel());
            panel.addTab("Eliminar Usuario", createRemove(RemoveAdmin.USER).getPanel());
            panel.addTab("Eliminar Criterio", createRemove(RemoveAdmin.CRITERION).getPanel());
            panel.addTab("Backup", createBackup().getPanel());
            panel.addTab("Excel", createExcel().getPanel());
        }
        
        if(controller!=null){
            panel.setController(controller);
        }
    }
    
    /**
     * Create the tab excel
     * @return  the factory that create the excel's panel
     */
    private FactoryWindows createExcel(){
        //create the list of users and theirs lists of farms
        LinkedHashMap<User, LinkedList<Farm>> list=new LinkedHashMap<>();
        //for every user in the database...
        for (User user : Database.getInstance().getListUsers()) {
            //store his list of farms
            list.put(user, Database.getInstance().getListFarms(user.getId()));
        }
        //create the excel's panel
        FactoryExcel factory=new FactoryExcel(list);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create the tab new user
     * @return  the factory that create the new user's panel
     */
    private FactoryWindows createNewUser(){
        FactoryNewUser factory=new FactoryNewUser();
        factory.createElements();
        return factory;
    }
    
    /**
     * Create the tab new criterion
     * @return  the factory that create the new criterion's panel
     */
    private FactoryWindows createNewCriterion(){
        FactoryNewCriterion factory=new FactoryNewCriterion();
        factory.createElements();
        return factory;
    }
    
    /**
     * Create a tab that remove user or criterion, specific by an enumeration
     * @param what specific what that should remove with an enumeration RemoveAdmin
     * @return the concrete factory
     */
    private FactoryWindows createRemove(RemoveAdmin what){
        FactoryRemove factory=new FactoryRemove(what);
        factory.createElements();
        return factory;
    }

    /**
     * Create the tab backup
     * @return  the factory that create the bakcup's panel
     */
    private FactoryWindows createBackup(){
        FactoryBackup factory=new FactoryBackup();
        factory.createElements();
        return factory;
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
