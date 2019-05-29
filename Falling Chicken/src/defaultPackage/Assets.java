package defaultPackage;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage chickenLeft, chickenRight, cat , target , eggLeft, eggRight, background;
	
	public static void init() {
		
		chickenLeft = ImageLoader.loadImage("/textures/chickenLeft.png");
		chickenRight = ImageLoader.loadImage("/textures/chickenRight.png");
		eggLeft = ImageLoader.loadImage("/textures/eggLeft.png");
		eggRight = ImageLoader.loadImage("/textures/eggRight.png");
		cat = ImageLoader.loadImage("/textures/cat.png");
		background = ImageLoader.loadImage("/textures/background.jpg");
		target = ImageLoader.loadImage("/textures/sepet.png");
	}
	
}
