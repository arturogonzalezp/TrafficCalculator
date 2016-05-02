package mx.arturoysamuel.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import mx.arturoysamuel.calculator.XValue;

public class TrafficPanel extends JPanel {

	
	private int streetsCount;
	private RoadLine[] roadLine;
	private ColorStreet[] colorStreet;
	private int[] xCoordinates;
	private int[] yCoordinates;
	private int maxValue;
	private int minValue;
	private List<XValue> xValue;
	private boolean[] directionsH, directionsV;
	
	private boolean isStreetColored = false;
	
	private static final int X_INIT = 50;
	private static final int X_LAST = 450;
	private static final int Y_INIT = 50;
	private static final int Y_LAST = 450;

	public TrafficPanel(int streetsCount, boolean[] directionsV, boolean[] directionsH) {
		this.streetsCount = streetsCount;
		this.directionsV = directionsV;
		this.directionsH = directionsH;
		this.setPreferredSize(new Dimension(500, 500));
		this.setBorder(BorderFactory.createTitledBorder("Traffic Calculator"));
	}

	public int getStreetsCount() {
		return this.streetsCount;
	}
	
	public void setXValue(List<XValue> xValue) {
		this.xValue = xValue;
	}
	
	public List<XValue> getXValue() {
		return this.xValue;
	}
	
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	
	public int getMinValue() {
		return this.minValue;
	}
	
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public int getMaxValue() {
		return this.maxValue;
	}
	
	public void setIsStreetsColored(boolean isStreetsColored) {
		this.isStreetColored = isStreetsColored;
	}
	
	private boolean getIsStreetsColored() {
		return this.isStreetColored;
	}
	
	public void paintLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		this.roadLine = new RoadLine[this.streetsCount];

		int xDiv;
		if (this.streetsCount % 2 == 0) {
			xDiv = (int) Math.ceil((this.streetsCount / 2)) + 1;
		}
		else {
			xDiv = (int) Math.ceil((this.streetsCount / 2)) + 2;
		}
		int yDiv = (int) Math.ceil((this.streetsCount / 2)) + 1;
		int xVar = this.getPreferredSize().width / xDiv;
		int yVar = this.getPreferredSize().height / yDiv;
		int j = 1;
		double jumpValue = Math.ceil(((double) this.streetsCount / (double) 2));
		int addValue;
		int addValue2;
		int stabValue = 0;
		
		int oddNumberOfStreets;
		int arrayIndex = 0;
		
