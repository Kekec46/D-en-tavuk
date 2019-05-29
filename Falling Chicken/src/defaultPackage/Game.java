package defaultPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	private SecureRandom rng = new SecureRandom();
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//In Game Variables
	private Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private Font f2 = new Font(Font.SANS_SERIF ,Font.PLAIN, 40);
	private Font f3 = new Font(Font.SANS_SERIF,Font.PLAIN, 25);
	
	public int score = 0;
	public int level = 1;
	public int life = 3;
	public boolean game_over = false;
	public boolean pause = true;
	public boolean start = false;
	
	//Input
	private KeyManager keyManager;
	
	//Objects
	Background background = new Background(0);
	Chicken chicken = new Chicken(this,432, 40);
	ArrayList <Cat> catList = new ArrayList<Cat>();
	ArrayList <Egg> eggList = new ArrayList<Egg>();
	ArrayList <Target> targetList =  new ArrayList<Target>();
	
	public Game(String title, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager(this); 
		
	}
	
	private void init() {
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
	}
	
	private void tick() {
		
		keyManager.tick();
		if(pause)
			return;
		background.tick();
		chicken.tick();
		
		spawnCat(); // Cat Spawn
		spawnTarget(); // Target Spawn
		

			
		// Cat Tick
		if(!catList.isEmpty()) {
			
			for(int i = 0; i < catList.size(); i++) {
				
				catList.get(i).tick();
				if(chicken.collisionWithCat(catList.get(i).getX(), catList.get(i).getY())) {
					
					life--;
					
					if(life == 0) {
						
						pause = true;
						game_over = true;
						return;
					
					}
						
					
					chicken.setX(432);
					chicken.setY(42);
					targetList.clear();
					catList.clear();
					eggList.clear();
					
					
					
				}
				
				if(!catList.isEmpty() && catList.get(i).getY() + 96 <= 0)
					catList.remove(i);
			}
			
		}
		
		// Egg Tick
		if(!eggList.isEmpty()) {
			
			for(int i = 0; i < eggList.size(); i++) {
				
				eggList.get(i).tick();
				
				if(eggList.get(i).getX() + 22 <= 0 || eggList.get(i).getX() >= 960)
					eggList.remove(i);
				
			}
					
		}
		
		//Target Tick
		if(!targetList.isEmpty()) {
			
			for(int i = 0; i < targetList.size(); i++) {
				
				targetList.get(i).tick();
				
				targetList.get(i);
				if(targetList.get(i).getY() + Target.size[targetList.get(i).getId()] <= 0)
					targetList.remove(i);
			}
			
		}
		
		if(!targetList.isEmpty() && !eggList.isEmpty()) { // Collision With Target
			
			for(int i = 0; i < targetList.size(); i++) {
				
				for(int j = 0; j < eggList.size(); j++)
				
					if(targetList.get(i).collisonWithEgg(eggList.get(j).getX(), eggList.get(j).getY())) {
						
						int id = targetList.get(i).getId();
						
						if(id == 0)
							score += 20;
						else if(id == 1)
							score += 10;
						else
							score += 5;
						
						if(score >= 100 * level) {
							
							++level;
							Cat.setSpeed(3.0f + ((level - 1) * 0.5f));
						}
						
						
						
						eggList.remove(j);
						targetList.remove(i);
						--j;
						--i;
						break;
					}
				
			}
			
		}
			
		}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			
			display.getCanvas().createBufferStrategy(3);
			return ;
		}
		
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		
		background.render(g);
		
		if(start)
		chicken.render(g);
		
		
		// Cat Rendering
		if(!catList.isEmpty()) {
			
			for (Cat cat : catList) {
				
				cat.render(g);
			}
			
		}
		//Egg Rendering
		if(!eggList.isEmpty()) {
			
			for (Egg egg : eggList) {
				
				egg.render(g);
			}
			
		}
		//Target Rendering
		if(!targetList.isEmpty()) {
			
			for (Target target : targetList) {
				
				target.render(g);
			}
			
		}
		
		if(!start) {
			
			g.setFont(f2);
			g.setColor(Color.RED);
			
			g.drawString("Welcome The Falling Chicken Game \n ", 150, 200);
			g.drawString("Press Enter To Start The Game", 190, 275);
			
			g.setFont(f3);
			g.setColor(Color.BLACK);
			
			g.drawString("How To Play?", 22, 450);
			g.drawString("-Use W,A,S,D to move and press SPACE to throw an egg.", 15, 490);
			g.drawString("-Press ESC to pause and resume the game.", 15, 530);
			g.drawString("-Try to avoid from the cats.", 15, 570);
			g.drawString("-Small Targets: 20 Point, Mid Size Targets: 10 Point, Big Targets: 5 Point.", 15, 610);
			g.drawString("-Every 100 point game becomes harder.", 15, 650);
			
		}
		
		g.setFont(f1);
		g.drawString("Score: " + score, 5, 20); // Show Score
		g.drawString("Level: " + level, 465, 20);
		g.drawString("Life: " + life, 900 ,20);
		
		if(game_over) {
			
			g.setFont(f2);
			g.setColor(Color.RED);
			g.drawString("Game Over :(", 350, 200);
			g.setFont(f3);
			g.setColor(Color.BLACK);
			g.drawString("Press R To Restart The Game", 310, 245);
			g.drawString("Press Q To Exit The Game", 310, 280);
			
		}
		
		//End Drawing!
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				
				System.out.println("Ticks and Frames: " + ticks);
				System.out.println(Cat.speed);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	public void spawnCat() { // size add later
		
		int frequency, posx;
		
		frequency = rng.nextInt(10000);
		posx = rng.nextInt(864);
		
		if(frequency < 150)
			catList.add(new Cat(posx,800));
	
	}
	
	public void spawnTarget(){
		
		int frequency, posx;
		
		frequency = rng.nextInt(10000);
		posx = rng.nextInt(896);
		
		if(frequency < 10)
			targetList.add(new Target(0,posx + 24, 800));
		else if(frequency >= 10 && frequency < 30)
			targetList.add(new Target(1,posx + 16, 800));
		else if(frequency >= 30 && frequency < 55)
			targetList.add(new Target(2,posx, 800));
	}
	
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public synchronized void start() {
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		
		if(!running)
			return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
