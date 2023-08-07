package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
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
    private int attackCounter = 0;
    
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(10, 10, 28, 34);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackArea.width = 48;
		attackArea.height = 48;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
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

	    for (int i = 0; i < 6; i++) {
	        walkUpImages[i] = uTool.scaleImage(setupImage("/player/up_0" + i), 128, 128);
	        walkDownImages[i] = uTool.scaleImage(setupImage("/player/down_0" + i), 128, 128);
	        walkLeftImages[i] = uTool.scaleImage(setupImage("/player/left_0" + i), 128, 128);
	        walkRightImages[i] = uTool.scaleImage(setupImage("/player/right_0" + i), 128, 128);
	    }

	    idleUp = uTool.scaleImage(setupImage("/player/idle_up"), 128, 128);
	   	idleDown = uTool.scaleImage(setupImage("/player/idle_down"), 128, 128);
	   	idleLeft = uTool.scaleImage(setupImage("/player/idle_left"), 128, 128);
	   	idleRight = uTool.scaleImage(setupImage("/player/idle_right"), 128, 128);
	}   
	
	public void getPlayerAttackImage() {
	    for (int i = 0; i < 4; i++) {
	        attackUpImages[i] = uTool.scaleImage(setupImage("/player/attacku_0" + i), 128, 128);
	        attackDownImages[i] = uTool.scaleImage(setupImage("/player/attackd_0" + i), 128, 128);
	        attackLeftImages[i] = uTool.scaleImage(setupImage("/player/attackl_0" + i), 128, 128);
	        attackRightImages[i] = uTool.scaleImage(setupImage("/player/attackr_0" + i), 128, 128);
	    }
	}
	public void update() {
		//DEV SPEED BOOST
		if(keyH.kPressed == true) {
			speed += 1;
			System.out.println("Speed+1");
			keyH.kPressed = false;
		}
		
		if(keyH.spacePressed == true ) { 
			playerAttackCounter();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.ePressed == true) {
			
		attacking = false;
			
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
		
	
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false && keyH.ePressed == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}

		gp.keyH.ePressed = false;
		
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
		 
		if(keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false && keyH.spacePressed == false) {
			
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
		//KEEP OUTSIDE OF KEYH STATEMENTS
	    if (invincible == true) {
	    	invincibleCounter++;
	    	if(invincibleCounter > 60) {
	    		invincible = false;
	    		invincibleCounter = 0;
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
					hasCoin += 2;
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
	public void playerAttackCounter() {
		 
		boolean startAttackAnimation = false;
		if (!attacking && !startAttackAnimation) {
	        gp.playSE(17);
	        startAttackAnimation = true;
	    }
			attacking = true; 
	        spriteCounter++;
	        if (spriteCounter > 9/2) {
	        	attackCounter++;
	        	if(attackCounter == 2 && attackCounter < 4) {
	        		//STORE CURRENT VALUES 
	        		int currentWorldX = worldX;
	        		int currentWorldY = worldY;
	        		int solidAreaWidth = solidArea.width;
	        		int solidAreaHeight = solidArea.height;
	        		
	        		//ADJUST PLAYERS WORLDX/Y FOR THE ATTACK AREA
	        		switch(direction) {
	        		case "up": worldY -= attackArea.height; break;
	        		case "upIdle": worldY -= attackArea.height; break;
	        		case "down": worldY += attackArea.height; break;
	        		case "downIdle": worldY += attackArea.height; break;
	        		case "left": worldX -= attackArea.width; break;
	        		case "leftIdle": worldX -= attackArea.width; break;
	        		case "right": worldX += attackArea.width; break;
	        		case "rightIdle": worldX += attackArea.width; break;
	        		}
	        		//ATTACK AREA BECOMES SOLID AREA
	        		solidArea.width = attackArea.width;
	        		solidArea.height = attackArea.height;
	        		//CHECK MONSTER COLLISION WITHIN THE UPDATED WORLDX/Y AND SOLIDAREA
	        		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
	        		damageMonster(monsterIndex);
	        		
	        		//RESTORE WORLD X/Y AND SOLID AREA
	        		worldX = currentWorldX;
	        		worldY = currentWorldY;
	        		solidArea.width = solidAreaWidth;
	        		solidArea.height = solidAreaHeight;
	        		
	        	}
	            if (attackCounter >= 4) {
	            	attackCounter = 0;
	                attacking = false;
	                gp.keyH.spacePressed = false;
	                startAttackAnimation = false;
	            }
	            spriteCounter = 0;
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
	public void damageMonster(int i) {
		
		if (i != 999) {
			
			if(gp.monster[i].invincible == false) {
					if(gp.monster[i].name == "Green Slime") {gp.playSE(15);}
				gp.monster[i].life --;
				gp.monster[i].invincible = true;
				//gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
				}
			}
		
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(attacking == false) {image = walkUpImages[frameCounter];}
            if(attacking == true) {image = attackUpImages[attackCounter];}
            break;
        case "down":
        	if(attacking == false) {image = walkDownImages[frameCounter];}
        	if(attacking == true) {image = attackDownImages[attackCounter];}
            break;
        case "left":
        	if(attacking == false) {image = walkLeftImages[frameCounter];}
        	if(attacking == true) {image = attackLeftImages[attackCounter];}
            break;
        case "right":
        	if(attacking == false) {image = walkRightImages[frameCounter];}
        	if(attacking == true) {image = attackRightImages[attackCounter];}
            break;
        case "upIdle":
			if(attacking == false) {image = idleUp;}
			if(attacking == true) {image = attackUpImages[attackCounter];}
			break;
        case "downIdle":
			if(attacking == false) {image = idleDown;}
			if(attacking == true) {image = attackDownImages[attackCounter];}
			break;
        case "leftIdle":
			if(attacking == false) {image = idleLeft;}
			if(attacking == true) {image = attackLeftImages[attackCounter];}
			break;	
		case "rightIdle":
			if(attacking == false) {image = idleRight;}
        	if(attacking == true) {image = attackRightImages[attackCounter];}
			break;
		
		
		
		}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));;
		}
		
		g2.drawImage(image, screenX-41, screenY-64, null);
		//RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));;
		
		//PLAYER HITBOX DEBUG
		//g2.setColor(Color.RED);
	    //g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
