package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DependentVariablesPanel extends JPanel{
	GridLayout gridLayout;
	
	public DependentVariablesPanel() {
		this.setPreferredSize(new Dimension(250, 400));
		this.setBorder(BorderFactory.createTitledBorder("Input Dependent Variables (vph)"));
	}
}
