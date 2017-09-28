import java.awt.Color;
import java.awt.Graphics;

public class Pug extends GameObject {
	int xSpeed;
	int ySpeed;
	int gravity = 1;

	Pug(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		xSpeed = 2; 
		ySpeed = 0; 

	}

	void update() {
		super.update();
		x += xSpeed;
		y += ySpeed;
		ySpeed += gravity;
		
		if(y >= 650 - height){
			y = 650 - height; 
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

}
