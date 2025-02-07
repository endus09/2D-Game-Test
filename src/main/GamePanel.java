package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;

// by endus
public class GamePanel extends JPanel implements Runnable
{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 5;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    InputHandler input = new InputHandler();
    Thread gameThread;
    TileManager tileM = new TileManager(this);
    Player player = new Player(this, input);



    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
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

    // choose a gameloop (preference on second one)
    @Override
//    public void run()
//    {
//        double drawInterval = 1000000000 / FPS; // 1/60th of a second
//        double nextDraw = System.nanoTime() + drawInterval;               // using nanoseconds for accuracy
//
//        while (gameThread != null)
//        {
//
//            // update: update information such as character positions
//            update();
//
//            // draw: draw the screen with the updated information
//            repaint();
//
//            try
//            {
//                double timeBuffer = nextDraw - System.nanoTime();
//                timeBuffer = timeBuffer / 1000000;                  // converting to miliseconds
//                if (timeBuffer < 0)
//                {
//                    timeBuffer = 0;
//                }
//                Thread.sleep((long) timeBuffer);
//
//                nextDraw = nextDraw + drawInterval;
//
//            } catch (InterruptedException ex)
//            {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//    }
    public void run()
    {
        double drawInterval = 1000000000 / FPS;    // 1/60th of a second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1)
            {

                //update: update information such as character positions
                update();

                // draw: draw the screen with the updated information
                repaint();
                delta--;
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        
        g2.dispose();
    }
}
