package UI.Panel;

import Controller.GameController;
import UI.IconButton;
import UI.UIConstants;
import kalahProject.GameModel;
import tools.GridBag;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Main Game Panel
 *
 * @author XiaoyuLi
 */

public abstract class GamePanel extends JPanel{

    static final long serialVersionUID = 1L;
    private static BufferedImage background;
    static BufferedImage[] storeIcon;
    static BufferedImage[] houseIcon;

    JLabel clock;
    public IconButton pauseButton;
    IconButton newGameButton;
    IconButton pieRuleButton;

    int p1EndIndex;
    int p2EndIndex;
    int p1StartIndex;
    int p2StartIndex;

    GameModel gameModel;
    GameController gameController;

    ArrayList<JButton> houseButtons = new ArrayList<>();

    private JLabel p1Score;
    private JLabel p2Score;

    /**
     * Constructor
     * @param gameModel
     * @param gameController
     */
    GamePanel(GameModel gameModel,GameController gameController) {
        this.gameModel = gameModel;
        this.gameController = gameController;
        initialize();
        addListener();
    }


    /********************************************************************
     * abstract classes, sub class need implement this
     ********************************************************************/
    abstract void updateBoard();
    abstract void addListener();
    public abstract void showWinner();

    /**
     * Initialization
     */
    public void initialize() {
        p1EndIndex = gameModel.p1EndIndex();
        p2EndIndex = gameModel.p2EndIndex();
        p1StartIndex = gameModel.p1StartIndex();
        p2StartIndex = gameModel.p2StartIndex();

        houseIcon = new BufferedImage[7];
        storeIcon = new BufferedImage[7];

        try {
            for (int i = 0; i < UIConstants.HOUSE_ICON.length; ++i) {
                houseIcon[i] = ImageIO.read(UIConstants.HOUSE_ICON[i]);
                storeIcon[i] = ImageIO.read(UIConstants.STORE_ICON[i]);
            }
            background = ImageIO.read(UIConstants.GAME_BACKGROUND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.add(getTopPanel(), BorderLayout.NORTH);
        this.add(getLeftPanel(), BorderLayout.WEST);
        this.add(getRightPanel(), BorderLayout.EAST);
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getBottomPanel(), BorderLayout.SOUTH);

    }


    /**
     * JButtons Initialization
     */
    public void initButtons() {
        int size = gameModel.getNumHouses();

        //Initialize houses
        for (int i = 0; i < size; ++i) {
            JButton jButton = new JButton();

            //button and font customize, allow transparent image
            if (i != p1EndIndex && i != p2EndIndex) {
                jButton.setText(Integer.toString(gameController.seedCount(i)));
            }

            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            jButton.setFont(UIConstants.FONT_SMALL);

            //position the text based on player and set player2's house not clickable at beginning
            if (i < p1EndIndex) {
                jButton.setHorizontalTextPosition(AbstractButton.CENTER);
                jButton.setVerticalTextPosition(AbstractButton.BOTTOM);
            } else if (i > p1EndIndex && i < p2EndIndex) {
                jButton.setHorizontalTextPosition(AbstractButton.CENTER);
                jButton.setVerticalTextPosition(AbstractButton.TOP);
            }
            houseButtons.add(jButton);
        }
    }

    /**
     * Contains player 2 status, clock, functional buttons
     * @return the north panel
     */
    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(UIConstants.GAME_TOP_BOTTOM_EMPTY_SIZE);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        //player2 score panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(UIConstants.GAME_PLAYERPANEL_SIZE);
        leftPanel.setOpaque(false);
        p2Score = new JLabel(Integer.toString(gameController.seedCount(p2EndIndex)));
        p2Score.setFont(UIConstants.FONT_BOLD);
        leftPanel.add(p2Score, BorderLayout.EAST);

        //new game, pause, pie rule buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(UIConstants.GAME_PLAYERPANEL_SIZE);
        rightPanel.setOpaque(false);

        newGameButton = new IconButton(UIConstants.NEW_GAME_ICON, UIConstants.NEW_GAME_ICON, UIConstants.NEW_GAME_ICON, "New Game");
        newGameButton.setIcon(UIConstants.NEW_GAME_ICON);
        pauseButton = new IconButton(UIConstants.RESUME_GAME_ICON, UIConstants.PAUSE_GAME_ICON, UIConstants.RESUME_GAME_ICON, "PAUSU/RESUME");
        pauseButton.setIcon(UIConstants.PAUSE_GAME_ICON);

        pieRuleButton = new IconButton(UIConstants.PIE_RULE_DISABLE_ICON, UIConstants.PIE_RULE_ICON, UIConstants.PIE_RULE_DISABLE_ICON, "Pie Rule");
        pieRuleButton.setIcon(UIConstants.PIE_RULE_DISABLE_ICON);
        pieRuleButton.setEnabled(false);


        rightPanel.add(newGameButton);
        rightPanel.add(pauseButton);
        rightPanel.add(pieRuleButton);

        JPanel clockPanel = new JPanel();
        clockPanel.setOpaque(false);
        clock = new JLabel(Integer.toString(gameController.getRemainingTime()));
        clock.setFont(UIConstants.CLOCK_FONT);
        clock.setForeground(Color.WHITE);
        clockPanel.add(clock);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(clockPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);
        return topPanel;
    }

