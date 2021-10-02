// --== CS400 File Header Information ==--
// Name: Irving Peng
// Email: cpeng53@wisc.edu
// Team: Red
// Group: BF
// TA: BRIANNA COCHRAN
// Lecturer: GARY DAHL
// Notes to Grader: n/a
public class HashNode<KeyType, ValueType>{
	private KeyType key;
	private ValueType value;
	
	public HashNode(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	public KeyType getKey() {
		return key;
	}
	public void setKey(KeyType key) {
		this.key = key;
	}
	public ValueType getValue() {
		return value;
	}
	public void setValue(ValueType value) {
		this.value = value;
	}
}
