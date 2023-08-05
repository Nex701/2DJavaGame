package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ChestOpen extends Entity {


	public OBJ_ChestOpen(GamePanel gp) {
		super(gp);
		name = "ChestOpen";
		down1 = setup("/genesis_tiles/Tile_ID_324");
		collision = true;
	}
}
