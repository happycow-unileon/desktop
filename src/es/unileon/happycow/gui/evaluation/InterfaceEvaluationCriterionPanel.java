package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.EvaluationControllerCriterion;
import es.unileon.happycow.handler.Category;
import java.util.LinkedList;
import javax.swing.DefaultListModel;

/**
 *
 * @author dorian
 */
public interface InterfaceEvaluationCriterionPanel {
    public void setNumberCow(int number);
    public String getCriterion();
    public String getValoration();
    public void addValoration(String valoration);
    public void addCriterion(String criterion);
    public void removeCriterion(String criterion);
    public void removeValorations();
    public void setController(EvaluationControllerCriterion controller);
    public Category getSelectedCategory();
    public void setModelValoration(DefaultListModel list);
    public void setModelCriterion(DefaultListModel list);
    public void setPonderationCriterion(float ponderation);
    public void setPonderationCategory(float ponderation);
    
    public void setHelp(String help);
    public void deshabilitarValoraciones();
    public void habilitarValoraciones();
    
    public void setComboCriterion(Category cat, LinkedList<String> list);
    
    public void setInformation(String... information);
    
    public void setCriterion(Category cat, LinkedList<String> list);
    
}
