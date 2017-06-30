package array;

import java.util.Arrays;

public class Array {
	
	public String name;
	public int length;
	public long[][] array;
	
	public Array(String name, int n, boolean fillRandom) {
		this.name = name;
		this.length = n;
		if (fillRandom) {
			this.array = matrixFillRandom(n);
		} else {
			this.array = createEmptyArr(n);
		}
		
	}
	
	
	private long[][] createEmptyArr(int n) {
		long[][] matrix = new long[n][n];
		return matrix;
	}

	
	
	public long getElement(int n, int m) {
		return this.array[n][m];
	}
	
	public void setElement(int n, int m, long what) {
		this.array[n][m] = what;
	}
	
	public void setElement(int n, int m, long what, Array where) {
		where.setElement(n, m, what);
	}
	
	
	private long[] [] matrixFillRandom(int n) {
        long [] []matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (long) (100d * Math.random());
            }
        }
        
        return matrix;
    }
	
	public void show() {
		System.out.println("Matrix " + name + " is: ");
		for (int i = 0; i < this.length; i++) {
			System.out.println(Arrays.toString(this.array[i]));
		}
		System.out.println();
	}

}
