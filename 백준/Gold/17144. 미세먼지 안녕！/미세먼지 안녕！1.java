import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_17144_미세먼지안녕>
 * 문제 : T초가 지난 후 방에 남아있는 미세먼지의 양을 구해라!
 * 
 * 1. 배열 복사해서 사용하고 1초가 지날 때마다 다시 확산하기 전에 원본배열을 copy배열의 값으로 갱신해줄 것!!
 * 2. 공기청정기 작동시켜 미세먼지 순환시킬 때 큐나 dx,dy 사용하는 것보다 그냥 for문 4번 돌리는게 더 간단한 것 같음
 * 		- 순환할 때는 맨 뒤에서부터 당겨줘야 함!!
 * 
 * @author 유세진
 *
 */

public class Main {
    
    static int R;
    static int C;
    static int T; // T초 후
    static int[][] room; // 원본 배열
    static int[][] copy; // 복사 배열
    static int cleaner_x_top; // 공기청정기(윗칸)의 x좌표 (행)
    static int cleaner_x_down; // 공기청정기(아래칸)의 x좌표 (행)    
    static int cleaner_y = 0; // 공기청정기(윗칸)의 y좌표 (열)
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        copy = new int[R][C];
        
        // 방 상태 입력받기
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 원본 배열 복사
        for(int i=0; i<R; i++) {
            copy[i] = room[i].clone();
        }
        
        // 공기청정기의 좌표 저장
        for(int r=0; r<R; r++) {
            if(room[r][0] == -1) {
                cleaner_x_top = r;
                break;
            }
        }
        cleaner_x_down = cleaner_x_top + 1;
        // System.out.println(cleaner_x + " " + cleaner_y);
        
        // T초 후 방의 상태 구하기
        for(int i=0; i<T; i++) {	    
	        spread(); // 1. 미세먼지 확산하기
	        airCleaner(); // 2. 공기청정기 작동
        }
        
        // T초가 지난 후 방에 남아있는 미세먼지의 양 구하기
        int answer = 0;
        for(int i=0; i<R; i++) {
        	for(int j=0; j<C; j++) {
        		if(copy[i][j] != 0 && copy[i][j] != -1) {
        			answer += copy[i][j];
        		}
        	}
        }
        
        System.out.println(answer);
    }
    
    
    // 미세먼지 확산 메서드 -> 원본 배열 필요!!
    private static void spread() {
        
//      System.out.println("미세먼지 확산 시작");
        
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                // 미세먼지가 있으면 확산
                if(copy[i][j] != 0 && copy[i][j] != -1) {
                    int amount = room[i][j] / 5; // 확산되는 미세먼지의 양
//                    System.out.println("확산되는 미세먼지의 양 : " + amount);
                    // 확산되는 미세먼지의 양이 0이라 확산이 일어나지 않으면 continue
                    if(amount == 0) {
                        continue;
                    }
                    
                    // count() : 미세먼지 확산까지 처리!!
                    int count = count(i, j, amount); // 확산되는 방향의 개수 (현재 x좌표와 y좌표, 확산되는 미세먼지 양 전달)
//                    System.out.println("확산 방향 : " + count + "개");
                    
                    copy[i][j] = copy[i][j] - (amount * count);
                    
//                    for(int a=0; a<R; a++) {
//                        System.out.println(Arrays.toString(copy[a]));
//                    }
//                    System.out.println();
                }
            }
        }
        
//        System.out.println("미세먼지 확산 후 방의 상태");
//        for(int a=0; a<R; a++) {
//            System.out.println(Arrays.toString(copy[a]));
//        }
//        System.out.println();
        
    }
    
    
    // 미세먼지의 확산 가능한 방향의 개수를 세는 메서드
    // + 미세먼지 확산까지 처리!!
    private static int count(int x, int y, int amount) {
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };
        
        int count = 0;
        
        for(int i=0; i<4; i++) {
            int temp_x = x + dx[i];
            int temp_y = y + dy[i];
            
            // 방을 벗어나지 않고 공기청정기가 없으면 확산
            if(temp_x >= 0 && temp_x < R && temp_y >= 0 && temp_y < C && room[temp_x][temp_y] != -1) {
                copy[temp_x][temp_y] += amount; // 미세먼지 확산 발생
                count++; // 확산 가능한 방향의 수 증가
            }
        }
        
        return count;
    }
    
    
    // 공기청정기 작동으로 미세먼지가 순환하는 메서드
    // 주의 : 맨 뒤에서부터 당겨야함!!
    private static void airCleaner() {
    	
        // 공기청정기(윗칸) -> 반시계방향으로 순환
        for(int i=cleaner_x_top-1; i>0; i--) { // ↓
        	copy[i][0] = copy[i-1][0];
        }
        
        for(int i=0; i<C-1; i++) { // ←
        	copy[0][i] = copy[0][i+1];
        }
        
        for(int i=0; i<cleaner_x_top; i++) { // ↑
        	copy[i][C-1] = copy[i+1][C-1];
        }
        
        for(int i=C-1; i>=2; i--) { // →
        	copy[cleaner_x_top][i] = copy[cleaner_x_top][i-1];
        }
        copy[cleaner_x_top][1] = 0;
        
        
        // 공기청정기(아래칸) -> 시계방향으로 순환
        for(int i=cleaner_x_down+1; i<R-1; i++) { // ↑
            copy[i][0] = copy[i+1][0];
        }
        
        for(int i=0; i<C-1; i++) { // ←
            copy[R-1][i] = copy[R-1][i+1];
        }
        
        for(int i=R-1; i>cleaner_x_down; i--) { // ↓
            copy[i][C-1] = copy[i-1][C-1];
        }
        
        for(int i=C-1; i>1; i--) { // →
            copy[cleaner_x_down][i] = copy[cleaner_x_down][i-1];
        }
        copy[cleaner_x_down][1] = 0; // 순환하는 첫 번째 칸 0 처리
        
        
//        System.out.println("공기청정기 작동 후 방의 상태");
//        for(int a=0; a<R; a++) {
//            System.out.println(Arrays.toString(copy[a]));
//        }
//        System.out.println();
        
        // 다음 초에 다시 확산해주기 위해 원본 배열 상태 갱신
        for(int i=0; i<R; i++) {
        	room[i] = copy[i].clone();
        }
        
    }

}
