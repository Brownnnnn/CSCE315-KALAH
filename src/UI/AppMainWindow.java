package UI;

import UI.Panel.*;

import javax.swing.*;
import java.awt.*;

/**
 * Main application GUI
 *
 * @author XiaoyuLi
 */
public class AppMainWindow {

    public static JPanel mainPanelCenter;
    public static HelpPanel helpPanel;
    public static RankPanel rankPanel;
    public static JPanel gamePanel;
    public static ConfigPanel configPanel;
    public static SettingPanel settingPanel;

    private JFrame app;

    public AppMainWindow() {
        initialize();
    }

    public void setVisible() {
        app.setVisible(true);
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Initialize main window
        app = new JFrame();
        app.setSize(UIConstants.MAIN_WINDOW_WIDTH, UIConstants.MAIN_WINDOW_HEIGHT);
        app.setTitle(UIConstants.APP_NAME);
        app.setBackground(UIConstants.BACKGROUND_COLOR);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setLocationRelativeTo(null);

        //Initialize panels
        SideBarPanel sidebarPanel = new SideBarPanel();
        configPanel = new ConfigPanel();
        helpPanel = new HelpPanel();
        rankPanel = new RankPanel();
        settingPanel = new SettingPanel();


        mainPanelCenter = new JPanel(true);
        mainPanelCenter.setLayout(new BorderLayout());
        mainPanelCenter.add(configPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        //Add components to main panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(mainPanelCenter, BorderLayout.CENTER);

        app.add(mainPanel);

    }
}
