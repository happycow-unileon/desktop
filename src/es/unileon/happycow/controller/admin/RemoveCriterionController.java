package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.RemoveCriterion;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.model.composite.Criterion;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class RemoveCriterionController extends Controller {
//    private JFrameController fatherController;

    private final RemoveCriterion panel;

    public RemoveCriterionController(RemoveCriterion panel) {
        this.panel = panel;
//                JFrameApplication.getInstance().getHelp().setHelpOnButton(panel.getButtonHelp(), "AyudaAdminEliminarCriterio");
//                JFrameApplication.getInstance().getHelp().setHelp(panel, "AyudaAdminEliminarCriterio");
//                break;
//           
    }

    public void remove() {
        String target = panel.getElement();
        boolean resultado;
        String targetModified = target.split("-")[0];
        resultado = Database.getInstance().removeCriterion(new IdCriterion(targetModified));
        if (resultado) {
            panel.removeElement(target);
        } else {
            JOptionPane.showMessageDialog(panel, "Error eliminando el criterio", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void update() {
        LinkedList<String> list = new LinkedList<>();

        LinkedList<Criterion> cri = Database.getInstance().getListCriterion();
        for (Criterion criterion : cri) {
            list.add(criterion.getName() + "-" + criterion.getCategory().getValue());
        }
        panel.changeCombo(list);

    }

}
