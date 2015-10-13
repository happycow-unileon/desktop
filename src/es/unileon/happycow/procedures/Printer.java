package es.unileon.happycow.procedures;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;
import javax.swing.text.*;
import javax.imageio.*;
import javax.imageio.stream.*;

/**
 * Printer class
 * Cloned from example
 * @author dorian
 */
public class Printer {

    public static int DEFAULT_DPI = 72;
    public static float DEFAULT_PAGE_WIDTH_INCH = 8.5f;
    public static float DEFAULT_PAGE_HEIGHT_INCH = 11f;

    int x = 100;
    int y = 80;

    GraphicsConfiguration gc;
    PrintService[] services;
    PrintService defaultService;
    DocFlavor flavor;
    PrintRequestAttributeSet attributes;
    Vector pjlListeners = new Vector();
    Vector pjalListeners = new Vector();
    Vector psalListeners = new Vector();

    public Printer() {
        gc = null;
        attributes = new HashPrintRequestAttributeSet();
        flavor = null;
        defaultService = PrintServiceLookup.lookupDefaultPrintService();
        services = PrintServiceLookup.lookupPrintServices(flavor, attributes);

        // do something with the supported docflavors
        if (defaultService != null) {
            DocFlavor[] df = defaultService.getSupportedDocFlavors();
            for (int i = 0; i < df.length; i++) {
                System.out.println(df[i].getMimeType() + " " + df[i].getRepresentationClassName());
            }
        }

        // if there is a default service, but no other services
        if (defaultService != null && (services == null || services.length == 0)) {
            services = new PrintService[1];
            services[0] = defaultService;
        }

    }
    
    public boolean hasServices(){
        return defaultService!=null && services!=null;
    }

    /**
     * Set the GraphicsConfiguration to display the print dialog on.
     *
     * @param gc a GraphicsConfiguration object
     */
    public void setGraphicsConfiguration(GraphicsConfiguration gc) {
        this.gc = gc;
    }

    public void setServices(PrintService[] services) {
        this.services = services;
    }

    public void setDefaultService(PrintService service) {
        this.defaultService = service;
    }

    public void setDocFlavor(DocFlavor flavor) {
        this.flavor = flavor;
    }

    public void setPrintRequestAttributes(PrintRequestAttributeSet attributes) {
        this.attributes = attributes;
    }

    public void setPrintDialogLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addPrintJobListener(PrintJobListener pjl) {
        pjlListeners.addElement(pjl);
    }

    public void removePrintJobListener(PrintJobListener pjl) {
        pjlListeners.removeElement(pjl);
    }

    public void addPrintServiceAttributeListener(PrintServiceAttributeListener psal) {
        psalListeners.addElement(psal);
    }

    public void removePrintServiceAttributeListener(PrintServiceAttributeListener psal) {
        psalListeners.removeElement(psal);
    }

