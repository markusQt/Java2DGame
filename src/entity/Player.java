package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.keyH = kh;	
		SetDefaultValues();
		GetPlayerImage();
	}
	
	public void GetPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}
	
	public void SetDefaultValues(){
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void Update(){
		
		if(keyH.upPressed ||keyH.downPressed ||keyH.leftPressed ||keyH.rigthPressed) {
			if (keyH.upPressed) {
				direction = "up";
				y -= speed;
			}else if(keyH.downPressed) {
				direction = "down";
				y += speed;
			}else if(keyH.leftPressed) {
				System.out.println("left pressed");
				direction = "left";
				x -= speed;
			}else if (keyH.rigthPressed) {
				System.out.println("right pressed");
				direction = "right";
				x += speed;
			}	
			
			spriteCounter++;
			
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}else if (spriteNum == 2)
				{
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			
		}

	}
	
	public void Draw(Graphics2D g2) {
		BufferedImage myPlayerIamge = null;
		
		switch (direction) {
			case "up": {	
				if(spriteNum == 1)
				{
					myPlayerIamge = up1;
				}else if (spriteNum == 2)
				{
					myPlayerIamge = up2;
				}
				
				break;
			}
			case "down": {		
				if(spriteNum == 1)
				{
					myPlayerIamge = down1;
				}else if (spriteNum == 2)
				{
					myPlayerIamge = down2;
				}
				
				break;
			}
			case "right": {	
				if(spriteNum == 1)
				{
					myPlayerIamge = right1;
				}else if (spriteNum == 2)
				{
					myPlayerIamge = right2;
				}
			
				System.out.println("Draw rigth");
				break;
			}
			case "left": {	
				if(spriteNum == 1)
				{
					myPlayerIamge = left1;
				}else if (spriteNum == 2)
				{
					myPlayerIamge = left2;
				}
				
				break;
			}		
		}
		
		g2.drawImage(myPlayerIamge,x,y,gp.tileSize,gp.tileSize,null);
	}
}
