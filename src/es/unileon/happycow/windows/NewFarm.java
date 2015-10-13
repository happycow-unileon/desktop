package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 * Window new farm
 * @author dorian
 */
public class NewFarm extends IWindow{
    public static Window TYPE=Window.NEW_FARM;

    public NewFarm(IFactory factory) {
        super("Nueva granja", true, false, new IdWindow(TYPE, false), factory);
    }
    
    @Override
    public Window getType() {
        return TYPE;
    }
}
