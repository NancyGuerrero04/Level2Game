import java.awt.Color;
import java.awt.Graphics;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive;
	
	GameObject() {
		isAlive = true; 
	}

	void update() {
		x++;
	}
	
	void draw(Graphics g){ 
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 100, 100);
	}
	
	
}
