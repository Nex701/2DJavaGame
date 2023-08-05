package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	//NPC DIALOGUE TEXT POSITIONING
	public static List<String> splitTextIntoLines(String text, int lineWidth) {
	    List<String> lines = new ArrayList<>();
	    String[] words = text.split(" ");
	    StringBuilder lineBuilder = new StringBuilder();

	    for (String word : words) {
	        if (lineBuilder.length() + word.length() + 1 <= lineWidth) {
	            if (lineBuilder.length() > 0) {
	                lineBuilder.append(" ");
	            }
	            lineBuilder.append(word);
	        } else {
	            lines.add(lineBuilder.toString());
	            lineBuilder = new StringBuilder(word);
	        }
	    }

	    if (lineBuilder.length() > 0) {
	        lines.add(lineBuilder.toString());
	    }

	    return lines;
	}
}
