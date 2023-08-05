package main;

import java.awt.Rectangle;

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
	    int entityTopRow = entityTopWorldY / gp.tileSize;

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

		    for (int i = 0; i < gp.obj.length; i++) {
		        if (gp.obj[i] != null) {
		            // Get entity's solid area position
		            entity.solidArea.x = entity.worldX + entity.solidArea.x;
		            entity.solidArea.y = entity.worldY + entity.solidArea.y;
		            // Get object's solid area position
		            gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
		            gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

		            // Check if the entity is in an idle direction
		            boolean isIdle = entity.direction.equals("upIdle") || entity.direction.equals("downIdle") ||
		                             entity.direction.equals("leftIdle") || entity.direction.equals("rightIdle");
		            Rectangle tempSolidArea = new Rectangle(entity.solidArea);

		            if (isIdle) {
		                switch (entity.direction) {
		                    case "upIdle":
		                        tempSolidArea.y -= entity.speed;
		                        break;
		                    case "downIdle":
		                        tempSolidArea.y += entity.speed;
		                        break;
		                    case "leftIdle":
		                        tempSolidArea.x -= entity.speed;
		                        break;
		                    case "rightIdle":
		                        tempSolidArea.x += entity.speed;
		                        break;
		                }
		                if (tempSolidArea.intersects(gp.obj[i].solidArea)) {
		                    if (gp.obj[i].collision == true) {
		                        entity.collisionOn = true;
		                    }
		                    if (player == true) {
		                        index = i;
		                    }
		                }
		            } else {
		                // Check for collision while moving
		                switch (entity.direction) {
		                    case "up":
		                        entity.solidArea.y -= entity.speed;
		                        break;
		                    case "down":
		                        entity.solidArea.y += entity.speed;
		                        break;
		                    case "left":
		                        entity.solidArea.x -= entity.speed;
		                        break;
		                    case "right":
		                        entity.solidArea.x += entity.speed;
		                        break;
		                }

		                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
		                    if (gp.obj[i].collision == true) {
		                        entity.collisionOn = true;
		                    }
		                    if (player == true) {
		                        index = i;
		                    }
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
	//NPC OR MONSTER
	public int checkEntity(Entity entity, Entity[] target) {
	 
		 int index = 999;

		    for (int i = 0; i < target.length; i++) {
		        if (target[i] != null) {
		            // Get entity's solid area position
		            entity.solidArea.x = entity.worldX + entity.solidArea.x;
		            entity.solidArea.y = entity.worldY + entity.solidArea.y;
		            // Get object's solid area position
		            target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
		            target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

		            // Check if the entity is in an idle direction
		            boolean isIdle = entity.direction.equals("upIdle") || entity.direction.equals("downIdle") ||
		                             entity.direction.equals("leftIdle") || entity.direction.equals("rightIdle");
		            Rectangle tempSolidArea = new Rectangle(entity.solidArea);

		            if (isIdle) {
		                switch (entity.direction) {
		                    case "upIdle":
		                        tempSolidArea.y -= entity.speed;
		                        break;
		                    case "downIdle":
		                        tempSolidArea.y += entity.speed;
		                        break;
		                    case "leftIdle":
		                        tempSolidArea.x -= entity.speed;
		                        break;
		                    case "rightIdle":
		                        tempSolidArea.x += entity.speed;
		                        break;
		                }
		                if (tempSolidArea.intersects(target[i].solidArea)) {
		                	if(target[i] != entity) {
		                	entity.collisionOn = true;
		                    index = i;
		                	}
		                }
		            } else {
		                // Check for collision while moving
		                switch (entity.direction) {
		                    case "up":
		                        entity.solidArea.y -= entity.speed;
		                        break;
		                    case "down":
		                        entity.solidArea.y += entity.speed;
		                        break;
		                    case "left":
		                        entity.solidArea.x -= entity.speed;
		                        break;
		                    case "right":
		                        entity.solidArea.x += entity.speed;
		                        break;
		                }

		                if (entity.solidArea.intersects(target[i].solidArea)) {
		                	if(target[i] != entity) {
		                    entity.collisionOn = true;
		                    index = i;
		                	}
		                }
		            }

		            entity.solidArea.x = entity.solidAreaDefaultX;
		            entity.solidArea.y = entity.solidAreaDefaultY;
		            target[i].solidArea.x = target[i].solidAreaDefaultX;
		            target[i].solidArea.y = target[i].solidAreaDefaultY;
		        }
		    }
		    return index;
		}
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		//get object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up": entity.solidArea.y -= entity.speed; break;
		case "down": entity.solidArea.y += entity.speed; break;
		case "left": entity.solidArea.x -= entity.speed; break;
		case "right": entity.solidArea.x += entity.speed; break;
		}
		
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
		
	}
	// Method to set the canInteract variable for Entities
    
    public int checkInteraction(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // Calculate the center position of the entity's solid area
                int entityCenterX = entity.worldX + entity.solidArea.x + entity.solidArea.width / 2;
                int entityCenterY = entity.worldY + entity.solidArea.y + entity.solidArea.height / 2;

                // Calculate the center position of the target's solid area
                int targetCenterX = target[i].worldX + target[i].solidArea.x + target[i].solidArea.width / 2;
                int targetCenterY = target[i].worldY + target[i].solidArea.y + target[i].solidArea.height / 2;

                // Calculate the distance between the centers of the two entities
                double distance = Math.sqrt(Math.pow(targetCenterX - entityCenterX, 2) + Math.pow(targetCenterY - entityCenterY, 2));

                // Check if the distance is within the interaction range
                if (distance <= 65) {
                    index = i;
                    // Set the canInteract property for the NPC
                    target[i].setCanInteract(true);
                    break; 
                } else {
                    // Set the canInteract property to false for targets outside the range
                    target[i].setCanInteract(false);
                }
            }
        }

        return index;
    }
}
	