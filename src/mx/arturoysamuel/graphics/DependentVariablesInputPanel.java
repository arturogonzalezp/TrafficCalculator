package mx.arturoysamuel.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mx.arturoysamuel.calculator.DependentVariable;
import mx.arturoysamuel.calculator.FinalEcuation;

public class DependentVariablesInputPanel extends JPanel{
	private List<Integer> DependentVariablesIndex;
	private DependentVariablesInputContainerPanel panelDependentVariablesInputContainer;
	private JButton buttonCalculateEcuations;
	
	public DependentVariablesInputPanel(){
		this.setPreferredSize(new Dimension(200, 400));
		this.setBorder(BorderFactory.createTitledBorder("Input Dependent Variables (vph)"));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	public void setInputs(List<Integer> dependentVariablesIndex){
		this.setDependentVariablesIndex(dependentVariablesIndex);
		this.setPanelDependentVariablesInputContainer(new DependentVariablesInputContainerPanel(this.getDependentVariablesIndex()));
		this.add(this.getPanelDependentVariablesInputContainer());
	}
	public void updatePanel(){
		if(this.getDependentVariablesIndex().size() < 10){
			this.getPanelDependentVariablesInputContainer().setSize(new Dimension(200, 14*this.getDependentVariablesIndex().size()));
			this.getPanelDependentVariablesInputContainer().revalidate();
			this.getPanelDependentVariablesInputContainer().repaint();
		}
		this.revalidate();
		this.repaint();
	}
	public DependentVariablesInputContainerPanel getPanelDependentVariablesInputContainer() {
		return panelDependentVariablesInputContainer;
	}
	public void setPanelDependentVariablesInputContainer(
			DependentVariablesInputContainerPanel panelDependentVariablesInputContainer) {
		this.panelDependentVariablesInputContainer = panelDependentVariablesInputContainer;
	}
	public List<Integer> getDependentVariablesIndex() {
		return DependentVariablesIndex;
	}
	public void setDependentVariablesIndex(List<Integer> dependentVariablesIndex) {
		DependentVariablesIndex = dependentVariablesIndex;
	}
	public JButton getButtonCalculateEcuations() {
		return buttonCalculateEcuations;
	}
	public void setButtonCalculateEcuations(JButton buttonCalculateEcuations) {
		this.buttonCalculateEcuations = buttonCalculateEcuations;
	}
}
