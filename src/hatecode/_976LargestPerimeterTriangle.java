package hatecode;

import java.util.*;
public class _976LargestPerimeterTriangle {
/*
976. Largest Perimeter Triangle
Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.

 

Example 1:

Input: [2,1,2]
Output: 5
*/
    //thinking process:
    //
    public int largestPerimeter(int[] A) {
        if(A == null || A.length <3) return 0;
        
        Arrays.sort(A);
        for(int i = A.length - 1; i>=2; i--) {
            if(A[i] < A[i-1] + A[i-2]) return A[i] +A[i-1] +A[i-2];
        }
        return 0;
    }
}