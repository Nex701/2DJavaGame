package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, kPressed, spacePressed;
	//DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				gp.playSE(3);
				if(gp.ui.commandNum < 0)
					gp.ui.commandNum = 2;
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				gp.playSE(3);
				if(gp.ui.commandNum > 2)
					gp.ui.commandNum = 0;
			}
			if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					//gp.playMusic(12);
				}
				if(gp.ui.commandNum == 1) {
					//SAVE GAME
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		//PLAYSTATE
		else if(gp.gameState == gp.playState) {
		if(code == KeyEvent.VK_W) {upPressed = true;}
		if(code == KeyEvent.VK_A) {leftPressed = true;}
		if(code == KeyEvent.VK_S) {downPressed = true;}
		if(code == KeyEvent.VK_D) {rightPressed = true;}
		if(code == KeyEvent.VK_P) {gp.gameState = gp.pauseState;}
		if(code == KeyEvent.VK_E) {ePressed = true;}
		if(code == KeyEvent.VK_K) {kPressed = true;}
		if(code == KeyEvent.VK_K) {kPressed = true;}
		if(code == KeyEvent.VK_SPACE) {spacePressed = true;}
		//RENDERING DEBUG
		if(code == KeyEvent.VK_T) {
			if(checkDrawTime == false) {
				checkDrawTime = true;
			}
			else if(checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
	}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		//DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
		}
}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}

	}
}
