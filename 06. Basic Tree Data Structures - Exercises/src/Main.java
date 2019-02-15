import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Map<Integer, Tree<Integer>> nodeByValue = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()) - 1;

        while (n-- > 0) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            addEdge(tokens[0], tokens[1]);
        }

//        Problem 1
//        System.out.printf("Root node: %d", getRootValue().value);

//        Problem 2
//        Tree<Integer> root = getRootValue();
//        System.out.println(printTree(root, new StringBuilder(), 0));

//        Problem 3
//        System.out.printf("Leaf nodes: %s", Arrays.toString(getLeafNodes()).replaceAll("[\\[\\],]", ""));

//        Problem 4
//        System.out.printf("Middle nodes: %s", Arrays.toString(getMiddleNodes()).replaceAll("[\\[\\],]", ""));

//        Problem 5
//        System.out.printf("Deepest node: %d", getDeepestNode().value);

//        Problem 6
//        System.out.print("Longest path: ");
//        printPath(getDeepestNode());

//        Problem 7
//        int target = Integer.parseInt(reader.readLine());
//        List<Tree<Integer>> leafs = new LinkedList<>();
//        getPathsWithSum(getRootValue(), 0, target, leafs);
//        System.out.printf("Paths of sum %d:%n", target);
//        leafs.forEach(Main::printPath);

//        Problem 8 80/100
//        int target = Integer.parseInt(reader.readLine());
//        List<Tree<Integer>> parents = new LinkedList<>();
//        getSubtreesWithAGivenSum(getRootValue(), 0, target, parents);
//        System.out.printf("Subtrees of sum %d:%n", target);
//        parents.forEach(Main::printPreOrder);
    }

    private static void printPreOrder(Tree<Integer> node) {
        System.out.print(node.value + " ");
        node.children.forEach(Main::printPreOrder);
    }

    private static int getSubtreesWithAGivenSum(Tree<Integer> node, int current, int target, List<Tree<Integer>> roots) {
        if (node == null) {
            return 0;
        }

        current = node.value;
        for (Tree<Integer> child : node.children) {
            current += getSubtreesWithAGivenSum(child, current, target, roots);
        }

        if (current == target) {
            roots.add(node);
        }

        return current;
    }

    private static void printPath(Tree<Integer> leaf) {
        Deque<Integer> stack = new ArrayDeque<>();
        Tree<Integer> current = leaf;
        while (current != null) {
            stack.push(current.value);
            current = current.parent;
        }

        System.out.println(stack.toString().replaceAll("[\\[\\],]", ""));
    }

    private static void getPathsWithSum(Tree<Integer> node, int current, int target, List<Tree<Integer>> leafs) {
        if (node == null) {
            return;
        }

        current += node.value;
        if (current == target) {
            leafs.add(node);
        }

        for (Tree<Integer> child : node.children) {
            getPathsWithSum(child, current, target, leafs);
        }
    }

    private static Tree<Integer> getDeepestNode() {
        Deque<Tree<Integer>> stack = new ArrayDeque<>();
        stack.push(getRootValue());

        Tree<Integer> deepestNode = null;

        while (!stack.isEmpty()) {
            Tree<Integer> current = stack.pop();

            if (current != null && current.children.size() > 0) {
                deepestNode = current.children.get(0);

                for (Tree<Integer> child : current.children) {
                    stack.push(child);
                }
            }
        }

        return deepestNode;
    }

    private static int[] getMiddleNodes() {
        return nodeByValue.values()
                .stream()
                .filter(node -> node.parent != null && node.children.size() != 0)
                .mapToInt(node -> node.value)
                .sorted()
                .toArray();
    }

    private static int[] getLeafNodes() {
        return nodeByValue.values()
                .stream()
                .filter(node -> node.children.size() == 0)
                .mapToInt(node -> node.value)
                .sorted()
                .toArray();
    }

    private static String printTree(Tree<Integer> current, StringBuilder builder, int indentation) {
        builder.append(repeatStr(" ", indentation)).append(current.value).append(System.lineSeparator());
        for (Tree<Integer> child : current.children) {
            printTree(child, builder, indentation + 1);
        }

        return builder.toString();
    }

    private static Tree<Integer> getRootValue() {
        return nodeByValue.values().stream().filter(node -> node.parent == null).findFirst().orElse(null);
    }

    private static Tree<Integer> getNodeByValue(int value) {
        nodeByValue.putIfAbsent(value, new Tree<>(value));
        return nodeByValue.get(value);
    }

    private static void addEdge(int parent, int child) {
        Tree<Integer> parentNode = getNodeByValue(parent);
        Tree<Integer> childNode = getNodeByValue(child);

        parentNode.children.add(childNode);
        childNode.parent = parentNode;
    }

    private static String repeatStr(String str, int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count * 2; i++) {
            text.append(str);
        }
        return text.toString();
    }
}