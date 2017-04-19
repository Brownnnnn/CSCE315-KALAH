package UI.Panel;

import Controller.GameController;
import UI.AppMainWindow;
import UI.IconButton;
import UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Main side bar panel
 *
 * @author XiaoyuLi
 */
public class SideBarPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static IconButton gameButton;
    private static IconButton rankButton;
    private static IconButton tutorialButton;
    private static IconButton settingButton;

    public SideBarPanel() {
        initialize();
        addButtons();
        addListeners();
    }

    private void initialize() {
        Dimension size = new Dimension(UIConstants.SIDEBAR_WIDTH, UIConstants.SIDEBAR_HEIGHT);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setBackground(UIConstants.PRIMARY_COLOR);
        this.setLayout(new GridLayout(2, 1));

    }

    private void addButtons() {
        gameButton = new IconButton(UIConstants.GAME_ICON, UIConstants.GAME_ICON_ENABLED, UIConstants.GAME_ICON, "A");
        gameButton.setIcon(UIConstants.GAME_ICON_ENABLED);
        rankButton = new IconButton(UIConstants.RANK_ICON, UIConstants.RANK_ICON_ENABLED, UIConstants.RANK_ICON, "B");
        tutorialButton = new IconButton(UIConstants.TUTORIAL_ICON, UIConstants.TUTORIAL_ICON_ENABLED, UIConstants.TUTORIAL_ICON, "");
        settingButton = new IconButton(UIConstants.SETTING_ICON, UIConstants.SETTING_ICON_ENABLED, UIConstants.SETTING_ICON, "");

        JPanel TopPanel = new JPanel();
        TopPanel.setBackground(UIConstants.PRIMARY_COLOR);
        //mac setting
        TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -6));
        //windows setting
//		TopPanel.setLayout(new FlowLayout(-5, -3, -10));
        TopPanel.add(gameButton);
        TopPanel.add(rankButton);
        TopPanel.add(tutorialButton);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setBackground(UIConstants.PRIMARY_COLOR);
        ButtonPanel.setLayout(new BorderLayout(0, 0));
        ButtonPanel.add(settingButton, BorderLayout.SOUTH);

        //Add components to main panel
        this.add(TopPanel);
        this.add(ButtonPanel);
    }

    private void addListeners() {
        gameButton.addActionListener(e -> {
            gameButton.setIcon(UIConstants.GAME_ICON_ENABLED);
            rankButton.setIcon(UIConstants.RANK_ICON);
            tutorialButton.setIcon(UIConstants.TUTORIAL_ICON);
            settingButton.setIcon(UIConstants.SETTING_ICON);

            AppMainWindow.mainPanelCenter.removeAll();

            if (GameController.running) {
                AppMainWindow.mainPanelCenter.add(AppMainWindow.gamePanel, BorderLayout.CENTER);
//                AppMainWindow.gamePanel.timer.restart();
            } else {
                AppMainWindow.mainPanelCenter.add(AppMainWindow.configPanel, BorderLayout.CENTER);
            }
            AppMainWindow.mainPanelCenter.updateUI();
        });

        rankButton.addActionListener(e ->  {
            gameButton.setIcon(UIConstants.GAME_ICON);
            rankButton.setIcon(UIConstants.RANK_ICON_ENABLED);
            tutorialButton.setIcon(UIConstants.TUTORIAL_ICON);
            settingButton.setIcon(UIConstants.SETTING_ICON);
            if (GameController.running) {
//                AppMainWindow.gamePanel.timer.stop();
            }
            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(AppMainWindow.rankPanel, BorderLayout.CENTER);
            AppMainWindow.mainPanelCenter.updateUI();
        });

        tutorialButton.addActionListener(e -> {
            gameButton.setIcon(UIConstants.GAME_ICON);
            rankButton.setIcon(UIConstants.RANK_ICON);
            tutorialButton.setIcon(UIConstants.TUTORIAL_ICON_ENABLED);
            settingButton.setIcon(UIConstants.SETTING_ICON);
            if (GameController.running) {
//                AppMainWindow.gamePanel.timer.stop();
            }
            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(AppMainWindow.helpPanel, BorderLayout.CENTER);
            AppMainWindow.mainPanelCenter.updateUI();
        });


        settingButton.addActionListener(e ->  {
            rankButton.setIcon(UIConstants.RANK_ICON);
            gameButton.setIcon(UIConstants.GAME_ICON);
            tutorialButton.setIcon(UIConstants.TUTORIAL_ICON);
            settingButton.setIcon(UIConstants.SETTING_ICON_ENABLED);
            if (GameController.running) {
//                AppMainWindow.gamePanel.timer.stop();
            }
            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(AppMainWindow.settingPanel, BorderLayout.CENTER);
            AppMainWindow.mainPanelCenter.updateUI();
        });
    }

}
