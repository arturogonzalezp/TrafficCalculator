package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	
	GridLayout gLayout;
	private JLabel[] lbInfo;
	
	public InfoPanel(int numOfInputs) {
		this.gLayout = new GridLayout(12, 1);
		this.setLayout(this.gLayout);
		this.lbInfo = new JLabel[numOfInputs];
		for (int i = 0; i < this.lbInfo.length; i++) {
			this.lbInfo[i] = new JLabel(Character.toString((char) (65 + i)));
			this.add(this.lbInfo[i]);
		}
		this.setPreferredSize(new Dimension(20, 400));
	}
}
