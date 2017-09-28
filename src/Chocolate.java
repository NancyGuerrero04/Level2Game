import java.awt.Color;
import java.awt.Graphics;

public class Chocolate extends GameObject{
	Chocolate(int x, int y, int width, int height){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
	}

	void update(){
		super.update();
		x--;
	}
	
	void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
