package es.unileon.happycow.application;

import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.help.HelpTheme;
import es.unileon.happycow.windows.Window;
import java.util.Observable;


/**
 *
 * @author dorian
 */
public class JFrameController extends Observable {

    private ActionsFrame action;
    private Window state;
    private Parameters parameters;
    private boolean cowView;

    public JFrameController() {
        state = Window.LOGIN;
        parameters = new Parameters();
        cowView = false;
        help();
    }

    private void help() {
        HelpSystem.getInstance();
    }

    public boolean isCowView() {
        return cowView;
    }

    public void setCowView(boolean cowView) {
        this.cowView = cowView;
    }

    public ActionsFrame getAction() {
        return action;
    }

    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    public void setState(Window state) {
        this.state = state;
        action = ActionsFrame.STATE;
        notifyChanges();
    }

    public Window getState() {
        return state;
    }

    public void comeBack() {
        action = ActionsFrame.BACK;
        notifyChanges();
    }

    /**
     * Da por finalizado el programa
     */
    public void exit() {
        //operaciones antes de cerrar todo...
        Database.getInstance().closeDB();
        System.exit(0);
    }

    public void addParameter(String key, String value) {
        parameters.addParameter(key, value);
    }

    public void addParameter(String key, Boolean value) {
        parameters.addParameter(key, value);
    }

    public void addParameter(String key, Integer value) {
        parameters.addParameter(key, value);
    }

    public void addParameter(String key, Object value) {
        parameters.addParameter(key, value);
    }

    public void clearParameters() {
        parameters.clearParameters();
    }

    public Parameters getParameters() {
        return parameters;
    }
}
