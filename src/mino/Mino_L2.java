package mino;

import java.awt.Color;

public class Mino_L2 extends Mino {
	public Mino_L2() {
		super.create(Color.BLUE);
	}
	
	public void setXY(int x, int y) {
		/*
		 *   o
		 *   o   <- b_0 is this one. Center of rotation
		 * o o
		 * 
		 * Let's use this to show it:
		 * 		1
		 * 		0
		 * 	  3 2
		 */
		blocks[0].x = x;
		blocks[0].y = y;

		blocks[1].x = x;
		blocks[1].y = y - Block.SIZE;

		blocks[2].x = x;
		blocks[2].y = y + Block.SIZE;

		blocks[3].x = x - Block.SIZE;
		blocks[3].y = y + Block.SIZE;
	}
	
	public void getDirection1() {
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x;
		tempBlocks[1].y = blocks[0].y - Block.SIZE;

		tempBlocks[2].x = blocks[0].x;
		tempBlocks[2].y = blocks[0].y + Block.SIZE;

		tempBlocks[3].x = blocks[0].x - Block.SIZE;
		tempBlocks[3].y = blocks[0].y + Block.SIZE;

		updateXY(1);
	}
	
	
	public void getDirection2() {
		/*
		 * 3
		 * 2 0 1
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x + Block.SIZE;
		tempBlocks[1].y = blocks[0].y;

		tempBlocks[2].x = blocks[0].x - Block.SIZE;
		tempBlocks[2].y = blocks[0].y;

		tempBlocks[3].x = blocks[0].x - Block.SIZE;
		tempBlocks[3].y = blocks[0].y - Block.SIZE;

		updateXY(2);
	}
	public void getDirection3() {
		/*
		 *   2 3
		 *   0
		 *   1
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x;
		tempBlocks[1].y = blocks[0].y + Block.SIZE;

		tempBlocks[2].x = blocks[0].x;
		tempBlocks[2].y = blocks[0].y - Block.SIZE;

		tempBlocks[3].x = blocks[0].x + Block.SIZE;
		tempBlocks[3].y = blocks[0].y - Block.SIZE;

		updateXY(3);
	}
	public void getDirection4() {
		/*		
		 *   1 0 2
		 *       3
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x - Block.SIZE;
		tempBlocks[1].y = blocks[0].y;

		tempBlocks[2].x = blocks[0].x + Block.SIZE;
		tempBlocks[2].y = blocks[0].y;

		tempBlocks[3].x = blocks[0].x + Block.SIZE;
		tempBlocks[3].y = blocks[0].y + Block.SIZE;

		updateXY(4);
	}
}
