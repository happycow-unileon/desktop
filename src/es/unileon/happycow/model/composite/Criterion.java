package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.composite.table.Entity;
import java.util.LinkedList;

public class Criterion extends Composite implements Cloneable {

    /**
     * Category of the criterion
     */
    private final IdHandler category;
    /**
     * Short description about the criterion
     */
    private final String description;
    /**
     * Longer description about the criterion, the help of the criterion
     */
    private final String help;
    /**
     * Name of the criterion
     */
    private final String name;

    /**
     * Constructor
     *
     * @param idCriterion the identifier of the criterion
     * @param category
     */
    public Criterion(IdHandler idCriterion, IdHandler category) {
        this(idCriterion, category, "", "", 1);
    }

    public Criterion(String idCriterion, IdHandler category) {
        this((IdHandler) new IdCriterion(idCriterion), category);
    }

    public Criterion(IdHandler idCriterion, int weighing) {
        this(idCriterion, null, "", "", weighing);
    }

    /**
     * Builder
     *
     * @param idCriterion the identifier of the criterion
     * @param category the category
     * @param description the description
     * @param help the longer description
     * @param weighing
     * @param name the name
     */
    public Criterion(IdHandler idCriterion, IdHandler category, String description, String help, int weighing) {

        this.category = category;
        this.description = description;
        this.help = help;
        this.name = idCriterion.toString();
        
        this.TYPE=Entity.CRITERION;
        id = idCriterion;
        _list = new LinkedList<>();
        _weighing = weighing;
    }

    public Criterion(String name, IdHandler category, String description, String help, int weighing) {
        this(new IdCriterion(name), category, description, help, weighing);
    }

    public Criterion(String name, IdHandler category, String description, String help) {
        this(new IdCriterion(name), category, description, help, 1);
    }
    

    /**
     * Get the category
     *
     * @return the category
     */
    public IdHandler getCategory() {
        return category;
    }

    /**
     * Get the description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the help, longer description
     *
     * @return the help
     */
    public String getHelp() {
        return help;
    }

    /**
     * Get the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
