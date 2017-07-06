package main;

import java.util.Scanner;
import array.Array;
import functions.Functions;

//This has a Cyclomatic Complexity = 6
public class MainMatrix {
    /**
     * @author Tishchenko Vladislav
     */

    /*
     * Scanner
     */
    private static Scanner in;
    /*
     * Array of thread to work with matrix multiplying
     */
    private static Thread[] threadArray;
    /*
     * arrayA - first matrix (A); arrayB - second matrix (B); arrayResult -
     * result matrix (A multiply B)
     */
    private static Array arrayA, arrayB, arrayResult;

    // This has a Cyclomatic Complexity = 10
    public static void main(final String[] args) {

    in = new Scanner(System.in);
    System.out.println("Enter N of matrix [N,N]");
    final int enterN = in.nextInt();

    System.out.println("Enter the number of threads");
    int threadCount = in.nextInt();

    // Validation of entered data
    if (threadCount < 1 || enterN < 1) {
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

    if (threadCount >= arrayA.getLength()) {

        // Reduce the number of threads to the size of the matrix
        threadCount = arrayA.getLength();

        // Create array of threads
        threadArray = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
        final int ourIndex = i;
        threadArray[ourIndex] = new Thread(() -> {
            final Functions funcArray = new Functions();
            funcArray.multiply(arrayA, arrayB, arrayResult, 0, ourIndex);
        });
        }

    } else if (threadCount < arrayA.getLength() && threadCount >= 1) {

        int rowsPerThread = 1;
        while ((double) (arrayA.getLength()) / (double) (rowsPerThread) > (double) (threadCount)) {
        rowsPerThread++;
        }

        // Validation of correct choise "rowsPerThread"
        if (rowsPerThread * threadCount > arrayA.getLength()) {
        rowsPerThread--;
        }

        // Now we are going to create an array with boundary conditions.
        // Each thread will be filling its own rows(*) of the Result array.
        // Row numbers(*) we are getting from this array
        int[] arrayBoundCond = new int[threadCount + 1];
        arrayBoundCond[threadCount] = arrayA.getLength() - 1;
        arrayBoundCond[0] = -1;
        for (int i = 1; i < threadCount; i++) {
            arrayBoundCond[i] = arrayBoundCond[i - 1] + rowsPerThread;
        }

        // Creating our threads
        threadArray = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
        final int j = i;
        threadArray[j] = new Thread(() -> {
            // Creating new variable of Functions type
            final Functions funcArray = new Functions();

            // Start multiply matrix A and B and set result to some rows
            // of matrix Result
            funcArray.multiply(arrayA, arrayB, arrayResult, arrayBoundCond[j] + 1,
        	    arrayBoundCond[j + 1]);
        });
        }

    }

    // Starting threads and wait until they finished
    try {
        waitThread(threadCount);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // show Result matrix
    arrayResult.show();

    }

    /*
     * Starting and waiting for threads for matrix multiplying
     */
    private static void waitThread(final int length) throws InterruptedException {
    // start and wait for all threads
    for (int j = 0; j < length; j++) {
        // start thread
        threadArray[j].start();

        // wait until it finished
        threadArray[j].join();
    }

    }

}
