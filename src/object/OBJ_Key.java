package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

	public OBJ_Key() {
		
		name = "Key";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/genesis_tiles/Tile_ID_448.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}