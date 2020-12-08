import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.LinkedList;

/*
Snake object represents the player of the game, which
has functionality to change direction based on keyboard input.
*/
public class Snake implements KeyListener { 
    private Display display;
    private SnakeNode head;
    private SnakeNode tail;
    private Color color;
    private Queue<Direction> moveQueue;
    private int foodEaten;

    /*
    Constructs a Snake object and initializes necessary fields.
    */
    public Snake(Display display, int x, int y, int length, Direction direction, Color color) {
        this.display = display;
        this.display.addKeyListener(this);
        this.moveQueue = new LinkedList<Direction>();
        this.head = new SnakeNode(x, y, direction, true);
        this.foodEaten = length;
        this.tail = this.head;
    }

    /*
    Resets Snake position to starting position 
    and length to 5.
    */
    public void reset() {
        this.killSnake(this.head);
        this.head = new SnakeNode(32, 18, Direction.NORTH, true);
        this.tail = head;
        this.foodEaten = 5;
    }
    
    /*
    Updates the position and direction of the Snake based
    on user keyboard input.
    */
    public void update() {
        // Check if head is on food
        updateSnake(this.head, this.moveQueue.poll());
        if(this.foodEaten > 0) {
            int tailX = this.tail.getX();
            int tailY = this.tail.getY();
            Direction tailDirection = this.tail.getDirection();
            this.tail.setNext(new SnakeNode(tailX - tailDirection.getX(), 
                                        tailY - tailDirection.getY(), tailDirection, false));
            this.tail = this.tail.getNext();
            this.foodEaten--;
        }
    }

    /*
    Sets all references to SnakeNodes in Snake to null
    */
    public void killSnake(SnakeNode node) {
        if(node == null) {
            return;
        }
        this.display.setBlock(node.getX(), node.getY(), null);
        killSnake(node.getNext());
    }

    /*
    Returns whether given position is an instance of Food 
    on the playing field.
    */
    private boolean isOnFood(int x, int y) {
        return this.display.getBlock(x, y) instanceof Food;
    }

    /*
    Returns whether given position is an instance of SnakeNode
    in the playing field.
    */
    private boolean isOnSnakeNode(int x, int y) {
        return this.display.getBlock(x, y) instanceof SnakeNode;
    }

    /*
    Moves each SnakeNode object within the Snake based on user keyboard input.
    */
    private void updateSnake(SnakeNode node, Direction previousDirection) {  
        if(node != null) {
            Direction oldDirection = node.getDirection();
            Direction newDirection;
            if(previousDirection == null || previousDirection.opposes(oldDirection)) {
                newDirection = oldDirection;
            } else {
                newDirection = previousDirection;
            }
            int newX = node.getX() + newDirection.getX();
            int newY = node.getY() + newDirection.getY();

            if(newX >= Display.WIDTH || newX < 0 || newY >= Display.HEIGHT || newY < 0
                        || this.isOnSnakeNode(newX, newY)) {
                this.display.setRunning(false);
                this.reset();
                return;
            }

            if(this.isOnFood(newX, newY)) {
                this.display.createFood();
                this.foodEaten = 3;
            }

            this.display.setBlock(node.getX(), node.getY(), null);
            node.setX(newX);
            node.setY(newY);
            this.display.setBlock(node.getX(), node.getY(), node);

            node.setDirection(newDirection);
            updateSnake(node.getNext(), oldDirection);
        }
    }

    /*
    Overrides and implements the keyPressed method to
    track the keyboard input of the user.
    */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Direction d = null;
        switch(keyCode) {
            case KeyEvent.VK_RIGHT:
                d = Direction.EAST;
                break;
            case KeyEvent.VK_LEFT:
                d = Direction.WEST;
                break;
            case KeyEvent.VK_DOWN:
                d = Direction.SOUTH;
                break;
            case KeyEvent.VK_UP:
                d = Direction.NORTH;
                break;
            case KeyEvent.VK_SPACE:
                this.display.start();
                break;
            default:
                System.out.println("Invalid key pressed");
        }
        if(d != null && this.moveQueue.size() < 5) {
            this.moveQueue.add(d);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
