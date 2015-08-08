package es.unileon.happycow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


/**
 * Tres formas de poner en marcha el splash screen
 * Por terminal: en la carpeta classes (en build):
 * java -splash:images/logo.png es.unileon.happycow.gui.Splash
 * 
 * Por jar: en la carpeta del proyecto hay un manifest. Incluir la línea:
 * SplashScreen-Image: images/logo.png
 * Contruir o arrancar el programa a través de netbeans (para que cree el jar).
 * Ejecutar el jar manualmente (por consola o doble click)
 * 
 * En propiedades del proyecto, Run -> configuración por default, que viene para
 * ejecutar el splashscreen y un parámetro que es -splash:./resources/images/logo.png
 * 
 * Para el resto de ocasiones, olvidarse del splash screen y arrancar directamente
 * desde la ventana de login.
 *
 * @author dorian
 */
public class Splash{
    //el rectangulo del texto del splashscreen
    private static Rectangle2D splashTextArea;
    //el splashscreen
    private static SplashScreen mySplash;
    //la barra de progreso
    private static RoundRectangle2D splashProgressArea;
    //los graficos que manejan el splashscreen
    private static Graphics2D splashGraphics;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //inicializo el splashScreen
        splashInit();
        
        //invoco el run principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /**
                 * Poner aquí el trabajo a realizar, no abrir ninguna ventana
                 * hasta que se haya terminado de cargar y se termina con el splashscreen
                 * Aquí le ponemos lo que tengamos en Init
                 */
//                actualizarProgreso("Idiomas...", 10);
//                actualizarProgreso("Preparando la base de datos....", 35);
//                //abro la base de datos
//                DataBaseOperations db=Database.getInstance();
//                db.openDB();
//                actualizarProgreso("Preparando ventanas...", 65);
//                JFrameApplication.getInstance();
//                actualizarProgreso("Finalizando...", 100);
//                dormir();
//                
//                //inicios
//                JFrameApplication.start();
//                JFrameController.getInstance().login();
//                JFrameApplication.getInstance().setVisible(true);
//                JFrameApplication.getInstance().pack();
            }
        });
    }
    
    /**
     * Para testeo
     */
    private static void dormir(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException ex){}
    }
    
    /**
     * Inicializo el splashScreen
     */
    private static void splashInit(){
        //recojo la instancia
        mySplash = SplashScreen.getSplashScreen();
        //si efectivamente tenemos splashScreen...
        if (mySplash != null){
            //recojo sus dimensiones
            Dimension ssDim = mySplash.getSize();
            int height = ssDim.height;
            int width = ssDim.width;
            
            // creo la barra de progreso
            splashProgressArea = new RoundRectangle2D.Double(
                    width * .05, height*.92, width*.4, 
                    12, 15, 15);
            //creo el área del texto
            splashTextArea = new Rectangle2D.Double(
                    (splashProgressArea.getX()), 
                    (splashProgressArea.getY() - 35), width * .4, 32.);

            // creo los gráficos para el SplashScreen y establezco el texto
            splashGraphics = mySplash.createGraphics();
            
            Font font = new Font("Dialog", Font.BOLD, 14);
            splashGraphics.setFont(font);
            
            // inicializo la barra de progreso...
            actualizarProgreso("Empezando...", 0);
        }else{
            System.out.println("Fallo al mostrar...");
        }
    }
    
    /**
     * Muestra el texto del splashscreen
     * @param texto
     */
    public static void splashText(String texto){
        //siempre que tengamos visible el splashscreen...
        if (mySplash != null && mySplash.isVisible()){
            // booramos el texto anterior repintando la zona
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.fill(splashTextArea);

            // dibujamos el texto estableciendo su colocacion
            splashGraphics.setPaint(Color.WHITE);
            splashGraphics.drawString(texto, (int)(splashProgressArea.getX() + 15),
                    (int)(splashProgressArea.getY() - 10));

            // actualizamos gráficos para que se muestre los cambios
            mySplash.update();
        }
    }
    
    /**
     * mostramos una barra de progreso
     * @param pct porcentaje de la barra 0-100
     */
    public static void splashProgress(int pct){
        //siempre que tengamos visible el splashscreen...
        if (mySplash != null && mySplash.isVisible()){

            // borramos el progreso anterior pintandolo de nuevo
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashProgressArea);

            // dibujamos la linea externa
            splashGraphics.setPaint(Color.RED);
            splashGraphics.draw(splashProgressArea);

            // calculamos el ancho del progreso actual
            int x = (int) splashProgressArea.getMinX();
            int y = (int) splashProgressArea.getMinY();
            int wid = (int) splashProgressArea.getWidth();
            int hgt = (int) splashProgressArea.getHeight();

            int doneWidth = Math.round(pct*wid/100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid-1));  // limit 0-width

            // pintamos el progreso
            splashGraphics.setPaint(Color.GREEN);
            splashGraphics.fillRoundRect(x, y+1, doneWidth, hgt-1, 15, 15);

            // actualizamos para que se vean los cambios
            mySplash.update();
        }
    }
    
    /**
     * 
     * @param texto texto a mostrar
     * @param porcentaje  de la barra de progreso
     */
    private static void actualizarProgreso(String texto, int porcentaje){
        splashText(texto); //mostramos texto
        splashProgress(porcentaje); //mostramos porcentaje
    }
    
}
