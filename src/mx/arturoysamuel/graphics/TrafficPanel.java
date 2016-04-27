package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TrafficPanel extends JPanel {

	int numOfInputs;
	RoadLine[] roadLine;

	private static final int X_INIT = 50;
	private static final int X_LAST = 450;
	private static final int Y_INIT = 50;
	private static final int Y_LAST = 450;

	public TrafficPanel(int numOfInputs) {
		this.numOfInputs = numOfInputs;
		this.setPreferredSize(new Dimension(500, 500));
		this.setBorder(BorderFactory.createTitledBorder("Traffic Calculator"));
	}

	public void paintLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		this.roadLine = new RoadLine[this.numOfInputs];
		
		int xDiv;
		if (this.numOfInputs % 2 == 0) {
			xDiv = (int) Math.ceil((this.numOfInputs / 2)) + 1;
		}
		else {
			xDiv = (int) Math.ceil((this.numOfInputs / 2)) + 2;
		}
		int yDiv = (int) Math.ceil((this.numOfInputs / 2)) + 1;
		int xVar = this.getPreferredSize().width / xDiv;
		int yVar = this.getPreferredSize().height / yDiv;
		int j = 1;
		double jumpValue = Math.ceil(((double) this.numOfInputs / (double) 2));
		int addValue;
		int addValue2;
		int stabValue = 0;
		
		if (this.numOfInputs % 2 == 0) {
			addValue = 1;
			addValue2 = 0;
		}
		else {
			addValue = -1;
			addValue2 = 1;
		}
		
		for (int i = 0; i < this.roadLine.length; i++) {
			if (i % 2 == 0) {
				this.roadLine[i] = new RoadLine(0, xVar * j, Y_INIT, xVar * j, Y_LAST);
				this.roadLine[i].paintInitString(g2d, Character.toString((char) (64 + j)));
				this.roadLine[i].paintFinalString(g2d, Character.toString((char) (64 + jumpValue * 3 - j + addValue + addValue2)));
				for (int k = 0; k < Math.ceil((double) this.roadLine.length / (double) 2); k++) {
					if ((this.numOfInputs == 5 && j == 3) || (this.numOfInputs == 7 && j == 4)) {
						stabValue = -1;
					}
					g.fillOval(xVar - 5 + (k * xVar), yVar * (j + stabValue) - 5, 10, 10);
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

	public void paint(Graphics g) {
		super.paint(g);
		paintLines(g);
	}
}
