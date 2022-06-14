package CNode;

import java.util.Random;
public abstract class Random_Abstract {
	public static  Shapee[] arr ;
	public static Shapee[] randomNode(int n) {
		arr = new Shapee[n];
		Random r = new Random();
		for (int i = 0; i < n; i++) {
		//	arr[i].setValue(1 + r.nextInt(n));
		//	arr[i] = new Shapee(1 + r.nextInt(n),i);
		if(SetSize.SHAPEE_TYPE== Shapee.Type.COLUMN) 
		{
			arr[i]= new CNode(1 + r.nextInt(n),i);
			((CNode) arr[i]).randomCNodes(arr.length,arr[i].getValue(),(CNode) arr[i]);
		}
		else
			
		{
			arr[i]= new CNode_C(1 + r.nextInt(n),i);
			((CNode_C) arr[i]).randomCNodes(arr.length,arr[i].getValue(),(CNode_C) arr[i]);
		}
		}
		return arr;
	}
	
}
