import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p03_LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] sequence = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int bestNum = 0;
        int bestLength = 0;

        int currentNum = sequence[0];
        int currentLength = 1;

        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] == currentNum) {
                currentLength++;
            } else {
                if (currentLength > bestLength) {
                    bestNum = currentNum;
                    bestLength = currentLength;
                }
                currentNum = sequence[i];
                currentLength = 1;
            }
        }

        if (currentLength > bestLength) {
            bestNum = currentNum;
            bestLength = currentLength;
        }

        for (int i = 0; i < bestLength; i++) {
            System.out.print(bestNum + " ");
        }
    }
}
