package mx.arturoysamuel.graphics;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel {
	private GridLayout gridLayout;
	private JLabel[] labelInfo;
	public InfoPanel(int numOfStreets) {
		int numOfInputs = numOfStreets*2;
		this.setGridLayout(new GridLayout(numOfInputs, 1));
		this.setLayout(this.getGridLayout());
		this.setPreferredSize(new Dimension(50, 400));
		this.setLabelInfo(new JLabel[numOfInputs]);
		for (int i = 0; i < this.getLabelInfo().length; i++) {
			this.getLabelInfo()[i] = new JLabel(Character.toString((char) (65 + i)),SwingConstants.CENTER);
			this.add(this.getLabelInfo()[i]);
		}
	}
	public GridLayout getGridLayout() {
		return gridLayout;
	}
	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}
	public JLabel[] getLabelInfo() {
		return labelInfo;
	}
	public void setLabelInfo(JLabel[] labelInfo) {
		this.labelInfo = labelInfo;
	}
}
