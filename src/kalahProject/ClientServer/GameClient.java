package kalahProject.ClientServer;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by jhinchley on 3/23/17.
 */
public class GameClient {
    private static ExchangeThread clientExchangeThread;

    public static void run(int PORT){
        try {
            Socket socket = new Socket("localhost",PORT);
            System.out.println("Client IP: "+socket.getLocalAddress()+" port: "+socket.getPort());
            clientExchangeThread=new ExchangeThread(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ExchangeThread getExchangeThread(){
        return clientExchangeThread;
    }
}
