package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	
	
	public static void main(String[] args) {
		
		ImageIcon logo = new ImageIcon("sword.png");
		
		JFrame frame = new JFrame();
	
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("2D Adventure");
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();

	}

}
