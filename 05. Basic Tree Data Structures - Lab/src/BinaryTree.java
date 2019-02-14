import java.util.function.Consumer;

public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        this.leftChild = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        builder.append(repeatStr(" ", indent));
        builder.append(this.value).append("\n");

        if (this.leftChild != null) {
            builder.append(this.leftChild.printIndentedPreOrder(indent + 1, new StringBuilder()));
        }

        if (this.rightChild != null) {
            builder.append(this.rightChild.printIndentedPreOrder(indent + 1, new StringBuilder()));
        }

        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
           this.leftChild.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if (this.rightChild != null) {
           this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }
        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }

    private String repeatStr(String str, int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count * 2; i++) {
            text.append(str);
        }
        return text.toString();
    }
}
