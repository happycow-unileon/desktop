package es.unileon.happycow.database.prototype;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for store the initial set of criterions if none exists on database
 *
 * @author dorian
 */
public class InsertCriterion {

    /**
     * List of sentences to store criterions in database
     */
    private final LinkedList<String> inserts;
    /**
     * Document with the data of xml
     */
    private Document xml;
    /**
     * The path of xml
     */
    private final String pathXml = "CriterionsBase.xml";

    /**
     * Constructor
     */
    public InsertCriterion() {
        this.inserts = new LinkedList<>();
    }

    /**
     * Start with the parser
     * @return 
     */
    private boolean run() {
        boolean result = true;
        if (getXml()) {
            getSentences();
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Get the list of sentences to store in database
     * @return 
     */
    public LinkedList execute() {
        if (run()) {
            return inserts;
        } else {
            return null;
        }
    }

    /**
     * Get the xml file
     *
     * @return
     */
    private File getXML() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File xmlCriterions = new File(System.getProperty("java.io.tmpdir")
                + System.getProperty("file.separator")
                + "criterionsHappyCow.xml");
        try {

            xmlCriterions.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        System.out.println(xmlCriterions.exists());
        System.out.println(xmlCriterions.getAbsolutePath());
        try {
            // read this file into InputStream
            inputStream = getClass().getResourceAsStream("/criterions/".concat(pathXml));

            // write the inputStream to a FileOutputStream
            outputStream
                    = new FileOutputStream(xmlCriterions);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return xmlCriterions;
        }
    }

    /**
     * Get the xml document
     * @return 
     */
    private boolean getXml() {
        boolean result = true;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            xml = docBuilder.parse(getXML());
        } catch (IOException ex) {
            System.out.println(ex.toString());
            result = false;
        } catch (ParserConfigurationException | SAXException ex) {
            result = false;
            System.out.println(ex.toString());
        }
        return result;
    }

    /**
     * Get the sentences from the xml
     */
    private void getSentences() {
        xml.getDocumentElement().normalize();
        System.out.println("Leyendo del nodo: " + xml.getDocumentElement().getNodeName());
        NodeList criterions = xml.getElementsByTagName("criterion");

        for (int i = 0; i < criterions.getLength(); i++) {
            StringBuilder aSentence = new StringBuilder();
            Node criterion = criterions.item(i);
            Element el = (Element) criterion;
            aSentence.append("INSERT INTO CRITERION (NOMBRECRITERIO,DESCRIPCION,HELP,CATEGORIA) VALUES ");
            aSentence.append("('");
            aSentence.append(getTagValue("name", el));
            aSentence.append("','");
            aSentence.append(getTagValue("description", el));
            aSentence.append("','");
            aSentence.append(getTagValue("help", el));
            aSentence.append("','");
            aSentence.append(getTagValue("category", el));
            aSentence.append("')");

            inserts.add(aSentence.toString());
        }

    }

    /**
     * Get the value of a given tag/element
     * @param tag
     * @param elemento
     * @return 
     */
    private String getTagValue(String tag, Element elemento) {
        NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
        Node valor = (Node) lista.item(0);
        return valor.getNodeValue();
    }

}
