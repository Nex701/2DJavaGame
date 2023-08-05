package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Chest1;
import object.OBJ_ChestOpen;

public class Player extends Entity {
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	public int hasKey = 0;
	public int hasCoin = 0;
	
	private BufferedImage[] walkUpImages = new BufferedImage[6];
    private BufferedImage[] walkDownImages = new BufferedImage[6];
    private BufferedImage[] walkLeftImages = new BufferedImage[6];
    public BufferedImage[] walkRightImages = new BufferedImage[6];
    private BufferedImage[] attackUpImages = new BufferedImage[4];
    private BufferedImage[] attackDownImages = new BufferedImage[4];
    private BufferedImage[] attackLeftImages = new BufferedImage[4];
    private BufferedImage[] attackRightImages = new BufferedImage[4];
    
    //USED FOR PLAYER ANIMATIONS
    private final int BASE_ANIMATION_SPEED = 12;
    private int frameCounter = 0;
    private final int maxFrames = 6; // Adjust this based on the number of frames for walking animations
    
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(10, 10, 28, 34);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX =  55 * gp.tileSize;
		worldY =  75 * gp.tileSize;;
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	public void getPlayerImage() {
		
		UtilityTool uTool = new UtilityTool();

	    for (int i = 0; i < 6; i++) {
	        walkUpImages[i] = uTool.scaleImage(setupImage("/player/up_0" + i), 128, 128);
	        walkDownImages[i] = uTool.scaleImage(setupImage("/player/down_0" + i), 128, 128);
	        walkLeftImages[i] = uTool.scaleImage(setupImage("/player/left_0" + i), 128, 128);
	        walkRightImages[i] = uTool.scaleImage(setupImage("/player/right_0" + i), 128, 128);
	    }

	    for (int i = 0; i < 4; i++) {
	        attackUpImages[i] = uTool.scaleImage(setupImage("/player/attacku_0" + i), 128, 128);
	        attackDownImages[i] = uTool.scaleImage(setupImage("/player/attackd_0" + i), 128, 128);
	        attackLeftImages[i] = uTool.scaleImage(setupImage("/player/attackl_0" + i), 128, 128);
	        attackRightImages[i] = uTool.scaleImage(setupImage("/player/attackr_0" + i), 128, 128);
	    }

	    idleUp = uTool.scaleImage(setupImage("/player/idle_up"), 128, 128);
	    idleDown = uTool.scaleImage(setupImage("/player/idle_down"), 128, 128);
	    idleLeft = uTool.scaleImage(setupImage("/player/idle_left"), 128, 128);
	    idleRight = uTool.scaleImage(setupImage("/player/idle_right"), 128, 128);
	}
	public void update() {
		//DEV SPEED BOOST
		if(keyH.kPressed == true) {
			speed += 1;
			System.out.println("Speed+1");
			keyH.kPressed = false;
		}
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.ePressed == true) {
		
		if(keyH.upPressed == true) {
			direction = "up";
		}
		else if(keyH.downPressed == true) {
			direction = "down";
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
		}
		
		// CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		//OBJECT INTERACTION DISTANCE
		interactObject();
		
		//CHECK NPC COLLISION
		gp.cChecker.checkEntity(this, gp.npc);
		interactNPC();
		
		//CHECK MONSTER COLLISION
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		//CHECK EVENT
		gp.eHandler.checkEvent();
		gp.keyH.ePressed = false;
		
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false && keyH.ePressed == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}

		double animationSpeedAdjustment = 1.0 + 0.33 * (speed - 1);
	    int animationSpeed = (int) (BASE_ANIMATION_SPEED / animationSpeedAdjustment);
	    spriteCounter++;
	    if (spriteCounter > animationSpeed) {
	        frameCounter++;
	        if (frameCounter >= maxFrames) {
	            frameCounter = 0;
	        }
	        spriteCounter = 0;
	    }
	    
	}
		//KEEP OUTSIDE OF KEYH STATEMENTS
	    if (invincible == true) {
	    	invincibleCounter++;
	    	if(invincibleCounter > 60) {
	    		invincible = false;
	    		invincibleCounter = 0;
	    	}
	    }
	    
		if(keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false) {
			
			if(direction == "down") {
				direction = "downIdle";
		}
			else if(direction == "up") {
				direction = "upIdle";
		}
			else if(direction == "left") {
				direction = "leftIdle";
		}
			else if(direction == "right") {
				direction = "rightIdle";
		}
	}
		
}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
					gp.playSE(9);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("+1 Key");
				break;
			case "Coin":
				gp.playSE(9);
				hasCoin++;
				gp.obj[i] = null;
				gp.ui.showMessage("+1 Coin");
			break;
			}
			
			}
			
		}
	
	//ADDS AN INVISABLE RANGE TO OBJECTS TO ALLOW FOR INTERACTION AT A DISTANCE
	public void interactObject() {
		
		int objIndex = gp.cChecker.checkInteraction(this, gp.obj);
		if (objIndex != 999) {
			
			String objectName = gp.obj[objIndex].name;
			
			//System.out.println(canInteract);
			
	        if (gp.keyH.ePressed) {
	        	switch(objectName) {
				case "Door":
					
					break;
				case "Chest1":
					if(hasKey < 1) {
						gp.gameState = gp.dialogueState;
						gp.ui.currentDialogue = "You need a key to open this chest";
					}
					else if (hasKey > 0) {
					gp.playSE(4);
					hasKey--;
					hasKey++;
					hasCoin += 2;
					gp.ui.showMessage("+1 Key");
			        gp.ui.showMessage("+1 Coin");
			        gp.ui.showMessage("+1 Coin");
			        
			        // Store the current position of the chest
	                int chestX = gp.obj[objIndex].worldX;
	                int chestY = gp.obj[objIndex].worldY;
	                
			        gp.obj[objIndex] = new OBJ_ChestOpen(gp);
			        gp.obj[objIndex].worldX = chestX;
	                gp.obj[objIndex].worldY = chestY;
                    }break;
			        
					}
				}
	            
	        }
	    }
	
	public void interactNPC() {
	    // Check interaction with NPCs
	    int npcIndex = gp.cChecker.checkInteraction(this, gp.npc);

	    if (npcIndex != 999) {

	        if (gp.keyH.ePressed) {
	            gp.gameState = gp.dialogueState;
	            gp.npc[npcIndex].speak();

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
	        }
	    }
	}
	public void contactMonster(int i) {
		
		if(i !=999) {
			
			if(invincible == false) {
				gp.playSE(13);
				life -= 1;
				invincible = true;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
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
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));;
		}
		
		g2.drawImage(image, screenX-41, screenY-64, null);
		//RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));;
		
		//PLAYER HITBOX DEBUG
		//g2.setColor(Color.RED);
	    //g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
