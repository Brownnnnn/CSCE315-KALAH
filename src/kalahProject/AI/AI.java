package kalahProject.AI;

import Controller.GameController;
import UI.UIConstants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by jhinchley on 3/25/17.
 */

public class AI{
    public static int level;
    private GameController gameController;
    private String savedMoves = "";
    private MinMaxTree tree;
    public AI(GameController gameController){
        setController(gameController);
        System.out.println("AI CREATED");

        //System.out.println(gameController);
        ArrayList<Integer> gamestate = gameController.gameModel().getHouses();
        //System.out.println(gamestate);

        //System.out.println(tree);

        if (level == 3){
            tree = new MinMaxTree(gameController);
            MinMaxNode n = tree.buildTree(gamestate,1);
            tree.setRoot(n);
        }
//


//        tree.printTree(tree.getRoot());
        //System.out.println("past root");
    }

    public void run(int begin, int end) {
        resetSavedMoves();
        while(gameController.isMyTurn()) {
            if(!gameController.isRunning()) break;

            switch (level) {
                case 1 : gameController.move(getRandomMove(begin,end)); break;
                case 2 : gameController.move(getGreedy(begin,end)); break;
                case 3 : gameController.move(getMinimax(begin,end)); break;
                default: throw new NoSuchElementException();
            }
            if (!gameController.gameModel().isGoAgain()) {
                gameController.switchTurn();
            }
        }
        gameController.setUpdateBoard(true);
    }

    //Valid moves for the AI, if we want to do one for first player with a hint button, just
    //change player index for player 1
    private boolean validMoves(int index) {
        return gameController.seedCount(index) > 0;
    }

    //produces random number between the bounds of possible moves, but repeats until it
    //until the number represents a valid move
    private int getRandomMove(int p2StartIndex, int p2EndIndex) {
        boolean validMove = false;
        int min = p2StartIndex;
        int max = p2EndIndex;
        int randomNum = 0;
        Random rand = new Random();

        while(!validMove) {
            System.out.println("**************************");
            randomNum = rand.nextInt(max-min) + min;
            System.out.println("The random number is " + randomNum);
            validMove = validMoves(randomNum);
            System.out.println("Valid Move is " + validMove);
            System.out.println(gameController.seedCount(randomNum));
            System.out.println("**************************");
        }
        /**
         * REMEMBER TO ADD THIS AT END OF YOU MINIMAX
         */
        savedMoves += Integer.toString(randomNum) + " ";
        return randomNum;

    }

    private int getGreedy(int p2StartIndex, int p2EndIndex) {
        //find possible moves
        LinkedList<Integer> validMoves = new LinkedList<Integer>();
        for(int i = p2StartIndex; i < p2EndIndex; i++) {
            if(gameController.seedCount(i) != 0) {
                validMoves.addLast(i);
            }
        }
        //find possible outcomes from possible moves
        LinkedList<Integer> outcomes = new LinkedList<Integer>();
        for(int i : validMoves) {
            //simulate moves
            //store amount of seeds added to bank, store -1 if move allows for another move
            //check if move ends in bank
            int finalPos = i + gameController.seedCount(i);

            if(finalPos == p2EndIndex) {
                outcomes.addLast(-1);
            }
            //check if move passes bank
            else if(finalPos > p2EndIndex) {
                outcomes.addLast(1);
            }
            //move does not increase bank
            else {
                outcomes.addLast(0);
            }
        }

        //choose move with best outcome, -1 has priority, then highest value has next priority
        int bestMove = outcomes.getFirst();
        for(int i = 0; i < outcomes.size(); i++) {
            if(outcomes.get(i) == -1) {
                bestMove = i;
                break;
            }
            else if(outcomes.get(i) == 1) {
                bestMove = i;
            }
            else if(outcomes.get(i) == 0 && bestMove != 1 && bestMove != -1) {
                bestMove = i;
            }
        }

        /**
         * REMEMBER TO ADD THIS AT END OF YOU MINIMAX
         */
        savedMoves += Integer.toString(validMoves.get(bestMove)) + " ";
        return validMoves.get(bestMove);
    }

    public int getMinimax(int p2StartIndex, int p2EndIndex){
        int bestMove = 0;
        /**
         * REMEMBER TO ADD THIS AT END OF YOU MINIMAX
         */
        ArrayList<Integer> gamestate = gameController.gameModel().getHouses();
        System.out.println(tree.getRoot().toString());
        bestMove = tree.minimax(tree.getRoot(), true);
        System.out.println("myMove: "+bestMove);
        if(!validMoves(bestMove)) {
        	bestMove = getGreedy(p2StartIndex, p2EndIndex);
        }
        savedMoves += Integer.toString(bestMove) + " ";
        return bestMove;
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public void resetSavedMoves() {
        savedMoves = "";
    }

    public String getSavedMoves() {
        return savedMoves;
    }

    public GameController getGameController() {
        return gameController;
    }
}
