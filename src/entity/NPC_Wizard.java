package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.UtilityTool;


public class NPC_Wizard extends Entity {

	private int minX, minY, maxX, maxY;
	
	public NPC_Wizard(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
		
		solidArea = new Rectangle(32, 32, 32, 36);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		//NPC BOUNDRIES
		minX = gp.tileSize* 88;
        minY = gp.tileSize* 93;
        maxX = gp.tileSize* 96;
        maxY = gp.tileSize* 98;
	}
	public void getImage() {
		
		UtilityTool uTool = new UtilityTool();
		
		up1 = uTool.scaleImage(setupImage("/npc/up_01"), 96, 112);
		up2 = uTool.scaleImage(setupImage("/npc/up_02"), 96, 112);
		down1 = uTool.scaleImage(setupImage("/npc/down_01"), 96, 112);
		down2 = uTool.scaleImage(setupImage("/npc/down_02"), 96, 112);
		left1 = uTool.scaleImage(setupImage("/npc/left_01"), 96, 112);
		left2 = uTool.scaleImage(setupImage("/npc/left_02"), 96, 112);
		right1 = uTool.scaleImage(setupImage("/npc/right_01"), 96, 112);
		right2 = uTool.scaleImage(setupImage("/npc/right_02"), 96, 112);
		idleUp = uTool.scaleImage(setupImage("/npc/idle_up"), 96, 112);
		idleDown = uTool.scaleImage(setupImage("/npc/idle_down"), 96, 112);
		idleLeft = uTool.scaleImage(setupImage("/npc/idle_left"), 96, 112);
		idleRight = uTool.scaleImage(setupImage("/npc/idle_right"), 96, 112);
		
		
	}
	
	public void update() {

       setnpcBoundaries(minX, minY, maxX, maxY);
    }
	
	public void setDialogue() {
    	
		dialogues[0] = "You're the first traveler to stumble upon my dwelling in a long time.";
		dialogues[1] = "The forest whispers of your arrival. You have a unique fate, my friend.";
		dialogues[2] = "The forest can be treacherous, but you emerged unscathed. A testament to your skills!";
		dialogues[3] = "Ah, it's not every day I get visitors. What brings you to this mystical place?";
		dialogues[4] = "The forest guards its secrets well, but you managed to find your way. Fascinating!";
		dialogues[5] = "My little corner in the woods is a haven for knowledge and magic. How did you discover it?";
		dialogues[6] = "I sense an aura of determination about you. Not many possess such resolve!";
		dialogues[7] = "Curious, curious indeed! The forest's magic must have guided you here.";
		dialogues[8] = "Ah, an adventurer in these treacherous woods? Impressive!";
		dialogues[9] = "Welcome, traveler, to my humble abode. You've braved quite the forest to find me!";
		dialogues[10] = "Ah, fate has brought you to my door. What mysteries do you seek in this place?";
		dialogues[11] = "Ah, an adventurer in these treacherous woods? Impressive!";
		dialogues[12] = "Step closer, adventurer. Let me see the wonders you carry from the forest's depths.";
		dialogues[13] = "In these enchanted woods, few dare to venture. Yet here you stand!";
		dialogues[14] = "In these ancient woods, one's destiny often intertwines with forgotten powers.";
    	
    	
    }
	public void speak() {
		
		super.speak();
	}
	public void draw(Graphics2D g2) {
		
		super.draw(g2);
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        //HITBOX DEBUG
        /*int rectX = screenX + solidArea.x;
        int rectY = screenY + solidArea.y;
        int rectWidth = solidArea.width;
        int rectHeight = solidArea.height;

        g2.setColor(Color.white);
        g2.fillRect(rectX, rectY, rectWidth, rectHeight);*/
        
		if (canInteract && gp.gameState != gp.dialogueState) {
            // Draw 'E' above
            g2.setFont(interactionFont);
            g2.setColor(Color.YELLOW);
            int textX = screenX + 64;
            int textY = screenY + 16;
            g2.drawString("E", textX, textY);
            }
	}
}
	
