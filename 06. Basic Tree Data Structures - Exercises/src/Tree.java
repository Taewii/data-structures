import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    T value;
    Tree<T> parent;
    List<Tree<T>> children;

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }
}
