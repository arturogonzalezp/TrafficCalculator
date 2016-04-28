import java.util.ArrayList;
import java.util.List;
import mx.arturoysamuel.calculator.DependentVariable;
import mx.arturoysamuel.calculator.FinalEcuation;
import mx.arturoysamuel.calculator.GaussJordanElimination;
import mx.arturoysamuel.calculator.XValue;
import mx.arturoysamuel.graphics.TrafficFrame;


public class Main {
	public static void main(String[] args) {
		TrafficFrame frmTraffic = new TrafficFrame();
		
		/*double[][] A = {
				{-1, 0, 0, 1},
				{1, -1, 0, 0},
				{0, 0, -1, 1},
				{0, 1, -1, 0},
		};
		double[] b = { 100, 300, 500, 100};*/
		/*double[][] A = {
				{1, 0, 1, 0, 0, 0, 0},
				{1, -1, 0, 1, 0, 0, 0},
				{0, 1, 0, 0, -1, 0, 0},
				{0, 0, 1, 0, 0, 1, 0},
				{0, 0, 0, 1, 0, 1, -1},
				{0, 0, 0, 0, -1, 0, 1},
				{0, 0, 0, 0, 0, 0, 0}
	        };
		double[] b = {800, 200, 500, 750, 600, 50, 0};*/
		/*double[][] A = {
				{1,0,1,0,0,0,0,0,0,0,0,0},
				{1,-1,0,1,0,0,0,0,0,0,0,0},
				{0,1,0,0,-1,0,0,0,0,0,0,0},
				{0,0,1,0,0,1,0,-1,0,0,0,0},
				{0,0,0,1,0,1,-1,0,-1,0,0,0},
				{0,0,0,0,-1,0,1,0,0,1,0,0},
				{0,0,0,0,0,0,0,-1,0,0,1,0},
				{0,0,0,0,0,0,0,0,1,0,-1,1},
				{0,0,0,0,0,0,0,0,0,1,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}
	        };
		double[] b = {800,200,500,400,0,450,150,700,1000,0,0,0};*/
		/*double[][] A = {
				{1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,-1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,-1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,-1,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,1,-1,0,0,-1,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,-1,1,0,0,0,-1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,-1,0,0,0,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,0,0,-1,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,-1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	        };
		double[] b = {800,200,100,700,400,0,0,250,150,700,800,500,0,0,0,0,0};*/
		
		/*GaussJordanElimination gj = new GaussJordanElimination(A, b);
		List<Integer> dependentVariablesIndex = gj.getDependentVariables();
		List<DependentVariable> dependentVariables = new ArrayList<DependentVariable>();
		double[] results = gj.getResults();
		double[][] solvedSingleMatrix = gj.getSolvedSingleMatrix();
		double[][] originalMatrix = gj.getOriginalMatrix();
		int N = gj.getN();
		FinalEcuation[] finalEcuations = new FinalEcuation[N];
		
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
			System.out.println("X" + (dependentVariableIndex + 1) + " = " + "X" + (dependentVariableIndex + 1));
		}
				
		System.out.println("\nMatrix Results: ");
		for (int i = 0; i < results.length; i++) {
			System.out.println("X" + (i + 1) + " = " + results[i]);
			finalEcuations[i] = new FinalEcuation((int) results[i]);
		}

		for (int i = 0; i < N; i++) {
			if(dependentVariablesIndex.indexOf(i) == -1){
				for (int j = i+1; j < solvedSingleMatrix[i].length; j++) {
					if(solvedSingleMatrix[i][j] != 0){
						finalEcuations[i].addXValue((int) -solvedSingleMatrix[i][j],j);
					}
				}
			}else{
				finalEcuations[i].addXValue(1,i);
			}
		}
		
		System.out.println("\nResult Ecuations: ");
		for (int i = 0; i < finalEcuations.length; i++) {
			System.out.println("X" + (i + 1) + " = " + finalEcuations[i]);
		}
		
		System.out.println("\nAdd missing values... ");
		for (Integer dependentVariableIndex : dependentVariablesIndex) {
			int dependentVariableValue = Integer.parseInt(JOptionPane.showInputDialog(null, "Value of X" + (dependentVariableIndex + 1) + ": "));
			dependentVariables.add(new DependentVariable(dependentVariableIndex, dependentVariableValue));
		}
		
		System.out.println("\nFinal Results: ");
		for (int i = 0; i < finalEcuations.length; i++) {
			int result = finalEcuations[i].getIndependentValue();
			for (XValue xValue : finalEcuations[i].getxValues()) {
				for (DependentVariable dependentVariable : dependentVariables) {
					if(dependentVariable.getIndex() == xValue.getIndex()){
						result += xValue.getValue()*dependentVariable.getValue();
					}
				}
			}
			System.out.print("X" + (i + 1) + " = " + result);
			if(result < 0){
				System.out.print(" <-ERROR, no puede ser negativo el tráfico...");
			}
			System.out.println();
		}*/
		System.out.println("\nFinal...");
	}

}
