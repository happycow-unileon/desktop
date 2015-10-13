
package es.unileon.happycow.database.backup;

import java.io.IOException;
import java.io.Serializable;

/**
 *Class map to store and retrieve a ponderation from the database
 * @author dorian
 */
public class PonderationDB implements Serializable{
    /**
     * Identificador de la evaluación
     */
    private int id;
    /**
     * Nombre de lo que pondera (categoría o criterio)
     */
    private String name;
    /**
     * La ponderación
     */
    private float ponderation;

    /**
     * Constructor
     * @param id
     * @param name
     * @param ponderation 
     */
    public PonderationDB(int id, String name, float ponderation) {
        this.id = id;
        this.name = name;
        this.ponderation = ponderation;
    }

    /**
     * Get the id evaluation
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id evaluation
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Get the ponderation
     * @return 
     */
    public float getPonderation() {
        return ponderation;
    }

    /**
     * Set the name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the ponderation
     * @param ponderation 
     */
    public void setPonderation(float ponderation) {
        this.ponderation = ponderation;
    }
    
    /**
     * Read an object from a stream
     * @param stream
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        id=stream.readInt();
        name=(String)stream.readObject();
        ponderation=stream.readFloat();
    }

    /**
     * Write the object to a stream
     * @param stream
     * @throws IOException 
     */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeInt(id);
        stream.writeObject(name);
        stream.writeFloat(ponderation);
    }
    
}
