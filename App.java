import javax.swing.JFrame;

/*
App represents the class which will create and run
the general display of the program.
*/
public class App {
    private JFrame frame;
    private Display display;

    /*
    Constructs an App object which will set up and create the
    display as per passed width and height parameters representing 
    pixels.
    */
    public App(int width, int height) {
        this.frame = new JFrame("Snake Game");
        this.display = new Display();
    
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().add(display);

        this.frame.pack();
        this.frame.setSize(width, height);
        this.frame.setEnabled(true);
        this.frame.setVisible(true);
    }

    /*
    Creates an App object which will run the display.
    */
    public static void main(String[] args) {
        App app = new App(960, 540);
    }
}
