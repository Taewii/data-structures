public class AStar {

    public AStar(char[][] map) {
        throw new UnsupportedOperationException();
    }

    public static int getH(Node current, Node goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        throw new UnsupportedOperationException();
    }
}
