package defaultPackage;

import java.awt.Graphics;

public class Background {
	
	private float y;
	
	public Background(float y) {
		
		this.y = y;
		
	}
	

	public void tick() {
		
		y -= 3.0f;
		if(y <= -640)
			y = 0;
	}
	
	public void render(Graphics g) {
		
		
		g.drawImage(Assets.background, 0,(int) y, 960, 800 , null);
		g.drawImage(Assets.background, 0,(int) y + 640 , 960, 800, null);
	}
	

}
