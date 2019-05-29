package defaultPackage;

import java.awt.Graphics;

public class Cat {
	
	private float x , y;
	public static float speed = 3.0f;

	public Cat(float x, float y) {
		
		
		this.x = x;
		this.y = y;
		
	}
	
	public void tick() {
		
		y -= speed;
		
	}

	public void render(Graphics g) {
	
		g.drawImage(Assets.cat,(int) x, (int) y, 120, 120, null);
		
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
	
	public static float getSpeed() {
		return speed;
	}

	public static void setSpeed(float speed) {
		Cat.speed = speed;
	}
	

}
