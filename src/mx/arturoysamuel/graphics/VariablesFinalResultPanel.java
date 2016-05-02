package mx.arturoysamuel.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mx.arturoysamuel.calculator.FinalEcuation;
import mx.arturoysamuel.calculator.XValue;

public class VariablesFinalResultPanel extends JPanel{
	private List<JLabel> labels;
	private FinalEcuation[] finalEcuations; 
	public VariablesFinalResultPanel(){
		this.setPreferredSize(new Dimension(200, 400));
		this.setBorder(BorderFactory.createTitledBorder("Streets values(vph) "));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setLabels(new ArrayList<JLabel>());
	}
	public void printResults(List<XValue> xValues){
		this.emptyPanel();
		this.addEcuations();
		JLabel resultsLabel = new JLabel("Results");
		resultsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		resultsLabel.setBorder(new EmptyBorder(20, 10, 10, 10));
		this.getLabels().add(resultsLabel);
		this.add(resultsLabel);
		for (XValue xValue : xValues) {
			JLabel tempLabel = new JLabel(xValue.drawString() + " = " + xValue.getValue());
			tempLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			this.getLabels().add(tempLabel);
			this.add(tempLabel, Component.LEFT_ALIGNMENT);
		}
		this.revalidate();
		this.repaint();
	}
	public void emptyPanel(){
		for (JLabel label : this.getLabels()) {
			this.remove(label);
		}
		this.getLabels().clear();
		System.gc();
		this.revalidate();
		this.repaint();
	}
	public void addEcuations(FinalEcuation[] finalEcuations){
		this.setFinalEcuations(finalEcuations);
		this.addEcuations();
	}
	public void addEcuations(){
		this.getLabels().add(new JLabel("Ecuations"));
		this.getLabels().get(0).setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getLabels().get(0).setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(this.getLabels().get(0), Component.LEFT_ALIGNMENT);
		for (int i = 1; i <= this.getFinalEcuations().length; i++) {
			this.getLabels().add(new JLabel("X" + i + " = " + this.getFinalEcuations()[(i-1)].toString()));
			this.getLabels().get(i).setAlignmentX(Component.LEFT_ALIGNMENT);
			this.add(this.getLabels().get(i), Component.LEFT_ALIGNMENT);
		}
		this.revalidate();
		this.repaint();
	}
	public List<JLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<JLabel> labels) {
		this.labels = labels;
	}
	public FinalEcuation[] getFinalEcuations() {
		return finalEcuations;
	}
	public void setFinalEcuations(FinalEcuation[] finalEcuations) {
		this.finalEcuations = finalEcuations;
	}
}
