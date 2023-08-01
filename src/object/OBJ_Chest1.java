package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest1 extends SuperObject {

	public OBJ_Chest1() {
		
		name = "Chest1";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/genesis_tiles/Tile_ID_299.png"));
			collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
