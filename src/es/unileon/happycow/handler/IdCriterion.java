package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdCriterion implements IdHandler {

    private String name;

    public IdCriterion(String name) {
        if (name.contains("Criterion-")) {
            String[] result = name.split("Criterion-", 2);
            this.name = result[1];
        }else{
            this.name=name;
        }
    }

    public IdCriterion(IdHandler id) {
        if (id.toString().contains("Criterion-")) {
            IdCriterion cri = (IdCriterion) id;
            this.name = cri.getName();
        } else {
            throw new IllegalArgumentException("No es un identificador de criterio");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Criterion-" + name;
    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return name;
    }
}
