package arrayFunc;

import array.Array;

public class Functions {

	
	public Array multiply(Array arr1, Array arr2, Array where, int rowBegin, int rowEnd) {
		int length = arr1.length;
		Array res = new Array("Result of Multiply", length, false);
		
		
		for(int i = rowBegin; i <= rowEnd; i++){
		      for(int j = 0; j < length; j++){
		    	  long cellResult = 0l;
		          for(int k = 0; k < length; k++){
		        	  cellResult += arr1.getElement(i, k) * arr2.getElement(k, j);
		          }
		          res.setElement(i, j, cellResult, where);
		      }
		  }
		
		return res;
	}
	
}
