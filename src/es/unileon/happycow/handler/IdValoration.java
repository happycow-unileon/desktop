package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdValoration implements IdHandler{

    private final int id;

    public IdValoration(int id) {
        this.id = id;
    }

    public IdValoration(String id) {
        if(id.contains("Valoration-")){
        String[] result=id.split("Valoration-",2);
        this.id = Integer.parseInt(result[1]);
        }else{
            this.id=Integer.parseInt(id);
        }
    }

    public IdValoration(IdHandler id) {
        if(id.toString().contains("Valoration-")){
            IdValoration valoration = (IdValoration) id;
            this.id = valoration.getId();
        }else{
            throw new IllegalArgumentException("No es un identificador de valoración");
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Valoration-"+String.valueOf(id);
    }

    @Override
    public int compareTo(IdHandler another) {
            return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return String.valueOf(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj.toString().compareTo(this.toString())==0;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
