import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

/*
Display represents a JPanel which implements the Runnable interface.
This class will handle creating, updating, and drawing the various objects 
within the game.
*/
public class Display extends JPanel implements Runnable {
    private final int updatesPerSecond = 10;
    public static final int HEIGHT = 36;
    public static final int WIDTH = 64;

    private Thread thread;
    private boolean running;
    private Block[][] blocks;
    private Snake snake;
    private Random r;

    /*
    Constructs a Display object, which will initialize
    the necessary fields.
    */
    public Display() {
        this.setFocusable(true);
        this.blocks = new Block[WIDTH][HEIGHT];
        this.snake = new Snake(this, 32, 18, 5, Direction.NORTH, Color.BLACK);
        this.r = new Random();
        this.createFood();
    }

    /*
    Sets new random position for Food object
    */
    public void createFood() {
        int x = r.nextInt(WIDTH);
        int y = r.nextInt(HEIGHT);
        while(this.blocks[x][y] instanceof SnakeNode) {
            x = r.nextInt(WIDTH);
            y = r.nextInt(HEIGHT);
        }
        Color c = Color.RED;
        this.blocks[x][y] = new Food(x, y, c);
    }

    /*
    Sets running boolean
    */
    public void setRunning(boolean b) {
        this.running = b;
    }
    
    /*
    Initializes and begins the thread which
    handles updating and drawing.
    */
    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    /*
    Updates the states of each object within
    the game.
    */
    private void update() {
        //update snake position.
        this.snake.update();
    }

    /*
    Draws the background and each object within the game
    on the JPanel.
    */
    @Override
    public void paintComponent(Graphics g) {
        if(this.running) {
            int sideLength = this.getHeight() / this.blocks[0].length;
            for(int i = 0; i < this.blocks.length; i++) {
                for(int j = 0; j < this.blocks[0].length; j++) {
                    if(this.blocks[i][j] != null) {
                        this.blocks[i][j].draw(g, i * sideLength, j * sideLength, sideLength, sideLength);
                    } else {
                        Block.draw_default(g, i * sideLength, j * sideLength, sideLength, sideLength);
                    }
                }
            }
        } else {
            Font font = new Font("Serif", 200, 50);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("Click spacebar to begin", 145, this.getHeight()/2);
        }
    }

    /*
    Main loop of game which handles updating and repainting
    the JPanel.
    */
    @Override
    public void run() {
        long updateTime = 0;
        long targetUpdateTime = (long) 1000 / this.updatesPerSecond;
        this.running = true;
        while(this.running) {
            if(System.currentTimeMillis() - updateTime >= targetUpdateTime) {
                //update snakes, food, etc.
                this.update();
                this.repaint();
                updateTime = System.currentTimeMillis();
            }
            long waitTime = targetUpdateTime - (System.currentTimeMillis() - updateTime);
            if(waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    Sets the Block at the given position to a given
    Block.
    */
    public void setBlock(int x, int y, Block b) {
        this.blocks[x][y] = b;
    }

    /*
    Returns the Block at given position.
    */
    public Block getBlock(int x, int y) {
        return this.blocks[x][y];
    }
}
