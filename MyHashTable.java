import java.util.*;
import java.util.Arrays;
/**
 * Hash table
 *
 * @author Connor Jordan
 * @version V1
 */
public class MyHashTable<K, V> {
    private int size;
    private double loadFactor;
    private MyHashTableNode<K, V>[] table;

    /**
     * Constructor for objects of class MyHashTable
     */
    public MyHashTable() {
        size = 0;
        loadFactor = .7;
        table =(MyHashTableNode<K, V>[]) new MyHashTableNode[10];
    }

    /**
     * Associate the specified value with the specified key in this hash table
     *
     * @param  key   the key
     * @param  value  the value
     */
    public void put(K key, V value) {
        int keyHashed = hashFunction(key);
        
        if(table[keyHashed] != null) { //has value
            if(searchBucket(keyHashed, key) != null && 
                    searchBucket(keyHashed, key).getKey().equals(key)) {
                searchBucket(keyHashed, key).setValue(value);
            } else {
                size++;
                addToBucket(keyHashed, new MyHashTableNode(key, value));
            }
        } else {
            size++;
            table[keyHashed] = new MyHashTableNode(key, value);
        }
        
        if((double)size / table.length >= loadFactor) {
            expandHashTable();
        }
    }
    
    /**
     * Returns the value to which the specified key is mapped, or null 
     * if this hash table contains no mapping for the key.
     * 
     * @param  key  the key for the location to return;
     * 
     * @return the value of the key
     */
    public V get(K key) {
        int keyHashed = hashFunction(key);
        MyHashTableNode<K,V> currNode = table[keyHashed];
        
        if(searchBucket(keyHashed, key) == null) { //empty
            return null;
        } else { //not empty
            return searchBucket(keyHashed, key).getValue();
        }
    }
    
    /**
     *  Removes and returns the key (and its corresponding value) 
     *  from this hash table.
     *  
     *  @param  key  the key to remove the value
     *  
     *  @return  the string to be removed
     */
    public K remove(K key) {
        if (get(key) == null) {
            return null;
        } else {
            int keyHashed = hashFunction(key);
            MyHashTableNode<K, V> currNode = table[keyHashed];
            while(!(currNode.getKey().equals(key))) {
                currNode = currNode.getNext();
            }
            removeFromBucket(keyHashed, currNode);
            size--;
            return currNode.getKey();
        }
    }
    
    /**
     * Returns number of mappings in hash table.
     * 
     * @return the number of mappings
     */
    public int size() {
        return size;
    }
    
    /**
     * Indicates whether hash table is empty.
     * 
     * @return if the hash table is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the contents of the hash table for testing purposes.
     * 
     * @return the contents of the hash table
     */
    public String toString() {
        return Arrays.toString(table);
    }
    
    /**
     * Hashes the inputed key
     * 
     * @param  key  the key to be hashed
     * @return  the value of the key hashed
     */
    private int hashFunction(K key) {
        int hash;
        hash = key.hashCode();
        return Math.abs(hash % table.length);
    }
    
    /**
     * Searches the bucket for a node
     * 
     * @param  hashValue  the value of the key hashed
     * @param  key  the key for the value
     * 
     * @return  the found node
     */
    private MyHashTableNode<K, V> searchBucket(int hashValue, K key) {
        MyHashTableNode<K, V> currNode = table[hashValue];
        if(table[hashValue] == null) { // search bucket does this
            return null;
        } else {
            while (currNode != null) {
                if(currNode.getKey().equals(key)) {
                    return currNode;
                }
                currNode = currNode.getNext();
            }
            return null;
        }
    }
    
    /**
     * Adds a key value pair to a bucket
     * 
     * @param  hashValue  the value of the key hashed
     * @param  newNode  the node to be added to the bucket
     */
    private void addToBucket(int hashValue, MyHashTableNode newNode) {
        newNode.setNext(table[hashValue]);
        table[hashValue] = newNode;
    }
    
    /**
     * Removes the inputed node from the bucket
     * 
     * @param  hashValue  the hash value of the node to be removed
     * @param  oldNode  the node to be removed
     */
    private void removeFromBucket(int hashValue, MyHashTableNode oldNode) {
        MyHashTableNode<K, V> currNode = table[hashValue];
        if(currNode.equals(oldNode)) { //start
            table[hashValue] = oldNode.getNext();
        } else if (oldNode.getNext() == null) { //end
            while (!(currNode.getNext().equals(oldNode))) {
                currNode = currNode.getNext();
            }
            currNode.setNext(null);
        } else { //middle
            while (!(currNode.getNext().equals(oldNode))) {
                currNode = currNode.getNext();
            }
            currNode.setNext(oldNode.getNext());
        }
    }
    
    /**
     * Doubles the size of the hashTable
     */
    private void expandHashTable() {
        MyHashTableNode<K, V>[] oldTable = table;
        table = (MyHashTableNode<K, V>[]) 
                new MyHashTableNode[table.length * 2];
        for(int i = 0; i < oldTable.length; i++) {
            MyHashTableNode<K, V> currNode = oldTable[i];
            while(currNode != null) {
                MyHashTableNode<K, V> nextNode = currNode.getNext();
                addToBucket(currNode.getHashCode() % table.length, currNode);
                currNode = nextNode;
            }
        }
    }
}

class MyHashTableNode<K, V> {
    private MyHashTableNode next;
    private K key;
    private V value;
    private int hashCodeVal;
    
    MyHashTableNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.hashCodeVal = Math.abs(key.hashCode());
    }
    
    K getKey() {
        return key;
    }
    
    V getValue() {
        return value;
    }
    
    MyHashTableNode<K, V> getNext() {
        return next;
    }
    
    int getHashCode() {
        return hashCodeVal;
    }
    
    void setNext(MyHashTableNode next) {
        this.next = next;
    }
    
    void setValue(V value) {
        this.value = value;
    }
    
    public String toString() {
        String toBeReturned = key + "->" + value;
        MyHashTableNode currNode = next;
        
        while (currNode != null) {
            toBeReturned += "|" + currNode.getKey() + "->" + 
                    currNode.getValue();
            currNode = currNode.getNext();
        }
        return toBeReturned;
    }
}
