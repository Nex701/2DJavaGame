package main;

import entity.NPC_Cat1;
import entity.NPC_Wizard;
import monster.MON_GreenSlime;
import object.OBJ_Chest1;
import object.OBJ_ChestOpen;
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
		
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 62 * gp.tileSize;
		gp.obj[0].worldY = 84 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 61 * gp.tileSize;
		gp.obj[1].worldY = 73 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 49 * gp.tileSize;
		gp.obj[2].worldY = 66 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Coin(gp);
		gp.obj[3].worldX = 63 * gp.tileSize;
		gp.obj[3].worldY = 67 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Coin(gp);
		gp.obj[4].worldX = 50 * gp.tileSize;
		gp.obj[4].worldY = 84 * gp.tileSize;

		gp.obj[5] = new OBJ_Coin(gp);
		gp.obj[5].worldX = 52 * gp.tileSize;
		gp.obj[5].worldY = 73 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Coin(gp);
		gp.obj[6].worldX = 66 * gp.tileSize;
		gp.obj[6].worldY = 74 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Chest1(gp);
		gp.obj[7].worldX = 45 * gp.tileSize;
		gp.obj[7].worldY = 75 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Chest1(gp);
		gp.obj[8].worldX = 24 * gp.tileSize;
		gp.obj[8].worldY = 87* gp.tileSize;
		
		gp.obj[9] = new OBJ_Chest1(gp);
		gp.obj[9].worldX = 54 * gp.tileSize;
		gp.obj[9].worldY = 59* gp.tileSize;
		
		gp.obj[10] = new OBJ_Chest1(gp);
		gp.obj[10].worldX = 57 * gp.tileSize;
		gp.obj[10].worldY = 59* gp.tileSize;
		
		gp.obj[11] = new OBJ_Coin(gp);
		gp.obj[11].worldX = 46 * gp.tileSize;
		gp.obj[11].worldY = 92 * gp.tileSize;
		
		gp.obj[12] = new OBJ_Coin(gp);
		gp.obj[12].worldX = 65 * gp.tileSize;
		gp.obj[12].worldY = 92 * gp.tileSize;
		
		gp.obj[13] = new OBJ_Coin(gp);
		gp.obj[13].worldX = 23 * gp.tileSize;
		gp.obj[13].worldY = 89 * gp.tileSize;
		
		gp.obj[14] = new OBJ_Coin(gp);
		gp.obj[14].worldX = 25 * gp.tileSize;
		gp.obj[14].worldY = 89 * gp.tileSize;
		

		
		
		
		
		
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Cat1(gp);
		gp.npc[0].worldX = 54 * gp.tileSize;
		gp.npc[0].worldY = 66 * gp.tileSize;
		
		gp.npc[1] = new NPC_Cat1(gp);
		gp.npc[1].worldX = 60 * gp.tileSize;
		gp.npc[1].worldY = 74 * gp.tileSize;
		
		gp.npc[2] = new NPC_Cat1(gp);
		gp.npc[2].worldX = 21 * gp.tileSize;
		gp.npc[2].worldY = 76 * gp.tileSize;
		
		gp.npc[3] = new NPC_Cat1(gp);
		gp.npc[3].worldX = 51 * gp.tileSize;
		gp.npc[3].worldY = 84 * gp.tileSize;
		
		gp.npc[4] = new NPC_Cat1(gp);
		gp.npc[4].worldX = 51 * gp.tileSize;
		gp.npc[4].worldY = 84 * gp.tileSize;
		
		gp.npc[5] = new NPC_Wizard(gp);
		gp.npc[5].worldX = 92 * gp.tileSize;
		gp.npc[5].worldY = 94 * gp.tileSize;
	}
	public void setMonster() {
		
		gp.monster[0] = new MON_GreenSlime(gp);
		gp.monster[0].worldX = 28 * gp.tileSize;
		gp.monster[0].worldY = 83 * gp.tileSize;
		
		gp.monster[1] = new MON_GreenSlime(gp);
		gp.monster[1].worldX = 25 * gp.tileSize;
		gp.monster[1].worldY = 83 * gp.tileSize;
		
		gp.monster[2] = new MON_GreenSlime(gp);
		gp.monster[2].worldX = 20 * gp.tileSize;
		gp.monster[2].worldY = 83 * gp.tileSize;
		
		gp.monster[3] = new MON_GreenSlime(gp);
		gp.monster[3].worldX = 36 * gp.tileSize;
		gp.monster[3].worldY = 83 * gp.tileSize;
	}
}	