    public boolean printJEditorPane(JEditorPane jep, PrintService ps) {
        if (ps == null || jep == null) {
            System.out.println("printJEditorPane: jep or ps is NULL, aborting...");
            return false;
        }

        // get the root view of the preview pane
        View rv = jep.getUI().getRootView(jep);

        // get the size of the view (hopefully the total size of the page to be printed
        int x = (int) rv.getPreferredSpan(View.X_AXIS);
        int y = (int) rv.getPreferredSpan(View.Y_AXIS);

        // find out if the print has been set to colour mode
        DocPrintJob dpj = ps.createPrintJob();
        PrintJobAttributeSet pjas = dpj.getAttributes();

        // get the DPI and printable area of the page. use default values if not available
        // use this to get the maximum number of pixels on the vertical axis
        PrinterResolution pr = (PrinterResolution) pjas.get(PrinterResolution.class);
        int dpi;
        float pageX, pageY;
        if (pr != null) {
            dpi = pr.getFeedResolution(PrinterResolution.DPI);
        } else {
            dpi = DEFAULT_DPI;
        }
        MediaPrintableArea mpa = (MediaPrintableArea) pjas.get(MediaPrintableArea.class);
        if (mpa != null) {
            pageX = mpa.getX(MediaPrintableArea.INCH);
            pageY = mpa.getX(MediaPrintableArea.INCH);
        } else {
            pageX = DEFAULT_PAGE_WIDTH_INCH;
            pageY = DEFAULT_PAGE_HEIGHT_INCH;
        }
        int pixelsPerPageY = (int) (dpi * pageY);
        int pixelsPerPageX = (int) (dpi * pageX);

        int minY = Math.max(pixelsPerPageY, y);

        // make colour true if the user has selected colour, and the PrintService can support colour
        boolean colour = pjas.containsValue(Chromaticity.COLOR);
        colour = colour & (ps.getAttribute(ColorSupported.class) == ColorSupported.SUPPORTED);

        // create a BufferedImage to draw on
        int imgMode;
        if (colour) {
            imgMode = BufferedImage.TYPE_3BYTE_BGR;
        } else {
            imgMode = BufferedImage.TYPE_BYTE_GRAY;
        }
        BufferedImage img = new BufferedImage(pixelsPerPageX, minY, imgMode);
        Graphics myGraphics = img.getGraphics();
        myGraphics.setClip(0, 0, pixelsPerPageX, minY);
        myGraphics.setColor(Color.WHITE);
        myGraphics.fillRect(0, 0, pixelsPerPageX, minY);

        // call rootView.paint( myGraphics, rect ) to paint the whole image on myGraphics
        rv.paint(myGraphics, new Rectangle(0, 0, pixelsPerPageX, minY));

        try {
            // write the image as a JPEG to the ByteArray so it can be printed 
            Iterator writers = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = (ImageWriter) writers.next();

            // mod: Added the iwparam to create the highest quality image possible
            ImageWriteParam iwparam = writer.getDefaultWriteParam();
            iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwparam.setCompressionQuality(1.0f); // highest quality

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);
            writer.setOutput(ios);

            // get the number of pages we need to print this image
            int imageHeight = img.getHeight();
            int numberOfPages = (int) Math.ceil(minY / (double) pixelsPerPageY);

            // print each page
            for (int i = 0; i < numberOfPages; i++) {
                int startY = i * pixelsPerPageY;

                // get a subimage which is exactly the size of one page
                BufferedImage subImg = img.getSubimage(0, startY, pixelsPerPageX, Math.min(y - startY, pixelsPerPageY));

                // mod: different .write() method to use the iwparam parameter with highest quality compression
                writer.write(null, new IIOImage(subImg, null, null), iwparam);

                SimpleDoc sd = new SimpleDoc(out.toByteArray(), DocFlavor.BYTE_ARRAY.JPEG, null);
                printDocument(sd, ps);

                // reset the ByteArray so we can start the next page
                out.reset();
            }
        } catch (PrintException e) {
            System.out.println("Error printing document.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Error creating ImageOutputStream or writing to it.");
            e.printStackTrace();
            return false;
        }

        // uncomment this code and comment out the 'try-catch' block above
        // to print to a JFrame instead of to the printer
          /*          JFrame jf = new JFrame();
         PaintableJPanel jp = new PaintableJPanel();
         jp.setImage( img );
         JScrollPane jsp = new JScrollPane( jp );
         jf.getContentPane().add( jsp );
         Insets i = jf.getInsets();
         jf.setBounds( 0, 0, newX, y );
         jf.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
         jf.setVisible( true );*/
        return true;
    }

    /**
     * Print the document to the specified PrintService. This method cannot tell
     * if the printing was successful. You must register a PrintJobListener
     *
     * @return false if no PrintService is selected in the dialog, true
     * otherwise
     */
    public boolean printDocument(Doc doc, PrintService ps) throws PrintException {
        if (ps == null) {
            return false;
        }
        addAllPrintServiceAttributeListeners(ps);
        DocPrintJob dpj = ps.createPrintJob();
        addAllPrintJobListeners(dpj);
        dpj.print(doc, attributes);
        return true;
    }

    public PrintService showPrintDialog() {
        return ServiceUI.printDialog(
                gc, x, y, services, defaultService, flavor, attributes);
    }

    private void addAllPrintServiceAttributeListeners(PrintService ps) {
        // add all listeners that are currently added to this object 
        for (int i = 0; i < psalListeners.size(); i++) {
            PrintServiceAttributeListener p = (PrintServiceAttributeListener) psalListeners.get(i);
            ps.addPrintServiceAttributeListener(p);
        }
    }

    private void addAllPrintJobListeners(DocPrintJob dpj) {
        // add all listeners that are currently added to this object 
        for (int i = 0; i < pjlListeners.size(); i++) {
            PrintJobListener p = (PrintJobListener) pjlListeners.get(i);
            dpj.addPrintJobListener(p);
        }
    }
}
