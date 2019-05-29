package defaultPackage;

import java.awt.Graphics;

public class Target {
	
	private float x, y;
	private float speed = 3.0f;
	private int id;
	public static int [] size = {36, 48, 64};
	
	
	public Target(int id, float x ,float y) {
		
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	

	public void tick() {
		
		y -= speed;
	}
	
	public void render(Graphics g) { 
		
		g.drawImage(Assets.target, (int) x, (int) y, size[id], size[id], null);
		
	}
	
	public boolean collisonWithEgg(float x, float y) { // Collision With Target And Egg 
		
		if((this.x + size[id] ) < x || this.x > (x + 22) || (this.y + size[id] + 10) < y || this.y - 10 > (y + 17))
			return false;
		else {
			
			System.out.println("CollisonWithTarget");
			return true;
		}
			
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
