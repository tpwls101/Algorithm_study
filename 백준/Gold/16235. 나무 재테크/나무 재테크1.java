import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_16235_나무재테크>
 * 주의사항!!
 * list에서 죽은 나무를 제거할 때, 뒤에서부터 해야 한다.
 * 앞에서부터 제거하면 for문 돌리면서 i번째 인덱스의 값이 바뀌어 차례대로 제거할 수 없다.
 * 
 * 다른 풀이를 보니 Collections.sort를 매년 사용하면 시간초과 난다고 하는데 내 풀이는 괜찮았다.
 * 이유는..?
 * 생각해봤는데 다른 사람들의 풀이에서는 모든 나무를 리스트에 담아 정렬하지만,
 * 나는 한 칸의 나무만 정렬하기 때문에 괜찮은 것 같다.
 * Collections.sort()의 시간복잡도 : O(NlogN)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 땅의 크기
	static int M; // 처음 땅에 심은 나무의 개수
	static int K; // 년 수
	static int[][] A; // 추가될 양분의 양
	static int[][] nutrient; // 현재 땅의 양분 상태
	static ArrayList<Integer>[][] trees; // 현재 심어진 나무들의 상태를 저장하는 배열
	static ArrayList<Integer>[][] original; // 복사 배열
	static List<Integer> dead; // 봄에 죽은 나무들의 나이를 저장
	
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        A = new int[N][N];
        nutrient = new int[N][N];
        trees = new ArrayList[N][N];
        original = new ArrayList[N][N];
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		trees[i][j] = new ArrayList<>();
        		original[i][j] = new ArrayList<>();
        	}
        }
        
        // 처음에 양분은 모든 칸에 5만큼 들어있다
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		nutrient[i][j] = 5;
        	}
        }
		
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		A[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 상도가 심은 나무의 위치와 나이
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken()) - 1;
        	int y = Integer.parseInt(st.nextToken()) - 1;
        	int age = Integer.parseInt(st.nextToken());
        	
        	trees[x][y].add(age);
        }
        
        // K년 동안 나무 재테크 진행
        for(int year=0; year<K; year++) {
        	for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				if(trees[i][j].size() > 0) {
    					// 1. 봄 : 자신의 나이만큼 양분을 먹고 나이 1 증가, 양분을 먹을 수 없는 나무는 죽음
    					dead = new ArrayList<>();
    					plusAge(i, j, trees[i][j]);
    					
    					// 2. 여름 : 봄에 죽은 나무가 양분으로 변함
    					for(int age : dead) {
    						nutrient[i][j] += age / 2;
    					}
    				}
    			}
    		}
        	
        	// 3. 가을 : 나무 번식
        	for(int i=0; i<N; i++) {
        		original[i] = trees[i].clone(); // 배열 복사
        	}
        	
        	for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				if(original[i][j].size() > 0) {
    					spread(i, j, original[i][j]);
    				}
    			}
        	}

        	// 4. 겨울 : 땅에 양분 추가
        	for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				nutrient[i][j] += A[i][j];
    			}
        	}
        }
        
        // K년이 지난 후 땅에 살아있는 나무의 개수
        int answer = 0;
        for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(trees[i][j].size() > 0) {
					answer += trees[i][j].size();
				}
			}
    	}
        System.out.println(answer);
	}
	
	static void plusAge(int x, int y, ArrayList<Integer> treeList) {
		// 나이가 어린 나무부터 양분을 흡수하고 나이를 먹는다
		Collections.sort(treeList);
		for(int i=0; i<treeList.size(); i++) {
			int age = treeList.get(i);
			
			// 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽는다
			if(nutrient[x][y] - age < 0) {
				// 주의 : list에서 제거할 때 i번째 인덱스가 바뀌므로 뒤에서부터!!
				for(int j=treeList.size()-1; j>=i; j--) {
					dead.add(treeList.get(j));
					treeList.remove(j);
				}
				break;
			}
			nutrient[x][y] -= age;
			treeList.set(i, age+1);
		}
		
		trees[x][y] = treeList; // 살아남은 나무들만 다시 저장
	}
	
	static void spread(int x, int y, ArrayList<Integer> treeList) {
		for(int age : treeList) {
			if(age % 5 == 0) { // 나무 나이가 5의 배수인 것만 번식
				for(int i=0; i<8; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
						trees[nx][ny].add(1); // 나이가 1인 나무가 생김
					}
				}
			}
		}
	}

}
