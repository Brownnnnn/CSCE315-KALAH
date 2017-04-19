package kalahProject.AI;

import java.util.ArrayList;

/**
 * Created by jhinchley on 3/25/17.
 */

//
public class MinMaxNode {

    private ArrayList<Integer> boardState;

    //holds values
    private Integer value;

    //maps a move to the next board state
    private ArrayList<MinMaxNode> children;
    
    //constructor
    public MinMaxNode() {
    	boardState = null;
    	value = 0;
    	children = new ArrayList<>();
    }
    
    public MinMaxNode(Integer val, ArrayList<Integer> bs) {
    	boardState = bs;
    	value = val;
    	//System.out.println("here");
    }


    public boolean isTerminal() {
        //if node has no children
        return children == null;
    }

    public Integer getValue() {
        return value;
    }

    public ArrayList<MinMaxNode> getChildren() {
        return children;
    }

    public void setBoardState(ArrayList<Integer> boardState) {
        this.boardState = boardState;
    }

    public void setChildren(ArrayList<MinMaxNode> children) {
        this.children = children;
    }
    //the bottom leaf of a tree should hold how many seeds each player has scored


    @Override
    public String toString() {
        String output = "Value:" +String.valueOf(value);
        return output;
    }

}
