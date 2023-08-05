package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Devskull extends Entity {

	
	public OBJ_Devskull(GamePanel gp) {
		super(gp);
		name = "Dev_skull";
		down1 = setup("/genesis_tiles/Tile_ID_494");

	}
}
