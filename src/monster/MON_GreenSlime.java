package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity {
	
	private BufferedImage[] walkUpImages = new BufferedImage[4];
    private BufferedImage[] walkDownImages = new BufferedImage[4];
    private BufferedImage[] walkLeftImages = new BufferedImage[4];
    private BufferedImage[] walkRightImages = new BufferedImage[4];
    private BufferedImage death, deathLeft;
    
	private int frameCounter = 0;
    private final int maxFrames = 4;

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Green Slime";
		speed = 1;
		maxLife = 3;
		life = maxLife;
		
		solidArea = new Rectangle(3, 18, 42, 30);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		
		for (int i = 0; i < 4; i++) {
	        walkUpImages[i] = uTool.scaleImage(setup("/monster/slimeright_0" + i), 48, 48);
	        walkDownImages[i] = uTool.scaleImage(setupImage("/monster/slimeright_0" + i), 48, 48);
	        walkLeftImages[i] = uTool.scaleImage(setupImage("/monster/slimeleft_0" + i), 48, 48);
	        walkRightImages[i] = uTool.scaleImage(setupImage("/monster/slimeright_0" + i), 48, 48);
	    }
		
			death = uTool.scaleImage(setup("/monster/slimedeath"), 48, 48);
			deathLeft =	uTool.scaleImage(setup("/monster/slimedeathLeft"), 48, 48);
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if (collisionOn) {
	            switch (direction) {
	                case "up":
	                    direction = "down";
	                    break;
	                case "down":
	                    direction = "up";
	                    break;
	                case "left":
	                    direction = "right";
	                    break;
	                case "right":
	                    direction = "left";
	                    break;
	            }
	        } else {
	            // If there's no collision, change direction randomly
	            if (i <= 25 && !direction.equals("up")) {
	                direction = "up";
	            } else if (i <= 50 && !direction.equals("down")) {
	                direction = "down";
	            } else if (i <= 75 && !direction.equals("left")) {
	                direction = "left";
	            } else if (!direction.equals("right")) {
	                direction = "right";
	            }
	        }
			
			actionLockCounter = 0;
		}
	}
	public void update() {
		super.update();
		
		spriteCounter++;
		if (spriteCounter > 8) {
	        frameCounter++;
	        if (frameCounter >= maxFrames) {
	            frameCounter = 0;
	        }
	        spriteCounter = 0;
	    }
	}
	
public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + solidArea.width > gp.player.worldX - gp.player.screenX &&
                worldX - solidArea.width < gp.player.worldX + gp.player.screenX &&
                worldY + solidArea.height > gp.player.worldY - gp.player.screenY &&
                worldY - solidArea.height < gp.player.worldY + gp.player.screenY) {

        	switch(direction) {
    		case "up":
    			image = walkUpImages[frameCounter];
    			break;
    		case "down":
    			image = walkDownImages[frameCounter];
    			break;
    		case "left":
    			image = walkLeftImages[frameCounter];
    			break;
    		case "right":
    			image = walkRightImages[frameCounter];
    			break;
    		case "rightIdle":
    			image = walkRightImages[1];
    			break;
    		case "leftIdle":
    			image = walkLeftImages[1];
    			break;
    		case "upIdle":
    			image = walkUpImages[1];
    			break;
    		case "downIdle":
    			image = walkDownImages[1];
    			break;
    			
    		}
        	
        	//MONSTER HP BAR
        	if(type == 2 && hpBarOn == true) {
        		
        		double oneScale = (double)gp.tileSize/maxLife;
        		double gpBarValue = oneScale*life;
        		
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        	g2.setColor(new Color(35,35,35));
        	g2.fillRoundRect(screenX-1, screenY-1, gp.tileSize+2, 9, 7, 7);
        		
        	g2.setColor(new Color(255,0,30));
        	g2.fillRoundRect(screenX, screenY, (int)gpBarValue, 7, 7, 7);
        	
        	hpBarCounter++;
        	
        	if(hpBarCounter == 600) {
        		hpBarCounter = 0;
        		hpBarOn=false;
        		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        	}
        }
        	
        	if(invincible == true) {
        		hpBarOn = true;
        		hpBarCounter = 0;
        		changeAlpha(g2,0.4F);
    		}
 
        	if(dying == true) {
        		dyingAnimation(g2);
        	}
        	
        	g2.drawImage(image, screenX, screenY, null);
        	
        	changeAlpha(g2,1F);
        }
	}

	public void dyingAnimation(Graphics2D g2) {
		
		
		if (!playDeathSound) {
            gp.playSE(16);
            playDeathSound = true;
        }
		if(direction == "left" || direction == "leftIdle") {
			image = deathLeft;
		}
		else {
			image = death;
		}
		
		super.dyingAnimation(g2);
		
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}
