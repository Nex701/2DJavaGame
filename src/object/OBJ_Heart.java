package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

	
	public OBJ_Heart(GamePanel gp) {
		
		super(gp);
		name = "heart";
		
			image = uTool.scaleImage(setupImage("/objects/fullHeart"), gp.tileSize*2/3, gp.tileSize*2/3);
			image2 = uTool.scaleImage(setupImage("/objects/halfHeartTransparent"), gp.tileSize*2/3, gp.tileSize*2/3);
			image3 = uTool.scaleImage(setupImage("/objects/emptyHeartTransparent"), gp.tileSize*2/3, gp.tileSize*2/3);
			
		
	}
}
