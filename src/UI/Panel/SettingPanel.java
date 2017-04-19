package UI.Panel;

import UI.AppMainWindow;
import UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Main Setting Panel
 *
 * @author XiaoyuLi
 */
public class SettingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JPanel settingPanelMain;
    private static JPanel optionPanel;
    private static JPanel aboutPanel;
    private static JPanel settingOptionPanel;
    private static JPanel settingAboutPanel;


    /**
     * Construction
     */
    public SettingPanel() {
        initialize();
        addListener();
    }


    /**
     * Initialization
     */
    private void initialize() {
        settingOptionPanel = new SettingPanelOption();
        settingAboutPanel = new SettingPanelAbout();

        this.setBackground(UIConstants.BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        this.add(getTopPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }


    /**
     * Top panel will contain the title of panel
     */
    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 5));

        JLabel title = new JLabel("SETTING");
        title.setFont(UIConstants.FONT_BOLD);
        title.setForeground(UIConstants.SECONDARY_COLOR);
        topPanel.add(title);

        return topPanel;
    }

    /**
     * Center Panel - LEFT is menu list, RIGHT is the interface.
     */
    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIConstants.PRIMARY_COLOR);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, UIConstants.TERTIARY_COLOR));

        //Left side bar
        JPanel menuList = new JPanel();
        menuList.setPreferredSize(UIConstants.MENU_LIST_SIZE);
        menuList.setBackground(UIConstants.PRIMARY_COLOR);
        menuList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //Menus name
        JLabel optionLabel = new JLabel("OPTIONS");
        JLabel aboutLabel = new JLabel("ABOUT");
        optionLabel.setForeground(Color.white);
        optionLabel.setFont(UIConstants.FONT_NORMAL);
        aboutLabel.setForeground(Color.white);
        aboutLabel.setFont(UIConstants.FONT_NORMAL);

        //Option panel is active panel by default
        optionPanel = new JPanel();
        optionPanel.setBackground(UIConstants.SECONDARY_COLOR);
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        optionPanel.setPreferredSize(UIConstants.MENU_ITEM_SIZE);
        optionPanel.add(optionLabel);

        aboutPanel = new JPanel();
        aboutPanel.setBackground(UIConstants.PRIMARY_COLOR);
        aboutPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        aboutPanel.setPreferredSize(UIConstants.MENU_ITEM_SIZE);
        aboutPanel.add(aboutLabel);

        //Add items to menu list
        menuList.add(optionPanel);
        menuList.add(aboutPanel);

        //Main window, setting panel is shown by default.
        settingPanelMain = new JPanel();
        settingPanelMain.setBackground(UIConstants.PRIMARY_COLOR);
        settingPanelMain.setLayout(new BorderLayout());
        settingPanelMain.add(settingOptionPanel);

        //Add components to main panel
        centerPanel.add(menuList, BorderLayout.WEST);
        centerPanel.add(settingPanelMain, BorderLayout.CENTER);

        return centerPanel;
    }

    /**
     * Add mouse clicker listener to menus
     */
    private void addListener() {
        optionPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                optionPanel.setBackground(UIConstants.SECONDARY_COLOR);
                aboutPanel.setBackground(UIConstants.PRIMARY_COLOR);

                SettingPanel.settingPanelMain.removeAll();
                SettingPanel.settingPanelMain.add(settingOptionPanel);
                AppMainWindow.settingPanel.updateUI();

            }
        });

        aboutPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                aboutPanel.setBackground(UIConstants.SECONDARY_COLOR);
                optionPanel.setBackground(UIConstants.PRIMARY_COLOR);

                SettingPanel.settingPanelMain.removeAll();
                SettingPanel.settingPanelMain.add(settingAboutPanel);
                AppMainWindow.settingPanel.updateUI();

            }
        });

    }
}