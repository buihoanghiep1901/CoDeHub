package sequentialSearch;

import java.util.ArrayList;

import shape.Square;

public class SequentialSearch {
	public int sequentialSearch(ArrayList<Square> arraySquare, int x) {
		int n = arraySquare.size();
		for (int i = 0; i < n; i++) {
			if (arraySquare.get(i).getNumber() == x)
				return i;
		}
		return -1;
	}
}
