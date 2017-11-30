import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pug extends GameObject {
	int xSpeed;
	int ySpeed;
	int gravity = 1;
	private static BufferedImage pugImg;


	Pug(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		xSpeed = 0; 
		ySpeed = 0; 
		
		try {
			pugImg = ImageIO.read(this.getClass().getResourceAsStream("pug.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void update() {
		super.update();
		x += xSpeed;
		y += ySpeed;
		ySpeed += gravity;
		
		if(y >= 680 - height){
			y = 680 - height; 
		}
		
		if(x >= 750){ 
			x = 720; 
		}
		
		if(x <= 0){
			x = 10; 
		}
		
		if(y <= 0){
			y = 10; 
		}
	}

	void draw(Graphics g) {
		g.drawImage(Pug.pugImg, x, y, width, height, null);

	}
	
	void getBigger(){
		height+=20;
		width+=20;
	}

}
