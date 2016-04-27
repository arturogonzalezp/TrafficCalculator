package mx.arturoysamuel.calculator;

public class XValue {
	private int index;
	private int value;
	public XValue(int value, int index){
		this.setValue(value);
		this.setIndex(index);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String toString(){
		if(this.getValue() > 0){
			return "+X" + (this.getIndex() + 1);
		}else{
			return "-X" + (this.getIndex() + 1);
		}
	}
}
