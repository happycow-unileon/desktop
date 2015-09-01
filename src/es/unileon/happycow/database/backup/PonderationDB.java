
package es.unileon.happycow.database.backup;

import java.io.IOException;
import java.io.Serializable;

/**
 *
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

    public PonderationDB(int id, String name, float ponderation) {
        this.id = id;
        this.name = name;
        this.ponderation = ponderation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getPonderation() {
        return ponderation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPonderation(float ponderation) {
        this.ponderation = ponderation;
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        id=stream.readInt();
        name=(String)stream.readObject();
        ponderation=stream.readFloat();
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeInt(id);
        stream.writeObject(name);
        stream.writeFloat(ponderation);
   // Aquí escribimos en stream los bytes que queramos que se envien por red.
    }
    
}
