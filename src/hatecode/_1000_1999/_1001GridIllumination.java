package hatecode._1000_1999;

import java.util.*;
public class _1001GridIllumination {
/*
1001. Grid Illumination
On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.

Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).

For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.

After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)

Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].


Example 1:

Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
Output: [1,0]
*/
    //O(Q + L)/O(L) l =lamps.length, Q = queries.length
    
    //thinking process:
    
    //Given a board nxn, 2D array stands for lamps, and all 
    //points on same x, y and both dial will be Illumination
    //
    //we have a query coordination, we want to know whether 
    //the coordination is illuminated or not
    //and after each query, the lamp will turned off in order
    
    //so this is like N queen questions
    
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, 
                    {1,-1}, {-1,1}, {-1,-1}, {0,0}};
    
	public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        //rowNumer, lamp count
	    Map<Integer, Integer> m1 = new HashMap<>();       // row number to count of lamps
        //col number, lamp count
	    Map<Integer, Integer> m2 = new HashMap<>();        // col number to count of lamps
        Map<Integer, Integer> m3 = new HashMap<>();        // diagonal x-y to count of lamps
        Map<Integer, Integer> m4 = new HashMap<>();       // diagonal x+y to count of lamps
        //x * c + y, change to 1 dimension index, 
        Map<Integer, Boolean> m5 = new HashMap<>();      // whether lamp is  ON|OFF
        
        // increment counters while adding lamps
        for(int i=0; i<lamps.length; i++){
            int x = lamps[i][0];
            int y = lamps[i][1];
            m1.put(x, m1.getOrDefault(x, 0) + 1);
            m2.put(y, m2.getOrDefault(y, 0) + 1);
            m3.put(x-y, m3.getOrDefault(x-y, 0) + 1);
            m4.put(x+y, m4.getOrDefault(x+y, 0) + 1);
            m5.put(N*x + y, true);
        }

        int[] ans = new int[queries.length];
        // address queries
        for(int i=0; i<queries.length; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            
            ans[i] = (m1.getOrDefault(x, 0) > 0 
                    || m2.getOrDefault(y, 0) > 0 
                    || m3.getOrDefault(x-y, 0) > 0 
                    || m4.getOrDefault(x+y, 0) > 0) ? 1 : 0;            
            // switch off the lamps, change the 5 maps
            //we have 8 directions but with origin point
            for(int d=0; d<dirs.length; d++){
                int x1 = x + dirs[d][0];
                int y1 = y + dirs[d][1];
                if (m5.containsKey(N*x1 + y1) && m5.get(N*x1 + y1)) {
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    m1.put(x1, m1.getOrDefault(x1, 1) - 1);
                    m2.put(y1, m2.getOrDefault(y1, 1) - 1);
                    m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
                    m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
                    m5.put(N*x1+y1, false);
                }
            }
        }
        
        return ans;
    }
}