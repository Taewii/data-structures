import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TextEditor editor = new TextEditorImpl();
        StringBuilder output = new StringBuilder();

        String input;
        while (!"end".equals(input = reader.readLine())) {
            String[] params = input.split("\\s+");

            switch (params[0]) {
                case "login":
                    editor.login(params[1]);
                    break;
                case "logout":
                    editor.logout(params[1]);
                    break;
                case "users":
                    Iterable<String> usersToPrint;
                    if (params.length == 1) {
                        usersToPrint = editor.users(null);
                    } else {
                        usersToPrint = editor.users(params[1]);
                    }
                    usersToPrint.forEach(user -> output.append(user).append(System.lineSeparator()));
                    break;
            }

            if (params.length == 1) {
                continue;
            }

            switch (params[1]) {
                case "insert":
                    String string = input.split("\"")[1];
                    editor.insert(params[0], Integer.parseInt(params[2]), string);
                    break;
                case "prepend":
                    String str = input.split("\"")[1];
                    editor.prepend(params[0], str);
                    break;
                case "substring":
                    editor.substring(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                    break;
                case "delete":
                    editor.delete(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                    break;
                case "clear":
                    editor.clear(params[0]);
                    break;
                case "print":
                    output.append(editor.print(params[0])).append(System.lineSeparator());
                    break;
                case "length":
                    output.append(editor.length(params[0])).append(System.lineSeparator());
                    break;
                case "undo":
                    editor.undo(params[0]);
                    break;
            }
        }
        System.out.println(output.toString());
    }
}
