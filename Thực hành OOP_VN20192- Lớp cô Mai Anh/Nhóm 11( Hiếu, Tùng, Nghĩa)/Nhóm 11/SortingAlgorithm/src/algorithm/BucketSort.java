package algorithm;

import items.itemArray;
import javafx.scene.shape.Rectangle;


public class BucketSort implements Algorithm{
//	Rectangle[] bucketarray = Controller.bucketarray;
//	Array bucketarray;
//	Controller newBucketArray = new Controller();
//	Rectangle[] bucketArray = newBucketArray.getBucketArray();
	
	private static BucketSort newBucket = new BucketSort();
	
	public static BucketSort getBucket() {
		return newBucket;
	}

	@Override
	public void sort(itemArray array) {
		for(int i=0;i<array.itemsLength();i++) {
	//		array.steps.addBucketpreStep(array.getItemNo(i));
		}
		int[] num = {0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<array.itemsLength();i++) {
			int bucket = array.getItemNo(i).getValue()/10;
			
			for(int j=0;j<10;j++) {
				if(bucket ==j){
				array.steps.addBucketStep(array.getItemNo(i),i, bucket, num[j]);
				System.out.println(num[j]);num[j]++;
//				bucketArray[j].setWidth(70);
//				bucket0.setWidth(70);
			}	
				
			}
			
		}
		
	}





}
