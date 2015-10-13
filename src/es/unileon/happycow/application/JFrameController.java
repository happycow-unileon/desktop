package es.unileon.happycow.application;

import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.Window;
import java.util.Observable;


/**
 * Controller of the state of jframe (principal window)
 * @author dorian
 */
public class JFrameController extends Observable {
    /**
     * Action to be done
     */
    private ActionsFrame action;
    /**
     * Actual state of window (login, evaluation, admin...)
     */
    private Window state;
    /**
     * Parameters asociated with changes
     */
    private Parameters parameters;
    /**
     * TODO: change to a better site, a property configuration per user
     * Show cow or criterion evaluation?
     */
    private boolean cowView;

    /**
     * Start with login
     */
    public JFrameController() {
        state = Window.LOGIN;
        parameters = new Parameters();
        cowView = false;
        help();
    }

    /**
     * Set the help system
     */
    private void help() {
        HelpSystem.getInstance();
    }

    /**
     * Is cow view?
     * @return true if is cow view
     */
    public boolean isCowView() {
        return cowView;
    }

    /**
     * Set the view
     * @param cowView true if is cow view or false if is criterion view
     */
    public void setCowView(boolean cowView) {
        this.cowView = cowView;
    }

    /**
     * Get the action to be done
     * @return action
     */
    public ActionsFrame getAction() {
        return action;
    }

    /**
     * Notify observer of changes
     */
    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    /**
     * Set the new state of the window (login, evaluation, admin...)
     * @param state the new state
     */
    public void setState(Window state) {
        this.state = state;
        //the action is change to a state
        action = ActionsFrame.STATE;
        notifyChanges();
    }

    /**
     * Get the actual state of window
     * @return 
     */
    public Window getState() {
        return state;
    }

    /**
     * Come back to a previous state
     */
    public void comeBack() {
        //the action is coming back
        action = ActionsFrame.BACK;
        notifyChanges();
    }

    /**
     * Da por finalizado el programa
     */
    public void exit() {
        //operaciones antes de cerrar todo...
        Database.getInstance().closeDB();
        //TODO cerrar el sistema de ayuda
        System.exit(0);
    }

    /**
     * Add a parameter asociated with the change of state
     * @param key
     * @param value 
     */
    public void addParameter(String key, String value) {
        parameters.addParameter(key, value);
    }

    /**
     * Add a parameter asociated with the change of state
     * @param key
     * @param value 
     */
    public void addParameter(String key, Boolean value) {
        parameters.addParameter(key, value);
    }

    /**
     * Add a parameter asociated with the change of state
     * @param key
     * @param value 
     */
    public void addParameter(String key, Integer value) {
        parameters.addParameter(key, value);
    }

    /**
     * Add a parameter asociated with the change of state
     * @param key
     * @param value 
     */
    public void addParameter(String key, Object value) {
        parameters.addParameter(key, value);
    }

    /**
     * Clear all parameter setted
     */
    public void clearParameters() {
        parameters.clearParameters();
    }

    /**
     * Get the parameters
     * @return Parameters
     */
    public Parameters getParameters() {
        return parameters;
    }
}
