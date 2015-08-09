package es.unileon.happycow.factory;

import es.unileon.happycow.gui.IWindow;
import es.unileon.happycow.gui.Window;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.factory.FactoryLogin;
import es.unileon.happycow.factory.FactoryNewFarm;
import es.unileon.happycow.factory.admin.FactoryAdmin;
import es.unileon.happycow.gui.windows.Administrator;
import es.unileon.happycow.gui.windows.Login;
import es.unileon.happycow.gui.windows.NewFarm;
import java.util.HashMap;

/**
 *
 * @author dorian
 */
public class FactoryWindows {

    public static IWindow create(Window type, HashMap<String,String> parameters) {
        IWindow window = null;
        Factory factory = null;
        switch (type) {
            case LOGIN:
                factory = new FactoryLogin(parameters);
                break;

            case NEW_FARM:
                factory = new FactoryNewFarm(parameters);
                break;
                
            case ADMINISTRATION:
                factory = new FactoryAdmin(parameters);
                break;
        }

        if (factory == null) {
            return window;
        }

        switch (type) {
            case LOGIN:
                window = new Login(factory);
                break;

            case NEW_FARM:
                window = new NewFarm(factory);
                break;
                
            case ADMINISTRATION:
                window=new Administrator(factory);

            default:
                break;
        }
        
        return window;
    }
}
