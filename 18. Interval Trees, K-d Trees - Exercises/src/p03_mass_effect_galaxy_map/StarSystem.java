package p03_mass_effect_galaxy_map;

public class StarSystem implements Comparable<StarSystem> {

    private String name;
    private int x;
    private int y;

    public StarSystem(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInside(int x1, int y1, int x2, int y2) {
        return this.x >= x1 && this.x <= x2 && this.y >= y1 && this.y <= y2;
    }

    @Override
    public int compareTo(StarSystem other) {
        if (this.x < other.x) return -1;
        if (this.x > other.x) return +1;
        return Integer.compare(this.y, other.y);
    }
}
