//package tools.CommandParser;
//
//import kalahProject.GameModel;
//
//import java.io.BufferedReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * Created by jhinchley on 3/29/17.
// */
//public class ClientParser {
//    //parse the server
////    GameModel aiModel;
////    ClientParser(GameModel aiModel){
////        this.aiModel = aiModel;
////    }
//    public static String parse(BufferedReader bufferedReader){
//        String response = null;
//        //read from input
//        Scanner scanner = new Scanner("");
//        String command;
//        if (scanner.hasNext()){
//            //get first command
//            command = scanner.next();
//            switch (command.toUpperCase()){
//                //parse info command
//                case "INFO":{
//                    //do some parsing for game config
//                    //these fields are always there or error
//                    int holesPerSide = scanner.nextInt();
//                    int seedsPerHole = scanner.nextInt();
//                    long timePerTurn = scanner.nextLong();
//                    String playerStr = scanner.next();
//                    String configOption = scanner.next();
//                    boolean isFirstPlayer = false;
//                    boolean isStandardConfig = true;
//                    if(playerStr.equals("F")){
//                        isFirstPlayer = true;
//                    }
//                    if (configOption.equals("R")){
//                        isStandardConfig = false;
//                    }
//                    //TODO change false to PLAYER 2
//                    aiModel = new GameModel();
//                    if (!isStandardConfig){
//                        ArrayList<Integer> houseConfig = new ArrayList<>();
//                        while (scanner.hasNext()){
//                            houseConfig.add(scanner.nextInt());
//                        }
//                        //aiModel.(houseConfig);
//                    }
//
//
//                    break;
//                }
//                //parse acknowledge commands
//                case "WELCOME":
//                case "READY":
//                case "OK":
//                case "ILLEGAL":
//                case "TIME":
//                case "LOSER":
//                case "WINNER":
//                case "TIE":
//                {
//
//                    break;
//                }
//
//                //parse move or raise an error
//                default:
//                {
//
//                }
//            }
//        }
//
//        return response;
//    }
//}
