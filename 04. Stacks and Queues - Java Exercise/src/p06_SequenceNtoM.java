import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class p06_SequenceNtoM {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Deque<Item> queue = new ArrayDeque<>();
        queue.offer(new Item(nums[0], null));

        while (!queue.isEmpty()) {
            Item item = queue.poll();

            if (item.value == nums[1]) {
                item.print();
                return;
            }

            if (item.value < nums[1]) {
                for (int i = 0; i < 3; i++) {
                    int newVal = item.value;
                    switch (i) {
                        case 0:
                            newVal += 1;
                            break;
                        case 1:
                            newVal += 2;
                            break;
                        case 2:
                            newVal *= 2;
                            break;
                        default:
                            break;
                    }

                    if (newVal == nums[1]) {
                        new Item(newVal, item).print();
                        return;
                    } else if (newVal < nums[1]) {
                        queue.offer(new Item(newVal, item));
                    }
                }
            }
        }
    }
}

class Item {
    Integer value;
    Item previous;

    public Item(Integer value, Item previous) {
        this.value = value;
        this.previous = previous;
    }

    public void print() {
        Deque<Integer> stack = new ArrayDeque<>();
        Item current = this;

        while (current != null) {
            stack.push(current.value);
            current = current.previous;
        }

        StringBuilder sb = new StringBuilder();
        stack.forEach(item -> sb.append(item).append(" -> "));
        System.out.println(sb.toString().substring(0, sb.length() - 4));
    }
}
