
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

// by endus
public class GamePanel extends JPanel implements Runnable
{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    InputHandler input = new InputHandler();
    Thread gameThread;

    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 100, 0));
        this.setDoubleBuffered(true);
        this.setFocusable(true);  // make sure you set this so the program can focus on inputs
        this.addKeyListener(input);  // adding a key listener is important
    }

    public void startGameThread()
    {
        this.requestFocusInWindow();  // this requests for extra focus on the game window
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS; // 1/60th of a second
        double nextDraw = System.nanoTime() + drawInterval;               // using nanoseconds for accuracy

        while (gameThread != null)
        {

            // update: update information such as character positions
            update();

            // draw: draw the screen with the updated information
            repaint();

            try
            {
                double timeBuffer = nextDraw - System.nanoTime();
                timeBuffer = timeBuffer / 1000000;                  // converting to miliseconds
                if (timeBuffer < 0)
                {
                    timeBuffer = 0;
                }
                Thread.sleep((long) timeBuffer);

                nextDraw = nextDraw + drawInterval;

            } catch (InterruptedException ex)
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void update()
    {
        if (input.upPress == true)
        {
            playerY = playerY - playerSpeed;
        } else if (input.downPress == true)
        {
            playerY = playerY + playerSpeed;
        } else if (input.leftPress == true)
        {
            playerX = playerX - playerSpeed;
        } else if (input.rightPress == true)
        {
            playerX = playerX + playerSpeed;
        }
    }

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
