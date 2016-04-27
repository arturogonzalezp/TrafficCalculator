package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{
	
	GridLayout gLayout;
	JTextField[] tfInput;
	
	public InputPanel(int numOfInputs) {
		this.gLayout = new GridLayout(12, 1);
		this.setLayout(this.gLayout);
		this.tfInput = new JTextField[numOfInputs];
		this.setPreferredSize(new Dimension(200, 400));
		for (int i = 0; i < this.tfInput.length; i++) {
			this.tfInput[i] = new JTextField();
			this.add(this.tfInput[i]);
		}
	}
	
}
