import java.awt.Color;
import java.awt.Graphics;

public class Pizza extends GameObject {
	int xSpeed; 
	Pizza(int x, int y, int width, int height, int xSpeed) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		
	}

	void update() {
		super.update();
		x-=xSpeed;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}

	
}
