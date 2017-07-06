package array;

import java.util.Arrays;

/**
* 
*
* @author Tishchenko Vladislav
*/
public class Array {
    /*
     *  name of Array
     */
        private String name;
    /*
     *  length of Array array
     */
        private int length;
    /*
     *  our matrix or array
     */
        private long[][] matrix; 

        public Array(final String name, final int arrayLength, final boolean fillRandom) {
            this.name = name;
            this.length = arrayLength;
            if (fillRandom) {
                this.matrix = matrixFillRandom(arrayLength);
            } else {
                this.matrix = createEmptyArr(arrayLength);
            }

        }
        
        
    /*
     *  get name
     */    
        public String getName() {
            return this.name;
        }
        
        
    /*
     *  get length
     */   
        public int getLength() {
        return this.length;
        }
        
        
    /*
     *  get matrix
     */  
        public long[][] getMatrix() {
        return this.matrix.clone();
        }
        
        
    /*
     *  set name
     */  
        public void setName(final String name) {
        this.name = name;
        }
        
        
    /*
     *  set length
     */
        public void setLength(final int length) {
        this.length = length;
        }
        
        
    /*
     *  create empty array
     */
        private long[][] createEmptyArr(final int arrayLength) {
            return new long[arrayLength][arrayLength];
        }
        
        
    /*
     *  return value of element from array
     */
        public long getElement(final int row, final int column) {
            return this.matrix[row][column];
        }
        
        
    /*
     *  set value to array element
     */
        public void setElement(final int row, final int column, final long what) {
            this.matrix[row][column] = what;
        }
        
        
    /*
    * 
    *
    * set value to array of class Array 
    */
        public void setElement(final int row, final int column, final long what, final Array where) {
            where.setElement(row, column, what);
        }
        
        
    /*
     *  fill array with random values 
     */
        private long[][] matrixFillRandom(final int arrayLength) {
            long[][] matrix = new long[arrayLength][arrayLength];

            for (int i = 0; i < arrayLength; i++) {
                for (int j = 0; j < arrayLength; j++) {
                    matrix[i][j] = (long) (100d * Math.random());
                }
            }

            return matrix;
        }
        
        
    /*
     *  print array to console
     */
        public void show() {
            System.out.println("Matrix " + name + " is: ");
        
            //Go through the elements of the array
            for (int i = 0; i < this.length; i++) {
                //print element
                System.out.println(Arrays.toString(this.matrix[i]));
            }
            System.out.println();
        }

    }
