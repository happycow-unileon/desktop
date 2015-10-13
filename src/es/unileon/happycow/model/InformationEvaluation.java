package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdHandler;
import java.io.Serializable;
import java.sql.Date;

/**
 * Represent the information of an evaluation
 * @author dorian
 */
public class InformationEvaluation  implements Serializable {
/**
 * Id of evaluation
 */
    private IdHandler idEvaluation;
    /**
     * id of farm
     */
    private IdHandler idFarm;
    /**
     * id of user
     */
    private IdHandler idUser;
    /**
     * note
     */
    private float nota;
    /**
     * food
     */
    private float alimentacion;
    /**
     * health
     */
    private float salud;
    /**
     * comfort
     */
    private float comfort;
    /**
     * behaviour
     */
    private float comportamiento;
    /**
     * date
     */
    private Date fecha;
    /**
     * number of cows
     */
    private int numberCows;

    /**
     * 
     * @param idEvaluation
     * @param idFarm
     * @param user
     * @param nota
     * @param alimentacion
     * @param salud
     * @param comfort
     * @param comportamiento
     * @param fecha
     * @param number 
     */
    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user,
            float nota, float alimentacion, float salud, float comfort,
            float comportamiento, Date fecha, int number) {
        this.idEvaluation = idEvaluation;
        this.nota = nota;
        this.idUser=user;
        this.alimentacion = alimentacion;
        this.salud = salud;
        this.comfort = comfort;
        this.comportamiento = comportamiento;
        this.fecha = fecha;
        this.idFarm = idFarm;
        this.numberCows = number;
    }

    /**
     * 
     * @param idEvaluation
     * @param idFarm
     * @param user
     * @param alimentacion
     * @param salud
     * @param comfort
     * @param comportamiento
     * @param fecha
     * @param number 
     */
    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user,
            float alimentacion, float salud, float comfort, float comportamiento,
            Date fecha, int number) {
        this(idEvaluation, idFarm, user,
                (alimentacion + salud + comfort + comportamiento) / 4,
                alimentacion, salud, comfort, comportamiento, fecha, number);
    }

    /**
     * 
     * @param idEvaluation
     * @param idFarm
     * @param user
     * @param fecha
     * @param number 
     */
    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user,
            Date fecha, int number) {
        this(idEvaluation, idFarm, user, 0, 0, 0, 0, fecha, number);
    }

    /**
     * 
     * @param idEvaluation
     * @param idFarm
     * @param user
     * @param number 
     */
    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user, int number) {
        this(idEvaluation, idFarm, user, 0, 0, 0, 0, new Date(new java.util.Date().getTime()), number);
    }

    /**
     * Get number of cows during this evaluation
     * @return 
     */
    public int getNumberCows() {
        return numberCows;
    }

    /**
     * Get the id of evaluation
     * @return 
     */
    public IdHandler getIdEvaluation() {
        return idEvaluation;
    }

    /***
     * Get the associated farm's id
     * @return 
     */
    public IdHandler getIdFarm() {
        return idFarm;
    }

    public float getNota() {
        return nota;
    }

    public IdHandler getIdUser() {
        return idUser;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public float getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(float alimentacion) {
        this.alimentacion = alimentacion;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public float getComfort() {
        return comfort;
    }

    public void setComfort(float comfort) {
        this.comfort = comfort;
    }

    public float getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(float comportamiento) {
        this.comportamiento = comportamiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InformationEvaluation) {
            InformationEvaluation other = (InformationEvaluation) obj;
            return other.getIdEvaluation().compareTo(idEvaluation) == 0;
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return idEvaluation.getValue().hashCode();
    }
}
