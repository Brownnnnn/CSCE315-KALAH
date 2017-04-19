package kalahProject;

import tools.RandomSplit;

import java.util.ArrayList;

/**
 * Created by jhinchley on 3/14/17
 */
public class GameModel {
    public static final boolean PLAYER1 = false;
    public static final boolean PLAYER2 = true;

    private int numSeeds;
    private int numHouses;

    private boolean goAgain;
    private boolean player;


    //Use a single list contain house and store.
    //Player 1 : 1-5 houses, 6 store	(number represent index)
    //Player 2 : 7-12 houses, 7 store	(number represent index)
    private ArrayList<Integer> houses = new ArrayList<>();


    //default constructor
    public GameModel(int seeds, int houses) {
        this.numSeeds = seeds;
        this.numHouses = houses;
        goAgain = false;
    }


    public void move(int pos) {
        int seeds = houses.get(pos);
        int next = pos + 1;
        int finalPos = pos + seeds;

        goAgain = false;

        //Set clicked house seed to 0
        houses.set(pos, 0);

        doMove(next, seeds, finalPos);

    }

    private void doMove(int curr, int seeds, int finalPos) {
        while (seeds > 0) {
            //each house seeds increment by 1, start over if index greater than size
            if (curr < numHouses) {
                seedIncrement(curr++);
            } else {

                seedIncrement(0);
                curr = 1;
            }

            //skip another player store and update final position
            if (curr == getOtherStoreIndex()) {
                //System.out.println("should not get here");
                curr++;
                finalPos++;
            }
            seeds--;
        }

        checkExtraMove(finalPos);

        //check bonus
        int index = finalPos % numHouses;
        if (isCapture(index)) {
            fixAfterCapture(index);
        }
    }

    /********************************************************************
     * helper function
     ********************************************************************/

    /**
     * distribute random number of seeds
     */

    public void stringDistributedSeed(String[] moves) {
        for(int i=0; i<2; ++i) {
            for (int j = p1StartIndex(); j < p1EndIndex(); ++j) {
                houses.add(Integer.parseInt(moves[j]));
            }
            houses.add(0);
        }
    }
    public String randomDistributeSeed() {
        StringBuilder SB = new StringBuilder();

        int total = numSeeds * ((numHouses - 2) / 2);
        int[] rInt = RandomSplit.doSplit(total, p1EndIndex(), 1);
        for(int i : rInt) {
            SB.append(Integer.toString(i));
            SB.append(" ");
        }
        //initialize houses
        for(int i=0; i<2; ++i) {
            for (int j = p1StartIndex(); j < p1EndIndex(); ++j) {
                houses.add(rInt[j]);
            }
            houses.add(0);
        }
        System.out.println(SB.toString());
        return SB.toString();
    }

    /**
     * distribute fixed number of seeds base on user selection
     */
    public void normalDistributeSeed() {
        for (int i = 0; i < numHouses; i++) {
            houses.add(numSeeds);
        }

        houses.set(p1EndIndex(), 0);
        houses.set(p2EndIndex(), 0);
    }

    /**
     * if the last seed lands in the player's store, the player gets an additional move
     * @param finalPosition
     */
    private void checkExtraMove(int finalPosition) {
        System.out.println("    Final Pos " + finalPosition%7);
        //TODO Do we have better way?
        int mid = numHouses / 2;
            if ((player == PLAYER1 && (finalPosition + 1) % mid == 0 && (((finalPosition + 1) / mid) % 2) != 0) ||
                (player == PLAYER2 && (finalPosition + 1) % mid == 0 && (((finalPosition + 1) / mid) % 2) == 0)) {
            goAgain = true;
        }
    }

