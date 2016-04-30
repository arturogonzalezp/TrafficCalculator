package mx.arturoysamuel.graphics;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DependentVariablesInputPanel extends JPanel{
	public DependentVariablesInputPanel(){
		this.setPreferredSize(new Dimension(200, 400));
		this.setBorder(BorderFactory.createTitledBorder("Input Dependent Variables (vph)"));
	}
}
