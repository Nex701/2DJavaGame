package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import main.UtilityTool;


public class NPC_Cat1 extends Entity {

	private int minX, minY, maxX, maxY;
	
	public NPC_Cat1(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
		
		solidArea = new Rectangle(16, 16, 34, 40);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		//NPC BOUNDRIES
		minX = gp.tileSize* 8;
		minY = gp.tileSize* 62;
		maxX = gp.tileSize* 67;
		maxY = gp.tileSize* 92;
	}
	public void getImage() {
		UtilityTool uTool = new UtilityTool();
		
		up1 = uTool.scaleImage(setupImage("/playerNeko/player_up_1"), 64, 64);
		up2 = uTool.scaleImage(setupImage("/playerNeko/player_up_2"), 64, 64);
		down1 = uTool.scaleImage(setupImage("/playerNeko/player_down_1"), 64, 64);
		down2 = uTool.scaleImage(setupImage("/playerNeko/player_down_2"), 64, 64);
		left1 = uTool.scaleImage(setupImage("/playerNeko/player_left_1"), 64, 64);
		left2 = uTool.scaleImage(setupImage("/playerNeko/player_left_2"), 64, 64);
		right1 = uTool.scaleImage(setupImage("/playerNeko/player_right_1"), 64, 64);
		right2 = uTool.scaleImage(setupImage("/playerNeko/player_right_2"), 64, 64);
		idleUp = uTool.scaleImage(setupImage("/playerNeko/player_up_idle"), 64, 64);
		idleDown = uTool.scaleImage(setupImage("/playerNeko/player_down_idle"), 64, 64);
		idleLeft = uTool.scaleImage(setupImage("/playerNeko/player_left_idle"), 64, 64);
		idleRight = uTool.scaleImage(setupImage("/playerNeko/player_right_idle"), 64, 64);
		
	}
	
	public void update() {

	       setnpcBoundaries(minX, minY, maxX, maxY);
	    }
	
	public void setDialogue() {
    	
    	dialogues[0] = "Meow there! Welcome to our charming village";
        dialogues[1] = "Did you know we have a secret society of cats here? We call it the 'Paw-some Clan'!";
        dialogues[2] = "Pawsitively delightful day, isn't it?";
        dialogues[3] = "In our village, we have a 'Purr-tection Agency' to keep the mice in check!";
        dialogues[4] = "Rumor has it that the castle grounds are guarded by a fierce 'Lion-Knight'. But I'm the 'Purr-ince' of this village!";
        dialogues[5] = "We cats love napping and playing. It's the 'Purr-fect' life, don't you agree?";
        dialogues[6] = "What's a cat's favorite instrument? The 'Purrcussion'!";
        dialogues[7] = "If you're feeling down, just curl up in a sunbeam. It's the ultimate 'Paw-sitive' therapy!";
        dialogues[8] = "Fur-tunate to meet you! Our village is the purr-fect place for some whisker-ful adventures!";
        dialogues[9] = "What's a cat's favorite hobby? 'Purr-suing' adventures, of course!";
        dialogues[10] = "Every day is a 'paw-some' day when you live in a cat village!";
        dialogues[11] = "The castle gates might be closed, but the doors to our village are always open for you!";
        dialogues[12] = "Mew-nificent gardens and cat-tastic views await you!";
        dialogues[13] = "Pawsitively enchanting tales are whispered in the castle at night!";
        dialogues[14] = "They say the castle knights have a secret handshake. I wonder if it's a 'paw-shake'!";
        dialogues[15] = "What's the purr-ose of your visit? Let us know how we can help!";
        dialogues[16] = "Meow-gnificent day, isn't it?";
        dialogues[17] = "Feeling a little stressed? Come to me, and I'll give you some 'purr-therapy'!";
        dialogues[18] = "They say the castle wizard has a magical staff. I have a magical 'paw', and it's just as powerful!";
        dialogues[19] = "Once, I chased a butterfly all the way to the castle gates. I guess you could say I'm a 'fur-ocious' explorer!";
        dialogues[20] = "What's a cat's favorite dessert? Mice cream!";
        dialogues[21] = "What's a cat's favorite dessert? Mice cream!";
    	
    }
	public void speak() {
		
		super.speak();
	}
	public void draw(Graphics2D g2) {
		
		super.draw(g2);
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
		if (canInteract && gp.gameState != gp.dialogueState) {
		
		
        // Draw 'text' above

        g2.setFont(interactionFont);
        g2.setColor(Color.YELLOW);
        int textX = screenX + 17;
        int textY = screenY + 8;
        g2.drawString("Talk \'E\'", textX, textY);
		}
    }
}
	