    /**
     * @param index
     * @return true if the last seed lands in an empty house owned by the player
     */
    private boolean isCapture(int index) {
        if (index != p1EndIndex() && index != p2EndIndex()) {
            if ((player && index > p1EndIndex()) || (!player && index <= p1EndIndex())) {
                //We do move before checking, if a house at
                if (houses.get(index) == 1 && (houses.get(getOppositeIndex(index)) != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * if isCapture function return true and both the last seed and the opposite seeds
     * are captured and placed into the player's store.
     * @param index
     */
    private void fixAfterCapture(int index) {
        int store = getStoreIndex();
        //add 1 because last seed also count as bouns
        int bonus = houses.get(getOppositeIndex(index)) + 1;
        houses.set(store, houses.get(store) + bonus);
        houses.set(getOppositeIndex(index), 0);
        houses.set(index, 0);
    }

    /**
     * swap two player seed number
     */
    public void swapPlayerHouses() {
        ArrayList<Integer> temp = new ArrayList<>(houses.subList(p1StartIndex(), p1EndIndex() + 1));

        for (int i = p1StartIndex(), j = p2StartIndex(); i <= p1EndIndex(); ++i, ++j) {
            houses.set(i, houses.get(j));
        }

        for (int i = p2StartIndex(), j = p1StartIndex(); i <= p2EndIndex(); ++i, ++j) {
            houses.set(i, temp.get(j));
        }
    }


    /**
     * if bottom is winner, calculating top player score ant set its houses to 0
     * @return true if all of bottom houses empty
     */
    private boolean isBottomWin() {
        for (int i = p1StartIndex(); i < p1EndIndex(); ++i)
            if (houses.get(i) > 0) return false;

        //
        for (int i = p2StartIndex(); i < p2EndIndex(); i++) {
            houses.set(p2EndIndex(), houses.get(p2EndIndex()) + houses.get(i));
            houses.set(i, 0);
        }
        return true;
    }

    /**
     * if top is winner, calculating bottom player score ant set its houses to 0
     * @return true if all of top houses empty
     */
    private boolean isTopWin() {
        for (int i = p2StartIndex(); i < p2EndIndex(); ++i)
            if (houses.get(i) > 0) return false;


        for (int i = p1StartIndex(); i < p1EndIndex(); i++) {
            houses.set(p1EndIndex(), houses.get(p1EndIndex()) + houses.get(i));
            houses.set(i, 0);
        }
        return true;
    }


    /**
     * increase seed number by 1
     * @param index
     */
    private void seedIncrement(int index) {
        houses.set(index, houses.get(index) + 1);
    }

    /********************************************************************
     * Setters
     ********************************************************************/

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public void setGoAgain(boolean goAgain) {
        this.goAgain = goAgain;
    }

    /********************************************************************
     * Getters
     ********************************************************************/

    public boolean isGameOver() {
        return (isTopWin() || isBottomWin());
    }

    public boolean isGoAgain() {
        return goAgain;
    }

    public ArrayList<Integer> getHouses() {
        return houses;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public int getNumSeeds() {
        return numSeeds;
    }

    public boolean getPlayer() {
        return player;
    }

    public int getStoreIndex() {
        return player ? p2EndIndex() : p1EndIndex();
    }

    public int getOtherStoreIndex() {
        return player ? p1EndIndex() : p2EndIndex();
    }

    private int getOppositeIndex(int index) {
        return numHouses - 2 - index;
    }

    public int getSeedByIndex(int index) {
        return houses.get(index);
    }

    public int p1StartIndex() {
        return 0;
    }

    public int p1EndIndex() {
        return numHouses / 2 - 1;
    }

    public int p2StartIndex() {
        return p1EndIndex() + 1;
    }

    public int p2EndIndex() {
        return numHouses - 1;
    }

    public int p1Score() {
        return houses.get(p1EndIndex());
    }

    public int p2Score() {
        return houses.get(p2EndIndex());
    }

    public void setHouses(ArrayList<Integer> houses) {
        System.out.println();
        for (int i = 0; i < getNumHouses(); i++) {
            this.houses.set(i,houses.get(i)) ;
        }
    }

}


