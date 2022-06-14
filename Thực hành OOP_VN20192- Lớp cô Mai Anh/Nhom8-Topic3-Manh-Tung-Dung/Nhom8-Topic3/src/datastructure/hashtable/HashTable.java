package datastructure.hashtable;

import java.util.ArrayList;

import datastructure.hashtable.HashNode;

public class HashTable<Key, Value> {

	private ArrayList<HashNode<Key, Value>> bucket = new ArrayList<>();
	private HashFunction<Key> hF = new HashFunction<Key>();
	private int numBuckets = 10;
	private int size;

	public HashTable() {
		this.initBucket();
	}
	
	private void initBucket() {
		for (int i = 0; i < this.numBuckets; i++) {
			this.bucket.add(null);
		}
	}

	public int getSize() {
		return this.size;
	}

	public int getNumBuckets() {
		return this.numBuckets;
	}

	public ArrayList<HashNode<Key, Value>> getBucket() {
		return this.bucket;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		bucket.clear();
		this.initBucket();
		this.size = 0;
	}

	public Value get(Key key) {
		int index = hF.hashFunction(key);
		if(index > bucket.size()) return null;
		HashNode<Key, Value> head = bucket.get(index);

		while (head != null) {
			if (head.key.equals(key))
				return head.value;
			head = head.next;
		}
		return null;
	}

	public Value remove(Key key) {
		int index = hF.hashFunction(key);
		HashNode<Key, Value> head = this.bucket.get(index);

		if (head == null)
			return null;
		if (head.key.equals(key)) {
			Value val = head.value;
			head = head.next;
			this.bucket.set(index, head);
			this.size--;
			return val;
		} else {
			HashNode<Key, Value> prev = null;
			while (head != null) {
				if (head.key.equals(key)) {
					prev.next = head.next;
					this.size--;
					return head.value;
				}
				prev = head;
				head = head.next;
			}
			this.size--;
			return null;
		}
	}

	public void add(Key key, Value value) {

		int index = hF.hashFunction(key);
		
		HashNode<Key, Value> head = this.bucket.get(index); 
		HashNode<Key, Value> newNode = new HashNode<>();
		newNode.key = key;
		newNode.value = value;
		if (head == null) {
			this.bucket.set(index, newNode);
			this.size++;
		} else {
			HashNode<Key, Value> tmpHashNode = head;
			while (tmpHashNode != null) {
				if (tmpHashNode.key.equals(key)) {
					tmpHashNode.value = value;
					break;
				}
				tmpHashNode = tmpHashNode.next;
			}
			if (tmpHashNode == null) {
				tmpHashNode = head;
				while (tmpHashNode.next != null) {
					tmpHashNode = tmpHashNode.next;
				}
				tmpHashNode.next = newNode;
				this.size++;
			}
		}
	}

}
