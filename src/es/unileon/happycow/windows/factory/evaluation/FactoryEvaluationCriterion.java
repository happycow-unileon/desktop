/*
 * 
 */
package es.unileon.happycow.windows.factory.evaluation;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.controller.evaluation.EvaluationCriterionController;
import es.unileon.happycow.gui.evaluation.PanelCriterionEvaluation;
import es.unileon.happycow.windows.factory.IFactory;
import java.util.HashMap;
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

    public FactoryEvaluationCriterion(HashMap<String, String> parameters) {
        super(parameters);
    }

    @Override
    public IController getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel getPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createElements() {
        super.createElements();
    }
    
}
