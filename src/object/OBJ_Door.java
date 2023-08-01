package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

	public OBJ_Door() {
		
		name = "Door";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/genesis_tiles/Tile_ID_222.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
}
