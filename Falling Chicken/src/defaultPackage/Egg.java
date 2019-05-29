package defaultPackage;

import java.awt.Graphics;

public class Egg {
	
	private float x,y;
	private float speed = 7.0f;
	private boolean isLeft;
	
	public Egg(float x, float y, boolean isLeft) {
		
		this.x = x;
		this.y = y;
		this.isLeft = isLeft;
		
	}
	
	public void tick() {
		
		if(isLeft)
			x -= speed;
		else
			x += speed;
	}

	public void render(Graphics g) {
		
		if(isLeft)
			g.drawImage(Assets.eggLeft, (int) x - 10,(int) y + 10, 22, 17, null);
		else
			g.drawImage(Assets.eggLeft, (int) x + 88,(int) y + 10, 22, 17, null);
		
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

}
