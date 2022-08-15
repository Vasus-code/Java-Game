package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2 * gamePanel.scale;

        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(ClassLoader.getSystemResourceAsStream("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch (IOException e){
           e.printStackTrace();
        }

    }

    public void update(){
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
            animateAfterframes = 15;
        } else {
            animateAfterframes = 55;
        }

        if (keyHandler.upPressed){
            y -= speed;
            direction = "up";
        }
        if (keyHandler.downPressed){
            y += speed;
            direction = "down";
        }
        if (keyHandler.rightPressed){
            x += speed;
            direction = "right";
        }
        if (keyHandler.leftPressed){
            x -= speed;
            direction = "left";
        }

        animate(animateAfterframes);
    }

    public void animate(int animateAfterFrames){
        spriteCounter++;
        if (spriteCounter > animateAfterFrames){
            if (spriteNum == 1)spriteNum = 2;
            else if (spriteNum == 2)spriteNum = 1;
            spriteCounter = 0;

        }
    }

    public  void draw(Graphics2D g2){
        BufferedImage currentImage = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    currentImage = up1;
                }
                if (spriteNum == 2){
                    currentImage = up2;
                }
                break;

            case "down":
                if (spriteNum == 1){
                    currentImage = down1;
                }
                if (spriteNum == 2){
                    currentImage = down2;
                }
                break;

            case "left":
                if (spriteNum == 1){
                    currentImage = left1;
                }
                if (spriteNum == 2){
                    currentImage = left2;
                }
                break;

            case "right":
                if (spriteNum == 1){
                    currentImage = right1;
                }
                if (spriteNum == 2){
                    currentImage = right2;
                }
                break;
        }

        g2.drawImage(currentImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
