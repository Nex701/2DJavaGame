package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Chest1;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public int hasKey = 0;
	public int hasCoin = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(16, 24, 16, 20);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX =  59 * gp.tileSize;
		worldY =  83 * gp.tileSize;;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
			idleUp = ImageIO.read(getClass().getResourceAsStream("/player/player_up_idle.png"));
			idleDown = ImageIO.read(getClass().getResourceAsStream("/player/player_down_idle.png"));
			idleLeft = ImageIO.read(getClass().getResourceAsStream("/player/player_left_idle.png"));
			idleRight = ImageIO.read(getClass().getResourceAsStream("/player/player_right_idle.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//update method gets called 60times per second.
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
		
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
		
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
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
					gp.playSE(2);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("+1 Key");
				break;
			case "Door":
				
				break;
			case "Dev_skull":
					speed += 5;
					gp.obj[i] = null;
					System.out.println("Speed+5");
				break;
			case "Coin":
				gp.playSE(2);
				hasCoin++;
				gp.obj[i] = null;
				gp.ui.showMessage("+1 Coin");
			break;
			case "Chest1":
				if (hasKey > 0) {
				gp.playSE(2);
				hasKey--;
				hasKey++;
				hasCoin += 2;
				gp.ui.showMessage("+1 Key");
		        gp.ui.showMessage("+1 Coin");
		        gp.ui.showMessage("+1 Coin");
		        gp.obj[i] = null;
		    }break;
			}
			
			}
			
		}

	
	public void draw(Graphics2D g2) {
		
		
		BufferedImage image = null;
		
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
	
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
