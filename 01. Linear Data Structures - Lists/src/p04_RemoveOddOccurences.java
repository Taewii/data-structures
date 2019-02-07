import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p04_RemoveOddOccurences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> sequence = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        Map<Integer, Integer> occurrences = new LinkedHashMap<>();

        for (int number : sequence) {
            occurrences.putIfAbsent(number, 0);
            occurrences.put(number, occurrences.get(number) + 1);
        }

        for (int number : sequence) {
            if (occurrences.get(number) % 2 == 0) {
                System.out.print(number + " ");
            }
        }
    }
}
