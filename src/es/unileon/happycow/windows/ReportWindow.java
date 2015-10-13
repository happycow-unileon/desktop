package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 * Window report
 * @author dorian
 */
public class ReportWindow extends IWindow{
    public static Window TYPE=Window.REPORT;

    public ReportWindow(IFactory factory) {
        super("Reporte",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
