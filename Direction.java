/*
Direction enum represents the 4 possible directions (North, South, East, West)
within the 2D game.
*/
public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);

    private int x;
    private int y;
    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
    Returns x-value of Direction
    */
    public int getX() {
        return this.x;
    }

    /*
    Returns y-value of Direction
    */
    public int getY() {
        return this.y;
    }

    /*
    Returns whether or not this Direction opposes the 
    passed Direction. For example, North opposes South,
    and East opposes West.
    */
    public boolean opposes(Direction d) {
        return this.x + d.x == 0 && this.y + d.y == 0;
    }
}
