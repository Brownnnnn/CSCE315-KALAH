package UI.Panel;

import UI.UIConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * HelpPanelTip contains tips and tricks about KALAH game.
 *
 * @author XiaoyuLi
 */
public class HelpPanelTips extends JPanel {

    private static final long serialVersionUID = 1L;

    private BufferedImage background;

    public HelpPanelTips() {
        // TODO Auto-generated constructor stub
        try {
            background = ImageIO.read(UIConstants.TIPS_BACKGROUND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }

}
