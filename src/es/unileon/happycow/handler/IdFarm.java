package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdFarm implements IdHandler {

    private int idFarm;

    public IdFarm(int idFarm) {
        this.idFarm = idFarm;
    }

    public IdFarm(String idFarm) {
        if (idFarm.contains("Farm-")) {
            String[] result = idFarm.split("Farm-", 2);
            this.idFarm = Integer.parseInt(result[1]);
        } else {
            this.idFarm = Integer.parseInt(idFarm);
        }
    }

    public IdFarm(IdHandler idFarm) {
        if (idFarm.toString().contains("Farm-")) {
            IdFarm usuario = (IdFarm) idFarm;
            this.idFarm = usuario.getIdFarm();
        } else {
            throw new IllegalArgumentException("El identificador no es de tipo granja");
        }
    }

    public int getIdFarm() {
        return idFarm;
    }

    @Override
    public String toString() {
        return "Farm-" + String.valueOf(idFarm);

    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return String.valueOf(idFarm);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().compareTo(this.toString()) == 0;
    }

    @Override
    public int hashCode() {
        return idFarm;
    }

}
