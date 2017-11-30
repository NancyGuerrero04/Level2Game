import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;

	int score = 0;

	long pizzaTimer = 0;
	long chocolateTimer = 0;
	int pizzaSpawnTime = 3000;
	int chocolateSpawnTime = 3000;
	int chocolateMaxSpawnTime = 5000; 
	int pizzaMaxSpawnTime = 5000;
	int pizzaSpeed = 1; 
	int chocolateSpeed =1;
	int speedIncreaseScore = 1;
	Pug pug; 
	

	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
		}

		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void managePiazzas() {

		if (System.currentTimeMillis() - pizzaTimer >= pizzaSpawnTime) {
			pizzaSpawnTime = pizzaMaxSpawnTime + new Random().nextInt(2000);
			int pizzaY = new Random().nextInt(600);
			addObject(new Pizza(800, pizzaY, 70, 70, pizzaSpeed));
			
			pizzaTimer = System.currentTimeMillis();
		}
		
			
	}

	public void manageChocolate() {
		if (System.currentTimeMillis() - chocolateTimer >= chocolateSpawnTime) {
			chocolateSpawnTime = chocolateMaxSpawnTime + new Random().nextInt(2000);
			int chocolateY = new Random().nextInt(600);
			addObject(new Chocolate(800, chocolateY, 70, 70, chocolateSpeed));
			chocolateTimer = System.currentTimeMillis();

		}

	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);

				if (o1.collisionBox.intersects(o2.collisionBox)) {
					if ((o1 instanceof Chocolate && o2 instanceof Pug)
							|| (o2 instanceof Chocolate && o1 instanceof Pug)) {
						o1.isAlive = false;
						o2.isAlive = false;
					}
					if ((o1 instanceof Pizza && o2 instanceof Pug)
							|| (o2 instanceof Pizza && o1 instanceof Pug)) {
						if(o1 instanceof Pizza){
							o1.isAlive = false;
						}
						if (o2 instanceof Pizza){
							o2.isAlive = false;
						}
						score++;
						pizzaMaxSpawnTime -= 100; 
						chocolateMaxSpawnTime -= 100; 
						pug.getBigger();
						
						 
						
						}
					}
				}
			}
		}
	
	
	

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public void reset() {
		objects.clear();
	}
}
