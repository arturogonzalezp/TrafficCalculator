package mx.arturoysamuel.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mx.arturoysamuel.calculator.DependentVariable;

public class DependentVariablesInputContainerPanel extends JPanel{
	private GridLayout layoutGrid;
	private JTextField[] textFieldDependentVariables;
	private JLabel[] labelVariableNumbers;
	public DependentVariablesInputContainerPanel(List<Integer> dependentVariablesIndex){
		this.setLayoutGrid(new GridLayout(dependentVariablesIndex.size(), 2));
		this.setLayout(this.getLayoutGrid());
		this.setTextFieldDependentVariables(new JTextField[dependentVariablesIndex.size()]);
		this.setLabelVariableNumbers(new JLabel[dependentVariablesIndex.size()]);
		int i = 0;
		for (Integer dependentVariableIndex : dependentVariablesIndex) {
			this.getLabelVariableNumbers()[i] = new JLabel("X" + (dependentVariableIndex + 1), SwingConstants.CENTER);
			this.getTextFieldDependentVariables()[i] = new JTextField();
			this.getTextFieldDependentVariables()[i].setHorizontalAlignment(JTextField.CENTER);
			this.add(this.getLabelVariableNumbers()[i]);
			this.add(this.getTextFieldDependentVariables()[i]);
			i++;
		}
	}
	public GridLayout getLayoutGrid() {
		return layoutGrid;
	}
	public void setLayoutGrid(GridLayout layoutGrid) {
		this.layoutGrid = layoutGrid;
	}
	public JTextField[] getTextFieldDependentVariables() {
		return textFieldDependentVariables;
	}
	public void setTextFieldDependentVariables(JTextField[] textFieldDependentVariables) {
		this.textFieldDependentVariables = textFieldDependentVariables;
	}
	public JLabel[] getLabelVariableNumbers() {
		return labelVariableNumbers;
	}
	public void setLabelVariableNumbers(JLabel[] labelVariableNumbers) {
		this.labelVariableNumbers = labelVariableNumbers;
	}
}
