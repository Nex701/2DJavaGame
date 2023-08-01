package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Devskull extends SuperObject {

	public OBJ_Devskull() {
		
		name = "Dev_skull";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/genesis_tiles/Tile_ID_494.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
