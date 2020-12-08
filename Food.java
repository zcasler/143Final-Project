import java.awt.*;

// Food object represents the object players are supposed to height
// with their snake to grow in size
public class Food implements Block {
    public int x;
    public int y;
    public Color c;

    // Food takes in a int x and y to construct the food object's 
    // position. And takes in a Color c to assign the food object a color 
    public Food(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    // Draws food object with given x and y coordinates and given width/height
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    // Sets the x value of Food object
    public void setX(int x){ 
        this.x = x;
    }

    // Sets the y value of Food object
    public void setY(int y) { 
        this.y = y;
    }

    // Sets the Color of the Food object
    public void setColor(Color c) {
        this.c = c;
    }

    // Returns the x value of Food object
    public int getX() {
        return x;
    }

    // Returns the y value of Food object
    public int getY() {
        return y;
    }

    // Returns the Color of the Food object
    public Color getColor() {
        return c;
    }

}
