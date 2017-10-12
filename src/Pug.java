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
		xSpeed = 0; 
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
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

}
