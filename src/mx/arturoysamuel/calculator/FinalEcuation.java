package mx.arturoysamuel.calculator;

import java.util.ArrayList;
import java.util.List;

public class FinalEcuation {
	private List<XValue> xValues;
	private int independentValue;
	public FinalEcuation(){
		this.setxValues(new ArrayList<XValue>());
		this.setIndependentValue(0);
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
	public String toString(){
		String s = this.getIndependentValue() + " ";
		for (XValue value : this.getxValues()) {
			s += value + " ";
		}
		return s;
	}
}
