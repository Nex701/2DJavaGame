package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
	    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
	    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
	    int entityTopWorldY = entity.worldY + entity.solidArea.y;
	    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

	    int entityLeftCol = entityLeftWorldX / gp.tileSize;
	    int entityRightCol = entityRightWorldX / gp.tileSize;
	    int entityTopRow = entityTopWorldY / gp.tileSize;
	    int entityBottomRow = entityBottomWorldY / gp.tileSize;

	    int[][][] mapLayers = {gp.tileM.mapTileNum, gp.mapLayer2.mapTileNum, gp.mapLayer3.mapTileNum}; // Add more layers

	    for (int layer = 0; layer < mapLayers.length; layer++) {
	        int row, col;
	        switch (entity.direction) {
	            case "up":
	                row = (entityTopWorldY - entity.speed)/gp.tileSize;
	                col = entityLeftCol;
	                break;
	            case "down":
	                row = (entityBottomWorldY + entity.speed)/gp.tileSize;
	                col = entityLeftCol;
	                break;
	            case "left":
	                row = entityTopRow;
	                col = (entityLeftWorldX - entity.speed)/gp.tileSize;
	                break;
	            case "right":
	                row = entityTopRow;
	                col = (entityRightWorldX + entity.speed)/gp.tileSize;
	                break;
	            default:
	                // Invalid direction, so no collision check needed
	                entity.collisionOn = false;
	                return;
	        }

	        // Check if the calculated row and col are within the bounds of the map
	        if (row >= 0 && row < gp.maxWorldRow && col >= 0 && col < gp.maxWorldCol) {
	            int tileNum = mapLayers[layer][col][row];
	            if (gp.tileM.tile[tileNum].collision) {
	                entity.collisionOn = true;
	                return; // Exit the method early if a collision is detected on any layer
	            }
	        }
	    }

	    // If no collision is detected on any layer, set collisionOn to false
	    entity.collisionOn = false;
	}

	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
						break;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
					}
				}
				return index;
		}
	}
	