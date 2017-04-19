//avatars :http://www.iconfont.cn/plus/collections/detail?cid=3223

package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * UI constants
 *
 * @author Xiaoyu Li
 */
public class UIConstants {

    public static int TIME_PER_ROUND = 100;

    /**
     * Basic informations
     */
    public final static String SCHOOL_NAME = "Texas A&M University";
    public final static String HELP = "HELP";
    public final static String RULES = "RULES";
    public final static String TIPS = "TIPS";
    public final static String RANK = "RANK";
    public final static String[] COLUMN_TITLE = {"RANK", "NAME", "SCORE", "MOVES"};
    public final static String RANK_PROMPT = "BEST 10 PLAYERS";
    public final static String CONFIG_PROMPT = "Ready?";
    public final static String CONFIG_PROMPT2 = "Choose Your Flavor.";
    public final static String CONFIG_EASY = "Easy Bot";
    public final static String CONFIG_MEDIUM = "Medium Bot";
    public final static String CONFIG_HARD = "Hard Bot";
    public final static String CONFIG_LOCAL = "Local Player";
    public final static String CONFIG_CREATE_AI = "Create AI";
    public final static String CONFIG_BACK = "Back";
    public final static String CONFIG_QUIT = "Quit";
    public final static String CONFIG_MULTI = "Multi player";

    public final static String CONFIG_PLACEHOLDER = "      ";

    /**
     * Game name and version
     */
    public final static String APP_NAME = "KALAH";
    public final static String APP_VERSION = "v_1.3";

    /**
     * Current working directory
     */
    private final static String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * Colors
     */
    public final static Color BACKGROUND_COLOR = Color.WHITE;
    //Disable icon color
    public final static Color PRIMARY_COLOR = new Color(100, 100, 100);
    //Enable icon color
    public final static Color SECONDARY_COLOR = new Color(0, 0, 0);
    //Border color
    public final static Color TERTIARY_COLOR = new Color(34, 34, 34);

    /**
     * TODO: Try to make all OS looks same
     * Sizes
     */

    //set width to 1000 on mac
    //set width to 1006 on mac
    public final static int MAIN_WINDOW_WIDTH = 1006;
    //set height to 522 on mac
    //set height to 530 on mac
    public final static int MAIN_WINDOW_HEIGHT = 530;

    public final static int SIDEBAR_WIDTH = 50;
    public final static int SIDEBAR_HEIGHT = 500;


    /**
     * Fonts
     */
    public final static Font FONT_BOLD = new Font("Monaco", Font.BOLD, 29);
    public final static Font FONT_NORMAL = new Font("Helvetica Neue", Font.PLAIN, 18);
    public final static Font FONT_SMALL = new Font("Consolas", Font.PLAIN, 15);
    public final static Font CLOCK_FONT = new Font("Monaco", Font.PLAIN, 22);


    /**
     * Main game icons
     */
    public final static File[] HOUSE_ICON = {
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_1_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_2_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_3_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_4_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_5_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "house_6_seeds.png"),
    };

    public final static File[] STORE_ICON = {
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_1_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_2_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_3_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_4_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_5_seeds.png"),
            new File(CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "store_6_seeds.png"),
    };

    public final static ImageIcon NEW_GAME_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "new_game.png");
    public final static ImageIcon PIE_RULE_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "pie_rule.png");
    public final static ImageIcon PIE_RULE_DISABLE_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "pie_rule_disable.png");
    public final static ImageIcon PAUSE_GAME_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "pause_game.png");
    public final static ImageIcon RESUME_GAME_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "resume_game.png");

    /**
     * Sidebar panel icons
     */
    public final static ImageIcon GAME_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "game.png");
    public final static ImageIcon GAME_ICON_ENABLED = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "gameEnable.png");
    public final static ImageIcon RANK_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "rank.png");
    public final static ImageIcon RANK_ICON_ENABLED = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "rankEnable.png");
    public final static ImageIcon TUTORIAL_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "help.png");
    public final static ImageIcon TUTORIAL_ICON_ENABLED = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "helpEnable.png");
    public final static ImageIcon SETTING_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "setting.png");
    public final static ImageIcon SETTING_ICON_ENABLED = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "settingEnable.png");


    /**
     * Background
     */

    public final static File RULES_BACKGROUND = new File(
            CURRENT_DIR + File.separator + "assets" + File.separator + "background" + File.separator + "rules_background.png");
    public final static File TIPS_BACKGROUND = new File(
            CURRENT_DIR + File.separator + "assets" + File.separator + "background" + File.separator + "tips_background.png");
    public final static File GAME_BACKGROUND = new File(
            CURRENT_DIR + File.separator + "assets" + File.separator + "background" + File.separator + "game_background.png");

    /**
     * rank panel
     */
    public final static ImageIcon CLEAR_TEXT_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "clearText.png");
    public final static ImageIcon EXPORT_TEXT_ICON = new ImageIcon(
            CURRENT_DIR + File.separator + "assets" + File.separator + "icon" + File.separator + "exportText.png");


    /**
     * Layout
     */
    public final static int MAIN_H_GAP = 25;
    public final static Dimension MENU_LIST_SIZE = new Dimension(250, MAIN_WINDOW_HEIGHT);
    public final static Dimension MENU_ITEM_SIZE = new Dimension(250, 50);

    public final static Dimension CONFIG_EMPTY_SIZE = new Dimension(350, MAIN_WINDOW_HEIGHT);

    public final static Dimension GAME_LEFT_RIGHT_EMPTY_SIZE = new Dimension(100, MAIN_WINDOW_HEIGHT);
    public final static Dimension GAME_TOP_BOTTOM_EMPTY_SIZE = new Dimension(MAIN_WINDOW_WIDTH, 50);
    public final static Dimension GAME_PLAYERPANEL_SIZE = new Dimension(218, 32);

}