		if (this.streetsCount % 2 == 0) {
			addValue = 1;
			addValue2 = 0;
			oddNumberOfStreets = (int) Math.ceil((double) this.roadLine.length / (double) 2);
			this.xCoordinates = new int[oddNumberOfStreets * oddNumberOfStreets];
			this.yCoordinates = new int[oddNumberOfStreets * oddNumberOfStreets];
		}
		else {
			addValue = -1;
			addValue2 = 1;
			oddNumberOfStreets = (int) Math.floor((double) this.roadLine.length / (double) 2);
			this.xCoordinates = new int[(int) (Math.ceil((double) this.roadLine.length / (double) 2)) * oddNumberOfStreets];
			this.yCoordinates = new int[(int) (Math.ceil((double) this.roadLine.length / (double) 2)) * oddNumberOfStreets];
		}
		int vCounter = 0;
		int hCounter = 0;
		boolean stepper = true;
		boolean isPositiveStreet;
		for (int i = 0; i < this.roadLine.length; i++) {
			System.out.println("i: " + i + "\n");
			if(stepper){
				isPositiveStreet = this.directionsV[vCounter];
				vCounter++;
				stepper = false;
			}else{
				isPositiveStreet = this.directionsH[hCounter];
				hCounter++;
				stepper = true;
			}
			
			if (i % 2 == 0) {
				this.roadLine[i] = new RoadLine(1, isPositiveStreet, xVar * j, Y_INIT, xVar * j, Y_LAST);
				this.roadLine[i].paintInitString(g2d, Character.toString((char) (64 + j)));
				this.roadLine[i].paintFinalString(g2d, Character.toString((char) (64 + jumpValue * 3 - j + addValue + addValue2)));
				for (int k = 0; k < oddNumberOfStreets + addValue2; k++) {
					if (j == oddNumberOfStreets + 1) {
						break;
					}
					if ((this.streetsCount == 5 && j == 3) || (this.streetsCount == 7 && j == 4)) {
						stabValue = -1;
					}
					g.fillOval(xVar + (k * xVar) - 5, yVar * (j + stabValue) - 5, 10, 10);
					this.xCoordinates[arrayIndex] = xVar + (k * xVar);
					this.yCoordinates[arrayIndex] = yVar * (j + stabValue);
					arrayIndex++;
				}
			}
			else {
				this.roadLine[i] = new RoadLine(0,isPositiveStreet, X_INIT, yVar * j, X_LAST, yVar * j);
				this.roadLine[i].paintFinalString(g2d, Character.toString((char) (64 + j + jumpValue)));
				this.roadLine[i].paintInitString(g2d, Character.toString((char) (64 + jumpValue * 4 - j + addValue)));
				j++;
			}
			this.roadLine[i].paintInitArrow(g2d);
			this.roadLine[i].paintFinalArrow(g2d);
			g2d.draw(this.roadLine[i].getRoadLine());
		}	
	}

	public void paintVariables(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int xIndex = 0;
		int streetsEven;
		int streetsOdd;
		int xIndexHorizontal;
		int xIndexVertical;
		int widthDivision;
		int heightDivision;
		int widthDivided;
		int heightDivided;
		
		if (this.streetsCount % 2 == 0) {
			streetsOdd = 1;
			streetsEven = 0;
			xIndexHorizontal = (this.streetsCount / 2) - 1;
			xIndexVertical = this.streetsCount / 2;
			widthDivision = (this.streetsCount / 2) + 1;
			heightDivision = (this.streetsCount / 2) + 1;
		}
		else {
			streetsOdd = 0;
			streetsEven = 1;
			xIndexHorizontal = (int) Math.floor((double) this.streetsCount / (double) 2);
			xIndexVertical = (int) Math.ceil((double) this.streetsCount / (double) 2);
			widthDivision = (int) (Math.ceil((double) this.streetsCount / (double) 2)) + 1;
			heightDivision = (int) (Math.ceil((double) this.streetsCount / (double) 2));	
		}
		widthDivided = this.getPreferredSize().width / widthDivision;
		heightDivided = this.getPreferredSize().height / heightDivision;
		
		this.colorStreet = new ColorStreet[(this.streetsCount / 2) * ((this.streetsCount / 2) - streetsOdd) + 
		                                             (((this.streetsCount / 2) - streetsEven - streetsOdd) * ((this.streetsCount / 2) + streetsEven))];
		for (int i = 0; i < (this.streetsCount / 2); i++) {
			for (int j = 0; j < (this.streetsCount / 2) - streetsOdd; j++) {;
				g.drawString("X" + Integer.toString(xIndex + (xIndexVertical * i) + 1), (((widthDivided / 2) + widthDivided)) + (j * widthDivided) - 5, heightDivided * (i + 1) - 10);
				this.colorStreet[xIndex + (xIndexVertical * i)] = new ColorStreet(Integer.toString(xIndex + (xIndexVertical * i)),
						this.xCoordinates[j], this.yCoordinates[i * (xIndexVertical)], 
						this.xCoordinates[j + 1] , this.yCoordinates[i * (xIndexVertical)]);
				g2d.draw(this.colorStreet[xIndex + (xIndexVertical * i)].getColorStreet());
				xIndex++;
			}
		}
		xIndex = xIndexHorizontal + 1;
		for (int i = 0; i < (this.streetsCount / 2) - streetsEven - streetsOdd; i++) {
			for (int j = 0; j < (this.streetsCount / 2) + streetsEven; j++) {
				g.drawString("X" + Integer.toString(xIndex + (xIndexHorizontal * i)),  widthDivided * (j + 1) + 10, (((heightDivided / 2) + heightDivided)) + (i * heightDivided) + 5);
				this.colorStreet[xIndex + (xIndexHorizontal * i) - 1] = new ColorStreet(Integer.toString(xIndex) + (xIndexHorizontal * i),
						this.xCoordinates[j], this.yCoordinates[j],
						this.xCoordinates[j], this.yCoordinates[(i + 1) * (xIndexVertical)]);
				g2d.draw(this.colorStreet[xIndex + (xIndexHorizontal * i) - 1].getColorStreet());
				xIndex++;
			}
		}
	}
	public Color getColorByPower(double power)
	{
	    double H = 0.4;
	    H -= power * 0.4;
	    double S = 1.0;
	    double B = 1.0;
	    return Color.getHSBColor((float)H, (float)S, (float)B);
	}
	public void setColorToStreets(Graphics g, List<XValue> resultXValues, int minValue, int maxValue) {
		Graphics2D g2d = (Graphics2D) g;
		ColorStreet tempColorStreet[] = new ColorStreet[this.colorStreet.length];
		for (int i = 0; i < this.colorStreet.length; i++) {
			g2d.setStroke(new BasicStroke(3));
			double valuePower = (double)resultXValues.get(i).getValue() / (double)maxValue;
			g2d.setColor(this.getColorByPower(valuePower));
			tempColorStreet[i] = new ColorStreet("X" + resultXValues.get(i).getIndex(), this.colorStreet[i].getXInitial(), this.colorStreet[i].getYInitial(), this.colorStreet[i].getXLast(), this.colorStreet[i].getYLast());
			g2d.draw(tempColorStreet[i].getColorStreet());
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLines(g);
		paintVariables(g);
		if (this.isStreetColored) {	
			setColorToStreets(g, getXValue(), getMinValue(), getMaxValue());
		}
	}
}
