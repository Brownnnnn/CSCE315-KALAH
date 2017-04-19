package Controller;

import UI.UIConstants;
import kalahProject.AI.AI;
import kalahProject.GameModel;

import java.util.ArrayList;
import java.util.Observable;

import static kalahProject.GameModel.PLAYER1;
import static kalahProject.GameModel.PLAYER2;

/**
 * Created by xiaoyu on 3/31/17.
 */

public class GameController extends Observable {

    private GameModel gameModel;
    private AI computer;

    private int roundCounter;
    private int remainingTime;

    public static boolean running = false;

    private boolean pieRule;
    private boolean randomize;

    private boolean myTurn;
    private boolean gamePause;
    private boolean updateBoard;
    private boolean server;
    private boolean winner;

    private String randomSeeds = " ";

    private int AILevel;

    private String savedMoves;

    public GameController(GameModel gameModel, boolean myTurn, boolean server, boolean randomize, boolean pieRule, int AIlevel) {
        this.gameModel = gameModel;
        this.myTurn = myTurn;
        this.server = server;
        this.randomize = randomize;
        this.pieRule = pieRule;
        this.AILevel = AIlevel;
        if(randomize) {
            randomSeeds = gameModel.randomDistributeSeed();
        } else {
            gameModel.normalDistributeSeed();
        }

        running = true;
        roundCounter = 0;
        savedMoves = "";
        remainingTime = UIConstants.TIME_PER_ROUND;
    }

    public GameController(GameModel gameModel, boolean myTurn, boolean server, boolean randomize, boolean pieRule, int AIlevel, String[] moves) {
        this.gameModel = gameModel;
        this.myTurn = myTurn;
        this.server = server;
        this.randomize = randomize;
        this.pieRule = pieRule;
        this.AILevel = AIlevel;

        gameModel.stringDistributedSeed(moves);
        running = true;
        roundCounter = 0;
        savedMoves = "";
        remainingTime = UIConstants.TIME_PER_ROUND;
    }


    public void aiMove(int begin, int end) {

        computer.run(begin,end);
        savedMoves = computer.getSavedMoves();

    }
    public boolean move(int index) {
        if(seedCount(index) == 0) return false;

        gameModel.move(index);
        savedMoves += index + " ";

        if (gameModel.isGameOver()) {
            running = false;
        }

        if(!gameModel.isGoAgain()) {
            roundIncrement();
        }

        setChanged();
        notifyObservers();
        return true;
    }

    public ArrayList<Integer> simulateMove(int index,ArrayList<Integer> gameState){
        //save state


        ArrayList<Integer> savedHouses = new ArrayList<>();
        for (int i = 0; i < gameModel.getNumHouses(); i++) {
            savedHouses.add(i,gameModel.getHouses().get(i));
        }
        gameModel().setHouses(gameState);
        //simulate a move
        gameModel.move(index);
        //save that new board state

        ArrayList<Integer> simulatedHouses = new ArrayList<>();
        for (int i = 0; i < gameModel.getNumHouses(); i++) {
            simulatedHouses.add(i,gameModel.getHouses().get(i));
        }
        //restore it to before simulation
        gameModel.setHouses(savedHouses);
        return simulatedHouses;
    }
    public Integer getScore(ArrayList<Integer> houses){
        System.out.println(houses.get(gameModel.p1EndIndex()));
        System.out.println(houses.get(gameModel.p2EndIndex()));
        return houses.get(gameModel.p1EndIndex()) - houses.get(gameModel.p2EndIndex());
    }

    public void countDown() {
        remainingTime--;
    }

    public void roundIncrement() {
        roundCounter++;
    }

    public void switchTurn() {
        myTurn = !myTurn;
        setChanged();
        notifyObservers();
    }

    public void switchPlayer() {
        gameModel.setPlayer(!gameModel.getPlayer());
    }

    public void setGamePause(boolean gamePause) {
        this.gamePause = gamePause;
    }

    public void setRandomSeeds(String randomSeeds) {
        this.randomSeeds = randomSeeds;
    }
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void setPieRule(boolean pieRule) {
        this.pieRule = pieRule;
    }
    public void createAI(){
        computer = new AI(this);
        computer.setController(this);
    }

    public void setAILevel(int level) {
        AILevel = level;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }


    public void setUpdateBoard(boolean updateBoard) {
        this.updateBoard = updateBoard;
        setChanged();
        notifyObservers();
    }

    public void setServer(boolean server){ this.server = server; }

    public boolean isServer() {
        return server;
    }
    public boolean isClient() {
        return !server;
    }
    public boolean isGamePause() {
        return gamePause;
    }
    public boolean isPieRule() { return pieRule; }
    public boolean isRandomize() { return randomize; }

    public boolean isRunning() {
        return running;
    }

    public boolean isUpdateBoard() {
        return updateBoard;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public boolean currentPlayer() {
        return gameModel.getPlayer();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public int getAILevel() {
        return AILevel;
    }

    public String getSavedMoves() {
        return savedMoves;
    }

    public void resetSavedMoves() {
        savedMoves = "";
    }

    public int seedCount(int index) {
        return gameModel.getSeedByIndex(index);
    }

    public boolean getWinner() {
        return gameModel.p1Score() > gameModel.p2Score() ? PLAYER1 : PLAYER2;
    }

    public int getWinnerScore() {
        return getWinner() ? gameModel.p2Score() : gameModel.p1Score();
    }

    public GameModel gameModel(){
        return gameModel;
    }

    public String getGameInfo() {
        String seeds = Integer.toString(gameModel.getNumSeeds());
        String houses = Integer.toString((gameModel.getNumHouses()-2)/2);
        String random = randomize ? "R" : "S";
        String time = Integer.toString(UIConstants.TIME_PER_ROUND);
        String turn = myTurn ? "S" : "F";
        return "INFO " + houses + " " + seeds + " " + time + " " + turn + " " + random  + " " + randomSeeds;
    }

    public boolean isWinner() {
        return winner;
    }
}
