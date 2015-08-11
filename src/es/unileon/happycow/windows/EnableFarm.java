package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class EnableFarm extends IWindow{
    public static Window TYPE=Window.ENABLE_FARM;

    public EnableFarm(IFactory factory) {
        super("Habilitar granja",false, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
