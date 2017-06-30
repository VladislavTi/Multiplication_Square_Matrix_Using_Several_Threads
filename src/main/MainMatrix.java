package main;

import java.util.Scanner;

import array.Array;
import arrayFunc.Functions;

public class MainMatrix {
	
	private static Scanner in;
	private static Thread[] threadArray;
	private static Array arrayA, arrayB, arrayResult;

	public static void main(String[] args) {

		in = new Scanner(System.in);
		System.out.println("Enter N of matrix [N,N]");
		int enterN = in.nextInt();
		
		System.out.println("Enter the number of threads");
		int threadCount = in.nextInt();
		
		//Validation of entered data
		if ((threadCount < 1) || (enterN < 1)){
			System.out.println("Thread count and matrix size must be >= 1");
			System.exit(0);
		}

		
		System.out.println("Creating first matrix...");
		arrayA = new Array("Matrix A", enterN, true);
		arrayA.show();
				
		
		System.out.println("Creating second matrix...");
		arrayB = new Array("Matrix B", enterN, true);
		arrayB.show();
			
		arrayResult = new Array("Matrix Result", enterN, false);
		
		
		if (threadCount >= arrayA.length) {
			
			//Reduce the number of threads to the size of the matrix
			threadCount = arrayA.length;
			
			threadArray = new Thread[threadCount];
			for (int i = 0; i < threadCount; i++) {
				final int j = i;
				threadArray[j] = new Thread(() -> {
					Functions funcArray = new Functions();
					funcArray.multiply(arrayA, arrayB, arrayResult, 0, j);
				});
			}
			
	
		} else if ((threadCount < arrayA.length) && (threadCount >= 1)) {
			
			threadArray = new Thread[threadCount];
			
			
			int rowsPerThread = 1;
			while ((double)(arrayA.length) / (double)(rowsPerThread) > (double)(threadCount)) {
				rowsPerThread++;
			}
			
			//Validation of correct choise "rowsPerThread"
			if (rowsPerThread * threadCount > arrayA.length) {
				rowsPerThread--;
			}
			

			//Now we are going to create an array with boundary conditions.
			//Each thread will be filling its own rows(*) of the Result array. 
			//Row numbers(*) we are getting from this array
			int [] arr = new int [threadCount + 1];
			arr[threadCount] = arrayA.length-1;
			arr[0] = -1; 
			for (int i=1; i<threadCount; i++) {
				arr[i] = arr[i-1] + rowsPerThread;
			}


			//Creating our threads
			for (int i = 0; i < threadCount; i++) {
				final int j = i;
				threadArray[j] = new Thread(() -> {
					Functions funcArray = new Functions();
					funcArray.multiply(arrayA, arrayB, arrayResult, arr[j]+1, arr[j+1]);
				});
			}
			
			
			
			
			
		} 
		
		//Starting threads and wait until they finished
		try {
			waitThread(threadCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//show Result matrix
		arrayResult.show();
	
	}
	
	
	
	
	private static void waitThread(final int length) throws InterruptedException {
		for (int j=0; j<length; j++) {
			threadArray[j].start();
			threadArray[j].join();
		}
	
	}
	

	
	
	

}
