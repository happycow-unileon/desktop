package es.unileon.happycow.database.backup;

import es.unileon.happycow.handler.IdHandler;
import java.io.IOException;
import java.io.Serializable;

/**
 * A map file to store and retrieve a valoration from the database
 * @author dorian
 */
public class ValorationDB implements Serializable{
    /**
     * Id of valoration
     */
    private IdHandler idValoration;
    /**
     * Id of evaluation
     */
    private IdHandler idEvaluation;
    /**
     * Id of criterion
     */
    private IdHandler idCriterion;
    /**
     * note
     */
    private float nota;
    /**
     * weighing
     */
    private float weighing;

    /**
     * Constructor
     * @param idValoration
     * @param idEvaluation
     * @param idCriterion
     * @param nota
     * @param weighing 
     */
    public ValorationDB(IdHandler idValoration, IdHandler idEvaluation, IdHandler idCriterion, float nota, float weighing) {
        this.idValoration = idValoration;
        this.idEvaluation = idEvaluation;
        this.idCriterion = idCriterion;
        this.nota = nota;
        this.weighing = weighing;
    }

    /**
     * Constructor, the weighing by default is 1
     * @param idValoration
     * @param idEvaluation
     * @param idCriterion
     * @param nota 
     */
    public ValorationDB(IdHandler idValoration, IdHandler idEvaluation, IdHandler idCriterion, float nota) {
        this(idValoration,idEvaluation,idCriterion,nota,1);
    }
    
    
    /**
     * Get the id of valoration
     * @return 
     */
    public IdHandler getIdValoration() {
        return idValoration;
    }

    /**
     * Get the id of evaluation
     * @return 
     */
    public IdHandler getIdEvaluation() {
        return idEvaluation;
    }

    /**
     * get the id of criterion
     * @return 
     */
    public IdHandler getIdCriterion() {
        return idCriterion;
    }

    /**
     * get the note
     * @return 
     */
    public float getNota() {
        return nota;
    }

    /**
     * get the weighing
     * @return 
     */
    public float getWeighing() {
        return weighing;
    }

    /**
     * set the id of valoration
     * @param idValoration 
     */
    public void setIdValoration(IdHandler idValoration) {
        this.idValoration = idValoration;
    }

    /**
     * Set the id of evaluation
     * @param idEvaluation 
     */
    public void setIdEvaluation(IdHandler idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    /**
     * Set the id of criterion
     * @param idCriterion 
     */
    public void setIdCriterion(IdHandler idCriterion) {
        this.idCriterion = idCriterion;
    }

    /**
     * Set the note
     * @param nota 
     */
    public void setNota(float nota) {
        this.nota = nota;
    }

    /**
     * Set the weighing
     * @param weighing 
     */
    public void setWeighing(float weighing) {
        this.weighing = weighing;
    }
    
    /**
     * Read the object from a stream
     * @param stream
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        idValoration=(IdHandler)stream.readObject();
        idEvaluation=(IdHandler)stream.readObject();
        idCriterion=(IdHandler)stream.readObject();
        nota=stream.readFloat();
        weighing=stream.readFloat();
    }

    /**
     * Write the object to a stream
     * @param stream
     * @throws IOException 
     */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(idValoration);
        stream.writeObject(idEvaluation);
        stream.writeObject(idCriterion);
        stream.writeFloat(nota);
        stream.writeFloat(weighing);
    }
}
