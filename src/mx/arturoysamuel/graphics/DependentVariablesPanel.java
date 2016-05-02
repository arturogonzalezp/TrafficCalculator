package mx.arturoysamuel.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DependentVariablesPanel extends JPanel{
	private VariablesFinalResultPanel panelVariablesFinalResult;
	private DependentVariablesInputPanel panelDependentVariablesInput;
	private GridLayout layoutGrid;
	public DependentVariablesPanel() {
		this.setPreferredSize(new Dimension(500, 400));
		this.setPanelVariablesFinalResult(new VariablesFinalResultPanel());
		this.setPanelDependentVariablesInput(new DependentVariablesInputPanel());
		this.setLayoutGrid(new GridLayout(1, 2));
		this.setLayout(this.getLayoutGrid());
		this.add(this.getPanelDependentVariablesInput());
		this.add(this.getPanelVariablesFinalResult());
	}
	public VariablesFinalResultPanel getPanelVariablesFinalResult() {
		return panelVariablesFinalResult;
	}
	public void setPanelVariablesFinalResult(VariablesFinalResultPanel panelVariablesFinalResult) {
		this.panelVariablesFinalResult = panelVariablesFinalResult;
	}
	public DependentVariablesInputPanel getPanelDependentVariablesInput() {
		return panelDependentVariablesInput;
	}
	public void setPanelDependentVariablesInput(
			DependentVariablesInputPanel panelDependentVariablesInput) {
		this.panelDependentVariablesInput = panelDependentVariablesInput;
	}
	public GridLayout getLayoutGrid() {
		return layoutGrid;
	}
	public void setLayoutGrid(GridLayout layoutGrid) {
		this.layoutGrid = layoutGrid;
	}
}
