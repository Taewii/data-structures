import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class p02_CalculateSequenceWithAQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(n);

        int index = 0;
        int[] numbers = new int[50];

        while (index < 50) {
            int num = queue.poll();
            numbers[index++] = num;

            queue.offer(num + 1);
            queue.offer(2 * num + 1);
            queue.offer(num + 2);
        }

        System.out.println(Arrays.toString(numbers).replaceAll("[\\[\\]]", ""));
    }
}
