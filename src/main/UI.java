package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import object.OBJ_Coin;
import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_20;
    BufferedImage KeyImage;
    BufferedImage CoinImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    private List<Message> messages = new ArrayList<>(); // Added a list to store messages

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_20 = new Font("Ariel", Font.PLAIN, 20);
        OBJ_Key key = new OBJ_Key();
        KeyImage = key.image;
        OBJ_Coin coin = new OBJ_Coin();
        CoinImage = coin.image;
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

        g2.setFont(arial_20);
        g2.setColor(Color.yellow);
        g2.drawImage(KeyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("" + gp.player.hasKey, 60, 65);
        g2.drawImage(CoinImage, gp.tileSize + 35, gp.tileSize / 2 - 3, gp.tileSize, gp.tileSize, null);
        g2.drawString("" + gp.player.hasCoin, 130, 65);

        // MESSAGE
        if (messageOn == true) {
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
}
