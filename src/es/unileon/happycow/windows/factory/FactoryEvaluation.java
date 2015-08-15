package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.windows.factory.evaluation.FactoryEvaluationCow;
import es.unileon.happycow.windows.factory.evaluation.FactoryEvaluationCriterion;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the login window
 *
 * @author dorian
 */
public class FactoryEvaluation extends IFactory {
    /**
     * La factoría que distingue qué evaluación instanciar y en el que delego
     * absolutamente todas las tareas
     */
    private IFactory concreteFactoryEvaluation;

    /**
     *
     * @param parameters
     */
    public FactoryEvaluation(Parameters parameters) {
        super(parameters);
        String mode = parameters.getString("mode");
        mode=(mode==null)? "":mode;
        switch (mode) {
            case "CRITERION":
                concreteFactoryEvaluation=new FactoryEvaluationCriterion(parameters);
                break;

            case "COW":
                concreteFactoryEvaluation=new FactoryEvaluationCow(parameters);
                break;
                
            default:
                concreteFactoryEvaluation=new FactoryEvaluationCriterion(parameters);
                break;
        }
    }

    /**
     *
     * @return 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController()
     */
    @Override
    public IController getController() {
        return concreteFactoryEvaluation.getController();
    }

    /**
     *
     * @return 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel()
     */
    @Override
    public JPanel getPanel() {
        return concreteFactoryEvaluation.getPanel();
    }

    /**
     * @see
     * es.unileon.happycow.abstractFactory.FactoryWindows#createController()
     */
    @Override
    public void createController() {
        concreteFactoryEvaluation.createController();
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        concreteFactoryEvaluation.createPanel();
    }

    @Override
    public void createElements() {
        concreteFactoryEvaluation.createElements();
    }

}
