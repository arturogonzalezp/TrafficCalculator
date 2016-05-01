package mx.arturoysamuel.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.xml.ws.WebEndpoint;

public class TrafficPanel extends JPanel {

	private int streetsCount;
	private RoadLine[] roadLine;
	private ColorStreet[] colorStreet;
	private int[] xCoordinates;
	private int[] yCoordinates;
	
	private static final int X_INIT = 50;
	private static final int X_LAST = 450;
	private static final int Y_INIT = 50;
	private static final int Y_LAST = 450;

	public TrafficPanel(int streetsCount) {
		this.streetsCount = streetsCount;
		this.setPreferredSize(new Dimension(500, 500));
		this.setBorder(BorderFactory.createTitledBorder("Traffic Calculator"));
	}

	public int getStreetsCount() {
		return this.streetsCount;
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
		for (int i = 0; i < this.roadLine.length; i++) {
			if (i % 2 == 0) {
				this.roadLine[i] = new RoadLine(0, xVar * j, Y_INIT, xVar * j, Y_LAST);
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
				this.roadLine[i] = new RoadLine(0, X_INIT, yVar * j, X_LAST, yVar * j);
				this.roadLine[i].paintFinalString(g2d, Character.toString((char) (64 + j + jumpValue)));
				this.roadLine[i].paintInitString(g2d, Character.toString((char) (64 + jumpValue * 4 - j + addValue)));
				j++;
			}
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
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLines(g);
		paintVariables(g);
	}
}
