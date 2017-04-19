package UI.Panel;

import UI.IconButton;
import UI.UIConstants;
import tools.ReadFile;

import javax.swing.*;
import java.awt.*;

/**
 * Main rank panel
 *
 * @author XiaoyuLi
 */
public class RankPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RankPanel() {
        initialize();
        addComponent();
    }

    private static String[][] initTableData() {
        String lines[] = ReadFile.scan("assets/highscores.txt").split("\n");
        String[][] linesCsv = new String[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            linesCsv[i] = lines[i].split(" ");
        }
        return linesCsv;
    }

    private void initialize() {
        this.setBackground(UIConstants.BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
    }

    private void addComponent() {
        this.add(getTopPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 5));

        JLabel labelTitle = new JLabel(UIConstants.RANK);
        labelTitle.setFont(UIConstants.FONT_BOLD);
        labelTitle.setForeground(UIConstants.SECONDARY_COLOR);
        topPanel.add(labelTitle);

        return topPanel;
    }

    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        centerPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));

        JPanel topPanelLeft = new JPanel();
        topPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 5));
        topPanelLeft.setBackground(UIConstants.BACKGROUND_COLOR);
        JLabel prompt = new JLabel(UIConstants.RANK_PROMPT);
        prompt.setFont(UIConstants.FONT_NORMAL);
        prompt.setForeground(UIConstants.SECONDARY_COLOR);
        topPanelLeft.add(prompt);

        JPanel topPanelRight = new JPanel();
        topPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT, UIConstants.MAIN_H_GAP, 5));
        topPanelRight.setBackground(UIConstants.BACKGROUND_COLOR);
        IconButton clear = new IconButton(UIConstants.CLEAR_TEXT_ICON, UIConstants.CLEAR_TEXT_ICON, UIConstants.CLEAR_TEXT_ICON, "");
        IconButton export = new IconButton(UIConstants.EXPORT_TEXT_ICON, UIConstants.EXPORT_TEXT_ICON, UIConstants.EXPORT_TEXT_ICON, "");
        topPanelRight.add(clear);
        topPanelRight.add(export);

        topPanel.add(topPanelLeft);
        topPanel.add(topPanelRight);

        Object[][] data = initTableData();
        String[] columns_title = UIConstants.COLUMN_TITLE;
        JTable jTable = new JTable(data, columns_title);
//		jTable.setFont(UIConstants.FONT_NORMAL);
        jTable.getTableHeader().setFont(UIConstants.FONT_NORMAL);
        jTable.getTableHeader().setBackground(UIConstants.BACKGROUND_COLOR);
        jTable.setRowHeight(30);
        jTable.setGridColor(Color.BLACK);
        jTable.setSelectionBackground(UIConstants.SECONDARY_COLOR);
        jTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane dataPanel = new JScrollPane(jTable);
//	    dataPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(dataPanel, BorderLayout.CENTER);

        return centerPanel;
    }
}
