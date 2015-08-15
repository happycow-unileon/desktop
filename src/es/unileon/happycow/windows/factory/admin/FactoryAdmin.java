package es.unileon.happycow.windows.factory.admin;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.admin.AdminController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.gui.admin.Administrator;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Create panel and controller of the window to administration
 * @author dorian
 */
public class FactoryAdmin extends IFactory{
    /**
     * Concrete controller
     */
    private AdminController controller;
    /**
     * Concrete panel
     */
    private Administrator panel;

    public FactoryAdmin(Parameters parameters) {
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
            panel.addTab("Eliminar Usuario", createRemoveUser().getPanel());
            panel.addTab("Eliminar Criterio", createRemoveCriterion().getPanel());
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
    private IFactory createExcel(){
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
    private IFactory createNewUser(){
        IFactory factory=new FactoryNewUser(parameters);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create the tab new criterion
     * @return  the factory that create the new criterion's panel
     */
    private IFactory createNewCriterion(){
        IFactory factory=new FactoryNewCriterion(parameters);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create a tab that remove user or criterion, specific by an enumeration
     * @param what specific what that should remove with an enumeration RemoveAdmin
     * @return the concrete factory
     */
    private IFactory createRemoveUser(){
        IFactory factory=new FactoryRemoveUser(parameters);
        factory.createElements();
        return factory;
    }
    
    /**
     * Create a tab that remove user or criterion, specific by an enumeration
     * @param what specific what that should remove with an enumeration RemoveAdmin
     * @return the concrete factory
     */
    private IFactory createRemoveCriterion(){
        IFactory factory=new FactoryRemoveCriterion(parameters);
        factory.createElements();
        return factory;
    }

    /**
     * Create the tab backup
     * @return  the factory that create the bakcup's panel
     */
    private IFactory createBackup(){
        IFactory factory=new FactoryBackup(parameters);
        factory.createElements();
        return factory;
    }
   
    
}
