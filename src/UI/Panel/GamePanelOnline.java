package UI.Panel;

import Controller.GameController;
import UI.AppMainWindow;
import UI.UIConstants;
import kalahProject.ClientServer.ExchangeThread;
import kalahProject.GameModel;
import tools.WriteFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by xiaoyu on 3/29/17.
 */
public class GamePanelOnline extends GamePanel implements Observer{
    private ExchangeThread exchangeThread;
    public Timer timer;
    public GamePanelOnline(GameModel gameModel, GameController gameController, ExchangeThread thread) {
        super(gameModel,gameController);

        gameController.addObserver(this);

        timer = new Timer(1000, new GamePanelOnline.TimerListener());
        timer.start();

        disableAllPlayerHouse();

        if(gameController.getAILevel() > 0) {
            gameController.createAI();
        }
        exchangeThread = thread;
        thread.setGameController(gameController);
        thread.setGamePanelOnline(this);
        serverInit();
    }

    public void updateBoard() {
        //Update seeds
        if(gameController.isMyTurn()){
            disableOtherPlayerHouse();
        } else{
            disableAllPlayerHouse();
        }
        updateSeedsImage();
        updateSeedsText();
        updateClock();

        revalidate();
        repaint();
    }


    public void addListener() {
        for (JButton b : houseButtons) b.addActionListener(new GamePanelOnline.GameListener());

        newGameButton.addActionListener( e->{
            timer.stop();
            timer = null;
            AppMainWindow.mainPanelCenter.removeAll();
            AppMainWindow.mainPanelCenter.add(new ConfigPanel());
            AppMainWindow.mainPanelCenter.updateUI();
        });

        pauseButton.addActionListener(e->{
            if (!gameController.isGamePause()) {
                exchangeThread.sendMessage("PAUSE");
                pauseGame();
            } else {
                exchangeThread.sendMessage("RESUME");
                resumeGame();
            }
            AppMainWindow.mainPanelCenter.updateUI();
        });

        pieRuleButton.addActionListener(e->{
            gameModel.swapPlayerHouses();
            exchangeThread.sendMessage("P");
            gameController.switchTurn();
            gameController.setPieRule(false);
            pieRuleButton.setEnabled(false);
            pieRuleButton.setIcon(UIConstants.PIE_RULE_DISABLE_ICON);
            updateBoard();
        });
    }

    private class GameListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            JButton source = (JButton) e.getSource();
            for (int i = 0; i < gameModel.getNumHouses(); ++i) {
                if (i == p1EndIndex || i == p2EndIndex) {
                    continue;
                }

                if (source == houseButtons.get(i)) {
                    boolean isValid = gameController.move(i);
                    if (isValid) {
                        if (!gameModel.isGoAgain()) {
                            gameController.switchTurn();

                            if (exchangeThread != null) {
                                exchangeThread.sendMessage(gameController.getSavedMoves());
                            }
                        }
                        updateBoard();
                    }
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

            if (gameController.getRemainingTime() <= 0) {
                timer.stop();
                timer = null;
                disableAllPlayerHouse();

                if(gameController.isMyTurn() && gameController.isServer()){
                    //client is lowser
                    exchangeThread.sendMessage("WINNER");
                    showLoser();
                } else if(gameController.isMyTurn() && gameController.isClient()){
                    //server is winner
                    exchangeThread.sendMessage("TIME");
                }
            }
        }
    }

    public void serverInit(){
        if(gameController.isServer()) {
            exchangeThread.sendMessage("WELCOME");
            exchangeThread.sendMessage(gameController.getGameInfo());
        }
    }

    public void showWinner() {

        String message = "You are the winner\n Enter your name : ";
        String playerName = JOptionPane.showInputDialog(message);
        if(playerName == null || playerName.equals("")){
            playerName = "hidden";
        }
        int score = gameController.getWinnerScore();
        WriteFile.recordScore(playerName,score,gameController.getRoundCounter());
        System.exit(0);
    }

    public void showLoser() {
        String message = "You are the Loser\n Bye";
        JOptionPane.showMessageDialog(null,message);
        System.exit(0);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (gameController.getRoundCounter() == 1 && gameController.isPieRule() && gameController.isMyTurn()) {
            pieRuleButton.setIcon(UIConstants.PIE_RULE_ICON);
            pieRuleButton.setEnabled(true);
        } else {
            pieRuleButton.setEnabled(false);
        }

        if (gameController.isUpdateBoard()) {
            updateBoard();
            gameController.setUpdateBoard(false);
            gameController.resetSavedMoves();
        }

        if(!gameController.isRunning()) {
            exchangeThread.sendMessage(gameController.getSavedMoves());
            exchangeThread.sendMessage("LOSER");
            timer.stop();
            timer = null;
            updateBoard();
            showWinner();
        }

    }

    public void resumeGame() {
        timer.restart();
        gameController.setGamePause(false);
        if(gameController.isMyTurn()) {
            disableOtherPlayerHouse();
        }
        pauseButton.setIcon(UIConstants.PAUSE_GAME_ICON);
    }

    public void pauseGame() {
        timer.stop();
        gameController.setGamePause(true);
        disableAllPlayerHouse();
        pauseButton.setIcon(UIConstants.RESUME_GAME_ICON);
    }

}
