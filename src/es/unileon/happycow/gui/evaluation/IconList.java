package es.unileon.happycow.gui.evaluation;

import javax.swing.ImageIcon;

/**
 *
 * @author antonio
 */
public class IconList {
    private final String nombreCriterio;
    private final String iconPath;
    private ImageIcon imagenCheck;
    
    public IconList(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
        this.iconPath = "/images/unchecked.png";
        this.imagenCheck = new ImageIcon(getClass().getResource(this.iconPath));
    }

    public ImageIcon getImagenCheck() {
        if (this.imagenCheck == null) {
            this.imagenCheck = new ImageIcon(this.iconPath);
        }
        return this.imagenCheck;
    }

    public String getNombreCriterio() {
        return this.nombreCriterio;
    }
    
    public void setUncheked(){
        this.imagenCheck = new ImageIcon(getClass().getResource("/images/unchecked.png"));
    }
    
    public void setChecked() {
        this.imagenCheck = new ImageIcon(getClass().getResource("/images/checked.png"));
    }
    
}
