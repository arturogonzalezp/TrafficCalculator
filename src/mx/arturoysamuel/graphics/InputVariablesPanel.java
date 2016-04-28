package mx.arturoysamuel.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputVariablesPanel extends JPanel{
	private InfoPanel panelInfo;
	private InputPanel panelInput;
	private JButton buttonCalculateDependentVariables;
	public InputVariablesPanel(int streetsCount) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(250,400));
		this.setBorder(BorderFactory.createTitledBorder("Input Vehicles per Hour (vph)"));
		this.setPanelInfo(new InfoPanel(streetsCount));
		this.setPanelInput(new InputPanel(streetsCount));
		this.setButtonCalculateDependentVariables(new JButton("Calculate"));
		this.add(this.getPanelInfo(),BorderLayout.WEST);
		this.add(this.getPanelInput(), BorderLayout.CENTER);
		this.add(this.getButtonCalculateDependentVariables(),BorderLayout.SOUTH);
	}
	public InfoPanel getPanelInfo() {
		return panelInfo;
	}
	public void setPanelInfo(InfoPanel panelInfo) {
		this.panelInfo = panelInfo;
	}
	public InputPanel getPanelInput() {
		return panelInput;
	}
	public void setPanelInput(InputPanel panelInput) {
		this.panelInput = panelInput;
	}
	public JButton getButtonCalculateDependentVariables() {
		return buttonCalculateDependentVariables;
	}
	public void setButtonCalculateDependentVariables(
			JButton buttonCalculateDependentVariables) {
		this.buttonCalculateDependentVariables = buttonCalculateDependentVariables;
	}
}
