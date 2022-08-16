package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    char[][] tileMap = new char[3200][3200];


    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        loadMap();

        tile = new Tile[10];


        getTileImage();

    }

    public void loadMap(){
        try{
            InputStream inputStream = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (row < gamePanel.maxScreenRow){
                String map = bufferedReader.readLine();
                while (col < gamePanel.maxScreenCol){
                    char indexOfTile = map.charAt(col);

                    //System.out.println(row + " " + col + " " + indexOfTile);
                    tileMap[row][col] = map.charAt(col);
                    //System.out.println(tileMap[row][col]);
                    col++;
                }
                row++;
                col = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(ClassLoader.getSystemResourceAsStream("tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(ClassLoader.getSystemResourceAsStream("tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(ClassLoader.getSystemResourceAsStream("tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(ClassLoader.getSystemResourceAsStream("tiles/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        Tile currentTile = tile[0];

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            if (tileMap[row][col] == 'a'){
                currentTile = tile[0];
            } if (tileMap[row][col] == 'b'){
                currentTile = tile[1];
            }if (tileMap[row][col] == 'c'){
                currentTile = tile[2];
            }if (tileMap[row][col] == 'd'){
                currentTile = tile[3];
            }

            g2.drawImage(currentTile.image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            x += gamePanel.tileSize;
            col++;

            if (col == gamePanel.maxScreenCol){
                col = 0;
                x = 0;
                y += gamePanel.tileSize;
                row++;
            }
        }


    }
}
