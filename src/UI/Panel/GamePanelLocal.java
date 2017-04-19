package UI.Panel;

import Controller.GameController;
import UI.AppMainWindow;
import UI.UIConstants;
import kalahProject.GameModel;
import tools.WriteFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GamePanelLocal extends GamePanel implements Observer {
    private Timer timer;

    public GamePanelLocal(GameModel gameModel,GameController gameController){
        super(gameModel,gameController);
        gameController.addObserver(this);
        timer = new Timer(1000, new TimerListener());
        timer.start();
    }

    public void updateBoard() {

        disableOtherPlayerHouse();
        updateSeedsImage();
        updateSeedsText();
        updateClock();

        revalidate();
        repaint();
    }

    public void addListener() {
        for (JButton b : houseButtons) b.addActionListener(new GamePanelLocal.GameListener());

        newGameButton.addActionListener( e->{
            timer.stop();
            timer = null;
            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(new ConfigPanel());
            AppMainWindow.mainPanelCenter.updateUI();
        });

        pauseButton.addActionListener(e->{
            if (!gameController.isGamePause()) {
                timer.stop();
                gameController.setGamePause(true);
                disableAllPlayerHouse();
                pauseButton.setIcon(UIConstants.RESUME_GAME_ICON);
            } else {
                timer.restart();
                gameController.setGamePause(false);
                disableOtherPlayerHouse();
                pauseButton.setIcon(UIConstants.PAUSE_GAME_ICON);
            }
            AppMainWindow.mainPanelCenter.updateUI();
        });

        pieRuleButton.addActionListener(e->{
            gameModel.swapPlayerHouses();
            gameController.switchPlayer();
            gameController.setPieRule(false);
            pieRuleButton.setEnabled(false);
            pieRuleButton.setIcon(UIConstants.PIE_RULE_DISABLE_ICON);
            updateBoard();
        });
    }

    private class GameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();


            for (int i = 0; i < gameModel.getNumHouses(); ++i) {
                if (i == p1EndIndex || i == p2EndIndex) {
                    continue;
                }
                if (source == houseButtons.get(i)) {
                    boolean isValid = gameController.move(i);
                    if(!gameModel.isGoAgain()){
                        gameController.switchPlayer();
                    }
                    if (isValid) updateBoard();
                }
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            gameController.countDown();
            clock.setText(Integer.toString(gameController.getRemainingTime()));

            //set color to red to warning player
            if (gameController.getRemainingTime() <= 3) {
                clock.setForeground(Color.RED);
            }

            //switch player, increase round counter, update board
            if (gameController.getRemainingTime() <= 0) {
                gameController.switchPlayer();
                gameController.roundIncrement();
                if (gameModel.isGoAgain()) {
                    gameModel.setGoAgain(false);
                }
                clock.setForeground(Color.WHITE);
                updateBoard();
            }
        }
    }

    public void showWinner() {
        String winner = gameController.getWinner() ? "Player 2" : "Player 1";
        String message = "The Winner is " + winner + "\n Enter your name : ";
        String playerName = JOptionPane.showInputDialog(message);
        if(playerName == null || playerName.equals("")){
            playerName = "hidden";
        }
        int score = gameController.getWinnerScore();
        WriteFile.recordScore(playerName,score,gameController.getRoundCounter());
        System.exit(0);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (gameController.getRoundCounter() == 1 && gameController.isPieRule() ) {
            pieRuleButton.setIcon(UIConstants.PIE_RULE_ICON);
            pieRuleButton.setEnabled(true);
        } else{
            pieRuleButton.setEnabled(false);
        }

        if (gameController.isUpdateBoard()) {
            updateBoard();
            gameController.setUpdateBoard(false);
        }

        if (!gameController.isRunning()) {
            timer.stop();
            timer = null;
            updateBoard();
            showWinner();
        }
    }

}
