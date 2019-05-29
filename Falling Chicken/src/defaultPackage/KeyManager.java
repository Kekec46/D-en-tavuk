package defaultPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private Game game;
	private boolean[] keys;
	public boolean up, down,left, right;
	
	public KeyManager(Game game) {
		
		this.game = game;
		keys = new boolean[256];
		
	}
	
	public void tick() {
		
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			game.eggList.add(new Egg(game.chicken.getX(), game.chicken.getY(), game.chicken.getIsLeft()));
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			if(game.pause)
				game.pause = false;
			else
				game.pause = true;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			game.start = true;
			game.pause = false;
		}
		
		else if(game.game_over && game.pause && e.getKeyCode() == KeyEvent.VK_R) {
			
			game.level = 1;
			game.life = 3;
			game.score = 0;
			game.catList.clear();
			game.eggList.clear();
			game.targetList.clear();
			game.chicken.setX(432);
			game.chicken.setY(40);
			
			game.game_over = false;
			game.pause = false;
				
		}
		
		else if(game.game_over && game.pause && e.getKeyCode() == KeyEvent.VK_Q)
			System.exit(0);
			
		else
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	

}
