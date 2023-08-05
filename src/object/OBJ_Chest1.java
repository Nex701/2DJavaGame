package object;

import java.awt.Color;
import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest1 extends Entity {

	
	public OBJ_Chest1(GamePanel gp) {
		super(gp);
		name = "Chest1";
		down1 = setup("/genesis_tiles/Tile_ID_299");
		collision = true;
	}
	public void draw(Graphics2D g2) {
		
		super.draw(g2);
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        
		if (canInteract && gp.gameState != gp.dialogueState) {
			
            // Draw 'text' above
            g2.setFont(interactionFont);
            g2.setColor(Color.YELLOW);
            int textX = screenX +2;
            int textY = screenY - 8;
            g2.drawString("OPEN \'E\'", textX, textY);
            }
	}

}
