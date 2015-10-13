package es.unileon.happycow.handler;


public class IdGeneric implements IdHandler{
	private String id;

	public IdGeneric(String id) {
		this.id = id;
	}

	public IdGeneric(IdHandler another) {
		this(another.toString());
	}

	@Override
	public int compareTo(IdHandler another) {
		return this.id.compareTo(another.toString());
	}

	@Override
	public String toString() {
		return this.id;
	}

    @Override
    public String getValue() {
        return this.id;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj.toString().compareTo(this.toString())==0;
    }

    @Override
    public int hashCode() {
     return id.hashCode();
    }

}
