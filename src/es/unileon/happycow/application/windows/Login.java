package es.unileon.happycow.application.windows;

import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class Login extends IWindow{
    public static Window TYPE=Window.LOGIN;

    public Login(Factory factory) {
        super("Login",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
