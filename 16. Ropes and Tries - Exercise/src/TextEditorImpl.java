import java.util.*;
import java.util.stream.Collectors;

public class TextEditorImpl implements TextEditor {

    private final Trie<Deque<StringBuilder>> userStrings;
    private final Map<String, Boolean> users;

    public TextEditorImpl() {
        this.userStrings = new Trie<>();
        this.users = new LinkedHashMap<>();
    }

    @Override
    public void login(String username) {
        this.users.put(username, true);
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder());
        this.userStrings.insert(username, stack);
    }

    @Override
    public void logout(String username) {
        this.users.replace(username, false);
    }

    @Override
    public void prepend(String username, String string) {
        this.insert(username, 0, string);
    }

    @Override
    public void insert(String username, int index, String string) {
        if (this.users.containsKey(username)) {
            Deque<StringBuilder> stack = this.userStrings.getValue(username);
            StringBuilder str = new StringBuilder();
            if (!stack.isEmpty()) {
                str = stack.peek();
            }
            str.insert(index, string);
            stack.push(str);
            this.userStrings.insert(username, stack);
        }
    }

    @Override
    public void substring(String username, int startIndex, int length) {
        if (this.users.containsKey(username)) {
            Deque<StringBuilder> stack = this.userStrings.getValue(username);
            StringBuilder str = stack.peek();
            StringBuilder newStr = new StringBuilder(str.substring(startIndex, startIndex + length));
            stack.push(newStr);
            this.userStrings.insert(username, stack);
        }
    }

    @Override
    public void delete(String username, int startIndex, int length) {
        if (this.users.containsKey(username)) {
            Deque<StringBuilder> stack = this.userStrings.getValue(username);
            StringBuilder str = stack.peek();
            str.delete(startIndex, startIndex + length);
            stack.push(str);
            this.userStrings.insert(username, stack);
        }
    }

    @Override
    public void clear(String username) {
        if (this.users.containsKey(username)) {
            Deque<StringBuilder> stack = this.userStrings.getValue(username);
            stack.push(new StringBuilder());
            this.userStrings.insert(username, stack);
        }
    }

    @Override
    public int length(String username) {
        if (this.users.containsKey(username)) {
            return this.userStrings.getValue(username).peek().length();
        }
        return 0;
    }

    @Override
    public String print(String username) {
        if (this.users.containsKey(username)) {
            return this.userStrings.getValue(username).peek().toString();
        }
        return "";
    }

    @Override
    public void undo(String username) {
        if (this.users.containsKey(username)) {
            this.userStrings.getValue(username).pop();
        }
    }

    @Override
    public Iterable<String> users(String prefix) {
        List<String> users;

        if (prefix == null) {
            users = this.users.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } else {
            users = this.users.entrySet().stream()
                    .filter(u -> u.getKey().startsWith(prefix) && u.getValue())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

        return users;
    }
}
