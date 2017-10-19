import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pizza extends GameObject {
	int xSpeed; 
	private static BufferedImage pizzaImg;
	Pizza(int x, int y, int width, int height, int xSpeed) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		
		try {
			pizzaImg = ImageIO.read(this.getClass().getResourceAsStream("pizza.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void update() {
		super.update();
		x-=xSpeed;
	}

	void draw(Graphics g) {

		g.drawImage(Pizza.pizzaImg, x, y, width, height, null);

	}

	
}
