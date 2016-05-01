package mx.arturoysamuel.calculator;

import java.util.ArrayList;
import java.util.List;

public class FinalEcuation {
	private List<XValue> xValues;
	private int independentValue;
	private int result;
	public FinalEcuation(){
		this.setxValues(new ArrayList<XValue>());
		this.setIndependentValue(0);
		this.setResult(-1);
	}
	public FinalEcuation(int independentValue){
		this();
		this.setIndependentValue(independentValue);
	}
	public List<XValue> getxValues() {
		return xValues;
	}
	public void setxValues(List<XValue> xValues) {
		this.xValues = xValues;
	}
	public int getIndependentValue() {
		return independentValue;
	}
	public void setIndependentValue(int independentValue) {
		this.independentValue = independentValue;
	}
	public void addXValue(int value, int index){
		this.getxValues().add(new XValue(value, index));
	}
	public int solveWithParameters(List<DependentVariable> dependentVariables){
		int tempResult = this.getIndependentValue();
		for(DependentVariable dependentVariable : dependentVariables){
			for (XValue xValue : this.getxValues()) {
				if(dependentVariable.getIndex() == xValue.getIndex()){
					tempResult += xValue.getValue()*dependentVariable.getValue();
				}
			}
		}
		this.setResult(tempResult);
		return this.getResult();
	}
	public int getResult(){
		return result;
	}
	private void setResult(int result) {
		this.result = result;
	}
	public String toString(){
		String s = this.getIndependentValue() + " ";
		for (XValue value : this.getxValues()) {
			s += value + " ";
		}
		if(this.getResult() >= 0){
			s+= "= " + this.getResult();
		}
		return s;
	}
}
