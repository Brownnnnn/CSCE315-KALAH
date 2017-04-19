package tools;

import javax.swing.*;
import java.awt.*;

public class GridBag {
    public static void add(JPanel panel, JComponent comp,
                           int xPos, int yPos, int compWidth, int compHeight, int xPadding, int yPadding,
                           int wInset, int hInset, int place, int stretch) {
        GridBagConstraints gcs = new GridBagConstraints();

        gcs.gridx = xPos;
        gcs.gridy = yPos;
        gcs.gridwidth = compWidth;
        gcs.gridheight = compHeight;
        gcs.weightx = 50;
        gcs.weighty = 50;
        gcs.insets = new Insets(yPadding, xPadding, yPadding, xPadding);
        gcs.anchor = place;
        gcs.fill = stretch;
        gcs.insets = new Insets(hInset, wInset, hInset, wInset);
        panel.add(comp, gcs);
    }
}
