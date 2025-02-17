package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	public int x, y;
	public static final int SIZE = 30;
	public Color color;
	
	public Block(Color color) {
		this.color = color;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, SIZE, SIZE);
	}
}
