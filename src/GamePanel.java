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
// Trying to add the song 
import javax.swing.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	GameObject object;
	Pug pug;
	ObjectManager manager;
	Font titleFont;
	Font textFont;
	Font scoreFont;
	Font numOfDaysFont;
	Font instructionsFont;
	Font instructionsTitleFont;

	final int MENU_STATE = 0;
	final int INSTRUCTIONS_STATE = 1;
	final int GAME_STATE = 2;
	final int END_STATE = 3;
	final int CREDITS_STATE = 4;
	// final int END_STATE = 4;
	private int currentState = MENU_STATE;
	private String score;
	private static BufferedImage dayImg;
	private static BufferedImage nightImg;
	private static BufferedImage creditsImg;
	int day = 1;
	int night = 2;
	int currentTimeOfDay;
	int timeOfDayIncreaseScore = 3;
	int numOfDays = 1;
	int increaseOfDay = 2; // 2 days go by, pizza and choco speed increase
	int backgroundX = 0;
	int titleFontX = 20;
	int titleFontY = 80;
	int textFontX = 105;
	int textFontY = 400;

	AudioClip sound;

	GamePanel() {
		manager = new ObjectManager();

		timer = new Timer(1000 / 60, this);
		object = new GameObject();
		titleFont = new Font("Arial", Font.PLAIN, 26);
		textFont = new Font("Arial", Font.PLAIN, 15);
		scoreFont = new Font("Arial", Font.PLAIN, 20);
		numOfDaysFont = new Font("Arial", Font.PLAIN, 20);
		instructionsFont = new Font("Arial", Font.PLAIN, 20);
		instructionsTitleFont = new Font("Arial", Font.PLAIN, 20);
		try {
			dayImg = ImageIO.read(this.getClass().getResourceAsStream("day_background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			nightImg = ImageIO.read(this.getClass().getResourceAsStream("night.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			creditsImg = ImageIO.read(this.getClass().getResourceAsStream("creditspug.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void playSound(String fileName) {
		sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
		

	}

	private void stopSound() {
		sound.stop();
	}

	void updateMenuState() {

	}

	void updateInstructionsState() {

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

	void updateCreditsState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(new Color(237, 238, 255));
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		// Title
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("✿  Doug's Jumping Journey  ✿", 23, 80);

		// Text
		g.setColor(Color.BLACK);
		g.setFont(textFont);
		g.drawString("Press ENTER to PLAY", 120, 400);
		g.drawString("Press SPACE for INSTRUCTIONS", 80, 420);

	}

	void drawInstructionsState(Graphics g) {
		g.setColor(new Color(237, 238, 255));
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		// Title
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("✿ Instructions ✿", 100, 80);

		// Text
		g.setFont(textFont);
		g.drawString("Press ENTER to PLAY", 135, 400);
		g.drawString("For Doug to move, press the arrow keys!", 70, 420);
		g.drawString("Avoid the deadly chocolates and eat", 85, 440);
		g.drawString("Doug's favorite snack, pizza!", 108, 460);
	}

	void drawGameState(Graphics g) {

		if (manager.score == timeOfDayIncreaseScore) {
			if (currentTimeOfDay == day) {
				currentTimeOfDay = night;
				numOfDays++;

			}

			else if (currentTimeOfDay == night) {
				currentTimeOfDay = day;

				manager.pizzaSpeed += 2;
				System.out.println("pizza faster");
				manager.chocolateSpeed += 1;

				// Reseting the size of pug when a new day
				pug.width = 90;
				pug.height = 90;
				pug.x = 160;

			}
			timeOfDayIncreaseScore += 3;

		}

		if (currentTimeOfDay == day) {
			g.drawImage(dayImg, backgroundX, 0, 192 * 5, 192 * 5, null);
			g.drawImage(dayImg, backgroundX + 192 * 5, 0, 192 * 5, 192 * 5, null);

			backgroundX -= 5;
			if (backgroundX < -192 * 5) {
				backgroundX = 0;

			}

		}

		if (currentTimeOfDay == night) {
			System.out.println("night");
			g.drawImage(GamePanel.nightImg, backgroundX, 0, 192 * 5, 192 * 5, null);
			g.drawImage(nightImg, backgroundX + 192 * 5, 0, 192 * 5, 192 * 5, null);

			backgroundX -= 5;
			if (backgroundX < -192 * 5) {
				backgroundX = 0;

			}

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
		g.setColor(new Color(255, 232, 229));
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		stopSound();

		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("✿ Game Over ✿", 100, 80);

		g.setFont(textFont);
		g.drawString("Score: " + score, 170, 150);

		g.setFont(textFont);
		g.drawString("Press ENTER to RESET", 120, 400);

		g.setFont(textFont);
		g.drawString("Press SPACE to see the CREDITS", 80, 420);

	}

	void drawCreditsState(Graphics g) {
		g.setColor(new Color(237, 238, 255));
		g.fillRect(0, 0, Level2Game_Runner.width, Level2Game_Runner.height);

		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("✿ Credits ✿", 140, 130);

		g.setFont(textFont);
		g.drawString("Press ENTER to RESET", 115, 400);

		g.setFont(textFont);
		g.drawString("Inspired by Doug the Pug ", 110, 150);

		g.setFont(textFont);
		g.drawString("Follow @itsdougthepug on Instagram", 76, 420);

		g.drawImage(creditsImg, 80, 170, 217, 172, null);

	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == INSTRUCTIONS_STATE) {
			drawInstructionsState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		} else if (currentState == CREDITS_STATE) {
			drawCreditsState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == INSTRUCTIONS_STATE) {
			updateInstructionsState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		} else if (currentState == CREDITS_STATE) {
			updateCreditsState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void NewGame() {
		manager.reset();
		manager.score = 0;
		numOfDays = 0;
		currentTimeOfDay = day;
		pug = new Pug(160, 440, 90, 90);
		manager.addObject(pug);
		manager.pug = pug;
		currentState = GAME_STATE;
		timeOfDayIncreaseScore = 3; // I added this line of code because after each 3 pizzas, it wasn't changing to
									// night. The pug kept on growing, so I checked the increaseScore and I guess it
									// would have been after 6 then 9, etc
		playSound("themesong.wav");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				NewGame();

			} else if (currentState == END_STATE) {
				currentState = CREDITS_STATE;
			} else if (currentState == INSTRUCTIONS_STATE) {
				NewGame();
				currentState = GAME_STATE;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU_STATE) {
				currentState = INSTRUCTIONS_STATE;

			} else if (currentState == END_STATE) {
				currentState = CREDITS_STATE;
			}
		}

		// if (currentState > END_STATE) {
		// currentState = MENU_STATE;
		// }

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

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pug.ySpeed = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pug.ySpeed = 0;
		}

	}
}
