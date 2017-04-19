package UI.Panel;

import UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jhinchley on 3/21/17.
 */
public class SliderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JSlider jSlider;

    SliderPanel(String title, int min, int max, int init, int tick) {
        super(true);
        this.setLayout(new BorderLayout());
        this.setBackground(UIConstants.BACKGROUND_COLOR);
//        this.setBorder(BorderFactory.createTitledBorder(title));

        JLabel jLabel = new JLabel(title);
        jSlider = new JSlider(min, max, init);

        //houseSlider.setMinorTickSpacing(0);
        jSlider.setMajorTickSpacing(tick);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.setBackground(UIConstants.BACKGROUND_COLOR);
        // We'll just use the standard numeric labels for now...
        //TODO it does not display
//        jSlider.setLabelTable(jSlider.createStandardLabels(9));

        add(jLabel, BorderLayout.WEST);
        add(jSlider, BorderLayout.CENTER);


    }

    int getValue() {
        return jSlider.getValue();
    }
}