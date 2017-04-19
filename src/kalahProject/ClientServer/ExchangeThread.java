package kalahProject.ClientServer;

import Controller.GameController;
import UI.AppMainWindow;
import UI.Panel.GamePanelOnline;
import kalahProject.AI.AI;
import kalahProject.GameModel;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by XiaoyuLi on 3/29/2017.
 */

public class ExchangeThread implements Runnable {
    private Socket socket;
    private GameController gameController;
    private GamePanelOnline gamePanelOnline;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    public ExchangeThread(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(this).start();
    }


    public void run() {
        try {
            while(true) {
                String message = bufferedReader.readLine();

                //check if message only ASCII character
                if(!message.matches("\\A\\p{ASCII}*\\z")){
                    //break;
                }

                //Print received message
                System.out.println("MESSAGE : " + message);

                String[] commands = message.split(" ");

                switch (commands[0]) {
                    case "WINNER" :
                    {
                        gamePanelOnline.showWinner();
                        break;
                    }

                    case "LOSER" :
                    {
                        gamePanelOnline.showLoser();
                        break;
                    }

                    case "TIME" :
                    {
                        sendMessage("LOSER");
                        break;
                    }
                    case "BEGIN" :
                    {
                        //both player will receive this message and update board at beginning
                        if(gameController.isMyTurn()) {
                            gamePanelOnline.updateBoard();
                        } else {
                            sendMessage("BEGIN");
                        }
                        break;
                    }
                    case "READY" :
                    {
                        sendMessage("BEGIN");
                        break;
                    }
                    case "PAUSE" :
                    {
                        gamePanelOnline.pauseGame();
                        break;
                    }
                    case "RESUME" :
                    {
                        gamePanelOnline.resumeGame();
                        break;
                    }
                    case "INFO" :
                    {

                        int houses = Integer.parseInt(commands[1])*2+2;
                        int seeds = Integer.parseInt(commands[2]);
                        int time = Integer.parseInt(commands[3]);
                        boolean myTurn = commands[4].equals("F");
                        boolean randomize = commands[5].equals("R");
                        String[] moves = new String[commands.length - 1 - 5];
                        System.arraycopy( commands, 6, moves, 0, moves.length );

                        GameModel gameModel = new GameModel(seeds,houses);
                        GameController gameController;

                        if(randomize) {
                            gameController = new GameController(gameModel,myTurn,false,randomize,true,AI.level,moves);
                        } else{
                            gameController = new GameController(gameModel,myTurn,false,randomize,true,AI.level);
                        }


                        AppMainWindow.mainPanelCenter.removeAll();
                        AppMainWindow.gamePanel = new GamePanelOnline(gameModel,gameController,GameClient.getExchangeThread());
                        AppMainWindow.mainPanelCenter.add(AppMainWindow.gamePanel, BorderLayout.CENTER);
                        AppMainWindow.mainPanelCenter.updateUI();
                        sendMessage("READY");

                        break;
                    }
                    case "P" :
                    {
                        gameController.gameModel().swapPlayerHouses();
                        gameController.switchTurn();
                        gameController.setUpdateBoard(true);
                        break;
                    }
                    case "ILLEGAL" :
                    {

                    }
                    default:
                    {
                        //Move
                        if(isNum(commands[0])) {
                            gameController.gameModel().swapPlayerHouses();
                            for(String index : commands) {
                                System.out.println("    Opponent moved " + index);
                                System.out.println("    Seed Count " + gameController.seedCount(Integer.parseInt(index)));
                                gameController.move(Integer.parseInt(index));
                            }
                            gameController.gameModel().swapPlayerHouses();

                            if(!gameController.gameModel().isGoAgain()) {
                                gameController.switchTurn();
                            }

                            if(gameController.getAILevel() > 0) {
//                                gameController.resetSavedMoves();
                                gameController.aiMove(0,6);
                                System.out.println("    AI moved " + gameController.getSavedMoves());
                                sendMessage(gameController.getSavedMoves());
                            }
                            gameController.setUpdateBoard(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(" servers run exception: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("servers finally exception:" + e.getMessage());
                }
            }
        }
    }

    public void sendMessage(String str){
        // write
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    public void setGamePanelOnline(GamePanelOnline gamePanelOnline) {this.gamePanelOnline = gamePanelOnline; }
}