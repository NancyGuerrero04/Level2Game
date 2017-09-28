import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x=200;
	int y=500;
	int width;
	int height;
	boolean isAlive;
	Rectangle collisionBox; 
	
	GameObject() {
		isAlive = true; 
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x,y,width,height);
	}
	
	void draw(Graphics g){ 
		
	}
	
	
}
