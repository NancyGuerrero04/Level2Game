import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	GameObject object;
	Pug pug;
	ObjectManager manager;
	Font titleFont;
	Font gameOverFont;
	Font scoreFont;
	Font resetFont;
	Font numOfDaysFont;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	private int currentState = MENU_STATE;
	private String score;
	private static BufferedImage dayImg;
	private static BufferedImage nightImg;
	int day = 1;
	int night = 2;
	int currentTimeOfDay;
	int timeOfDayIncreaseScore = 2;
	int numOfDays=1; 
	int increaseOfDay = 2; // 2 days go by, pizza and choco speed increase 

	GamePanel() {
		manager = new ObjectManager();
		timer = new Timer(1000 / 60, this);
		object = new GameObject();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		gameOverFont = new Font("Arial", Font.PLAIN, 48);
		scoreFont = new Font("Arial", Font.PLAIN, 20);
		resetFont = new Font("Arial", Font.PLAIN, 20);
		numOfDaysFont = new Font("Arial", Font.PLAIN, 20);

		try {
			dayImg = ImageIO.read(this.getClass().getResourceAsStream("day_background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			nightImg = ImageIO.read(this.getClass().getResourceAsStream("night.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void updateMenuState() {

	}

	void updateGameState() {

		manager.update();
		manager.managePiazzas();
		manager.manageChocolate();
		manager.checkCollision();
		score = String.valueOf(manager.getScore());
		if (pug.isAlive == false) {
			currentState = END_STATE;

		}

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("Title", 150, 80);

	}

	void drawGameState(Graphics g) {
		if (manager.score == timeOfDayIncreaseScore) {
			if (currentTimeOfDay == day) {
				currentTimeOfDay = night;
				numOfDays++; 
				if(numOfDays == increaseOfDay){
					manager.pizzaSpeed++; 
					System.out.println("pizza faster");
					manager.chocolateSpeed++;
					

					increaseOfDay += 2;
				}
				
			}

			else if (currentTimeOfDay == night) {
				currentTimeOfDay = day;
				
			}
			timeOfDayIncreaseScore += 2; 
			
		}
		
		if(currentTimeOfDay == day){
			g.drawImage(dayImg, 0, 0, 192 * 5, 192 * 5, null);
		}
		
		if(currentTimeOfDay == night){
			g.drawImage(GamePanel.nightImg, -500, 0, 470 * 2, 390 * 2, null);
		}
		
		manager.draw(g);
		
		g.setColor(Color.YELLOW);
		g.setFont(scoreFont);
		g.drawString("Score: " + score, 300, 40);
		
		g.setColor(Color.YELLOW);
		g.setFont(scoreFont);
		g.drawString("Day: " + numOfDays, 300, 80);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		g.setColor(Color.BLACK);
		g.setFont(gameOverFont);
		g.drawString("Game Over!", 70, 80);

		g.setFont(scoreFont);
		g.drawString("Score: " + score, 150, 150);

		g.setFont(resetFont);
		g.drawString("Press ENTER to Reset", 80, 250);

	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		System.out.println("a");

	}

	public void NewGame() {
		manager.score =0; 
		numOfDays=0; 
		currentTimeOfDay = day;
		pug = new Pug(180, 440, 90, 90);
		manager.addObject(pug);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		System.out.println("b");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState++;
				if (currentState == GAME_STATE) {
					NewGame();
				}
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}

			// if(MENU_STATE > currentState){
			// MENU_STATE = GAME_STATE;
			// }
		}

		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}

		// Arrow Keys
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pug.ySpeed = -20;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pug.ySpeed = 5;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("c");

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pug.ySpeed = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pug.ySpeed = 0;
		}

	}
}
