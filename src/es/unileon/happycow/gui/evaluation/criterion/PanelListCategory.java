/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.Category;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author dorian
 */
public class PanelListCategory extends JPanel {

    private IEvaluationCriterionController controller;

    private JToggleButton[] buttons;
    private JLabel titleCategory;

    public PanelListCategory() {
        initComponents();
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }

    public void setCategory(Category category) {
        for (JToggleButton button : buttons) {
            if (button.isSelected()) {
                button.setSelected(false);
            }
        }
        int ordinal = category.ordinal();
        buttons[ordinal].setSelected(true);
        titleCategory.setText(Category.getName(category));
    }

    public Category getCategory() {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                return Category.getEnum(i);
            }
        }
        return Category.FOOD;
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        titleCategory = new JLabel();

        buttons = new JToggleButton[4];
        buttons[Category.FOOD.ordinal()] = new JToggleButton(Category.getName(Category.FOOD));
        buttons[Category.HEALTH.ordinal()] = new JToggleButton(Category.getName(Category.HEALTH));
        buttons[Category.HOUSE.ordinal()] = new JToggleButton(Category.getName(Category.HOUSE));
        buttons[Category.BEHAVIOUR.ordinal()] = new JToggleButton(Category.getName(Category.BEHAVIOUR));
    }

    private void configureComponents() {
        Font font = titleCategory.getFont().deriveFont(25.0f);
        titleCategory.setFont(font);
    }

    private void addEvents() {
        buttons[Category.FOOD.ordinal()].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.categorySelected(Category.FOOD);
            }
        });
        buttons[Category.HEALTH.ordinal()].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.categorySelected(Category.HEALTH);
            }
        });
        buttons[Category.HOUSE.ordinal()].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.categorySelected(Category.HOUSE);
            }
        });
        buttons[Category.BEHAVIOUR.ordinal()].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.categorySelected(Category.BEHAVIOUR);
            }
        });
    }

    private void addLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for (JToggleButton button : buttons) {
            add(button);
        }

        add(Box.createHorizontalGlue());
        add(titleCategory);

    }
}
