import java.awt.*;

public class SnakeNode implements Block {
    private boolean isHead;
    private int x;
    private int y;
    private Direction direction; 
    private SnakeNode next;

    //constructs new SnakeNode object from x and y ints and a given direction
    public SnakeNode(int x, int y, Direction direction, boolean isHead) {
        this.isHead = isHead;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.next = null; 
    }

    //draws a snake node object with passed  x, y, height, and width ints
    public void draw(Graphics g, int x, int y, int width, int height) {
        if(this.isHead) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.GREEN.darker());
        }
        g.fillRect(x, y, width, height);
    }

    //sets x value of SnakeNode
    public void setX(int x){ 
        this.x = x;
    }

    //sets y value of snake node
    public void setY(int y) { 
        this.y = y;
    }

    //sets direction of SnakeNode   
    public void setDirection(Direction direction) { 
        this.direction = direction;
    }

    //returns x value of SnakeNode
    public int getX() { 
        return this.x;
    }    

    //returns y value of SnakeNode
    public int getY() { 
        return this.y;
    }

    //sets direction of SnakeNode
    public Direction getDirection() { 
        return this.direction;
    }

    //returns next SnakeNode of SnakeNode
    public SnakeNode getNext() { 
        return this.next;
    }

    //sets next SnakeNode 
    public void setNext(SnakeNode next) {
        this.next = next;
    }
}