    /**
     * Contains player 1 status
     * @return the south panel
     */
    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(UIConstants.GAME_TOP_BOTTOM_EMPTY_SIZE);

        //player1 score
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(UIConstants.GAME_PLAYERPANEL_SIZE);
        p1Score = new JLabel(Integer.toString(gameController.seedCount(p1EndIndex)));
        p1Score.setFont(UIConstants.FONT_BOLD);
        rightPanel.add(p1Score, BorderLayout.WEST);

        bottomPanel.add(rightPanel, BorderLayout.EAST);
        return bottomPanel;
    }

    /**
     * empty space
     * @return the west panel
     */
    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(UIConstants.GAME_LEFT_RIGHT_EMPTY_SIZE);
        return leftPanel;
    }
    /**
     * empty space
     * @return the east panel
     */
    private JPanel getRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(UIConstants.GAME_LEFT_RIGHT_EMPTY_SIZE);
        return rightPanel;
    }
    /**
     * the main game panel, it contains house and store buttons
     * @return the center panel
     */
    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        initButtons();
        disableOtherPlayerHouse();
        updateSeedsImage();

        //add houses
        for (int i = p1StartIndex, j = p2EndIndex - 1; i < p1EndIndex; ++i, --j) {
            GridBag.add(centerPanel, houseButtons.get(i), i + 1, 1, 1, 1, 10, 0, 5, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
            GridBag.add(centerPanel, houseButtons.get(j), i + 1, 0, 1, 1, 10, 0, 5, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        }

        //add two stores
        GridBag.add(centerPanel, houseButtons.get(p1EndIndex), p1EndIndex + 1, 0, 1, 2, 10, 45, 5, 35, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GridBag.add(centerPanel, houseButtons.get(p2EndIndex), 0, 0, 1, 2, 10, 40, 5, 35, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return centerPanel;
    }


    /**
     * disable opponent buttons
     */
    public void disableOtherPlayerHouse() {
        for (int i = p1StartIndex; i < p1EndIndex; ++i) {
            houseButtons.get(i).setEnabled(true);
        }
        for (int i = p2StartIndex; i < p2EndIndex; ++i) {
            houseButtons.get(i).setEnabled(false);
//            gameModel.getPlayer()
        }
    }

    /**
     * disable all player buttons
     */
    public void disableAllPlayerHouse() {
        for (int i = p1StartIndex; i < p1EndIndex; ++i) {
            houseButtons.get(i).setEnabled(false);
        }
        for (int i = p2StartIndex; i < p2EndIndex; ++i) {
            houseButtons.get(i).setEnabled(false);
        }
    }

    /**
     * make sure score text are up to date
     */
    void updateSeedsText() {
        //update house seeds number text
        for (int i = p1StartIndex, j = p2StartIndex; i < p1EndIndex; ++i, ++j) {
            houseButtons.get(i).setText(Integer.toString(gameController.seedCount(i)));
            houseButtons.get(j).setText(Integer.toString(gameController.seedCount(j)));
        }
        //update store seeds number text
        p1Score.setText(Integer.toString(gameController.seedCount(p1EndIndex)));
        p2Score.setText(Integer.toString(gameController.seedCount(p2EndIndex)));
    }

    /**
     * make sure seed iamge are up to date
     */
    void updateSeedsImage() {
        for (int i = 0; i < houseButtons.size(); ++i) {
            if (gameController.seedCount(i) < 7) {
                if (i == p1EndIndex || i == p2EndIndex) {
                    houseButtons.get(i).setIcon(new ImageIcon(storeIcon[(gameController.seedCount(i))]));
                } else {
                    houseButtons.get(i).setIcon(new ImageIcon(houseIcon[(gameController.seedCount(i))]));
                }
            } else {
                if (i == p1EndIndex || i == p2EndIndex) {
                    houseButtons.get(i).setIcon(new ImageIcon(storeIcon[6]));
                } else {
                    houseButtons.get(i).setIcon(new ImageIcon(houseIcon[6]));
                }
            }
        }
    }

    /**
     * make sure clock is reset to default value each round
     */
    void updateClock() {
        gameController.setRemainingTime(UIConstants.TIME_PER_ROUND);
        //update clock label immediately to fix delay
        clock.setText(Integer.toString(gameController.getRemainingTime()));
        clock.setForeground(Color.WHITE);
    }

    /**
     * at end of the game, popup a dialog ask user name, it will store current game status.
     */



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}


