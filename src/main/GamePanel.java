package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	//ScreenSettings:
	
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 16 * 3 = 48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768
	final int screenHeigth = tileSize * maxScreenRow;// 576
	//FPS
	
	int FPS = 60;
	KeyHandler kh = new KeyHandler();	
	Thread gameThread;
	Player myPlayer = new Player(this,kh);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(kh);
		this.setFocusable(true);		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);	
		gameThread.start();
	}

	@Override
//	public void run() {
//		
//		double drawIntervall = 1000000000 / FPS;
//		double nextDrawTime = System.nanoTime() + drawIntervall;
//		while(gameThread != null) {						
//			
//			//1 .update information such as character position
//			update();			
//			//2. Draw Screen with updated information
//			repaint();
//						
//			try {
//				double 	remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime / 1000000;
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long)remainingTime);
//				
//				nextDrawTime +=  drawIntervall ;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}		
//	}
	
	
	public void run() {
		
		double drawIntervall = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime ;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawIntervall;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();	
				delta--;
				drawCount++;
			}
			
			if (timer >= 1_000_000_000) {
				System.out.println("FPS: " + drawCount);
				timer = 0;
				drawCount = 0;
			}
		}
	
	}
	
	public void update() {
		myPlayer.Update();
	}
	
	public void paintComponent(Graphics g) {
		Toolkit.getDefaultToolkit().sync(); 
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		myPlayer.Draw(g2);
		g2.dispose();
	}
}
