package es.unileon.happycow.controller;

import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelOptions;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class BarOptionsController extends Controller {

    private final PanelOptions panel;
    private boolean viewCows;

    public BarOptionsController(PanelOptions panel) {
        this.panel = panel;
        viewCows = false;
    }

    public void close() {
        controller.exit();
    }

    public void unlogin() {
        controller.setState(Window.LOGIN);
    }

    public void comeBack() {
        controller.comeBack();
    }

    public void changeView() {
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

    public void changePassword() {
        controller.setState(Window.PASSWORD);
    }
}
