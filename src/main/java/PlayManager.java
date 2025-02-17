package main.java;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import mino.Block;
import mino.Mino;
import mino.Mino_L1;
import mino.Mino_L2;
import mino.Mino_Square;

public class PlayManager {
	final int WIDTH = 360;
	final int HEIGHT = 600;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;

	Mino currentMino, nextMino;
	final int MINO_START_X;
	final int MINO_START_Y;
	final int NEXT_MINO_START_X;
	final int NEXT_MINO_START_Y;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();

	public static int dropInterval = 60; // 60 frames = 1 second

	boolean effectCounterOn;
	int effectCounter;
	ArrayList<Integer> effectY = new ArrayList<>();
	boolean gameOver;

	int level = 1;
	int lines, score;

	public PlayManager() {
		// Set up where the teriminos are actually going to be falling
		left_x = (GamePanel.WIDTH - WIDTH) / 2;
		right_x = left_x + WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;

		MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
		MINO_START_Y = top_y + Block.SIZE;
		currentMino = pickMino();
		currentMino.setXY(MINO_START_X, MINO_START_Y);

		NEXT_MINO_START_X = right_x + 175;
		NEXT_MINO_START_Y = top_y + 500;

		nextMino = pickMino();
		nextMino.setXY(NEXT_MINO_START_X, NEXT_MINO_START_Y);
	}

	private Mino pickMino() {
		Mino mino;
		int i = new Random().nextInt(3);

		switch (i) {
		case 0:
			mino = new Mino_L1();
			break;
		case 1:
			mino = new Mino_L2();
			break;
		default:
			mino = new Mino_Square();
		}

		return mino;
	}

	public void update() {
		if (!currentMino.active) {
			for (int i = 0; i < 4; i++) {
				staticBlocks.add(currentMino.blocks[i]);
			}

			if (currentMino.blocks[0].x == MINO_START_X && currentMino.blocks[0].y == MINO_START_Y) {
				gameOver = true;
			}

			currentMino.deactiviating = false;

			currentMino = nextMino;
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino = pickMino();
			nextMino.setXY(NEXT_MINO_START_X, NEXT_MINO_START_Y);

			checkDelete();
		} else {
			currentMino.update();
		}
	}

	private void checkDelete() {
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		int lineCount = 0;

		while (x < right_x && y < bottom_y) {
			for (Block staticBlock : staticBlocks) {
				blockCount += (staticBlock.x == x && staticBlock.y == y) ? 1 : 0;
			}

			x += Block.SIZE;

			if (x == right_x) {

				if (blockCount == 12) {

					effectCounterOn = true;
					effectY.add(y);

					for (int i = staticBlocks.size() - 1; i >= 0; i--) {
						if (staticBlocks.get(i).y == y)
							staticBlocks.remove(i);
					}

					lineCount++;
					lines++;

					if (lines % 10 == 0 && dropInterval > 1) {
						level++;
						dropInterval -= (dropInterval > 10) ? 10 : 1;

					}

					for (Block staticBlock : staticBlocks)
						if (staticBlock.y < y)
							staticBlock.y += Block.SIZE;
				}

				blockCount = 0;
				x = left_x;
				y += Block.SIZE;
			}
		}

		score += (lineCount > 0) ? (10 * level) * lineCount : 0;

	}

	public void draw(Graphics2D g2) {

		// Mino dropping zone
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4f));
		g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

		// Next incoming mino preview
		int x = right_x + 100;
		int y = bottom_y - 200;
		g2.drawRect(x, y, 200, 200);
		g2.setFont(new Font("Arial", Font.PLAIN, 30));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString("NEXT", x + 60, y + 60);

		// Scoring frame
		g2.drawRect(x, top_y, 250, 300);
		x += 40;
		y = top_y + 90;
		g2.drawString("LEVEL: " + level, x, y);
		y += 70;
		g2.drawString("LINES: " + lines, x, y);
		y += 70;
		g2.drawString("SCORE: " + score, x, y);

		// Draw the current mino
		if (currentMino != null)
			currentMino.draw(g2);

		nextMino.draw(g2);

		for (Block staticBlock : staticBlocks) {
			staticBlock.draw(g2);
		}

		// For effects
		if (effectCounterOn) {
			effectCounter++;

			g2.setColor(Color.RED);
			for (Integer effY : effectY) {
				g2.fillRect(left_x + 2, effY + 2, WIDTH - 3, Block.SIZE - 3);
			}

			if (effectCounter >= 10) {
				effectCounterOn = false;
				effectCounter = 0;
				effectY.clear();
			}
		}

		g2.setFont(g2.getFont().deriveFont(50f));
		if (gameOver) {
			g2.setColor(Color.RED);
			x = left_x + Block.SIZE;
			y = top_y + 11 * Block.SIZE;
			g2.drawString("GAME OVER", x, y);
		} else if (KeyHandler.pause) {
			g2.setColor(Color.YELLOW);
			x = left_x + 3 * Block.SIZE;
			y = top_y + 11 * Block.SIZE;
			g2.drawString("PAUSED", x, y);
		}

		x = 35;
		y = top_y + 11 * Block.SIZE;
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("Weird Tetris", x, y);
		;

	}
}
