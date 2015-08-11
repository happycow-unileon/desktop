/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model.composite2;

import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;

/**
 *
 * @author dorian
 */
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
     */
    public Criterion(IdHandler idCriterion, IdHandler category, String description, String help, int weighing) {
        super();
        this.category = category;
        this.description = description;
        this.help = help;
        this.name = idCriterion.toString();

        this.entity = Entity.CRITERION;
        id = idCriterion;
        this.weighing = weighing;
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

    public String toString() {
        return "\t\tCriterio: " + this.id.toString() + "\n" + super.toString();
    }
}
