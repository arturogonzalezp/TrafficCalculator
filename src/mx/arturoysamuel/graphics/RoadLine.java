package mx.arturoysamuel.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;

public class RoadLine {
	
	private Line2D roadLine;
	private String str;
	private int orientation;
	private boolean streetOrientation;
	
	public RoadLine(int orientation, boolean streetOrientation, int xInit, int yInit, int xLast, int yLast) {
		this.orientation = orientation;
		this.streetOrientation = streetOrientation;
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
	
	public void paintInitArrow(Graphics g){
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		int nPoints = 3;
		int arrowBase = 10;
		int moveBy = 15;
		if(this.orientation == 0){
			yPoints[0] = (int) this.roadLine.getY1() - arrowBase;
			yPoints[1] = (int) this.roadLine.getY1() + arrowBase;
			yPoints[2] = (int) this.roadLine.getY1();
			if(this.streetOrientation){
				xPoints[0] = (int) this.roadLine.getX1() + moveBy;
				xPoints[1] = (int) this.roadLine.getX1() + moveBy;
				xPoints[2] = (int) this.roadLine.getX1() + moveBy + (2*arrowBase);
			}else{
				xPoints[0] = (int) this.roadLine.getX1() + moveBy + (2*arrowBase);
				xPoints[1] = (int) this.roadLine.getX1() + moveBy + (2*arrowBase);
				xPoints[2] = (int) this.roadLine.getX1() + moveBy;
			}
		}else if(this.orientation == 1){
			xPoints[0] = (int) this.roadLine.getX1() - arrowBase;
			xPoints[1] = (int) this.roadLine.getX1() + arrowBase;
			xPoints[2] = (int) this.roadLine.getX1();
			if(this.streetOrientation){
				yPoints[0] = (int) this.roadLine.getY1() + moveBy;
				yPoints[1] = (int) this.roadLine.getY1() + moveBy;
				yPoints[2] = (int) this.roadLine.getY1() + moveBy + (2*arrowBase);
			}else{
				yPoints[0] = (int) this.roadLine.getY1() + moveBy + (2*arrowBase);
				yPoints[1] = (int) this.roadLine.getY1() + moveBy + (2*arrowBase);
				yPoints[2] = (int) this.roadLine.getY1() + moveBy;
			}
		}
		
		g.setColor(Color.BLACK);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	public void paintFinalArrow(Graphics g){
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		int nPoints = 3;
		int arrowBase = 10;
		int moveBy = 15;
		if(this.orientation == 0){
			yPoints[0] = (int) this.roadLine.getY2() - arrowBase;
			yPoints[1] = (int) this.roadLine.getY2() + arrowBase;
			yPoints[2] = (int) this.roadLine.getY2();
			if(this.streetOrientation){
				xPoints[0] = (int) this.roadLine.getX2() - moveBy - (2*arrowBase);
				xPoints[1] = (int) this.roadLine.getX2() - moveBy - (2*arrowBase);
				xPoints[2] = (int) this.roadLine.getX2() - moveBy;
			}else{
				xPoints[0] = (int) this.roadLine.getX2() - moveBy;
				xPoints[1] = (int) this.roadLine.getX2() - moveBy;
				xPoints[2] = (int) this.roadLine.getX2() - moveBy - (2*arrowBase);
			}
		}else if(this.orientation == 1){
			xPoints[0] = (int) this.roadLine.getX2() - arrowBase;
			xPoints[1] = (int) this.roadLine.getX2() + arrowBase;
			xPoints[2] = (int) this.roadLine.getX2();
			if(this.streetOrientation){
				yPoints[0] = (int) this.roadLine.getY2() - moveBy - (2*arrowBase);
				yPoints[1] = (int) this.roadLine.getY2() - moveBy - (2*arrowBase);
				yPoints[2] = (int) this.roadLine.getY2() - moveBy;
			}else{
				yPoints[0] = (int) this.roadLine.getY2() - moveBy;
				yPoints[1] = (int) this.roadLine.getY2() - moveBy;
				yPoints[2] = (int) this.roadLine.getY2() - moveBy - (2*arrowBase);
			}
		}
		
		g.setColor(Color.BLACK);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
}
