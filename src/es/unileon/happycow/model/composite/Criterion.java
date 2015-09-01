package es.unileon.happycow.model.composite;

import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author dorian
 */
public class Criterion extends Composite implements Cloneable, Serializable {

    /**
     * Category of the criterion
     */
    private IdHandler category;
    /**
     * Short description about the criterion
     */
    private String description;
    /**
     * Longer description about the criterion, the help of the criterion
     */
    private String help;
    /**
     * Name of the criterion
     */
    private String name;

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

    public Criterion(IdHandler idCriterion, float weighing) {
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
    public Criterion(IdHandler idCriterion, IdHandler category, String description, String help, float weighing) {
        super();
        this.category = category;
        this.description = description;
        this.help = help;
        this.name = idCriterion.getValue();

        this.entity = Entity.CRITERION;
        id = idCriterion;
        this.weighing = weighing;
    }

    public Criterion(String name, IdHandler category, String description, String help, float weighing) {
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
        return "\t\tCriterio: " + this.id.getValue() + "\n" + super.toString();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        id=(IdHandler)stream.readObject();
        category=(IdHandler)stream.readObject();
        description=(String)stream.readObject();
        help=(String)stream.readObject();
        name=(String)stream.readObject();
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(id);
        stream.writeObject(category);
        stream.writeObject(description);
        stream.writeObject(help);
        stream.writeObject(name);
    }
}
