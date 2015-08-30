package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.windows.IWindow;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.windows.factory.admin.FactoryAdmin;
import es.unileon.happycow.windows.Administrator;
import es.unileon.happycow.windows.BarOptions;
import es.unileon.happycow.windows.EditFarm;
import es.unileon.happycow.windows.EnableFarm;
import es.unileon.happycow.windows.Evaluation;
import es.unileon.happycow.windows.ExcelWindow;
import es.unileon.happycow.windows.ListFarm;
import es.unileon.happycow.windows.Login;
import es.unileon.happycow.windows.ManageFarm;
import es.unileon.happycow.windows.NewFarm;
import es.unileon.happycow.windows.Password;
import es.unileon.happycow.windows.ReportWindow;

/**
 *
 * @author dorian
 */
public class FactoryWindows {

    public static IWindow create(Window type, Parameters parameters) {
        IWindow window = null;
        IFactory factory = null;
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
                factory = new FactoryOptionsBar(parameters);
                break;
                
            case MANAGE_FARM:
                factory = new FactoryManageFarm(parameters);
                break;
                
            case ENABLE_FARM:
                factory=new FactoryEnableFarm(parameters);
                break;
                
            case PASSWORD:
                factory=new FactoryPassword(parameters);
                break;
                
            case EVALUATION:
                factory= new FactoryEvaluation(parameters);
                break;
                
            case EDIT_FARM:
                factory=new FactoryEditFarm(parameters);
                break;
                
            case REPORT:
                factory=new FactoryReport(parameters);
                break;
                
            case EXCEL:
                factory=new FactoryExcel(parameters);
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
                window = new Administrator(factory);
                break;

            case LIST_FARMS:
                window = new ListFarm(factory);
                break;

            case BAR_OPTIONS:
                window = new BarOptions(factory);
                break;
                
            case MANAGE_FARM:
                window= new ManageFarm(factory);
                break;
                
            case ENABLE_FARM:
                window=new EnableFarm(factory);
                break;
                
            case PASSWORD:
                window=new Password(factory);
                break;

            case EVALUATION:
                window=new Evaluation(factory);
                break;
                
            case EDIT_FARM:
                window=new EditFarm(factory);
                break;
                
            case REPORT:
                window=new ReportWindow(factory);
                break;
                
            case EXCEL:
                window=new ExcelWindow(factory);
                break;
        }

        return window;
    }
}
