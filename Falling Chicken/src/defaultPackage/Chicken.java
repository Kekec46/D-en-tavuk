package defaultPackage;

import java.awt.Graphics;

public class Chicken {

	private Game game;
	
	private float x, y;
	private float speed = 7.0f;
	private boolean isLeft = true;
	
	public Chicken(Game game,float x, float y) {
		
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		
		if(game.getKeyManager().left) {
			
			x -= speed;
			isLeft = true;
		}
			
		if(game.getKeyManager().right) {
			
			x += speed;
			isLeft = false;
		}
		
		if(game.getKeyManager().up)
			y -= speed;
		if(game.getKeyManager().down)
			y += speed;
		
			 
			
		if(x <= 0)
			x = 0;
		if(x + 96 >= 960)
			x = 960 - 96;
		if(y <= 0)
			y = 0;
		if(y + 96 >= 800)
			y = 800 - 96;
	
	}
	
	public boolean collisionWithCat(float  x, float y) {
		
		if((this.x + 70) < x ||this.x > (x + 70) || (this.y + 60) < y || this.y > (y + 60) )
			return false;
		else {
			System.out.println("CollisonWithCat!");
			return true;
		}
	}
		
	
	
	public void render(Graphics g) {
		
		if(isLeft)
		g.drawImage(Assets.chickenLeft, (int) x, (int) y, 96 , 96, null);
		else
		g.drawImage(Assets.chickenRight, (int) x, (int) y, 96, 96, null);
	}
	
	public boolean getIsLeft() {
		
		return isLeft;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
