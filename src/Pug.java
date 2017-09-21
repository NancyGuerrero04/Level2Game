import java.awt.Color;
import java.awt.Graphics;

public class Pug extends GameObject {
	int xSpeed;
	int ySpeed;

	Pug(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		xSpeed = 2; 
		ySpeed = 0; 

	}

	void update() {
		x += xSpeed;
		y += ySpeed;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

}
