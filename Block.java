import java.awt.*;

/*
Block interface represents a single square in the
game display board.
*/
public interface Block {

    /*
    Returns x-value of Block.
    */
    int getX();
    /*
    Returns y-value of Block.
    */
    int getY();

    /*
    Draws Block in passed position with passed width and height.
    */
    void draw(Graphics g, int x, int y, int width, int height);

    /*
    Draws default Block using passed position, width, and height.
    */
    static void draw_default(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
