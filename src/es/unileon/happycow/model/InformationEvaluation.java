package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdHandler;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author dorian
 */
public class InformationEvaluation  implements Serializable {

    private IdHandler idEvaluation;
    private IdHandler idFarm;
    private IdHandler idUser;
    private float nota;
    private float alimentacion;
    private float salud;
    private float comfort;
    private float comportamiento;
    private Date fecha;
    private int numberCows;

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

    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user,
            float alimentacion, float salud, float comfort, float comportamiento,
            Date fecha, int number) {
        this(idEvaluation, idFarm, user,
                (alimentacion + salud + comfort + comportamiento) / 4,
                alimentacion, salud, comfort, comportamiento, fecha, number);
    }

    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user,
            Date fecha, int number) {
        this(idEvaluation, idFarm, user, 0, 0, 0, 0, fecha, number);
    }

    public InformationEvaluation(IdHandler idEvaluation, IdHandler idFarm, IdHandler user, int number) {
        this(idEvaluation, idFarm, user, 0, 0, 0, 0, new Date(new java.util.Date().getTime()), number);
    }

    public int getNumberCows() {
        return numberCows;
    }

    public IdHandler getIdEvaluation() {
        return idEvaluation;
    }

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
