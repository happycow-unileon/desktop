package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class BarOptions extends IWindow{
    public static Window TYPE=Window.BAR_OPTIONS;

    public BarOptions(IFactory factory) {
        super("BarOptions",true, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
