package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.EvaluationControllerCow;
import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.gui.evaluation.PanelEvaluationCow;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
import javax.swing.JPanel;

/**
 * Create the panel and the controller for a window that evaluate by cows
 * @author dorian
 */
public class FactoryEvaluationCowView implements FactoryWindows{
    private EvaluationControllerCow controller;
    private PanelEvaluationCow panel;
    
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
    public FactoryEvaluationCowView(IdHandler farm) {
        this.idFarm = farm;
        model = null;
    }

    
    /**
     * Builder, received a model with a made evaluation
     * The farm's identifier are inside the model
     * @param model 
     */
    public FactoryEvaluationCowView(InterfaceEvaluationModel model) {
        this.model=model;
        idFarm=null;
    }
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
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
            if(model==null){
                controller = new EvaluationControllerCow(panel, idFarm);
            }else{
                controller = new EvaluationControllerCow(panel, model);
            }
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
                panel=new PanelEvaluationCow();
        }
        
        //if controller exists, set the controller to the panel
        if(controller!=null){
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
