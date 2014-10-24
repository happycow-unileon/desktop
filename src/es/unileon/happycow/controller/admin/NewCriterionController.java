package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.NewCriterion;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Criterion;

/**
 *
 * @author dorian
 */
public class NewCriterionController implements InterfaceController{
    private final NewCriterion panel;

    public NewCriterionController(NewCriterion panel) {
        this.panel = panel;
    }
    
    public void saveCriterion(){
        String name=panel.getNameCriterion();
        IdHandler id=new IdCriterion(name);
        if(Database.getInstance().getCriterion(id)!=null){
            panel.setWarning("Ya existe un criterio con ese nombre.");
        }else{
            Criterion criterion=new Criterion(id, 
                    new IdCategory(panel.getCategory()), 
                    panel.getDescription(), panel.getHelp(), 1);
            if(Database.getInstance().newCriterion(criterion)){
                panel.setWarning("Criterio correctamente añadido.");
            }else{
                panel.setWarning("Problemas al añadir el criterio, pruebe otra vez.");
            }
        }
    }
}
