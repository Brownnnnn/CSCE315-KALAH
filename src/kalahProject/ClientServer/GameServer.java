package kalahProject.ClientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jhinchley on 3/23/17.
 */
public class GameServer extends Thread {
    //time is in minutes
    private static ExchangeThread serverExchangeThread;

    public static void run(int PORT) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Server " + PORT + " is running");
            Socket s = ss.accept();
            serverExchangeThread = new ExchangeThread(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExchangeThread getExchangeThread(){
        return serverExchangeThread;
    }

}
