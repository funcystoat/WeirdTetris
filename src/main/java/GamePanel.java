package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	final int FPS = 60;
	Thread gameThread;
	PlayManager pm;
	public static Sound music = new Sound();
	public static Sound soundEffect = new Sound();

	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		this.setLayout(null);

		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);

		pm = new PlayManager();
	}

	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
		
		music.play(0, true);
		music.loop();
	}

	private void update() {
		if (!KeyHandler.pause && !pm.gameOver)
			pm.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics2d = (Graphics2D) g;
		pm.draw(graphics2d);
	}

	@Override
	public void run() {
		// How often should we draw?
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			// delta tracks whether or not it is time to draw
			// a new frame. Note that the units are just
			// Frames, as the seconds cancel out.
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			// If delta = 1 Frame or more, time
			// update data and the screen
			if (delta >= 1) {
				update();
				repaint();

				// Better to do this than delta = 0
				// because if delta = 1.23, then
				// we have delta = 0.23, and we will update
				// sooner. Prevent lag.
				delta--;
			}
		}
	}
}
