
// --== CS400 File Header Information ==--
// Name: Irving Peng
// Email: cpeng53@wisc.edu
// Team: Red
// Group: BF
// TA: BRIANNA COCHRAN
// Lecturer: GARY DAHL
// Notes to Grader: n/a
import java.util.NoSuchElementException;
import java.util.LinkedList;
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	
	//table for key-value pair
	private LinkedList<HashNode<KeyType,ValueType>>[] hashTable;
	private int keyNumber;
	private int capacity;
	/**
	 * 
	 * @param capacity
	 */
	public HashTableMap(int capacity) {
		hashTable = new LinkedList[capacity];
		this.capacity = capacity;
		keyNumber = 0;
		for(int i = 0; i < capacity; i++) {
			//initializing all the index to be a empty linked list
			hashTable[i] = new LinkedList<HashNode<KeyType,ValueType>>();
		}
	}
	/**
	 * 
	 */
	public HashTableMap() {
		// with default capacity = 10
		hashTable = new LinkedList[10];
		this.capacity = 10;
		keyNumber = 0;
		for(int i = 0; i < capacity; i++) {
			//initializing all the index to be a empty linked list
			hashTable[i] = new LinkedList<HashNode<KeyType,ValueType>>();
		}
	}
	//helper method
	public void expand() {
		//check if the load factor is too large
		if(1==1) {
			LinkedList<HashNode<KeyType,ValueType>>[] oldHashTable = hashTable;//copy down the old array
			this.capacity = this.capacity * 2;//refresh the capacity
			hashTable = new LinkedList[capacity];//refresh the array
			for(int i = 0; i < capacity; i++) {
				//initializing all the index to be a empty linked list
				hashTable[i] = new LinkedList<HashNode<KeyType,ValueType>>();
			}
			keyNumber = 0;
			//System.out.println("Key number in expamd(): "+keyNumber);
			//iterating all keys in the old hash table
			for(int i = 0; i < oldHashTable.length; i++) {
				//copying all collection of the keys in a non-empty index
				if(oldHashTable[i]!=null) {
					for(int j = 0; j < oldHashTable[i].size(); j++) {
					//adding the key and value to a the new arr
						HashNode<KeyType, ValueType> thisNode = oldHashTable[i].get(j);
						KeyType thisKey = thisNode.getKey();
						ValueType thisValue = thisNode.getValue();
						this.put(thisKey, thisValue);
						//System.out.println(thisKey+" is added");
					}
				}
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		//check if key is null
		if(key == null)
			return false;
		//check if the key already existed
		int code = key.hashCode();
		int index = code % capacity;
		for(int i =0; i < hashTable[index].size(); i++) {
			//iterating through the matching index to check duplication
			HashNode<KeyType, ValueType> thisNode = hashTable[index].get(i);
			if(thisNode.getKey().equals(key))
				return false;
		}
		//add the key to the front of the link list
		double loadFactor = (double) (keyNumber + 1.0)/capacity;
		//System.out.println("keyNumber: "+keyNumber+ ". Capacity: "+ capacity +". loadfactor: "+ loadFactor);
		HashNode<KeyType, ValueType> newNode = new HashNode<KeyType, ValueType>(key,value);
		if(loadFactor >=0.85) {
			this.expand();//check if expansion is necessary
		}
		index = code % capacity; // the new index if after expansion
		hashTable[index].add(0,newNode);
		keyNumber++;
		return true;	
	}
	
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		int code = key.hashCode();
		int index = code % capacity;
		
		for(int i = 0; i < hashTable[index].size(); i++) {
			HashNode<KeyType,ValueType> thisNode = hashTable[index].get(i);
			if(thisNode.getKey().equals(key)) {
				return thisNode.getValue();
			}
		}
		
		throw new NoSuchElementException("The key is not found");
	}
	@Override
	public int size() {
		return keyNumber;
	}
	@Override
	public boolean containsKey(KeyType key) {
		int code = key.hashCode();
		int index = code % capacity;
		
		for(int i = 0; i < hashTable[index].size(); i++) {
			HashNode<KeyType,ValueType> thisNode = hashTable[index].get(i);
			if(thisNode.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public ValueType remove(KeyType key) {
		int code = key.hashCode();
		int index = code % capacity;
		
		for(int i = 0; i < hashTable[index].size(); i++) {
			HashNode<KeyType,ValueType> thisNode = hashTable[index].get(i);
			if(thisNode.getKey().equals(key)) {
				hashTable[index].remove(i);
				keyNumber--;
				return thisNode.getValue();
			}
		}
		return null;
	}
	@Override
	public void clear() {
		//set key number to 0
		keyNumber = 0;
		//set the hashTable to empty with same capacity
		hashTable = new LinkedList[capacity];
		for(int i = 0; i < capacity; i++) {
			//initializing all the index to be a empty linked list
			hashTable[i] = new LinkedList<HashNode<KeyType,ValueType>>();
		}
	}	
}
