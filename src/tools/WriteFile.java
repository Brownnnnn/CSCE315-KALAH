package tools;

import java.io.*;

/**
 * Created by XiaoyuLi on 3/28/2017.
 */
public class WriteFile {
    public static void recordScore(String playerName, Integer score, Integer moveCount) {
        String path = System.getProperty("user.dir") + File.separator + "assets" + File.separator + "highscores.txt";
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
                String lines[] = ReadFile.scan("assets/highscores.txt").split("\n");
                //TODO reevaluate highscores
                //give the winner the next highest rank
                int rank = lines.length+1;
                out.print(rank+" "+playerName+" "+score+" "+moveCount+"\n");
        } catch (IOException e) {
            System.out.println("fail ");

            // do something
        }
    }
}
