package kalahProject.AI;

import Controller.GameController;

import java.util.ArrayList;

/**
 * Created by jhinchley on 3/25/17.
 */
public class MinMaxTree {
    MinMaxNode rootNode;
    GameController gameController;
    MinMaxTree(GameController gC){
        gameController = gC;
    }
    public Integer minimax(MinMaxNode node, boolean isMaximizingPlayer){
        //base case at terminal node/leaf
        if(node.isTerminal()){
            return node.getValue();
        }
        //TODO prune the illegal moves
        //maximize the node
        if(isMaximizingPlayer){
            //-infinity
            Integer bestValue = -32000;
            for (int i = 0; i < node.getChildren().size(); i++) {
                //recurse
                Integer val = minimax(node.getChildren().get(i),false);
                //store who has bigger value
                bestValue = max(bestValue,val);
            }
            return bestValue;
        }
        //minimize the node
        else{
            //infinity
            Integer bestValue = 32000;
            for (MinMaxNode child:node.getChildren()) {
                Integer val = minimax(child,true);
                //store who has smaller value
                bestValue = min(bestValue,val);
            }
            return bestValue;
        }
    }
    public Integer max(Integer v1,Integer v2){
        if(v1 > v2 ){
            return v1;
        }
        else{
            return v2;
        }
    }
    public Integer min(Integer v1,Integer v2){
        if(v1 < v2 ){
            return v1;
        }
        else{
            return v2;
        }
    }

    //this only builds one level deep at the moment
    //call it on the node whose children you want to build
    public MinMaxNode buildTree(ArrayList<Integer> gameState, int levelDepth){



        //holds nodes that will become rootNodes children
        ArrayList<MinMaxNode> childNodes = new ArrayList<>();
        if(levelDepth == 0){
            return new MinMaxNode(gameController.getScore(gameState),gameState);
        }

        //creates a child for each possible move and stores gamestate of simulated move in child

        int start = gameController.gameModel().p1StartIndex();
        int end = gameController.gameModel().p1EndIndex();

        for (int i = start; i < end; i++) {
            ArrayList<Integer> simulatedMoves = gameController.simulateMove(i,gameController.gameModel().getHouses());
            MinMaxNode node = new MinMaxNode(gameController.getScore(simulatedMoves),simulatedMoves);
            childNodes.add(i,node);
        }
        //sets rootNodes children
        MinMaxNode newRoot = new MinMaxNode(gameController.getScore(gameState),gameState);
        newRoot.setChildren(childNodes);

        return newRoot;
    }

    public void setRoot(MinMaxNode root) {
        this.rootNode = root;
    }

    public MinMaxNode getRoot() {
        return rootNode;
    }


    public void printTree(MinMaxNode node){
        System.out.println(node.toString());
        System.out.println(node.getChildren());
        for (int i = 0; i < node.getChildren().size(); i++) {
        	if(node.getChildren() != null) {
        		printTree(node.getChildren().get(i));
        	}
        }
    }
}
