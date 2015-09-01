/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unileon.happycow.database.backup;

import es.unileon.happycow.handler.IdHandler;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author dorian
 */
public class ValorationDB implements Serializable{
    private IdHandler idValoration;
    private IdHandler idEvaluation;
    private IdHandler idCriterion;
    private float nota;
    private float weighing;

    public ValorationDB(IdHandler idValoration, IdHandler idEvaluation, IdHandler idCriterion, float nota, float weighing) {
        this.idValoration = idValoration;
        this.idEvaluation = idEvaluation;
        this.idCriterion = idCriterion;
        this.nota = nota;
        this.weighing = weighing;
    }

    public ValorationDB(IdHandler idValoration, IdHandler idEvaluation, IdHandler idCriterion, float nota) {
        this(idValoration,idEvaluation,idCriterion,nota,1);
    }
    
    

    public IdHandler getIdValoration() {
        return idValoration;
    }

    public IdHandler getIdEvaluation() {
        return idEvaluation;
    }

    public IdHandler getIdCriterion() {
        return idCriterion;
    }

    public float getNota() {
        return nota;
    }

    public float getWeighing() {
        return weighing;
    }

    public void setIdValoration(IdHandler idValoration) {
        this.idValoration = idValoration;
    }

    public void setIdEvaluation(IdHandler idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public void setIdCriterion(IdHandler idCriterion) {
        this.idCriterion = idCriterion;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setWeighing(float weighing) {
        this.weighing = weighing;
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        idValoration=(IdHandler)stream.readObject();
        idEvaluation=(IdHandler)stream.readObject();
        idCriterion=(IdHandler)stream.readObject();
        nota=stream.readFloat();
        weighing=stream.readFloat();
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(idValoration);
        stream.writeObject(idEvaluation);
        stream.writeObject(idCriterion);
        stream.writeFloat(nota);
        stream.writeFloat(weighing);
    }
}
