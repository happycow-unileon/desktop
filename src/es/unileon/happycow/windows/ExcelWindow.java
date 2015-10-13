package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *Window excel
 * @author dorian
 */
public class ExcelWindow extends IWindow{
    public static Window TYPE=Window.EXCEL;

    public ExcelWindow(IFactory factory) {
        super("Exportar a Excel",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
