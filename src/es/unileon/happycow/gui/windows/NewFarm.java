package es.unileon.happycow.gui.windows;

import es.unileon.happycow.application.IWindow;
import es.unileon.happycow.application.Window;
import es.unileon.happycow.controller.IController;
import es.unileon.happycow.handler.IdWindow;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class NewFarm extends IWindow{
    public static Window TYPE=Window.NEW_FARM;

    public NewFarm(IController controller, JPanel panel) {
        super("Nueva granja", true, false, new IdWindow(TYPE, false), panel, controller);
    }
}
