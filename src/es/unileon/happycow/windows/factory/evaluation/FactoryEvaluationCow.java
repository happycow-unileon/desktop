/*
 * 
 */
package es.unileon.happycow.windows.factory.evaluation;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.evaluation.EvaluationCowController;
import es.unileon.happycow.gui.evaluation.cow.PanelEvaluationCow;
import es.unileon.happycow.windows.factory.IFactory;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryEvaluationCow extends IFactory{
    private EvaluationCowController controller;
    private PanelEvaluationCow panel;

    public FactoryEvaluationCow(Parameters parameters) {
        super(parameters);
    }

    @Override
    public Controller getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }

    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new EvaluationCowController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelEvaluationCow();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }

    @Override
    public void createElements() {
        super.createElements();
    }
    
    
    
}
