package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity {

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Green Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		solidArea = new Rectangle(3, 18, 42, 30);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		
		up1 = setup("/monster/slimeright_01");
		up2 = setup("/monster/slimeright_02");
		down1 = setup("/monster/slimeright_01");
		down2 = setup("/monster/slimeright_02");
		left1 = setup("/monster/slimeleft_01");
		left2 = setup("/monster/slimeleft_02");
		right1 = setup("/monster/slimeright_01");
		right2 = setup("/monster/slimeright_02");
		idleUp = setup("/monster/slimeright_01");
		idleDown = setup("/monster/slimeright_01");
		idleLeft = setup("/monster/slimeright_02");
		idleRight = setup("/monster/slimeright_01");
		
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
	
}
