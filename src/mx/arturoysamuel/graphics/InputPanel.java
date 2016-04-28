package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{
	private GridLayout gridLayout;
	private JTextField[] textFieldInputs;
	private int numOfInputs;
	public InputPanel(int numOfStreets) {
		this.setNumOfInputs(numOfStreets*2);
		this.setGridLayout(new GridLayout(this.getNumOfInputs(), 1));
		this.setLayout(this.getGridLayout());
		this.setTextFieldInputs(new JTextField[this.getNumOfInputs()]);
		this.setPreferredSize(new Dimension(200, 400));
		for (int i = 0; i < this.getTextFieldInputs().length; i++) {
			this.getTextFieldInputs()[i] = new JTextField();
			this.getTextFieldInputs()[i].setHorizontalAlignment(JTextField.CENTER);
			this.add(this.getTextFieldInputs()[i]);
		}
	}
	public GridLayout getGridLayout() {
		return gridLayout;
	}
	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}
	public JTextField[] getTextFieldInputs() {
		return textFieldInputs;
	}
	public void setTextFieldInputs(JTextField[] textFieldInputs) {
		this.textFieldInputs = textFieldInputs;
	}
	public int getNumOfInputs() {
		return numOfInputs;
	}
	public void setNumOfInputs(int numOfInputs) {
		this.numOfInputs = numOfInputs;
	}	
}
