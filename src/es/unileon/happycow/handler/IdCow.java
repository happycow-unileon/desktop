package es.unileon.happycow.handler;

import java.io.Serializable;

/**
 *
 * @author dorian
 */
public class IdCow implements IdHandler, Serializable {

    private int id;

    public IdCow(int id) {
        this.id = id;
    }

    public IdCow(String id) {
        if (id.contains("Cow")) {
            String[] result = id.split("Cow-", 2);
            this.id = Integer.parseInt(result[1]);
        } else {
            this.id = Integer.parseInt(id);
        }
    }

    public IdCow(IdHandler id) {
        if (id.toString().contains("Cow-")) {
            IdCow cow = (IdCow) id;
            this.id = cow.getId();
        } else {
            throw new IllegalArgumentException("No es un identificador de vaca");
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cow-" + String.valueOf(id);
    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return String.valueOf(id);
    }

}
