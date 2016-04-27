package mx.arturoysamuel.graphics;

import java.awt.Graphics;
import java.awt.geom.Line2D;

public class RoadLine {
	
	Line2D roadLine;
	String str;
	
	public RoadLine(int orientation, int xInit, int yInit, int xLast, int yLast) {
		this.roadLine = new Line2D.Double(xInit, yInit, xLast, yLast);
	}
	
	public Line2D getRoadLine() {
		return this.roadLine;
	}
	
	public double getXInit() {
		return this.roadLine.getX1();
	}
	
	public double getXLast() {
		return this.roadLine.getX2();
	}
	
	public double getYInit() {
		return this.roadLine.getY1();
	}
	
	public double getYLast() {
		return this.roadLine.getY2();
	}
	
	public void paintInitString(Graphics g, String str) {
		this.str = str;
		g.drawString(str, (int) this.roadLine.getX1() - 15, (int) this.roadLine.getY1() + 10);
	}
	
	public void paintFinalString(Graphics g, String str) {
		this.str = str;
		g.drawString(str, (int) this.roadLine.getX2() + 10, (int) this.roadLine.getY2() + 10);
	}	
}
