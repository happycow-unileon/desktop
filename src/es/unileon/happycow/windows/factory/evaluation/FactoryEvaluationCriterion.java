package es.unileon.happycow.windows.factory.evaluation;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.controller.evaluation.EvaluationCriterionController;
import es.unileon.happycow.gui.evaluation.criterion.PanelEvaluationCriterion;
import es.unileon.happycow.windows.factory.IFactory;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryEvaluationCriterion extends IFactory{
    /**
     * Panel concreto
     */
    private PanelEvaluationCriterion panel;
    /**
     * Controlador concreto
     */
    private EvaluationCriterionController controller;

    public FactoryEvaluationCriterion(Parameters parameters) {
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
            controller=new EvaluationCriterionController(panel);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
         if(panel==null){
            panel=new PanelEvaluationCriterion();
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
