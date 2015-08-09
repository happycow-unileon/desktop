package es.unileon.happycow.factory;

import es.unileon.happycow.application.windows.IWindow;
import es.unileon.happycow.application.windows.Window;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.factory.FactoryLogin;
import es.unileon.happycow.factory.FactoryNewFarm;
import es.unileon.happycow.factory.admin.FactoryAdmin;
import es.unileon.happycow.application.windows.Administrator;
import es.unileon.happycow.application.windows.BarOptions;
import es.unileon.happycow.application.windows.ListFarm;
import es.unileon.happycow.application.windows.Login;
import es.unileon.happycow.application.windows.NewFarm;
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
                
            case LIST_FARMS:
                factory = new FactoryListFarm(parameters);
                break;
                
            case BAR_OPTIONS:
                factory=new FactoryOptionsBar(parameters);
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
break;
                
            case LIST_FARMS:
                window=new ListFarm(factory);
                break;
                
            case BAR_OPTIONS:
                window=new BarOptions(factory);
                break;
                 
            default:
                break;
        }
        
        return window;
    }
}
