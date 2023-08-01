package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class MapLayer3 {

    GamePanel gp;
    public int mapTileNum[] [];
    Tile[] layer3Tile; // Store a reference to the tile array

    public MapLayer3(GamePanel gp, Tile[] layer1Tile) {
        this.gp = gp;
        this.layer3Tile = layer1Tile; // Use the same tile array as in TileManager
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/map3.txt");
    }

public void loadMap(String filepath) {
		
		try {
			 InputStream is = getClass().getResourceAsStream(filepath);
		     BufferedReader br = new BufferedReader(new InputStreamReader(is));

		        int col = 0;
		        int row = 0;

		        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

		            String line = br.readLine();

		            while (col < gp.maxWorldCol) {

		                String numbers[] = line.split("\t");
		                int num = Integer.parseInt(numbers[col]);
		                mapTileNum[col][row] = num;
		                col++;
		            }
		            if (col == gp.maxWorldCol) {
		                col = 0;
		                row++;
		            }
		        }
		        br.close();
		        
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            // DRAWS LAYER 3
            int layer3TileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(layer3Tile[layer3TileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
