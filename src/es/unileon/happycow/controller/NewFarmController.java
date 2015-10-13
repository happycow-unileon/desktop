package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelNewFarm;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import javax.swing.JOptionPane;

/**
 * Controller of the window's new farm
 * @author dorian
 */
public class NewFarmController extends Controller {
    /**
     * The panel controlled
     */
    private final PanelNewFarm panel;

    /**
     * Constructor
     * @param panel 
     */
    public NewFarmController(PanelNewFarm panel) {
        this.panel = panel;
    }

    /**
     * Come back
     */
    public void returnWindow() {
        controller.comeBack();
    }

    /**
     * Check the farm's data
     * @return true if everything ok, false otherwise and show a dialog with the errors
     */
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

    /**
     * Check if the string is numeric (integer)
     * @param cadena
     * @return true if is numeric false otherwise
     */
    private boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Store the farm
     */
    public void saveFarm() {
        Farm farm;
        //check the integrity of data
        if (controlFarm()) {
            IdHandler identificationfarm = new IdFarm(Database.getInstance().nextIdFarm());
            farm = new Farm(identificationfarm, panel.getNameFarm(), panel.getIdFarm(),
                    panel.getAddressFarm(), panel.getNameFarmer(),
                    panel.getIdFarmer(), Integer.parseInt(panel.getNumberCows()),
                    Database.getInstance().getUser().getId(), panel.getOtherData());

            if (!Database.getInstance().newFarm(farm)) {
                JOptionPane.showMessageDialog(panel,
                        "Errores al guardar la granja, inténtelo de nuevo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //everything ok, next window
                controller.setState(Window.LIST_FARMS);
            }

        }
    }
}
