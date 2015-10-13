package es.unileon.happycow.controller;

import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelOptions;
import javax.swing.JOptionPane;

/**
 * Controller of the menu bar option
 * @author dorian
 */
public class BarOptionsController extends Controller {
    /**
     * panel
     */
    private final PanelOptions panel;
    /**
     * Selected view
     */
    private boolean viewCows;

    /**
     * Constructor
     * @param panel 
     */
    public BarOptionsController(PanelOptions panel) {
        this.panel = panel;
        viewCows = false;
    }

    /**
     * Close application
     */
    public void close() {
        controller.exit();
    }

    /**
     * Unlogin
     */
    public void unlogin() {
        controller.setState(Window.LOGIN);
    }

    /**
     * Come back
     */
    public void comeBack() {
        controller.comeBack();
    }

    /**
     * Change the view of evaluations
     */
    public void changeView() {
        //only when are not in evaluation
        if (controller.getState() != Window.EVALUATION) {
            viewCows = !viewCows;
            if (viewCows) {
                panel.setButtonView("Modo Vacas");
            } else {
                panel.setButtonView("Modo Criterios");
            }
            controller.setCowView(viewCows);

        } else {
            JOptionPane.showMessageDialog(null,
                    "No se puede cambiar durante una evaluación.", "No permitido",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Change the password
     */
    public void changePassword() {
        controller.setState(Window.PASSWORD);
    }
}
