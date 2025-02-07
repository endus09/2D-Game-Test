// superclass of all variables that will be used in player, monster and NPC classes

package entity;

import java.awt.image.BufferedImage;


public class Entity
{
    public int x, y;
    public int speed;
    
    public BufferedImage walk1, walk2, walk3, walk4;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
