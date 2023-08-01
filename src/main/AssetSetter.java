package main;

import object.OBJ_Chest1;
import object.OBJ_Coin;
import object.OBJ_Devskull;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 62 * gp.tileSize;
		gp.obj[0].worldY = 84 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 61 * gp.tileSize;
		gp.obj[1].worldY = 73 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = 59 * gp.tileSize;
		gp.obj[2].worldY = 81 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 62 * gp.tileSize;
		gp.obj[3].worldY = 89 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Devskull();
		gp.obj[4].worldX = 57 * gp.tileSize;
		gp.obj[4].worldY = 84 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Coin();
		gp.obj[5].worldX = 50 * gp.tileSize;
		gp.obj[5].worldY = 84 * gp.tileSize;

		gp.obj[6] = new OBJ_Coin();
		gp.obj[6].worldX = 52 * gp.tileSize;
		gp.obj[6].worldY = 73 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Coin();
		gp.obj[7].worldX = 66 * gp.tileSize;
		gp.obj[7].worldY = 74 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Chest1();
		gp.obj[8].worldX = 45 * gp.tileSize;
		gp.obj[8].worldY = 75 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Chest1();
		gp.obj[9].worldX = 24 * gp.tileSize;
		gp.obj[9].worldY = 87* gp.tileSize;
		
	}
}	
