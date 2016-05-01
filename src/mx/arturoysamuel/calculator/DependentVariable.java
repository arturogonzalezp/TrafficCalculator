package mx.arturoysamuel.calculator;

public class DependentVariable {
	private int value;
	private int index;
	public DependentVariable(int index){
		this.setIndex(index);
		this.setValue(0);
	}
	public DependentVariable(int index, int value){
		this(index);
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
