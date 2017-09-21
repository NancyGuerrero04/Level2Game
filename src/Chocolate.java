import java.awt.Color;
import java.awt.Graphics;

public class Chocolate extends GameObject{
	Chocolate(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
	}

	void update(){
		x--;
	}
	
	void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
