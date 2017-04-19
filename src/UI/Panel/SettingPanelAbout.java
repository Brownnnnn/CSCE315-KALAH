package UI.Panel;

import UI.UIConstants;
import tools.ReadFile;

import javax.swing.*;
import java.awt.*;

/**
 * Setting - About Panel
 *
 * @author XiaoyuLi
 */

public class SettingPanelAbout extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public SettingPanelAbout() {
        initialize();
    }

    /**
     * Initialization
     */
    private void initialize() {
        this.setBackground(UIConstants.BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getBottomPanel(), BorderLayout.SOUTH);
    }

    /**
     * Center panel contains programmer informations
     */
    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        centerPanel.setLayout(new GridLayout(3, 1));

        //appPanel contains application name and version.
        JPanel appPanel = new JPanel();
        //infoPanel contains developer information
        JPanel infoPanel = new JPanel();

        appPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        appPanel.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 0));
        infoPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 0));

        JLabel appNameLabel = new JLabel(UIConstants.APP_NAME);
        JLabel appVersionLabel = new JLabel(UIConstants.APP_VERSION);
        JLabel appAboutLabel = new JLabel(ReadFile.scan("assets/about.txt"));

        appNameLabel.setFont(UIConstants.FONT_NORMAL);
        appVersionLabel.setFont(UIConstants.FONT_NORMAL);
        appAboutLabel.setFont(UIConstants.FONT_NORMAL);

        Dimension size = new Dimension(200, 30);
        appNameLabel.setPreferredSize(size);
        appVersionLabel.setPreferredSize(size);

        //Add components to each individual panel
        appPanel.add(appNameLabel);
        appPanel.add(appVersionLabel);
        infoPanel.add(appAboutLabel);

        //Add components to main panel
        centerPanel.add(appPanel);
        centerPanel.add(infoPanel);

        return centerPanel;
    }

    /**
     * Bottom panel contains school informations
     */
    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 15));

        JLabel infoLabel = new JLabel(UIConstants.SCHOOL_NAME);
        infoLabel.setFont(UIConstants.FONT_NORMAL);
        infoLabel.setForeground(Color.gray);

        //Add components to main panel
        bottomPanel.add(infoLabel);

        return bottomPanel;
    }

}