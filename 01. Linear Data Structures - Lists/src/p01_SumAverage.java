import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p01_SumAverage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> nums = Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());
        int sum = nums.stream().mapToInt(Integer::parseInt).sum();
        double average = nums.stream().mapToInt(Integer::parseInt).average().getAsDouble();

        System.out.printf("Sum=%d; Average=%.2f", sum, average);
    }
}
