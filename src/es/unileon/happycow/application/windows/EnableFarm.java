package es.unileon.happycow.application.windows;

import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class EnableFarm extends IWindow{
    public static Window TYPE=Window.ENABLE_FARM;

    public EnableFarm(Factory factory) {
        super("Habilitar granja",false, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
