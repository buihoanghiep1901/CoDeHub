package binarySearch;

import java.util.ArrayList;

import shape.Square;

public class BinarySearch {
	int binarySearch(ArrayList<Square> arraySquare, int l, int r, int x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arraySquare.get(mid).getNumber() == x)
				return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arraySquare.get(mid).getNumber() > x)
				return binarySearch(arraySquare, l, mid - 1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arraySquare, mid + 1, r, x);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}
}
