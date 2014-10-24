package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.EvaluationControllerCriterion;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.gui.evaluation.PanelEvaluation;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
import javax.swing.JPanel;

/**
 * Create the panel and the controller of the evaluation's window
 * This evaluation evaluate by criterion
 * @author dorian, y otros
 */
public class FactoryEvaluationCriterionView implements FactoryWindows {
    /**
     * Concrete panel
     */
    private PanelEvaluation panel;
    /**
     * Concrete controller
     */
    private EvaluationControllerCriterion controller;
    /**
     * Model of evaluation
     */
    private final InterfaceEvaluationModel model;
    /**
     * identifier of the farm is evaluated
     */
    private final IdHandler idFarm;

    /**
     * Builder, receive the identifier of the farm is evaluating and
     * create a new model
     * @param farm 
     */
    public FactoryEvaluationCriterionView(IdHandler farm) {
        this.idFarm = farm;
        model = null;
    }

    
    /**
     * Builder, received a model with a made evaluation
     * The farm's identifier are inside the model
     * @param model 
     */
    public FactoryEvaluationCriterionView(InterfaceEvaluationModel model) {
        this.model=model;
        idFarm=null;
    }

    /**
     *
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController()
     */
    @Override
    public InterfaceController getController() {
        if (controller == null) {
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
        if (panel == null) {
            createPanel();
        }
        return panel;
    }

    /**
     * @see
     * es.unileon.happycow.abstractFactory.FactoryWindows#createController()
     */
    @Override
    public void createController() {
        if (panel == null) {
            createPanel();
        }

        //create a controller depending if I have the model or the identifier
        if (controller == null) {
            if(model==null){
                controller = new EvaluationControllerCriterion(panel, idFarm);
            }else{
                controller = new EvaluationControllerCriterion(panel, model);
            }
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if (panel == null) {
            panel = new PanelEvaluation();
        }

        if (controller != null) {
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
