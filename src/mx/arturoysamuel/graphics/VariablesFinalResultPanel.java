package mx.arturoysamuel.graphics;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class VariablesFinalResultPanel extends JPanel{
	public VariablesFinalResultPanel(){
		this.setPreferredSize(new Dimension(200, 400));
		this.setBorder(BorderFactory.createTitledBorder("Streets values(vph)"));
	}
}
