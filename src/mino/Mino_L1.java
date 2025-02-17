package mino;

import java.awt.Color;

public class Mino_L1 extends Mino {

	public Mino_L1() {
		super.create(Color.orange);
	}
	
	public void setXY(int x, int y) {
		/*
		 * o
		 * o   <- b_0 is this one. Center of rotation
		 * o o
		 */
		blocks[0].x = x;
		blocks[0].y = y;
		
		/*
		 * o   <- b_1 is this one.
		 * o   
		 * o o
		 */
		blocks[1].x = x;
		blocks[1].y = y - Block.SIZE;
		
		/*
		 * 						o   
		 * 						o   
		 *  b_2 is this one. -> o o
		 */
		blocks[2].x = x;
		blocks[2].y = y + Block.SIZE;
		
		/*
		 * o   
		 * o   
		 * o o <- b_3 is this one.
		 */
		blocks[3].x = x + Block.SIZE;
		blocks[3].y = y + Block.SIZE;
	}
	
	public void getDirection1() {
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x;
		tempBlocks[1].y = blocks[0].y - Block.SIZE;

		tempBlocks[2].x = blocks[0].x;
		tempBlocks[2].y = blocks[0].y + Block.SIZE;

		tempBlocks[3].x = blocks[0].x + Block.SIZE;
		tempBlocks[3].y = blocks[0].y + Block.SIZE;

		updateXY(1);
	}
	
	
	public void getDirection2() {
		/*
		 *   V this one is b_0. Center of rotation
		 * o o o
		 * o
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x + Block.SIZE;
		tempBlocks[1].y = blocks[0].y;

		tempBlocks[2].x = blocks[0].x - Block.SIZE;
		tempBlocks[2].y = blocks[0].y;

		tempBlocks[3].x = blocks[0].x - Block.SIZE;
		tempBlocks[3].y = blocks[0].y + Block.SIZE;

		updateXY(2);
	}
	public void getDirection3() {
		/*
		 * o o
		 *   o <- this one is b_0. Center of rotation
		 *   o
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x;
		tempBlocks[1].y = blocks[0].y + Block.SIZE;

		tempBlocks[2].x = blocks[0].x;
		tempBlocks[2].y = blocks[0].y - Block.SIZE;

		tempBlocks[3].x = blocks[0].x - Block.SIZE;
		tempBlocks[3].y = blocks[0].y - Block.SIZE;

		updateXY(3);
	}
	public void getDirection4() {
		/*
		 *     o
		 * o o o
		 *   ^ this one is b_0. Center of rotation
		 */
		tempBlocks[0].x = blocks[0].x;
		tempBlocks[0].y = blocks[0].y;

		tempBlocks[1].x = blocks[0].x - Block.SIZE;
		tempBlocks[1].y = blocks[0].y;

		tempBlocks[2].x = blocks[0].x + Block.SIZE;
		tempBlocks[2].y = blocks[0].y;

		tempBlocks[3].x = blocks[0].x + Block.SIZE;
		tempBlocks[3].y = blocks[0].y - Block.SIZE;

		updateXY(4);
	}
}
