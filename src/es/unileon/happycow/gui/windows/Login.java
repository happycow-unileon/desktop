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
public class Login extends IWindow{
    public static Window TYPE=Window.LOGIN;

    public Login(IController controller, JPanel panel) {
        super("Login",true, true, new IdWindow(TYPE, false), panel, controller);
    } 
}
