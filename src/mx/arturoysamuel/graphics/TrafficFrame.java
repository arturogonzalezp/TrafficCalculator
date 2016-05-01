package mx.arturoysamuel.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mx.arturoysamuel.calculator.DependentVariable;
import mx.arturoysamuel.calculator.FinalEcuation;
import mx.arturoysamuel.calculator.GaussJordanElimination;

public class TrafficFrame extends JFrame implements ActionListener{
	private TrafficPanel panelTraffic;
	private InputVariablesPanel panelInputVariables;
	private DependentVariablesPanel panelDependentVariables;
	private final int maxNumberOfStreets = 10;
	private final int minNumberOfStreets = 4;
	private int streetsCount;
	private int numOfVariables;
	private int numOfNodes;
	private FinalEcuation[] finalEcuations;
	private List<DependentVariable> dependentVariables;
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
		for (int i = this.getMinNumberOfStreets(), countUpToTwo = 0, sumVar = 3, sumNode = 2; this.getStreetsCount() >= i; i++) {
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
		this.setDependentVariables(new ArrayList<DependentVariable>());
		this.setPanelTraffic(new TrafficPanel(streetsCount));
		this.setPanelInputVariables(new InputVariablesPanel(streetsCount));
		this.setPanelDependentVariables(new DependentVariablesPanel());
		this.add(this.getPanelTraffic(), BorderLayout.WEST);
		this.add(this.getPanelInputVariables(), BorderLayout.CENTER);
		this.add(this.getPanelDependentVariables(), BorderLayout.EAST);
		this.pack();
		this.getPanelInputVariables().getButtonCalculateDependentVariables().addActionListener(this);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public FinalEcuation[] getFinalEcuations() {
		return finalEcuations;
	}
	public void setFinalEcuations(FinalEcuation[] finalEcuations) {
		this.finalEcuations = finalEcuations;
	}
	public List<DependentVariable> getDependentVariables() {
		return dependentVariables;
	}
	public void setDependentVariables(List<DependentVariable> dependentVariables) {
		this.dependentVariables = dependentVariables;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getPanelInputVariables().getButtonCalculateDependentVariables().equals(e.getSource())){
			JButton calculateEcuationsButton = (JButton) e.getSource();
			calculateEcuationsButton.setEnabled(false);
			JTextField[] textFields = this.getPanelInputVariables().getPanelInput().getTextFieldInputs();
			int[] inputValues = new int[textFields.length];
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
			
			//Temp for testing
			/*Random r = new Random();
			for (int i = 0; i < inputValues.length; i++) {
				inputValues[i] = r.nextInt(1000);
			}*/
			
			if(inputIsValid){
				for (int i = 0; i < inputValues.length; i++) {
					inputValues[i] = Integer.parseInt(textFields[i].getText());
					textFields[i].setEditable(false);
				}
				double[][] A = new double[this.getNumOfVariables()][this.getNumOfVariables()];
				double[] b = new double[this.getNumOfVariables()];
				for (int i = 0; i < this.getNumOfVariables(); i++) {
					for (int j = 0; j < this.getNumOfVariables(); j++) {
						A[i][j] = 0;
					}
					b[i] = 0;
				}
				
				for (int nodeNum = 0, row = 0, column = 0; nodeNum < this.getNumOfNodes(); nodeNum++) {
					if(row == 0 && column == 0){
						// Esquina izquierda superior
						int eastNodePos = 0;
						int southNodePost = eastNodePos + (this.getDirectionsV().length - 1);
						
						if(this.getDirectionsV()[column]){
							
							if(this.getDirectionsH()[row]){
								
								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][southNodePost] = 1;
								b[nodeNum] = inputValues[0] + inputValues[inputValues.length-1];
								
							}else{

								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][southNodePost] = -1;
								b[nodeNum] = -inputValues[0] + inputValues[inputValues.length-1];
								
							}
						}else{
							if(this.getDirectionsH()[row]){

								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][southNodePost] = -1;
								b[nodeNum] = -inputValues[0] + inputValues[inputValues.length-1];
								
							}else{

								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][southNodePost] = 1;
								b[nodeNum] = inputValues[0] + inputValues[inputValues.length-1];
								
							}
						}
						
					}else if(row == (this.getDirectionsH().length - 1) && column == 0){
						// Esquina izquierda inferior FALTAN las A!!!!!!
						int eastNodePos = (this.getNumOfVariables()-1) - (this.getDirectionsV().length - 2);
						int northNodePost = eastNodePos - this.getDirectionsV().length;
						
						if(this.getDirectionsV()[column]){
						
							if(this.getDirectionsH()[row]){
								
								A[nodeNum][eastNodePos] = -1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = -inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length] + inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length - 1];
							}else{

								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length] + inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length - 1];
							}
							
						}else{
							
							if(this.getDirectionsH()[row]){
								
								A[nodeNum][eastNodePos] = 1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length] + inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length - 1];
							}else{

								A[nodeNum][eastNodePos] = -1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = -inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length] + inputValues[(2*this.getDirectionsV().length) + this.getDirectionsH().length - 1];
							}
						}
						
					}else if(row == 0 && column == (this.getDirectionsV().length - 1)){
						// Esquina derecha superior
						int westNodePos = this.getDirectionsV().length - 2;
						int southNodePos = westNodePos+this.getDirectionsV().length;
						
						if(this.getDirectionsV()[column]){
	
							if(this.getDirectionsH()[row]){
								
								A[nodeNum][westNodePos] = 1;
								A[nodeNum][southNodePos] = -1;
								b[nodeNum] = -inputValues[column] + inputValues[this.getDirectionsV().length];
								
							}else{

								A[nodeNum][westNodePos] = 1;
								A[nodeNum][southNodePos] = 1;
								b[nodeNum] = inputValues[column] + inputValues[this.getDirectionsV().length];
								
							}
						}else{
							if(this.getDirectionsH()[row]){

								A[nodeNum][westNodePos] = 1;
								A[nodeNum][southNodePos] = 1;
								b[nodeNum] = inputValues[column] + inputValues[this.getDirectionsV().length];
								
							}else{

								A[nodeNum][westNodePos] = 1;
								A[nodeNum][southNodePos] = -1;
								b[nodeNum] = -inputValues[column] + inputValues[this.getDirectionsV().length];
								
							}
						}
						
					}else if(row == (this.getDirectionsH().length - 1) && column == (this.getDirectionsV().length - 1)){
						// Esquina derecha inferior
						int westNodePos = this.getNumOfVariables()-1;
						int northNodePost = westNodePos - (this.getDirectionsV().length - 1);
						
						if(this.getDirectionsV()[column]){
							
							if(this.getDirectionsH()[row]){
								
								A[nodeNum][westNodePos] = 1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[this.getDirectionsV().length + this.getDirectionsH().length] + inputValues[this.getDirectionsV().length + this.getDirectionsH().length -1];
								
							}else{

								A[nodeNum][westNodePos] = -1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[this.getDirectionsV().length + this.getDirectionsH().length] - inputValues[this.getDirectionsV().length + this.getDirectionsH().length -1];
								
							}
						}else{
							
							if(this.getDirectionsH()[row]){

								A[nodeNum][westNodePos] = -1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[this.getDirectionsV().length + this.getDirectionsH().length] - inputValues[this.getDirectionsV().length + this.getDirectionsH().length -1];
								
							}else{ 

								A[nodeNum][westNodePos] = 1;
								A[nodeNum][northNodePost] = 1;
								b[nodeNum] = inputValues[this.getDirectionsV().length + this.getDirectionsH().length] + inputValues[this.getDirectionsV().length + this.getDirectionsH().length -1];
								
							}
						}
					}else{
						if(row == 0){
							// Fila superior (no incluye a las esquinas)
							int westNodePos = column-1;
							int eastNodePos = column+1;
							int southNodePos = column + this.getDirectionsV().length - 1;
							if(this.getDirectionsV()[column]){	
								if(this.getDirectionsH()[row]){
									A[nodeNum][westNodePos] = -1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = 1;
								}else{
									A[nodeNum][westNodePos] = 1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = -1;
								}
							}else{
								if(this.getDirectionsH()[row]){
									A[nodeNum][westNodePos] = 1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = -1;
								}else{
									A[nodeNum][westNodePos] = -1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = 1;
								}
							}
							b[nodeNum] = inputValues[column];
							
						}else if(row == (this.getNumOfVariables() - 1)){
							// Fila inferior (no incluye a las esquinas)
							int westNodePos = column-1;
							int eastNodePos = column+1;
							int southNodePos = column - this.getDirectionsV().length - 1;
							if(this.getDirectionsV()[column]){	
								if(this.getDirectionsH()[row]){
									A[nodeNum][westNodePos] = -1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = 1;
								}else{
									A[nodeNum][westNodePos] = 1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = -1;
								}
							}else{
								if(this.getDirectionsH()[row]){
									A[nodeNum][westNodePos] = 1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = -1;
								}else{
									A[nodeNum][westNodePos] = -1;
									A[nodeNum][southNodePos] = 1;
									A[nodeNum][eastNodePos] = 1;
								}
							}
							b[nodeNum] = inputValues[column];
							
						} if(column == 0){
							// Lado izquierdo (no incluye a las esquinas)
							
						}else if(column == (this.getNumOfVariables() - 1)){
							// Lado derecho (no incluye a las esquinas
						}else{
							// Nodos Internos
						}
					}
					if((column + 1) % (this.getDirectionsV().length) == 0){
						row++;
						column = 0;
						continue;
					}
					column++;
				}
				
				for (int i = 0; i < A.length; i++) {
					for (int j = 0; j < A[i].length; j++) {
						System.out.printf("%4.0f ",A[i][j]);
					}
					System.out.println(" | " + b[i]);
				}
				
				GaussJordanElimination gj = new GaussJordanElimination(A, b);
				List<Integer> dependentVariablesIndex = gj.getDependentVariables();
				double[] results = gj.getResults();
				double[][] solvedSingleMatrix = gj.getSolvedSingleMatrix();
				double[][] originalMatrix = gj.getOriginalMatrix();
				int N = gj.getN();
				this.setFinalEcuations(new FinalEcuation[N]);
				
				System.out.println("Original Matrix: ");
				for (int i = 0; i < originalMatrix.length; i++) {
					for (int j = 0; j < originalMatrix[i].length; j++) {
						System.out.printf("%4.0f ",originalMatrix[i][j]);
					}
					System.out.println();
				}
				
				System.out.println("\nSolved Single Matrix: ");
				for (int i = 0; i < solvedSingleMatrix.length; i++) {
					for (int j = 0; j < solvedSingleMatrix[i].length; j++) {
						System.out.printf("%4.0f ",solvedSingleMatrix[i][j]);
					}
					System.out.println();
				}
				
				System.out.println("\nDependent Variables: ");
				for (Integer dependentVariableIndex : dependentVariablesIndex) {
					this.getDependentVariables().add(new DependentVariable(dependentVariableIndex));
					System.out.println("X" + (dependentVariableIndex + 1) + " = " + "X" + (dependentVariableIndex + 1));
				}
						
				System.out.println("\nMatrix Results: ");
				for (int i = 0; i < results.length; i++) {
					System.out.println("X" + (i + 1) + " = " + results[i]);
					this.getFinalEcuations()[i] = new FinalEcuation((int) results[i]);
				}
				
				for (int i = 0; i < N; i++) {
					if(dependentVariablesIndex.indexOf(i) == -1){
						for (int j = i+1; j < solvedSingleMatrix[i].length; j++) {
							if(solvedSingleMatrix[i][j] != 0){
								this.getFinalEcuations()[i].addXValue((int) -solvedSingleMatrix[i][j],j);
							}
						}
					}else{
						this.getFinalEcuations()[i].addXValue(1,i);
					}
				}
				for (Integer dependentVariableIndex : dependentVariablesIndex){
					int addValue = this.getFinalEcuations()[dependentVariableIndex].getIndependentValue();
					for (int i = 0; i < this.getFinalEcuations().length; i++) {
						if(dependentVariableIndex != i){
							this.getFinalEcuations()[i].updateIndependentValue(dependentVariableIndex, addValue);
						}
					}
					this.getFinalEcuations()[dependentVariableIndex].setIndependentValue(0);
				}
				
				System.out.println("\nResult Ecuations: ");
				for (int i = 0; i < this.getFinalEcuations().length; i++) {
					System.out.println("X" + (i + 1) + " = " + this.getFinalEcuations()[i]);
				}
				
				this.getPanelDependentVariables().getPanelDependentVariablesInput().setInputs(dependentVariablesIndex);
				this.getPanelDependentVariables().getPanelDependentVariablesInput().setButtonCalculateEcuations(new JButton("Calculate"));
				this.getPanelDependentVariables().getPanelDependentVariablesInput().getButtonCalculateEcuations().addActionListener(this);
				this.getPanelDependentVariables().getPanelDependentVariablesInput().add(this.getPanelDependentVariables().getPanelDependentVariablesInput().getButtonCalculateEcuations());
				this.getPanelDependentVariables().getPanelDependentVariablesInput().getButtonCalculateEcuations().setAlignmentX(Component.CENTER_ALIGNMENT);
				this.getPanelDependentVariables().getPanelDependentVariablesInput().updatePanel();
			}
		}else if(this.getPanelDependentVariables().getPanelDependentVariablesInput().getButtonCalculateEcuations().equals(e.getSource())){
			// Evento de Variables dependientes
			JTextField[] textFields = this.getPanelDependentVariables().getPanelDependentVariablesInput().getPanelDependentVariablesInputContainer().getTextFieldDependentVariables();
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
			    	JOptionPane.showMessageDialog(this, "No valid values");
					break;
				}
			}
			
			if(inputIsValid){
				boolean secondInputIsValid = true;
				String tempPrint = "";
				for (int i = 0; i < textFields.length; i++) {
					this.getDependentVariables().get(i).setValue(Integer.parseInt(textFields[i].getText()));
				}
				for (int i = 0; i < this.getFinalEcuations().length; i++) {
					if(this.getFinalEcuations()[i].solveWithParameters(dependentVariables) < 0){
						secondInputIsValid = false;
						JOptionPane.showMessageDialog(this, "The dependent variables cannot have those values, traffic can´t be negative");
						break;
					}else{
						tempPrint += "X" + (i + 1) + " = " + this.getFinalEcuations()[i].toString() + "\n";
					}
				}
				if(secondInputIsValid){
					JOptionPane.showMessageDialog(this, "Solved:\n" + tempPrint);
				}
			}
		}else{
			// Otro evento 
		}
	}
}
