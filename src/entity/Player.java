package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.InputHandler;

public class Player extends Entity
{

    GamePanel gp;
    InputHandler input;

    public Player(GamePanel gp, InputHandler input)
    {

        this.gp = gp;
        this.input = input;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage()
    {
        try
        {
            walk1 = ImageIO.read(getClass().getResourceAsStream("/playerAssets/pencilman_0001.png"));
            walk2 = ImageIO.read(getClass().getResourceAsStream("/playerAssets/pencilman_0002.png"));
            walk3 = ImageIO.read(getClass().getResourceAsStream("/playerAssets/pencilman_0003.png"));
            walk4 = ImageIO.read(getClass().getResourceAsStream("/playerAssets/pencilman_0004.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if (input.upPress == true || input.downPress == true ||input.leftPress == true ||input.rightPress == true){
                if (input.upPress == true)
        {
            direction = "up";
            y = y - speed;
        } else if (input.downPress == true)
        {
            direction = "down";
            y = y + speed;
        } else if (input.leftPress == true)
        {
            direction = "left";
            x = x - speed;
        } else if (input.rightPress == true)
        {
            direction = "right";
            x = x + speed;
        }
        spriteCounter++;
        if (spriteCounter > 5)
        {
            if (spriteNum == 1)
            {
                spriteNum = 2;
            } else if (spriteNum == 2)
            {
                spriteNum = 3;
            } else if (spriteNum == 3)
            {
                spriteNum = 4;
            } else if (spriteNum == 4)
            {
                spriteNum = 1;
            }
         spriteCounter = 0;
        }
        }
        

    }

    public void draw(Graphics2D g2)
    {
        //  g2.setColor(Color.white);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (spriteNum == 1)
        {
            image = walk1;
        }
        if (spriteNum == 2)
        {
            image = walk2;
        }
        if (spriteNum == 3)
        {
            image = walk3;
        }
        if (spriteNum == 4)
        {
            image = walk4;
        }
        
        g2.drawImage(image, x,y, gp.tileSize,gp.tileSize, null);

        // for sprite going up, down, left, and right
//        switch (direction)
//        {
//            case "up";
//                    image = up;
//                            break;
//            case "down"
//                    image = down;
//                            break;
//            case "left"
//                    image = left;
//                            break;
//            case "right"
//                    image = right;
//                            break;
//        }
    }
}
