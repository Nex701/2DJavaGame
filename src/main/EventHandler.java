package main;


public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].height = eventRect[col][row].x;
			eventRect[col][row].width = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}	
	}
	
	public void checkEvent() {
		
		//CHECK IF PLAYER IS ONE TIME AWAY FROM THE LAST EVENT
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
				
		if(canTouchEvent == true) {
			if(hit(49,92,"any") == true) { damagePit(49,92,gp.dialogueState);}
			if(hit(43,75, "any") == true) { healingPool(43,75,gp.dialogueState);}
		}
		
	}
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
	public void damagePit(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You tripped and took 1 Damage!!";
		gp.player.life -= 1;
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	public void healingPool(int col, int row, int gameState) {
		
		if(gp.keyH.ePressed == true) {
		gp.gameState = gameState;
		
			if(gp.player.life != gp.player.maxLife) {
				gp.player.life += 1;
				gp.ui.currentDialogue = "You heal 1hp";
				}
				else {
					gp.ui.currentDialogue = "You already have max hp";
				}
		}
	}
}
