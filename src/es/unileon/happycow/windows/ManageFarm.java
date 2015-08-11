package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class ManageFarm extends IWindow{
    public static Window TYPE=Window.MANAGE_FARM;

    public ManageFarm(IFactory factory) {
        super("Datos granja",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
