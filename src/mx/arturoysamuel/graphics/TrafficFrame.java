package mx.arturoysamuel.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.NumberOfDocuments;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TrafficFrame extends JFrame implements ActionListener{
	private TrafficPanel panelTraffic;
	private InputVariablesPanel panelInputVariables;
	private DependentVariablesPanel panelDependentVariables;
	private final int maxNumberOfStreets = 10;
	private final int minNumberOfStreets = 3;
	private int streetsCount;
	private int numOfVariables;
	private int numOfNodes;
	// True is down or right, False is up or left
	private boolean[] directionsV;
	private boolean[] directionsH;
	public TrafficFrame() {
		super("Traffic Calculator");
		this.setStreetsCount(this.getMaxNumberOfStreets() + 1);
		do{
			try{
				this.setStreetsCount(Integer.parseInt(JOptionPane.showInputDialog(this,"Street number: ")));
			}catch(Exception e){
				this.setStreetsCount(this.getMaxNumberOfStreets() + 1);
			}
		}while(this.getStreetsCount() > this.getMaxNumberOfStreets() || this.getStreetsCount() < this.getMinNumberOfStreets());
		
		this.setNumOfVariables(1);
		this.setNumOfNodes(2);
		for (int i = this.getMinNumberOfStreets(), countUpToTwo = 0, sumVar = 3, sumNode = 2; this.getStreetsCount() > i; i++) {
			if(countUpToTwo == 2){
				countUpToTwo = 0;
				sumVar += 2;
				sumNode +=1;
			}
			this.setNumOfVariables(this.getNumOfVariables() + sumVar);
			this.setNumOfNodes(this.getNumOfNodes() + sumNode);
			countUpToTwo++;
		}
		this.setDirectionsV(new boolean[(int) Math.ceil((double)this.getStreetsCount() / 2)]);
		this.setDirectionsH(new boolean[(int) Math.floor((double)this.getStreetsCount() / 2)]);
		Object[] optionsH = {"Left","Right"};
		Object[] optionsV = {"Up","Down"};
		int opc;
		for (int i = 0; i < this.getDirectionsH().length; i++) {
			opc = JOptionPane.showOptionDialog(
					this,
					"The direction of the " + (i + 1) + "° horizontal street:",
					"Direction Input",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					optionsH,
					optionsH[0]);
			if(opc == -1){
				i--;
			}else if(opc == 0){
				this.getDirectionsH()[i] = false;
			}else if(opc == 1){
				this.getDirectionsH()[i] = true;
			}
		}
		for (int i = 0; i < this.getDirectionsV().length; i++) {
			opc = JOptionPane.showOptionDialog(
					this,
					"The direction of the " + (i + 1) + "° vertical street:",
					"Direction Input",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					optionsV,
					optionsV[0]);
			if(opc == -1){
				i--;
			}else if(opc == 0){
				this.getDirectionsV()[i] = false;
			}else if(opc == 1){
				this.getDirectionsV()[i] = true;
			}
		}
		
		this.setPanelTraffic(new TrafficPanel(streetsCount));
		this.setPanelInputVariables(new InputVariablesPanel(streetsCount));
		this.setPanelDependentVariables(new DependentVariablesPanel());
		this.add(this.getPanelTraffic(), BorderLayout.WEST);
		this.add(this.getPanelInputVariables(), BorderLayout.CENTER);
		this.add(this.getPanelDependentVariables(), BorderLayout.EAST);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getPanelInputVariables().getButtonCalculateDependentVariables().addActionListener(this);
	}
	public int getMaxNumberOfStreets() {
		return maxNumberOfStreets;
	}

	public int getMinNumberOfStreets() {
		return minNumberOfStreets;
	}
	
	public TrafficPanel getPanelTraffic() {
		return panelTraffic;
	}

	public void setPanelTraffic(TrafficPanel panelTraffic) {
		this.panelTraffic = panelTraffic;
	}

	public InputVariablesPanel getPanelInputVariables() {
		return panelInputVariables;
	}

	public void setPanelInputVariables(InputVariablesPanel panelInputVariables) {
		this.panelInputVariables = panelInputVariables;
	}

	public DependentVariablesPanel getPanelDependentVariables() {
		return panelDependentVariables;
	}

	public void setPanelDependentVariables(
			DependentVariablesPanel panelDependentVariables) {
		this.panelDependentVariables = panelDependentVariables;
	}
	
	public int getStreetsCount() {
		return streetsCount;
	}
	public void setStreetsCount(int streetsCount) {
		this.streetsCount = streetsCount;
	}
	public int getNumOfVariables() {
		return numOfVariables;
	}
	public void setNumOfVariables(int numOfVariables) {
		this.numOfVariables = numOfVariables;
	}
	public int getNumOfNodes() {
		return numOfNodes;
	}
	public void setNumOfNodes(int numOfNodes) {
		this.numOfNodes = numOfNodes;
	}
	public boolean[] getDirectionsV() {
		return directionsV;
	}
	public void setDirectionsV(boolean[] directionsV) {
		this.directionsV = directionsV;
	}
	public boolean[] getDirectionsH() {
		return directionsH;
	}
	public void setDirectionsH(boolean[] directionsH) {
		this.directionsH = directionsH;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getPanelInputVariables().getButtonCalculateDependentVariables().equals(e.getSource())){
			JTextField[] textFields = this.getPanelInputVariables().getPanelInput().getTextFieldInputs();
			boolean inputIsValid = true;
			for (int i = 0; i < textFields.length; i++) {
				try {
				     if(Integer.parseInt(textFields[i].getText()) < 0){
				    	inputIsValid = false;
				    	JOptionPane.showMessageDialog(this, "No negative numbers");
				    	break;
				     }
				}
				catch (NumberFormatException ex) {
					inputIsValid = false;
			    	JOptionPane.showMessageDialog(this, "No valid value at input \"" + (char)(65 + i) + "\"");
					break;
				}
			}
			if(inputIsValid){
				double[][] A = new double[this.getNumOfVariables()][this.getNumOfVariables()];
				double[] b = new double[this.getNumOfVariables()];
				
				/*for (int i = 0; i < b.length; i++) {
					
				}
				
				for (int i = 0; i < this.getNumOfVariables(); i++) {
					for (int j = 0; j < this.getNumOfVariables(); j++) {
						// For para recorrer la matriz con los valores
					}
				}*/
				/* if(i == 0 && j == 0){
							// Esquina izquierda superior
						}else if(i == (this.getNumOfVariables() - 1) && j == 0){
							// Esquina izquierda inferior
						}else if(i == 0 && j == (this.getNumOfNodes() - 1)){
							// Esquina derecha superior
						}else if(i == (this.getNumOfVariables() - 1) && j == (this.getNumOfNodes() - 1)){
							// Esquina derecha inferior
						}else{
							if(i == 0){
								// Fila superior (no incluye a las esquinas)
							}else if(i == (this.getNumOfVariables() - 1)){
								// Fila inferior (no incluye a las esquinas)
							} if(j == 0){
								// Lado izquierdo (no incluye a las esquinas)
							}else if(j == (this.getNumOfVariables() - 1)){
								// Lado derecho (no incluye a las esquinas
							}else{
								// Nodos Internos
							}
						}
				 */
			}
		}else{
			
		}
	}
}
