import hashtable.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p02_Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HashTable<String, String> phonebook = new HashTable<>();

        String input;
        while (!"search".equals(input = reader.readLine())) {
            String[] params = input.split("-");
            phonebook.add(params[0], params[1]);
        }

        while (!"end".equals(input = reader.readLine())) {
            if (phonebook.containsKey(input)) {
                System.out.printf("%s -> %s%n", input, phonebook.get(input));
            } else {
                System.out.printf("Contact %s does not exist.%n", input);
            }
        }
    }
}
