package datastructure.hashtable;

public class HashFunction<Key> {

	private int hashMod;

	public HashFunction() {
		super();
		this.hashMod = 10;
	}

	public int getHashMod() {
		return hashMod;
	}

	public void setHashMod(int hashMod) {
		this.hashMod = hashMod;
	}

	public int hashFunction(Key key) {
		long hashCode = 0;
		long pow = 1;

		String keyString = String.valueOf(key);

		for (int i = 0; i < keyString.length(); i++) {
			hashCode = hashCode + (int) keyString.charAt(i) * pow;
			pow = pow * 31;
		}

		return (int) hashCode % this.hashMod;
	}
}
