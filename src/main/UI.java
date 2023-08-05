package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import object.OBJ_Heart;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font pauseFont;
    Font pickUpFont;
    BufferedImage full_heart, half_heart, empty_heart;
    BufferedImage KeyImage, CoinImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;
    private int frameIndex = 0;
    private final int totalFrames = 6;
    private final int animationDelay = 100; // Delay between frames in milliseconds
    private long lastFrameTime = 0;
    private List<Message> messages = new ArrayList<>(); // Added a list to store messages

    public UI(GamePanel gp) {
        this.gp = gp;
        pauseFont = new Font("Ariel", Font.PLAIN, 40);
        pickUpFont = new Font("Ariel", Font.BOLD, 20);
        
        //CREATE HUD OBJECT
        OBJ_Heart heart = new OBJ_Heart(gp);
        full_heart = heart.image;
        half_heart = heart.image2;
        empty_heart = heart.image3;
    }

    public void showMessage(String text) {
        // Draw the message above the character
        int characterX = gp.player.screenX + gp.tileSize / 2;
        int characterY = gp.player.screenY - gp.tileSize / 2;
        int messageX = characterX - 18;

        messages.add(new Message(text, messageX, characterY));
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

    	this.g2 = g2;
    	
        //TITLE STATE
        if(gp.gameState == gp.titleState) {
        	drawTitleScreen();
        }
        //PLAYSTATE
        if (gp.gameState == gp.playState) {
            //PLAYERS HEALTH
            drawPlayerLife();
        }
        //PAUSESTATE
        if(gp.gameState == gp.pauseState) {
        	drawPauseScreen();
        	drawPlayerLife();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
        	drawDialogueScreen();
        	drawPlayerLife();
        }
        // MESSAGE
        if (messageOn == true) {
        	g2.setFont(pickUpFont);
            g2.setColor(Color.yellow);
            g2.setFont(g2.getFont().deriveFont(10F));
            int totalMessageHeight = -30;
            
            for (Message message : messages) {
            	totalMessageHeight += gp.tileSize / 2; // Increment the total height
                int messageX = message.x; // Use the same x-coordinate for each message
                int messageY = message.y - totalMessageHeight; // Adjust y-coordinate based on total height

                g2.drawString(message.text, messageX, messageY);
            }

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
                messages.clear(); // Clear the list of messages
            }
        }
    }
    public void drawPlayerLife() {
    	
    	int x = gp.tileSize/2;
    	int y = gp.tileSize/3;
    	int i = 0;
    	
    	//DRAW MAX LIFE
    	while(i < gp.player.maxLife/2) {
    		g2.drawImage(empty_heart, x, y,null);
    		i++;
    		x += gp.tileSize*2/3;
    	}
    	
    	x = gp.tileSize/2;
    	y = gp.tileSize/3;
    	i = 0;
    	//DRAW CURRENT LIFE
    	while(i < gp.player.life) {
    		i++;
    		g2.drawImage(half_heart, x, y, null);
    		if(i < gp.player.life) {
    			g2.drawImage(full_heart, x, y, null);
    		}
    		i++;
    		x += gp.tileSize*2/3;
    	}
    }
    public void drawTitleScreen() {
    	
    	g2.setColor(new Color(0,0,0));
    	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    	
    	//TITLE NAME
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
    	String text = "Nexus Vanguard";
    	int x = getXforCenteredText(text);
    	int y = gp.tileSize*3;
    	
    	//SHADOW
    	g2.setColor(Color.gray);
    	g2.drawString(text, x+5, y+5);
    	
    	//MAIN COLOR
    	g2.setColor(Color.white);
    	g2.drawString(text, x, y);
    	
    	//MAIN CHARACTER IMAGE
    	x = gp.screenWidth / 3 - 28;
    	y -= 40;

    	// Calculate the elapsed time since the last frame update
    	long currentTime = System.currentTimeMillis();
    	long elapsedTime = currentTime - lastFrameTime;

    	// If enough time has passed, update the frame index
    	if (elapsedTime > animationDelay) {
    	    frameIndex = (frameIndex + 1) % totalFrames;
    	    lastFrameTime = currentTime;
    	}

    	// Draw the current frame
    	g2.drawImage(gp.player.walkRightImages[frameIndex], x, y, 256, 256, null);
    	//MENU
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,48));
    	
    	text = "NEW GAME";
    	x = getXforCenteredText(text) -18;
    	y += gp.tileSize*6 + 10;
    	g2.drawString(text, x, y);
    	if(commandNum == 0) {
    		g2.drawString(">", x-gp.tileSize, y);
    	}
    	
    	text = "LOAD GAME";
    	x = getXforCenteredText(text) -18;
    	y += gp.tileSize+10;
    	g2.drawString(text, x, y);
    	if(commandNum == 1) {
    		g2.drawString(">", x-gp.tileSize, y);
    	}
    	
    	text = "QUIT";
    	x = getXforCenteredText(text) -18;
    	y += gp.tileSize+10;
    	g2.drawString(text, x, y);
    	if(commandNum == 2) {
    		g2.drawString(">", x-gp.tileSize, y);
    	}
    }

    public void drawPauseScreen() {
    	
    	g2.setFont(pauseFont);
    	g2.setColor(Color.white);
    	String text = "Paused";
    	int x = getXforCenteredText(text);
    	int y = gp.screenHeight / 3;
    	
    	g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {
    	
    	//WINDOW
    	int x = gp.tileSize*3;
    	int y = gp.tileSize*8;
    	int width = gp.screenWidth - (gp.tileSize*6);
    	int height = gp.tileSize*3;
    	
    	drawSubWindow(x, y, width, height);
    	
    	x += gp.tileSize;
    	y += gp.tileSize;
    	//POSITION TEXT WITHIN WINDOW
    	int textWidth = width - gp.tileSize - (gp.tileSize/2);
    	
    	List<String> dialogueLines = UtilityTool.splitTextIntoLines(currentDialogue, textWidth);
    	
    	g2.setFont(pickUpFont);
        g2.setColor(Color.white);
        int lineSpacing = g2.getFontMetrics().getHeight(); // Get the height of the font
        int currentLineWidth = 0;

    	for (String line : dialogueLines) {
            String[] words = line.split(" ");
            for (String word : words) {
                int wordWidth = g2.getFontMetrics().stringWidth(word + " ");
                if (currentLineWidth + wordWidth <= textWidth) {
                    // Draw the word on the current line
                    g2.drawString(word + " ", x - 8 + currentLineWidth, y);
                    currentLineWidth += wordWidth;
                } else {
                    // Move to a new line
                    y += lineSpacing;
                    currentLineWidth = 0;
                    g2.drawString(word + " ", x - 8, y);
                    currentLineWidth += wordWidth;
                }
            }
            // Move to a new line after each complete line
            y += lineSpacing;
            currentLineWidth = 0;
        }
    	
    }
    public void drawSubWindow(int x, int y, int width, int height) {
    	
    	Color c = new Color(0,0,0,210);
    	g2.setColor(c);
    	g2.fillRoundRect(x, y, width, height, 35, 35);
    	
    	c = new Color(255,255,255);
    	g2.setColor(c);
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    // Inner class to hold message details
    private static class Message {
        String text;
        int x;
        int y;

        public Message(String text, int x, int y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }
    public int getXforCenteredText(String text) {
    	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    	int x = gp.screenWidth/2 - length/2;
    	return x;
    	
    }
}
