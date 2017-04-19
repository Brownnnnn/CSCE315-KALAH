package tools;

import java.util.Arrays;
import java.util.Random;

/**
 * @author XiaoyuLi
 * @
 */
public class RandomSplit {

    public static int[] doSplit(int total, int size, int min) {
        System.out.println(size);
        Random rand = new Random();
        int[] result = new int[size];
        int[] randomNumbers = new int[size + 1];

        randomNumbers[0] = 0;
        for (int i = 0; i < size - 1; ++i) {
            randomNumbers[i] = rand.nextInt(total);
        }
        randomNumbers[size] = total;

        Arrays.sort(randomNumbers);

        for (int i = 0; i < size; ++i) {
            result[i] = Math.abs(randomNumbers[i] - randomNumbers[i + 1]);
        }

        return result;
    }
}
