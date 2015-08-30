package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdEvaluation implements IdHandler {

    private final int identifier;

    public IdEvaluation(int identifier) {
        this.identifier = identifier;
    }

    public IdEvaluation(String id) {
        if (id.contains("Evaluation-")) {
            String[] result = id.split("Evaluation-", 2);
            this.identifier = Integer.parseInt(result[1]);
        } else {
            this.identifier = Integer.parseInt(id);
        }
    }

    public IdEvaluation(IdHandler id) {
        if (id.toString().contains("Evaluation-")) {
            IdEvaluation eval = (IdEvaluation) id;
            this.identifier = eval.getIdentifier();
        } else {
            throw new IllegalArgumentException("No es un identificador de evaluación");
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "Evaluation-" + String.valueOf(identifier);
    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return String.valueOf(identifier);
    }
}
