package functions;

import array.Array;

public class Functions {
    
    //This has a Cyclomatic Complexity = 4
    public Array multiply(Array arr1, Array arr2, Array where, int rowBegin, int rowEnd) {
        int length = arr1.getLength();
        Array res = new Array("Result of Multiply", length, false);

        for (int i = rowBegin; i <= rowEnd; i++) {
            for (int j = 0; j < length; j++) {
                long cellResult = 0L;
                for (int k = 0; k < length; k++) {
                    cellResult += arr1.getElement(i, k) * arr2.getElement(k, j);
                }
            res.setElement(i, j, cellResult, where);
            }
        }

        return res;
    }

}
