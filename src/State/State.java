package State;

public class State {
    private final String name;
    private final int[] downLeft;
    private final int[] upRight;

    public State(String name, int[] downLeft, int[] upRight) {
        this.name = name;
        this.downLeft = downLeft;
        this.upRight = upRight;
    }

    public String getName() {
        return name;
    }

    public int[] getDownLeft() {
        return downLeft;
    }

    public int[] getUpRight() {
        return upRight;
    }

    public boolean isIn(int[] point) {
        return point[0] >= downLeft[0] && point[1] >= downLeft[1] &&
                point[0] <= upRight[0] && point[1] <= upRight[1];
    }
}
