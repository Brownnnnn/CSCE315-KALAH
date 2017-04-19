package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Custom image button, allow to change an image on click
 *
 * @author Xiaoyu Li
 */
public class IconButton extends JButton {

    private static final long serialVersionUID = 1L;

    private ImageIcon enabledIcon;
    private ImageIcon disabledIcon;
    private String toolTip;


    public IconButton(ImageIcon normalIcon, ImageIcon enabledIcon, ImageIcon disabledIcon, String toolTip) {
        super(normalIcon);

        this.enabledIcon = enabledIcon;
        this.disabledIcon = disabledIcon;
        this.toolTip = toolTip;

        initialize();
        setUp();
    }

    private void setUp() {
        //Hover effect
//		this.setRolloverIcon(normalIcon);
        //Enable effect
        this.setPressedIcon(enabledIcon);
        //Disable effect
        this.setDisabledIcon(disabledIcon);
        //Tool tip
        if (!toolTip.equals("")) {
            this.setToolTipText(toolTip);
        }
    }

    private void initialize() {
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));
    }
}
