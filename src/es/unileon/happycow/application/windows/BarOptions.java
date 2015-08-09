package es.unileon.happycow.application.windows;

import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class BarOptions extends IWindow{
    public static Window TYPE=Window.BAR_OPTIONS;

    public BarOptions(Factory factory) {
        super("BarOptions",true, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
