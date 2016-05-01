package mx.arturoysamuel.calculator;

import java.util.ArrayList;
import java.util.List;

public class GaussJordanElimination {
    private static final double EPSILON = 1e-8;
    private final int N;      // N-by-N system
    private double[][] a;     // N-by-N+1 augmented matrix
    private double[][] originalMatrix;
    private double[][] solvedSingleMatrix;
    private double[] results;
    private List<Integer> dependentVariables;
    public GaussJordanElimination(double[][] A, double[] b) {
        N = b.length;
        originalMatrix = A;
        results = new double[N];
        solvedSingleMatrix = new double[N][N];
        
        a = new double[N][N+N+1];
        dependentVariables = new ArrayList<Integer>();
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                a[i][j] = A[i][j];
            }
        }
        
        for (int i = 0; i < N; i++){
            a[i][N+i] = 1.0;
        }
        for (int i = 0; i < N; i++){
        	a[i][N+N] = b[i];
        }
        solve();
        assert check(A, b);
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solvedSingleMatrix[i][j] = a[i][j];
				if(i == j && a[i][j] == 0){
					dependentVariables.add(i);
				}
			}
			results[i] = a[i][N+N];
		}
    }
    public static double getEpsilon() {
		return EPSILON;
	}
	public int getN() {
		return N;
	}
	public double[][] getOriginalMatrix() {
		return originalMatrix;
	}
	public double[][] getSolvedSingleMatrix() {
		return solvedSingleMatrix;
	}
	public List<Integer> getDependentVariables() {
		return dependentVariables;
	}
	public double[] getResults() {
		return results;
	}
	private void solve() {
        for (int p = 0, q = 0, difference = 0; p < N && q < N; p++, q++) {
            showSingleMatrix();
            int max = p;
            for (int i = p+1; i < N; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }
            swap(p, max);
            if(Math.abs(a[p][q]) <= EPSILON){
            	p--;
            	continue;
            }
            pivot(p, q);
            int lastRowPosible = N-1-difference;
            if(0 < q - p && lastRowPosible > p){
            	swap(p, lastRowPosible);
            	difference++;
            	q = p;
            }
        }
        //showSingleMatrix();
    }
    private void swap(int row1, int row2) {
        double[] temp = a[row1];
        a[row1] = a[row2];
        a[row2] = temp;
    }
    private void pivot(int p, int q) {  	
        for (int i = 0; i < N; i++) {
            double alpha = a[i][q] / a[p][q];
            for (int j = 0; j <= N+N; j++) {
                if (i != p && j != q){
                	a[i][j] -= alpha * a[p][j];
                }
            }
        }
        for (int i = 0; i < N; i++){
            if (i != p){
            	a[i][q] = 0.0;
            }
        }
        for (int j = 0; j <= N+N; j++){
            if (j != q){
            	a[p][j] /= a[p][q];
            }
        }
        a[p][q] = 1.0;
    }
    public double[] primal() {
        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
        	if (Math.abs(a[i][i]) > EPSILON){
                x[i] = a[i][N+N] / a[i][i];
            }else if (Math.abs(a[i][N+N]) > EPSILON){
                return null;
            }
        }
        return x;
    }
    private double[] dual() {
        double[] y = new double[N];
        for (int i = 0; i < N; i++) {
            if ((Math.abs(a[i][i]) <= EPSILON) && (Math.abs(a[i][N+N]) > EPSILON) ){
                for (int j = 0; j < N; j++){
                    y[j] = a[i][N+j];
                }
                return y;
            }
        }
        return null;
    }
    private boolean isFeasible() {
        return primal() != null;
    }
    private void show() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%4.1f ", a[i][j]);
            }
            System.out.printf("| ");
            for (int j = N; j < N+N; j++) {
                System.out.printf("%4.1f ", a[i][j]);
            }
            System.out.printf("| %4.1f\n", a[i][N+N]);
        }
        System.out.println();
    }
    private void showSingleMatrix(){
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%4.1f ", a[i][j]);
            }
            System.out.printf("| %4.1f\n", a[i][N+N]);
        }
        System.out.println();
    }
    private boolean check(double[][] A, double[] b) {
        if (isFeasible()) {
            double[] x = primal();
            for (int i = 0; i < N; i++) {
                double sum = 0.0;
                for (int j = 0; j < N; j++) {
                     sum += A[i][j] * x[j];
                }
                if (Math.abs(sum - b[i]) > EPSILON) {
                    System.out.println("not feasible");
                    System.out.printf("b[%d] = %8.3f, sum = %8.3f\n", i, b[i], sum);
                   return false;
                }
            }
            return true;
        }else{
            double[] y = dual();
            for (int j = 0; j < N; j++) {
                double sum = 0.0;
                for (int i = 0; i < N; i++) {
                     sum += A[i][j] * y[i];
                }
                if (Math.abs(sum) > EPSILON) {
                    System.out.println("invalid certificate of infeasibility");
                    System.out.printf("sum = %8.3f\n", sum);
                    return false;
                }
            }
            double sum = 0.0;
            for (int i = 0; i < N; i++) {
                sum += y[i] * b[i];
            }
            if (Math.abs(sum) < EPSILON) {
                System.out.println("invalid certificate of infeasibility");
                System.out.printf("yb  = %8.3f\n", sum);
                return false;
            }
            return true;
        }
    }
}