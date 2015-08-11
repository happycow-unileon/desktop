/*
 * 
 */
package es.unileon.happycow.windows.factory.evaluation;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.windows.factory.IFactory;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryEvaluationCow extends IFactory{

    public FactoryEvaluationCow(HashMap<String, String> parameters) {
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
