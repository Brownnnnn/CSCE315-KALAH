package tools.CommandParser;

import Controller.GameController;
import kalahProject.GameModel;

import java.io.BufferedReader;
import java.util.Scanner;

/**
 * Created by jhinchley on 3/29/17.
 */
public class ServerParser {
    //parse the server
    GameController gameController;
    ServerParser(GameModel gameModel){
        this.gameController = gameController;
    }
    //return response
    String parse(BufferedReader bufferedReader){
        String response = null;
        //read from input
        Scanner scanner = new Scanner("");
        String command;
        if (scanner.hasNext()){
            //get first command
            command = scanner.next();
            switch (command.toUpperCase()){
                case "OK":{
                    //got the AI's move
                    break;
                }
                case "P":{

                    break;
                }
                //parse move or raise an error
                default:
                {
                    try{
                        Integer move = scanner.nextInt();
                        boolean isValid = gameController.move(move);
                        if (isValid){
                            response = "OK";
                            return response;
                        }
                        else{
                            //send a message back that it was illegal move
                            response = "ILLEGAL";
                            return response;
                        }
                    }catch (Exception e){
                        System.out.println("Unknown message!: "+command);
                        e.printStackTrace();
                    }
                    break;

                }
            }
        }

        return response;
    }
}
