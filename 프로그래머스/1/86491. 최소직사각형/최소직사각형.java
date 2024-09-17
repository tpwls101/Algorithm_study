import java.util.*;

/**
큰 것끼리 비교하고 작은 것끼리 비교하는 것이 포인트!!
*/

class Solution {
    public int solution(int[][] sizes) {
        
        // <방법1>
        for(int[] size : sizes) {
            Arrays.sort(size);
        }
        int maxW = 0; // 작은 값들끼리 비교해서 가장 큰 값
        int maxH = 0; // 큰 값들끼리 비교해서 가장 큰 값
        for(int[] size : sizes) {
            maxW = Math.max(maxW, size[0]);
            maxH = Math.max(maxH, size[1]);
        }
        return maxW * maxH;
    }
}