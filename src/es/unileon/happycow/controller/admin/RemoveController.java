package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.JFrameController;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.gui.admin.Remove;
import es.unileon.happycow.gui.admin.RemoveAdmin;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class RemoveController implements InterfaceController{
    private JFrameController fatherController;
    private RemoveAdmin remove;
    private Remove panel;

    public RemoveController(RemoveAdmin remove, Remove panel) {
        this.remove = remove;
        this.panel = panel;
        switch(remove){
            case CRITERION:
                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarCriterio");
                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarCriterio");
                break;
            case USER:
                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarUsuario");
                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarUsuario");
                break;
        }
    }
    
    
    
    public void remove(){
        String target=panel.getElement();
        boolean resultado;
        switch(remove){
            case CRITERION:
                resultado=Database.getInstance().removeCriterion(new IdCriterion(target));
                if(resultado){
                    panel.removeElement(target);
                }else{
                    JOptionPane.showMessageDialog(panel, "Error eliminando el criterio", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case USER:
                resultado=Database.getInstance().removeUser(new User(target, "something", Rol.VETERINARIO));
                if(resultado){
                    panel.removeElement(target);
                }else{
                    JOptionPane.showMessageDialog(panel, "Error eliminando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
    
    public void update(){
        LinkedList<String> list=new LinkedList<>();
        switch(remove){
            case CRITERION:
                LinkedList<Criterion> cri=Database.getInstance().getListCriterion();
                for (Criterion criterion : cri) {
                    list.add(criterion.getName());
                }
                panel.changeCombo(list);
                break;
            case USER:
                
                LinkedList<User> user=Database.getInstance().getListUsers();
                for (User us : user) {
                    list.add(us.getName());
                }
                panel.changeCombo(list);
                break;
        }
    }
}
