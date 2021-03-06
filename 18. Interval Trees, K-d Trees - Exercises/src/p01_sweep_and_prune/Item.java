package p01_sweep_and_prune;

public class Item {
    private String id;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Item(String id, int x1, int y1) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + 10;
        this.y2 = y1 + 10;
    }

    public boolean intersect(Item that){
        return this.x1 <= that.x2 &&
                this.x2 >= that.x1 &&
                this.y1 <= that.y2 &&
                this.y2 >= that.y1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
        this.x2 = x1 + 10;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
        this.y2 = y1 + 10;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}