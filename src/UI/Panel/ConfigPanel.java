package UI.Panel;

import Controller.GameController;
import UI.AppMainWindow;
import UI.UIConstants;
import kalahProject.AI.AI;
import kalahProject.ClientServer.GameClient;
import kalahProject.ClientServer.GameServer;
import kalahProject.GameModel;
import tools.GridBag;

import javax.swing.*;
import java.awt.*;

/**
 * Game configuration panel
 *
 * @author XiaoyuLi
 */

public class ConfigPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int CLIENT = 0;
    private static final int SERVER = 1;

    private static JCheckBox randomize;
    private static JCheckBox pieRule;
    private static JCheckBox goFirst;

    private boolean onlineGame;
    private boolean AIGame;
    private boolean myTurn;
    private int AILevel;

    private static JButton startButton;
    private static JButton localGame;
    private static JButton createAI;
    private static JButton multiplayer;


    private static JComboBox<String> levelList;

    private SliderPanel houseSliderPanel;
    private SliderPanel seedSliderPanel;

    public ConfigPanel() {
        initialize();
        addListeners();
    }

    private void initialize() {
        startButton = new JButton();
        onlineGame = false;
        AIGame = false;
        myTurn = false;
        AILevel = 0;

        this.setLayout(new BorderLayout());
        this.setBackground(UIConstants.BACKGROUND_COLOR);
        this.add(getLeftPanel(), BorderLayout.WEST);
        this.add(getRightPanel(), BorderLayout.EAST);
        this.add(getModePanel(), BorderLayout.CENTER);
    }


    /**
     * empty box panel
     */
    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(UIConstants.CONFIG_EMPTY_SIZE);
        leftPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        return leftPanel;
    }


    /**
     * empty box panel
     */
    private JPanel getRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(UIConstants.CONFIG_EMPTY_SIZE);
        rightPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        return rightPanel;
    }

    /**
     * @return the mode selection panel
     */
    private JPanel getModePanel() {
        JPanel modePanel = new JPanel(new GridBagLayout());
        modePanel.setBackground(UIConstants.BACKGROUND_COLOR);
        JLabel topPadding = new JLabel(UIConstants.CONFIG_PLACEHOLDER);
        JLabel bottomPadding = new JLabel(UIConstants.CONFIG_PLACEHOLDER);
        localGame = new JButton("Local Game");
        createAI = new JButton("Create AI");
        multiplayer = new JButton("MultiPlayer");
        GridBag.add(modePanel, topPadding, 0, 0, 1, 1, 0, 5, 0, 10, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
        GridBag.add(modePanel, localGame, 0, 1, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(modePanel, createAI, 0, 2, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(modePanel, multiplayer, 0, 3, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(modePanel, bottomPadding, 0, 4, 1, 1, 0, 5, 0, 10, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
        return modePanel;
    }

    private JPanel getOptionPanel() {
        JPanel setting = new JPanel(new GridBagLayout());
        setting.setBackground(UIConstants.BACKGROUND_COLOR);
        addComponents(setting);
        return setting;
    }

    private void addComponents(JPanel panel) {
        JLabel topPadding = new JLabel(UIConstants.CONFIG_PLACEHOLDER);
        JLabel bottomPadding = new JLabel(UIConstants.CONFIG_PLACEHOLDER);
        startButton.setText("Start");

        //prompt
        JPanel promptPanel = getPromptPanel(UIConstants.CONFIG_PROMPT,UIConstants.CONFIG_PROMPT2);

        //Advance settings, include seed random distribution and pie rule.
        JPanel advSettingPanel = new JPanel(new BorderLayout());
        advSettingPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        randomize = new JCheckBox("Randomize");
        randomize.setBackground(UIConstants.BACKGROUND_COLOR);
        advSettingPanel.add(randomize,BorderLayout.NORTH);

        pieRule = new JCheckBox("Pie Rule");
        pieRule.setBackground(UIConstants.BACKGROUND_COLOR);
        pieRule.setSelected(true);
        advSettingPanel.add(pieRule,BorderLayout.CENTER);

        if(AIGame) {
            JButton easyAI = new JButton(UIConstants.CONFIG_EASY);
            JButton mediumAI = new JButton(UIConstants.CONFIG_MEDIUM);
            JButton hardAI = new JButton(UIConstants.CONFIG_HARD);
            easyAI.addActionListener(e -> {
                    AI.level = 1;
                    GameClient.run(1234);
            });

            mediumAI.addActionListener(e -> {
                AI.level = 2;
                GameClient.run(1234);
            });

            hardAI.addActionListener(e -> {
                AI.level = 3;
                GameClient.run(1234);
            });

            GridBag.add(panel, topPadding, 0, 0, 1, 1, 0, 5, 0, 10, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
            GridBag.add(panel, promptPanel, 0, 1, 1, 1, 0, 50, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
            GridBag.add(panel, easyAI, 0, 2, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            GridBag.add(panel, mediumAI, 0, 3, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            GridBag.add(panel, hardAI, 0, 4, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            GridBag.add(panel, bottomPadding, 0, 5, 1, 1, 0, 5, 0, 10, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
            return;
        }

        if(onlineGame) {
            goFirst = new JCheckBox("Go First");
            goFirst.setBackground(UIConstants.BACKGROUND_COLOR);
            goFirst.setSelected(true);
            advSettingPanel.add(goFirst,BorderLayout.SOUTH);
        }

        //basic setting, include variable houses and seeds
        houseSliderPanel = new SliderPanel("Houses: ", 4, 9, 6, 1);
        houseSliderPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        seedSliderPanel = new SliderPanel("Seeds:   ", 1, 10, 4, 3);
        seedSliderPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        //add components use grid bag layout.
        GridBag.add(panel, topPadding, 0, 0, 1, 1, 0, 5, 0, 10, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
        GridBag.add(panel, promptPanel, 0, 1, 1, 1, 0, 50, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GridBag.add(panel, advSettingPanel, 0, 16, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(panel, startButton, 0, 17, 1, 1, 300, 50, 0, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(panel, houseSliderPanel, 0, 8, 1, 1, 50, 0, 0, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(panel, seedSliderPanel, 0, 9, 1, 1, 50, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(panel, bottomPadding, 0, 20, 1, 1, 0, 5, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH);

    }


    /**
     * Add mouse clicker listener to menus
     */
    private void addListeners() {
        localGame.addActionListener(e -> {
            this.removeAll();
            this.add(getLeftPanel(), BorderLayout.WEST);
            this.add(getRightPanel(), BorderLayout.EAST);
            this.add(getOptionPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        createAI.addActionListener(e -> {
            AIGame = true;
            this.removeAll();
            this.add(getLeftPanel(), BorderLayout.WEST);
            this.add(getRightPanel(), BorderLayout.EAST);
            this.add(getOptionPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        multiplayer.addActionListener(e -> {
            onlineGame = true;
            this.removeAll();
            this.add(getLeftPanel(), BorderLayout.WEST);
            this.add(getRightPanel(), BorderLayout.EAST);
            HostSetting();
        });

        startButton.addActionListener(e -> {
            int seeds = seedSliderPanel.getValue();
            int houses = houseSliderPanel.getValue() * 2 + 2;

            GameModel gameModel = new GameModel(seeds,houses);

            if(onlineGame){
                if(goFirst.isSelected()) myTurn = true;
//                String port=JOptionPane.showInputDialog("Enter port: ");
                GameServer.run(1234);
                GameController gameController = new GameController(gameModel,myTurn,true,
                    randomize.isSelected(),pieRule.isSelected(),AILevel);
                AppMainWindow.gamePanel = new GamePanelOnline(gameModel,gameController,GameServer.getExchangeThread());
            } else {
                GameController gameController = new GameController(gameModel,true,false,
                    randomize.isSelected(),pieRule.isSelected(),AILevel);
                AppMainWindow.gamePanel = new GamePanelLocal(gameModel,gameController);
            }

            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(AppMainWindow.gamePanel, BorderLayout.CENTER);
            AppMainWindow.mainPanelCenter.updateUI();
        });
    }

    private void HostSetting() {
        Object[] options = {"Join","Create"};
        int n = JOptionPane.showOptionDialog(null, "Create or join a game", "Multiplayer Setting",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if(n == CLIENT) {
//            String port=JOptionPane.showInputDialog("Enter port: ");
            GameClient.run(1234);
        } else {
            this.add(getOptionPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    private JPanel getPromptPanel(String first, String second) {
        JLabel prompt = new JLabel(first);
        JLabel prompt2 = new JLabel(second);
        prompt.setFont(UIConstants.FONT_BOLD);
        JPanel promptPanel = new JPanel(new GridLayout(2, 1));
        promptPanel.add(prompt);
        promptPanel.add(prompt2);
        promptPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        return promptPanel;
    }
}