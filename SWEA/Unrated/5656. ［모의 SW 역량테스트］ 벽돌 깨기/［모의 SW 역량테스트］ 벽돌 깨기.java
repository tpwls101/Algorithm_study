import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <SW_5656_벽돌깨기>
 * N개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하고 남은 벽돌의 개수를 구해라!
 * 
 * 1. W개 중에 어디에 구슬을 쏠건지? -> N개
 * 		( 순서 중요! 중복 가능! => 중복 순열 !!! )
 * 2. 벽돌 파괴 : 구슬을 맞은 칸의 숫자 - 1 범위만큼 dfs
 * 3. 열을 기준으로 0이 아닌 것 -> 스택에 push -> pop해서 밑에서부터 다시 쌓아주기
 * 
 * !!! DFS로 안됨 !!! -> BFS로 바꾸삼ㅠㅠ
 * !!! min : static 선언으로 초기화 -> 테케 돌릴 떄마다 초기화해줄 것 !!!
 * !!! 시간초과 -> 벽돌값 1인 경우 가지치기하고 bfs 매개변수 많이 넘기지 말자 !!!
 * 
 * @author YooSejin
 *
 */

public class Solution {

    static int N; // 구슬을 쏘는 횟수
    static int W; // 너비(열)
    static int H; // 높이(행)
    static int[][] arr;
    static int[] numbers; // 중복순열을 저장할 배열
    static int min; // 최소한으로 남은 벽돌의 개수

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 시간 확인
//          long start = System.currentTimeMillis();

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            arr = new int[H][W];
            numbers = new int[N]; // N개의 구슬을 쏘는 경우의 수 (중복순열)
            min = Integer.MAX_VALUE; // 테케 돌릴 때마다 초기화 해주기 !!!!!!!!!!!!!!!!!!!!
            
            // 벽돌상태 입력받기
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 1. W개 중에 어디에 구슬을 쏠건지? -> N개 (순서 중요! 중복 가능! => 중복 순열 !!!)
            perm(0);
            
            System.out.printf("#%d %d\n", tc, min);
            
//            long estimated = System.currentTimeMillis() - start;
//            System.out.println("running time: " + estimated);
        }

    }


    // cnt : 현재까지 뽑은 수의 개수
    private static void perm(int cnt) {
        if(cnt == N) {
            //System.out.println(Arrays.toString(numbers));

            // 2. 벽돌 파괴 시작
            int[][] copy = new int[H][W];
            for(int i=0; i<H; i++) {
            	copy[i] = arr[i].clone(); // 배열 복사해서 사용할 것임
            }
            startDestroy(copy, numbers);

            //System.exit(0);
            return;
        }

        for(int i=0; i<W; i++) { // 구슬을 쏠 위치(인덱스) : 0 ~ W-1
            numbers[cnt] = i;
            perm(cnt+1);
        }
    }


    // 벽돌 파괴 시작 메서드 (N개의 구슬 발사)
    private static void startDestroy(int[][] copy, int[] numbers) {
    	
//    	System.out.println(Arrays.toString(numbers));     
    	
    	// numbers[] 임의 변경 (2, 2, 6)
//    	numbers[0] = 2;
//    	numbers[1] = 2;
//    	numbers[2] = 6;
    	
    	for(int i=0; i<N; i++) { // N개의 구슬 발사
            int loc = numbers[i]; // 구슬 발사 위치
//            System.out.println((i+1)+"번 구슬 : "+loc+"번째 열에 구슬 발사");
            
            for(int j=0; j<H; j++) {
                if(copy[j][loc] != 0) { // 구슬을 발사하는 열에서 0이 아닌 벽돌을 만나면 파괴
        
//                	System.out.println(loc + "번째 열, " + j + "번째 행에 구슬 발사");
                
                	destroy(copy, j, loc); // 복사한 배열, 파괴하는 벽돌의 x좌표, y좌표와 벽돌에 써있는 숫자 전달
                    
                	break;
                }
            }
        }
    	
        // N개의 구슬 발사 후 남은 벽돌의 개수 확인하기
        int count = 0; // 남은 벽돌의 개수
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(copy[i][j] != 0) {
                    count++;
                }
            }
        }
//        System.out.println("count : "+count);
        min = Math.min(count, min); // 최대한 많은 벽돌을 깨트리고 남은 벽돌은 최소로 갱신
    }


    
    // 벽돌 파괴
    // x : 현재 벽돌의 x좌표
    // y : 현재 벽돌의 y좌표
    // num : 벽돌에 써있는 숫자   -> 매개변수로 넘겨주면 시간초과!!!!!!!! -> bfs 매개변수로 많이 넘겨주지 말자 !!!!!!!!!!!! 
    private static void destroy(int[][] copy, int x, int y) {
//      System.out.println("x:"+x+" y:"+y+" num:"+num);
    	
    	Queue<int[]> queue = new ArrayDeque<>();
    	queue.add(new int[] {x,y}); // 파괴되는 벽돌의 x,y좌표와 벽돌값 삽입
    	// 벽돌 파괴(0) -> 여기서 XXX
    	
    	while(!queue.isEmpty()) {
    		int[] current = queue.poll();
    		int now_x = current[0]; // 현재 벽돌의 x좌표
    		int now_y = current[1]; // 현재 벽돌의 y좌표
    		int range = copy[now_x][now_y] ; // 파괴 범위
    		copy[now_x][now_y] = 0;
    		
    		for(int i=1; i<range; i++) {
	    		for(int j=0; j<4; j++) {
	    			int next_x = now_x + dx[j] * i;
	    			int next_y = now_y + dy[j] * i;
	    			
	    			if(next_x >= 0 && next_x < H && next_y >= 0 && next_y < W) {
	    				// 벽돌값이 1일 때 큐에 넣어주면 시간초과남;; ㅠㅠ 제끼자!!!
	    				if(copy[next_x][next_y] == 1) {
	    					copy[next_x][next_y] = 0;
	    					continue;
	    				}
	    				if(copy[next_x][next_y] != 0) { // 범위 내에서 벽돌이 있으면 파괴
//	    					System.out.println("큐에 삽입할 값 : " + next_x+" "+next_y+" "+copy[next_x][next_y]);
	    					queue.add(new int[] {next_x, next_y});
	    				}
	    			}
	    		}
    		}
    	}
    	
        
        // 구슬 한 번 쏘고 난 후의 상태
//        System.out.println("구슬 한 번 쏜거 다 파괴하고 난 후의 상태");
//        for(int i=0; i<H; i++) {
//        	System.out.println(Arrays.toString(copy[i]));
//        }
//        System.out.println();
        
    	
    	
    	
        // 구슬 하나를 쐈을 때 모두 파괴하고 난 후 스택 처리
    	Stack<Integer> stack = new Stack<>();
        for(int a=0; a<W; a++) { // 열을 기준으로
        	for(int b=0; b<H; b++) {
            	if(copy[b][a] != 0) { // 빈칸이 아닌 벽돌이 있으면
            		stack.push(copy[b][a]); // 스택에 push
            		copy[b][a] = 0;
            	}
            }
        	
            int r = H-1; // 배열의 마지막 행 (여기서부터 위로 벽돌을 쌓아줄 것임)
            while(!stack.isEmpty()) { // 스택에 있는 벽돌을
            	copy[r--][a] = stack.pop(); // 다시 밑에서부터 쌓아주기
            }
        }
        
//        System.out.println("스택 처리 후 상태 (벽돌 바닥으로 밀었을 때 상태)");
//        for(int q=0; q<H; q++) {
//        	System.out.println(Arrays.toString(copy[q]));
//        }
//        System.out.println();
        
    }

}