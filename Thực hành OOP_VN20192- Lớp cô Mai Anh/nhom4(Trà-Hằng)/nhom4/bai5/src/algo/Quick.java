package algo;

import element.Element;
import element.ElementArray;

public class Quick implements Algorithm {
    private static Quick ourInstance = new Quick();

    public static Quick getInstance() {
        return ourInstance;
    }

    private Quick() {
    }

    @Override
    public void sort(ElementArray array) {
    	int length = array.length();
        //sort(array, 0, array.length() - 1);
    	quickSort(array, 0, length - 1);  

        for (Element e: array.getAll()) {
            array.steps.addDoneStep(e);
        }
    }      
      
    void quickSort(ElementArray arr, int low, int high)
    {
        if (low < high)
        {
            // pi là chỉ số nơi phần tử này đã đứng đúng vị trí và là phần tử chia mảng làm 2 mảng con trái & phải 
            int pi = partition(arr, low, high);
     
            // Gọi đệ quy sắp xếp 2 mảng con trái và phải
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    int partition(ElementArray arr, int low, int high) 
    { 
        Element pivot = arr.getElementAt(high);  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
        	Element aj = arr.getElementAt(j);		
            if (aj.compareTo(pivot) >= 0) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                arr.swap(i, j);
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        arr.swap(i+1, high);
  
        return i+1; 
    } 
   
    @Override
    public String toString() {
        return "Quick";
    }
}
