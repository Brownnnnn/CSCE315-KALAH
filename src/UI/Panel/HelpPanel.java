package UI.Panel;

import UI.AppMainWindow;
import UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Main Help Panel
 *
 * @author XiaoyuLi
 */
public class HelpPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JPanel helpPanelMain;
    private static JPanel rulesPanel;
    private static JPanel tipsPanel;
    private static JPanel helpPanelRules;
    private static JPanel helpPanelTips;

    /**
     * Constructor
     */
    public HelpPanel() {
        initialize();
        addListener();
    }


    /**
     * Initialization
     */
    private void initialize() {
        this.setBackground(UIConstants.BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        helpPanelRules = new HelpPanelRules();
        helpPanelTips = new HelpPanelTips();

        //Add components to main panel
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

        JLabel title = new JLabel(UIConstants.HELP);
        title.setFont(UIConstants.FONT_BOLD);
        title.setForeground(UIConstants.SECONDARY_COLOR);

        //Add components to main panel
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

        //left side bar
        JPanel menuList = new JPanel();
        menuList.setPreferredSize(UIConstants.MENU_LIST_SIZE);
        menuList.setBackground(UIConstants.PRIMARY_COLOR);
        menuList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //menu names
        JLabel rulesLabel = new JLabel(UIConstants.RULES);
        JLabel tipsLabel = new JLabel(UIConstants.TIPS);
        rulesLabel.setFont(UIConstants.FONT_NORMAL);
        rulesLabel.setForeground(Color.white);
        tipsLabel.setFont(UIConstants.FONT_NORMAL);
        tipsLabel.setForeground(Color.white);

        //rules panel is active panel by default
        rulesPanel = new JPanel();
        rulesPanel.setBackground(UIConstants.SECONDARY_COLOR);
        rulesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        rulesPanel.setPreferredSize(UIConstants.MENU_ITEM_SIZE);
        rulesPanel.add(rulesLabel);

        tipsPanel = new JPanel();
        tipsPanel.setBackground(UIConstants.PRIMARY_COLOR);
        tipsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        tipsPanel.setPreferredSize(UIConstants.MENU_ITEM_SIZE);
        tipsPanel.add(tipsLabel);

        //add items to menu list
        menuList.add(rulesPanel);
        menuList.add(tipsPanel);

        //main window, rules panel is shown by default.
        helpPanelMain = new JPanel();
        helpPanelMain.setBackground(UIConstants.PRIMARY_COLOR);
        helpPanelMain.setLayout(new BorderLayout());
        helpPanelMain.add(helpPanelRules);

        //add components to main panel
        centerPanel.add(menuList, BorderLayout.WEST);
        centerPanel.add(helpPanelMain, BorderLayout.CENTER);

        return centerPanel;
    }


    /**
     * Add mouse clicker listener to menus
     */
    private void addListener() {
        rulesPanel.addMouseListener(new MouseListener() {

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
                rulesPanel.setBackground(UIConstants.SECONDARY_COLOR);
                tipsPanel.setBackground(UIConstants.PRIMARY_COLOR);

                HelpPanel.helpPanelMain.removeAll();
                HelpPanel.helpPanelMain.add(helpPanelRules);
                AppMainWindow.helpPanel.updateUI();

            }
        });

        tipsPanel.addMouseListener(new MouseListener() {

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
                tipsPanel.setBackground(UIConstants.SECONDARY_COLOR);
                rulesPanel.setBackground(UIConstants.PRIMARY_COLOR);

                HelpPanel.helpPanelMain.removeAll();
                HelpPanel.helpPanelMain.add(helpPanelTips);
                AppMainWindow.helpPanel.updateUI();

            }
        });

    }
}