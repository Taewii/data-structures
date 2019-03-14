import hashtable.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class p01_CountSymbols {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HashTable<Character, Integer> table = new HashTable<>();
        char[] text = reader.readLine().toCharArray();

        for (char ch : text) {
            if (!table.containsKey(ch)) {
                table.add(ch, 1);
            } else {
                table.addOrReplace(ch, table.get(ch) + 1);
            }
        }

        List<Character> keys = (List<Character>) table.keys();
        keys.stream().sorted(Comparator.naturalOrder()).forEach(ch -> System.out.printf("%s: %d time/s%n", ch, table.get(ch)));
    }
}
