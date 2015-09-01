/*
 * 
 */
package es.unileon.happycow.procedures;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.database.backup.FilesDB;
import es.unileon.happycow.database.backup.PonderationDB;
import es.unileon.happycow.database.backup.ValorationDB;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author amdiaz8
 */
public class Backup {

    private StringBuilder estado;

    public String rutaTemporalExportar = System.getProperty("java.io.tmpdir")
            .concat("/HappyCowExport");
    public String rutaTemporalImportar = System.getProperty("java.io.tmpdir")
            .concat("/HapppyCowImport");

    public Backup() {
        this.estado = new StringBuilder();
    }

    public String getEstado() {
        String result = estado.toString();
        estado = new StringBuilder();
        return result;
    }

    private void store(File destiny, List<?> list) throws FileNotFoundException, IOException {
        // Se abre el fichero donde se hará la copia
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destiny));
        for (Object object : list) {
            oos.writeObject(object);
        }
        oos.close();
    }

    public boolean backup(File carpeta) {
        boolean result = true;
        //Establezco la ruta temporal y creo la carpeta
        File temporal = new File(rutaTemporalExportar);
        temporal.mkdirs();

        try {
            File criterios = new File(temporal, "criterios");
            File usuarios = new File(temporal, "usuarios");
            File granjas = new File(temporal, "granjas");
            File evaluaciones = new File(temporal, "evaluaciones");
            File notas = new File(temporal, "notas");
            File ponderacionCriterio = new File(temporal, "ponCategorias");
            File ponderacionCategoria = new File(temporal, "ponCriterios");
            File files = new File(temporal, "files");

            // Se abre el fichero donde se hará la copia
            LinkedList<User> users = Database.getInstance().getListUsers();
            store(usuarios, users);

            LinkedList<Criterion> crit = Database.getInstance().getListCriterion();
            store(criterios, crit);

            LinkedList<Farm> farms = Database.getInstance().getAllFarms();
            store(granjas, farms);

            LinkedList<InformationEvaluation> evals = Database.getInstance()
                    .getAllInformationEvaluations();
            store(evaluaciones, evals);

            LinkedList<FilesDB> filesAdj = Database.getInstance().getAllFiles();
            store(files, filesAdj);

            LinkedList<ValorationDB> valorations = Database.getInstance().getAllValorations();
            store(notas, valorations);

            //quedan las ponderaciones
            LinkedList<PonderationDB> cat = Database.getInstance().getCategoryPonderation();
            store(ponderacionCategoria, cat);

            LinkedList<PonderationDB> ponderation = Database.getInstance().getCriterionPonderation();
            store(ponderacionCriterio, ponderation);

            exportar(carpeta.toString(), new Date().toString());

        } catch (Exception e) {
            result = false;
            estado.append("\nError exportando la base de datos:\n");
            estado.append(e.toString());
            estado.append("\n");
        }

        return result;
    }

    private void exportar(String carpeta, String fecha) throws Exception {

        //crea el comprimido
        File comprimido = new File(carpeta.concat("/backup-" + fecha + ".backup"));

        int contador = 1;
        //compruebo si no existe, si existe, se crea un nuevo nombre al comprimido
        //codigos (1).zip...
        boolean exist = true;
        while (exist) {
            if (comprimido.exists()) {
                comprimido = new File(carpeta.concat("/backup-" + fecha
                        .concat(Integer.toString(contador))
                        .concat(").backup")));
                contador++;
            } else {
                exist = false;
            }
        }
        //comprimo los ficheros exportados
        comprimir(comprimido);

        //borro todos los ficheros temporales
        borrarDirectorio(new File(rutaTemporalExportar));
    }

    /**
     * Comprime los ficheros exportados y los deja en la ruta especificada
     *
     * @param ruta donde se dejará el comprimido
     * @param llave la llave para la encriptación
     */
    private void comprimir(File comprimido) throws Exception {
        //lista los ficheros a comprimir
        File[] ficheros = new File(rutaTemporalExportar).listFiles();

        try {
            //creo los stream necesarios para crear el fichero comprimido
            FileOutputStream salidaFichero = new FileOutputStream(comprimido);
            ZipOutputStream salidaZip = new ZipOutputStream(salidaFichero);
            salidaZip.setLevel(9);

            //por cada fichero lo añado al comprimido
            for (int i = 0; i < ficheros.length; i++) {
                //cojo el fichero
                ZipEntry ze = new ZipEntry(ficheros[i].getName());
                FileInputStream fin = new FileInputStream(ficheros[i].toString());
                //lo paso por el encriptador
                try {
                    System.out.println("Compressing " + ficheros[i].toString());
                    //lo escribo en el comprimido
                    salidaZip.putNextEntry(ze);
                    for (int c = fin.read(); c != -1; c = fin.read()) {
                        salidaZip.write(c);
                    }
                } finally {
                    fin.close();
                }
            }
            //cierro el comprimido terminado
            salidaZip.close();
        } catch (IOException x) {
            throw new Exception("Error manejando el archivo: " + x.toString());
        }

    }

    /**
     * Se borra los ficheros temporales, método recursivo para borrar también
     * las posibles carpetas que haya dentro del directorio dado
     *
     * @param directorio el directorio a borrar con todo su contenido
     */
    private void borrarDirectorio(File directorio) {
        //cojo todos los ficheros/directorios que hay
        File[] ficheros = directorio.listFiles();

        //por cada fichero, si es directorio ejecuto de nuevo el método
        //para que elimine todo su interior y posteriormente lo elimino
        for (int x = 0; x < ficheros.length; x++) {
            if (ficheros[x].isDirectory()) {
                borrarDirectorio(ficheros[x]);
            }
            ficheros[x].delete();
        }

        //borro el propio directorio una vez terminado con su contenido
        directorio.delete();
    }

    /**
     * Realiza los pasos necesarios para importar el código
     *
     * @param comprimido el fichero comprimido
     * @param contrasena la contraseña del fichero
     */
    private boolean importar(File comprimido) {

        //establezco la ruta temporal y creo las carpetas
        File temporal = new File(rutaTemporalImportar);
        temporal.mkdirs();

        boolean correcto = false;
        try {
            //creo los stream necesarios
            FileInputStream fis = new FileInputStream(comprimido);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry entry;

            int buffer = 2048;
            byte data[] = new byte[buffer];
            //por cada fichero que está en el comprimido
            while ((entry = zis.getNextEntry()) != null) {
                correcto = true;
                System.out.println("Extracting: " + entry);
                //escribo el fichero pasandolo por el desencriptador
                FileOutputStream fos = new FileOutputStream(rutaTemporalImportar
                        .concat("/").concat(entry.getName()));

                int count;

                while ((count = zis.read(data, 0, buffer)) != -1) {
                    fos.write(data, 0, count);
                }
                fos.close();
            }
            zis.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            return correcto;
        }
    }

    private LinkedList<Object> read(File source) throws FileNotFoundException, IOException, ClassNotFoundException {
        LinkedList<Object> list = new LinkedList<>();
        // Se abre el fichero donde se leerá la copia
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(source));
        try {
            Object ob = oos.readObject();

            while (ob != null) {
                list.add(ob);
                ob = oos.readObject();
            }
        } catch (EOFException ex) {
            oos.close();
        }
        return list;
    }

    public boolean recuperarBackup(File fichero) {
        boolean result = true;
        if (fichero.exists()) {
            importar(fichero);
            try {

                Database.getInstance().startTransaccion();

                if (Database.getInstance().clearDB()) {
                    File temporal = new File(rutaTemporalImportar);
                    File criterios = new File(temporal, "criterios");
                    File usuarios = new File(temporal, "usuarios");
                    File granjas = new File(temporal, "granjas");
                    File evaluaciones = new File(temporal, "evaluaciones");
                    File notas = new File(temporal, "notas");
                    File ponderacionCriterio = new File(temporal, "ponCategorias");
                    File ponderacionCategoria = new File(temporal, "ponCriterios");
                    File files = new File(temporal, "files");
                    LinkedList<Object> list;

                    list = read(usuarios);
                    for (Object object : list) {
                        User user = (User) object;
                        result = result & Database.getInstance().newUser(user);
                    }

                    list = read(granjas);
                    for (Object object : list) {
                        Farm farm = (Farm) object;
                        result = result & Database.getInstance().newFarm(farm);
                    }

                    list = read(criterios);
                    for (Object object : list) {
                        Criterion criterion = (Criterion) object;
                        result = result & Database.getInstance().newCriterionBackup(criterion);
                    }

                    list = read(evaluaciones);
                    for (Object object : list) {
                        InformationEvaluation info = (InformationEvaluation) object;
                        result = result & Database.getInstance().saveInformationEvaluation(info);
                    }

                    list = read(notas);
                    for (Object object : list) {
                        ValorationDB valoration = (ValorationDB) object;
                        result = result & Database.getInstance().
                                saveValoration(valoration);
                    }

                    list = read(ponderacionCategoria);
                    LinkedList<PonderationDB> listC = new LinkedList<>();
                    for (Object object : list) {
                        PonderationDB pon = (PonderationDB) object;
                        listC.add(pon);
                    }
                    result = result & Database.getInstance().saveCategoryPonderation(listC);

                    list = read(ponderacionCriterio);
                    listC = new LinkedList<>();
                    for (Object object : list) {
                        PonderationDB pon = (PonderationDB) object;
                        listC.add(pon);
                    }
                    result = result & Database.getInstance().saveCriterionPonderation(listC);

                    list = read(files);
                    LinkedList<FilesDB> listFiles = new LinkedList<>();
                    for (Object object : list) {
                        FilesDB pon = (FilesDB) object;
                        listFiles.add(pon);
                    }
                    result = result & Database.getInstance().saveFiles(listFiles);

                }
            } catch (IOException | ClassNotFoundException e) {
                result = false;
                estado.append("\nError importando la base de datos:\n");
                estado.append(e.toString());
                estado.append("\n");
            } catch (SQLException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            }

            finImportacion();
        } else {
            result = false;
            estado.append("\nEl fichero a importar no existe o no es correcto\n");
        }

        if (result) {
            Database.getInstance().commit();
            System.out.println("Exito");
        } else {
            Database.getInstance().rollback();
            System.out.println("Fracaso");
        }
        return result;
    }

    /**
     * Cuando se termina con la importación, borro los ficheros temporales
     */
    private void finImportacion() {
        borrarDirectorio(new File(rutaTemporalImportar));
    }
}
