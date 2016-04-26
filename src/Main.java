import java.util.ArrayList;
import java.util.List;

import mx.arturoysamuel.calculator.GaussJordanElimination;
import mx.arturoysamuel.calculator.PairValues;


public class Main {

	public static void main(String[] args) {
		double[][] A = {
				{1, 0, 0, -1},
				{1, -1, 0, 0},
				{0, 0, -1, 1},
				{0, 1, -1, 0},
		};
		double[] b = { -100, 300, 500, 100};
		/*double[][] A = {
				{1, 0, 1, 0, 0, 0, 0},
				{1, -1, 0, 1, 0, 0, 0},
				{0, 1, 0, 0, -1, 0, 0},
				{0, 0, 1, 0, 0, 1, 0},
				{0, 0, 0, 1, 0, 1, -1},
				{0, 0, 0, 0, -1, 0, 1}
	        };
		double[] b = {800, 200, 500, 750, 600, 50};*/
		GaussJordanElimination.test(A, b);
		/*GaussJordanElimination gj = new GaussJordanElimination(A, b);
		if(gj.isFeasible()){
			double[] r = gj.primal();
			double[] finalResult = new double[r.length];
			double minVal = 0;
			List<PairValues> negativeNumPos = new ArrayList<PairValues>();
			for (int i = 0; i < r.length; i++) {
				if(minVal >= r[i]){
					minVal = r[i];
				}
				if(r[i] < 0){
					negativeNumPos.add(new PairValues(i, r[i]));
				}
			}
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < negativeNumPos.size(); j++) {
					if(A[i][negativeNumPos.get(j).index] < 0){
						
					}
					//System.out.println(negativeNumPos.get(j).index + ", " + negativeNumPos.get(j).value + " ");
				}
			}
			if(minVal < 0){
				double minValAbs = Math.abs(minVal);
				for(int i = 0; i < r.length; i++){
					r[i] = r[i] + minValAbs;
				}
			}
			for (int i = 0; i < r.length; i++) {
				System.out.println((char) (65 + i) + ": " + r[i]);
			}
		}else{
			System.err.println("ERROR");
		}*/
	}

}
