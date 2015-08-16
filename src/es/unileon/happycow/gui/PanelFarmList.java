package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ButtonListFarmIController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

/**
 *
 * @author dorian
 */
public final class PanelFarmList extends javax.swing.JPanel {

    private IdHandler idFarm;
    private ButtonListFarmIController controller;

    /**
     * Creates new form PanelFarm
     *
     * @param farm
     * @param controller
     */
    public PanelFarmList(Farm farm, ButtonListFarmIController controller) {
        this(farm, controller, "");
    }

    public PanelFarmList(Farm farm, ButtonListFarmIController controller, String tooltip) {
        initComponents();
        this.controller = controller;
        if (farm != null) {
            idFarm = farm.getIdFarm();
            StringBuilder result = new StringBuilder();
            result.append("<html>");
            result.append(farm.getInformation());
            result.append("</html>");
            setText(result.toString());
            setFarmName(farm.getFarmName());
        }
        setTooltip(tooltip);
    }

    public void setFarm(Farm farm) {
        if (farm != null) {
            idFarm = farm.getIdFarm();
            StringBuilder result = new StringBuilder();
            result.append("<html>");
            result.append(farm.getInformation());
            result.append("</html>");
            setText(result.toString());
            setFarmName(farm.getFarmName());
        }
    }

    public void setTooltip(String tooltip) {
        buttonFarm.setToolTipText(tooltip);
    }

    public IdHandler getId() {
        return idFarm;
    }

    public void setText(String text) {
        labelText.setText(text);
    }

    public void setFarmName(String name) {
        buttonFarm.setText(name);
    }

    public void setController(ButtonListFarmIController controller) {
        this.controller = controller;
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        buttonFarm = new javax.swing.JButton();
        labelText = new javax.swing.JLabel();

        Image img = new javax.swing.ImageIcon(
                getClass().getResource("/images/edit.png")).getImage();
        Image resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonEditFarm = new javax.swing.JButton(new ImageIcon(resized));
    }

    private void configureComponents() {
        labelText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buttonEditFarm.setBorderPainted(false);
        buttonEditFarm.setContentAreaFilled(false);
        buttonEditFarm.setFocusPainted(false);
        buttonEditFarm.setToolTipText("Modificar granja");

        decorator = new Decorator(buttonFarm,
                Anchor.WEST, Decorator.Direction.HORIZONTAL);
        decorator.setMargin(new Insets(0, 50, 0, 0));

        decorator.addDecoration(buttonEditFarm);
    }

    private void addEvents() {
        buttonFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.execute(idFarm);
                }
            }
        });

        buttonEditFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.edit(idFarm);
                }
            }
        });
    }

    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        add(decorator, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelText, gridBagConstraints);

    }

    private javax.swing.JButton buttonFarm;
    private javax.swing.JLabel labelText;

    private javax.swing.JButton buttonEditFarm;
    private Decorator decorator;
}
