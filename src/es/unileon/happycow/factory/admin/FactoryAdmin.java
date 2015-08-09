package es.unileon.happycow.factory.admin;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.AdminController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.gui.panels.admin.Administrator;
import es.unileon.happycow.gui.panels.admin.RemoveAdmin;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Create panel and controller of the window to administration
 * @author dorian
 */
public class FactoryAdmin extends Factory{
    /**
     * Concrete controller
     */
    private AdminController controller;
    /**
     * Concrete panel
     */
    private Administrator panel;

    public FactoryAdmin(HashMap<String, String> parameters) {
        super(parameters);
    }
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public IController getController() {
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
//            panel.addTab("Excel", createExcel().getPanel());
        }
        
        if(controller!=null){
            panel.setController(controller);
        }
    }
    
    /**
     * Create the tab excel
     * @return  the factory that create the excel's panel
     */
    private Factory createExcel(){
        //create the list of users and theirs lists of farms
        LinkedHashMap<User, LinkedList<Farm>> list=new LinkedHashMap<>();
        //for every user in the database...
        for (User user : Database.getInstance().getListUsers()) {
            //store his list of farms
            list.put(user, Database.getInstance().getListFarms(user.getId()));
        }
        //create the excel's panel TODO
//        Factory factory=new FactoryExcel(list);
//        factory.createElements();
//        return factory;
        return null;
    }
    
    /**
     * Create the tab new user
     * @return  the factory that create the new user's panel
     */
    private Factory createNewUser(){
        FactoryNewUser factory=new FactoryNewUser(parameters);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create the tab new criterion
     * @return  the factory that create the new criterion's panel
     */
    private Factory createNewCriterion(){
        FactoryNewCriterion factory=new FactoryNewCriterion(parameters);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create a tab that remove user or criterion, specific by an enumeration
     * @param what specific what that should remove with an enumeration RemoveAdmin
     * @return the concrete factory
     */
    private Factory createRemove(RemoveAdmin what){
        FactoryRemove factory=new FactoryRemove(what,parameters);
        factory.createElements();
        return factory;
    }

    /**
     * Create the tab backup
     * @return  the factory that create the bakcup's panel
     */
    private Factory createBackup(){
        FactoryBackup factory=new FactoryBackup(parameters);
        factory.createElements();
        return factory;
    }
   
    
}
