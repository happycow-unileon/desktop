package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.LoginController;
import es.unileon.happycow.controller.ReportController;
import es.unileon.happycow.gui.PanelLogin;
import es.unileon.happycow.gui.PanelReport;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the login window
 * @author dorian
 */
public class FactoryReport extends IFactory {
    /**
     * Panel concreto
     */
    private PanelReport panel;
    /**
     * Controlador concreto
     */
    private ReportController controller;

    public FactoryReport(Parameters parameters) {
        super(parameters);
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public Controller getController() {
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
            controller=new ReportController(panel);
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelReport();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }
   
}
