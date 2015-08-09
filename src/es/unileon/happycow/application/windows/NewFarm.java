package es.unileon.happycow.application.windows;

import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class NewFarm extends IWindow{
    public static Window TYPE=Window.NEW_FARM;

    public NewFarm(Factory factory) {
        super("Nueva granja", true, false, new IdWindow(TYPE, false), factory);
    }
    
    @Override
    public Window getType() {
        return TYPE;
    }
}
