class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max_x =0, max_y =0;
        for(int i = 0 ; i < sizes.length ; i++){
            int x = 0 ;
            int y = 0 ;
            if(sizes[i][0] > sizes[i][1] ){
                x = sizes[i][0] ;
                y = sizes[i][1] ;
            }else{
                x = sizes[i][1] ;
                y = sizes[i][0] ;
                
            }
            
            max_x = Math.max(max_x , x);
            max_y = Math.max(max_y , y);
        }
        
        return max_x * max_y;
    }
}