package es.unileon.happycow.gui.evaluation.criterion;

import javax.swing.ImageIcon;

/**
 *
 * @author antonio
 */
public class IconList {
    private final String nombreCriterio;
    private ImageIcon imagenCheck;
    private ImageIcon imagenNoCheck;
    private boolean checked;
    
    public IconList(String nombreCriterio) {
        checked=false;
        this.nombreCriterio = nombreCriterio;
        this.imagenCheck = new ImageIcon(getClass().getResource("/images/checked.png"));
        this.imagenNoCheck=new ImageIcon(getClass().getResource("/images/unchecked.png"));
    }

    public ImageIcon getImagenCheck() {
        if(checked){
//            System.out.println("ckequeado");
            return imagenCheck;
        }else{
//            System.out.println("NOOOOOO");
            return imagenNoCheck;
        }
        //return (checked)? imagenCheck:imagenNoCheck;
    }

    public String getNombreCriterio() {
        return this.nombreCriterio;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
    
}
