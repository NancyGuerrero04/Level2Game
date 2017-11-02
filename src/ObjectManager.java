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
	int chocolateMaxSpawnTime = 20000; 
	int pizzaMaxSpawnTime = 20000;
	int pizzaSpeed = 1; 
	int chocolateSpeed =1;
	int speedIncreaseScore = 1;
	

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
			pizzaSpawnTime = new Random().nextInt(pizzaMaxSpawnTime);
			addObject(new Pizza(800, 600, 70, 70, pizzaSpeed));
			pizzaTimer = System.currentTimeMillis();
		}
		
			
	}

	public void manageChocolate() {
		if (System.currentTimeMillis() - chocolateTimer >= chocolateSpawnTime) {
			chocolateSpawnTime = new Random().nextInt(chocolateMaxSpawnTime);
			addObject(new Chocolate(800, 600, 70, 70, chocolateSpeed));
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
