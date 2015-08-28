package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelEditFarm;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class EditFarmController extends Controller {

    private final PanelEditFarm panel;
    private final IdHandler idFarm;

    public EditFarmController(PanelEditFarm panel) {
        this(panel, null);
    }

    public EditFarmController(PanelEditFarm panel, IdHandler idFarm) {
        this.panel = panel;
        this.idFarm = idFarm;
    }

    public void returnWindow() {
        controller.comeBack();
    }

    private boolean controlFarm() {
        boolean correct = true;
        StringBuilder errores = new StringBuilder();
        if (panel.getNameFarm().trim().compareToIgnoreCase("") == 0) {
            correct = false;
            errores.append("\nDebe dar un nombre a la granja.\nUse el identificador de la granja como nombre si no se le ocurre uno.");
        }

        if (panel.getNumberCows().trim().compareTo("") == 0) {
            correct = false;
            errores.append("\nDebe introducir el número de vacas exactas/aproximadas que tiene la granja.");
        } else if (!isNumeric(panel.getNumberCows())) {
            correct = false;
            errores.append("\nDebe introducir un número entero de vacas.");
        }

        if (!correct) {
            JOptionPane.showMessageDialog(panel, errores, "Mala entrada", JOptionPane.WARNING_MESSAGE);
        }

        return correct;
    }

    private boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void saveFarm() {
        Farm farm;
        if (controlFarm()) {
            farm = Database.getInstance().getFarm(idFarm);
            farm.setAddress(panel.getAddressFarm());
            farm.setCowNumber(Integer.parseInt(panel.getNumberCows()));
            farm.setDniFarmer(panel.getIdFarmer());
            farm.setFarmName(panel.getNameFarm());
            farm.setFarmerName(panel.getNameFarmer());
            farm.setOtherData(panel.getOtherData());

            if (!Database.getInstance().updateFarm(farm)) {
                JOptionPane.showMessageDialog(panel,
                        "Errores al guardar la granja, inténtelo de nuevo",
                        "Error", JOptionPane.WARNING_MESSAGE);

            } else {
                controller.setState(Window.LIST_FARMS);
            }

        }
    }
}
