package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdUser implements IdHandler {

    private final String user;

    public IdUser(String user) {
        if (user.contains("User-")) {
            String[] result = user.split("User-", 2);
            this.user = result[1];
        } else {
            this.user = user;
        }
    }

    public IdUser(IdHandler id) {
        if (id.toString().contains("User-")) {
            IdUser usuario = (IdUser) id;
            this.user = usuario.getUser();
        } else {
            throw new IllegalArgumentException("No es un identificador de usuario");
        }
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "User-" + user;
    }

    @Override
    public String getValue() {
        return user;
    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }
}
