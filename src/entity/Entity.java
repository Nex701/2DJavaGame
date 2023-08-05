package entity;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	protected GamePanel gp;
	public int worldX;
	public int worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idleUp, idleDown, idleLeft, idleRight;
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	String dialogues[] = new String[30];
	int dialogueIndex = 0;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int type;// 0 = player, 1 = npc, 2 = monster
	public UtilityTool uTool = new UtilityTool();
	
	//CHARACTER STATUS
	public int maxLife;
	public int life;
	
	//Variables to handle interaction
    protected boolean canInteract = false;
    protected Font interactionFont = new Font("Arial", Font.BOLD, 12);
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void setCanInteract(boolean canInteract) {this.canInteract = canInteract;}
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if (collisionOn) {
	            switch (direction) {
	                case "up":
	                    direction = "upIdle";
	                    break;
	                case "down":
	                    direction = "downIdle";
	                    break;
	                case "left":
	                    direction = "leftIdle";
	                    break;
	                case "right":
	                    direction = "rightIdle";
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
	public void speak() {
		
		if(dialogues[dialogueIndex] == null){
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "downIdle";
		break;
		case "down":
			direction = "upIdle";
		break;
		case "left":
			direction = "rightIdle";
		break;
		case "right":
			direction = "leftIdle";
		break;
		case "upIdle":
			direction = "downIdle";
		break;
		case "downIdle":
			direction = "upIdle";
		break;
		case "leftIdle":
			direction = "rightIdle";
		break;
		case "rightIdle":
			direction = "leftIdle";
		break;
		}
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this,gp.npc);
		gp.cChecker.checkEntity(this,gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
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
    			if(spriteNum == 1) {
    				image = up1; 
    			}
    			if(spriteNum == 2) {
    				image = up2;
    			}
    			break;
    		case "down":
    			if(spriteNum == 1) {
    				image = down1;
    			}
    			if(spriteNum == 2) {
    				image = down2;
    			}
    			break;
    		case "left":
    			if(spriteNum == 1) {
    				image = left1;
    			}
    			if(spriteNum == 2) {
    				image = left2;
    			}
    			break;
    		case "right":
    			if(spriteNum ==1) {
    				image = right1;
    			}
    			if(spriteNum == 2) {
    				image = right2;
    			}
    			break;
    		case "rightIdle":
    			if(spriteNum == 1 || spriteNum == 2) {
    			image = idleRight;
    			}
    			break;
    		case "leftIdle":
    			if(spriteNum == 1 || spriteNum == 2) {
    			image = idleLeft;
    			}
    			break;
    		case "upIdle":
    			if(spriteNum == 1 || spriteNum == 2) {
    			image = idleUp;
    			}
    			break;
    		case "downIdle":
    			if(spriteNum == 1 || spriteNum == 2) {
    			image = idleDown;
    			}
    			break;
    			
    		}
 
        	g2.drawImage(image, screenX, screenY, null);
        	
        	
        }
	}
	
	public void setnpcBoundaries(int minX, int minY, int maxX, int maxY) {
        setAction();

        // Perform collision checks
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        // Calculate the new position based on the direction
        int newX = worldX;
        int newY = worldY;

        switch (direction) {
            case "up":
                newY -= speed;
                break;
            case "down":
                newY += speed;
                break;
            case "left":
                newX -= speed;
                break;
            case "right":
                newX += speed;
                break;
        }

     // Check if the new position is within the boundaries and no collision is detected
        if (!collisionOn && newX >= minX && newX + solidArea.width <= maxX && newY >= minY && newY + solidArea.height <= maxY) {
            // If within boundaries and no collision, update the position
            worldX = newX;
            worldY = newY;
        }
        
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	// Setup method for player images (x,x size)
    public BufferedImage setupImage(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 96, 96);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return image;
    }
    
}


