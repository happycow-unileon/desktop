package es.unileon.happycow.database.backup;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author dorian
 */
public class FilesDB implements Serializable {

    //identificador de la evaluación

    private int id;

    private String filename;

    private byte[] file;

    public FilesDB(int id, String filename, byte[] file) {
        this.id = id;
        this.filename = filename;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        id=stream.readInt();
        filename=(String)stream.readObject();
        file=(byte[])stream.readObject();
        
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(id);
        stream.writeObject(filename);
        stream.writeObject(file);
    }

}
