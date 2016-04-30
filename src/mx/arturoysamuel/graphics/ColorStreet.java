package mx.arturoysamuel.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

public class ColorStreet {
	
	private String xVariable;
	private int xInitial;
	private int yInitial;
	private int xLast;
	private int yLast;
	private int colorCodeRed;
	private int colorCodeGreen;
	Line2D colorStreet;
	
	public ColorStreet(String xVariable, int xInitial, int yInitial, int xLast, int yLast) {
		this.colorStreet = new Line2D.Double(xInitial, yInitial, xLast, yLast);
		this.xVariable = xVariable;
		this.xInitial = xInitial;
		this.yInitial = yInitial;
		this.xLast = xLast;
		this.yLast = yLast;
	}
	
	public void setXVariable(String xVariable) {
		this.xVariable = xVariable;
	}
	
	public String getXVariable() {
		return this.xVariable;
	}
	
	public void setXInitial(int xInitial) {
		this.xInitial = xInitial;
	}
	
	public int getXInitial() {
		return this.xInitial;
	}
	
	public void setYInitial(int yInitial) {
		this.yInitial = yInitial;
	}
	
	public int getYInitial() {
		return this.yInitial;
	}
	
	public void setXLast(int xLast) {
		this.xLast = xLast;
	}
	
	public int getXLast() {
		return this.xInitial;
	}
	
	public void setYLast(int yLast) {
		this.yLast = yLast;
	}
	
	public int getYLast() {
		return this.yInitial;
	}
	
	
	public void setColorCodeRed(int colorCodeRed) {
		this.colorCodeRed = colorCodeRed;
	}
	
	public int getColorCodeRed() {
		return this.colorCodeRed;
	}
	
	public void setColorCodeGreen(int colorCodeGreen) {
		this.colorCodeGreen = colorCodeGreen;
	}
	
	public int getColorCodeGreen() {
		return this.colorCodeGreen;
	}
	
	public Line2D getColorStreet() {
		return this.colorStreet;
	}
	
	public void paintStreet(Graphics g) {
		g.setColor(new Color(this.colorCodeRed, this.colorCodeGreen, 0));
	}
}
