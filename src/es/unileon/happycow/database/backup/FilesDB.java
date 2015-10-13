package es.unileon.happycow.database.backup;

import java.io.IOException;
import java.io.Serializable;

/**
 * A map class to store and retrieve a file in database
 *
 * @author dorian
 */
public class FilesDB implements Serializable {

    /**
     * Id evaluation
     */
    private int id;
    /**
     * file name
     */
    private String filename;
    /**
     * data file
     */
    private byte[] file;

    /**
     * Constructor
     * @param id of the evaluation
     * @param filename name of file
     * @param file data file
     */
    public FilesDB(int id, String filename, byte[] file) {
        this.id = id;
        this.filename = filename;
        this.file = file;
    }

    /**
     * Get the id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name
     * @return 
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the name
     * @param filename 
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Get data file
     * @return 
     */
    public byte[] getFile() {
        return file;
    }

    /***
     * set data file
     * @param file 
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Read the object from a stream
     * @param stream
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        id = stream.readInt();
        filename = (String) stream.readObject();
        file = (byte[]) stream.readObject();

    }

    /**
     * Write the object to a stream
     * @param stream
     * @throws IOException 
     */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(id);
        stream.writeObject(filename);
        stream.writeObject(file);
    }

}
