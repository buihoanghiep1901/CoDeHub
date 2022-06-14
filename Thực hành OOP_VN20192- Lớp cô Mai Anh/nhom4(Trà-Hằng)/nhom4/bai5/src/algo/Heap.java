package algo;

import element.ElementArray;

public class Heap implements Algorithm {

    private static Heap ourInstance = new Heap();

    public static Heap getInstance() {
        return ourInstance;
    }


    private Heap() {
    }

    @Override
    public void sort(ElementArray arr) {
        int n = arr.length();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
//            int temp = arr[0];
//            arr[0] = arr[i];
//            arr[i] = temp;
            arr.swap(0, i, true);
            arr.addDoneStep(arr.getElementAt(i).getIndex(), true);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        arr.addDoneStep(arr.getElementAt(0).getIndex(), true);
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(ElementArray arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
//        if (l < n && arr[l] > arr[largest])
        if (l < n) {
            if (arr.compare(l, largest, true) < 0)
                largest = l;
        }


        // If right child is larger than largest so far
//        if (r < n && arr[r] > arr[largest])
        if (r < n) {
            if (arr.compare(r, largest, true) < 0)
                largest = r;
        }


        // If largest is not root
        if (largest != i) {
//            int swap = arr[i];
//            arr[i] = arr[largest];
//            arr[largest] = swap;
            arr.swap(i, largest, true);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }


    @Override
    public String toString() {
        return "Heap";
    }
}
