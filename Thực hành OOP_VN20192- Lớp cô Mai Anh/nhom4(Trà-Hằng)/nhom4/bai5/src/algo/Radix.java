package algo;

import element.Element;
import element.ElementArray;

import java.util.Arrays;

public class Radix implements  Algorithm{

    private ElementArray array1;
    private ElementArray array2;
    private ElementArray array3;

    private static Radix ourInstance = new Radix();

    public static Radix getInstance() {
        return ourInstance;
    }
    @Override
    public  void sort(ElementArray arr) {

        int n = arr.length();
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }



    // A utility function to get maximum value in arr[]
     int getMax(ElementArray arr, int n)
    {
        int mx = arr.getElementAt(0).getValue();
        for (int i = 1; i < n; i++)
            if (arr.getElementAt(i).getValue() > mx)
                mx = arr.getElementAt(i).getValue();
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
     void countSort(ElementArray arr, int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr.getElementAt(i).getValue()/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr.getElementAt(i).getValue()/exp)%10 ] - 1] = arr.getElementAt(i).getValue();
            count[ (arr.getElementAt(i).getValue()/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr.getElementAt(i).setValue(output[i]);

        switch (exp){
            case 1:
//                arr.changeValue(output,1);
                ElementArray arrayTemp = new ElementArray(output, 1);
                setArray1(arrayTemp);
                break;
            case 10:
                ElementArray arrayTemp2 = new ElementArray(output, 2);
                setArray2(arrayTemp2);

                break;
            case 100:
                ElementArray arrayTemp3 = new ElementArray(output, 3);
                setArray3(arrayTemp3);
                break;
            default:
                break;

        }

//        print(arr, n);
    }

    public ElementArray getArray1() {
        return array1;
    }

    public void setArray1(ElementArray array1) {
        this.array1 = array1;
    }

    public ElementArray getArray2() {
        return array2;
    }

    public void setArray2(ElementArray array2) {
        this.array2 = array2;
    }

    public ElementArray getArray3() {
        return array3;
    }

    public void setArray3(ElementArray array3) {
        this.array3 = array3;
    }


    @Override
    public String toString() {
        return "Radix";
    }

}
