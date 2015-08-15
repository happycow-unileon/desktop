/*
 * 
 */
package es.unileon.happycow.windows.factory.evaluation;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.evaluation.EvaluationCriterionController;
import es.unileon.happycow.gui.evaluation.PanelCriterionEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.evaluation.EvaluationCriterionModel;
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
    private PanelCriterionEvaluation panel;
    /**
     * Controlador concreto
     */
    private EvaluationCriterionController controller;

    public FactoryEvaluationCriterion(Parameters parameters) {
        super(parameters);
    }

    @Override
    public IController getController() {
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
            IdHandler farm=new IdFarm(Integer.parseInt(parameters.getString("idFarm")));
            boolean isNew = parameters.getBoolean("isNew");
            
            EvaluationCriterionModel evaluation=new EvaluationCriterionModel(farm);
            
            if(!isNew){
                //rellenar los datos de evaluación
            }
            
            controller=new EvaluationCriterionController(panel, evaluation, isNew);
            panel.setController(controller);
        }
    }

    @Override
    public void createPanel() {
         if(panel==null){
            panel=new PanelCriterionEvaluation();
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
