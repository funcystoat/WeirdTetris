package mino;

import java.awt.Color;
import java.awt.Graphics2D;

import main.java.KeyHandler;
import main.java.PlayManager;

public class Mino {
	public Block blocks[] = new Block[4];
	public Block tempBlocks[] = new Block[4];
	int autoDropCounter = 0;
	public int direction = 1;
	boolean leftCollide, rightCollide, botCollide;
	public boolean active = true;
	public boolean deactiviating;
	int deactivationCounter = 0;

	public void create(Color color) {
		for (int i = 0; i < 4; i++) {
			blocks[i] = new Block(color);
			tempBlocks[i] = new Block(color);
		}
	}

	public void setXY(int x, int y) {
	};

	public void updateXY(int direction) {
		checkRotationCollision();

		if (leftCollide || rightCollide || botCollide)
			return;

		this.direction = direction;
		for (int i = 0; i < 4; i++) {
			blocks[i].x = tempBlocks[i].x;
			blocks[i].y = tempBlocks[i].y;
		}
	};

	public void getDirection1() {
	}

	public void getDirection2() {
	}

	public void getDirection3() {
	}

	public void getDirection4() {
	}

	public void checkMovementCollision() {
		leftCollide = false;
		rightCollide = false;
		botCollide = false;

		checkStaticBlockCollision();

		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].x == PlayManager.left_x) {
				leftCollide = true;
			}
			if (blocks[i].x + Block.SIZE == PlayManager.right_x) {
				rightCollide = true;
			}
			if (blocks[i].y + Block.SIZE == PlayManager.bottom_y) {
				botCollide = true;
			}
		}
	}

	public void checkRotationCollision() {
		leftCollide = false;
		rightCollide = false;
		botCollide = false;

		checkStaticBlockCollision();
		boolean collisionAlready = leftCollide || rightCollide || botCollide;

		for (int i = 0; i < tempBlocks.length && !collisionAlready; i++) {
			if (tempBlocks[i].x < PlayManager.left_x) {
				leftCollide = true;
			}
			if (tempBlocks[i].x + Block.SIZE > PlayManager.right_x) {
				rightCollide = true;
			}
			if (tempBlocks[i].y + Block.SIZE > PlayManager.bottom_y) {
				botCollide = true;
			}
			collisionAlready = leftCollide || rightCollide || botCollide;
		}
	}

	private void checkStaticBlockCollision() {
		for (Block staticBlock : PlayManager.staticBlocks) {
			for (Block block : blocks) {
				if (block.y + Block.SIZE == staticBlock.y && block.x == staticBlock.x) {
					botCollide = true;
				}
				if (block.x - Block.SIZE == staticBlock.x && block.y == staticBlock.y) {
					leftCollide = true;
				}
				if (block.x + Block.SIZE == staticBlock.x && block.y == staticBlock.y) {
					rightCollide = true;
				}
			}
		}
	}

	public void update() {
		if (deactiviating)
			deactiviating();

		if (KeyHandler.up) {
			switch (direction) {
			case 1:
				getDirection2();
				break;
			case 2:
				getDirection3();
				break;
			case 3:
				getDirection4();
				break;
			case 4:
				getDirection1();
				break;
			}
			KeyHandler.up = false;
		}

		checkMovementCollision();

		if (KeyHandler.left) {
			if (!leftCollide) {
				for (Block block : blocks) {
					block.x -= Block.SIZE;
				}
			}
			KeyHandler.left = false;
		}

		if (KeyHandler.down) {
			if (!botCollide) {
				for (Block block : blocks) {
					block.y += Block.SIZE;
				}
				autoDropCounter = 0;
			}
			KeyHandler.down = false;
		}

		if (KeyHandler.right) {
			if (!rightCollide) {
				for (Block block : blocks) {
					block.x += Block.SIZE;
				}
			}
			KeyHandler.right = false;
		}

		if (botCollide) {
			deactiviating = true;

		} else {
			autoDropCounter++;
			if (autoDropCounter >= PlayManager.dropInterval) {
				for (int i = 0; i < 4; i++) {
					blocks[i].y += Block.SIZE;
				}
				autoDropCounter = 0;
			}
		}
	};

	private void deactiviating() {
		deactivationCounter++;

		if (deactivationCounter >= 45) {
			deactivationCounter = 0;
			checkMovementCollision();

			if (botCollide)
				active = false;
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(blocks[0].color);

		int margin = 2;
		for (int i = 0; i < 4; i++) {
			g2.fillRect(blocks[i].x + margin, blocks[i].y + margin, Block.SIZE - 2 * margin, Block.SIZE - 2 * margin);
		}

	};
}
