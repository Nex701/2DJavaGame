package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[] [];
	private int[] collisionTiles = {7, 8, 9, 10, 11, 12, 32, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 57, 58, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 81, 82, 83, 84, 85, 86, 87, 88, 90, 91, 92, 94, 95, 96, 97, 98, 99, 104, 105, 106, 107, 108, 109, 110, 111, 112, 120, 121, 123, 124, 129, 130, 131, 132, 133, 134, 135, 136, 137, 155, 156, 157, 158, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 205, 206, 207, 208, 213, 215, 216, 217, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 236, 237, 238, 245, 246, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 261, 263, 275, 276, 277, 282, 286, 287, 288, 290, 291, 295, 296, 297, 299, 300, 301, 302, 303, 304, 306, 307, 315, 316, 317, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 331, 340, 341, 342, 345, 346, 347, 348, 349, 350, 352, 358, 365, 366, 370, 371, 372, 373, 374, 375, 377, 383, 384, 385, 386, 387, 388, 390, 394, 395, 396, 397, 398, 399, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 416, 417, 419, 425, 426, 427, 434, 435, 436, 440, 443, 444, 450, 451, 452, 453, 454, 456, 457, 458, 459, 460, 461, 462, 463, 465, 475, 476, 477, 478, 479, 481, 482, 483, 484, 485, 486, 487, 488, 490, 491, 495, 496, 497, 498, 499, 500, 502, 506, 507, 508, 509, 510, 511, 512, 513, 515, 520, 521, 522, 523, 524, 525, 527, 537, 538, 540, 541, 542, 543, 544, 545, 546, 549, 556, 559, 560, 575, 576, 577, 635};
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
	
		tile = new Tile[700];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map1_Floor.txt");
	}
	
	public void getTileImage() {
		
		try {
	        for (int i = 0; i < 650; i++) {
	            tile[i] = new Tile();
	            String imagePath = String.format("/genesis_tiles/Tile_ID_%03d.png", i);
	            tile[i].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
	            
	            for (int collisionTile : collisionTiles) {
                    if (i == collisionTile) {
                        tile[i].collision = true;
                        break; // No need to continue the loop if the collision is set
                    	}
	            	}
	        	}
			}
	        catch(IOException e) {
			e.printStackTrace();
		}
	}
	//TILE 6 USED FOR TRANSPARENT TILES
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
	        // DRAWS LAYER 1
	        int tileNum = mapTileNum[worldCol][worldRow];
	        int worldX = worldCol * gp.tileSize;
	        int worldY = worldRow * gp.tileSize;
	        int screenX = worldX - gp.player.worldX + gp.player.screenX;
	        int screenY = worldY - gp.player.worldY + gp.player.screenY;

	        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

	 
	            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	        }

	        worldCol++;

	        if (worldCol == gp.maxWorldCol) {
	            worldCol = 0;
	            worldRow++;
	        }
	    }
	}
}


















