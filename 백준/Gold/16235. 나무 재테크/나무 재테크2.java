import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
 * Queue나 Deque를 사용해서 풀 수 있다.
 * Deque -> 추가/삭제가 많을 땐 LinkedList, 검색이 많을 땐 ArrayList를 사용할 것!
 * 큐 삽입/삭제가 빈번하니 size() 확인할 때 주의! -> len을 미리 정의해서 사용해야 한다(계속 변하기 때문)
 * 
 * @author YooSejin
 *
 */

public class BJ_16235_나무재테크2 {
	
	static int N; // 땅의 크기
	static int M; // 처음 땅에 심은 나무의 개수
	static int K; // 년 수
	static int[][] A; // 추가될 양분의 양
	static int[][] nutrient; // 현재 땅의 양분 상태
	static Deque<Tree> tree; // 현재 심어진 나무들
	static Queue<Tree> dead; // 봄에 죽은 나무들(죽은 나무를 tree 리스트에서 제거하면서 dead 리스트에서도 한번에 제거하기 위해 큐 사용)
	
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;
		
		Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        A = new int[N][N];
        nutrient = new int[N][N];
        tree = new LinkedList<>();
        dead = new ArrayDeque<>();
        
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
        	
        	tree.add(new Tree(x, y, age));
        }
        
        // 모든 나무를 나이순으로 정렬 (초반 한번만 정렬해주면 된다!)
        Collections.sort((List<Tree>)tree); // deque를 list로 바꿔서 정렬
        
        // K년 동안 나무 재테크 진행
        for(int year=0; year<K; year++) {
        	dead = new ArrayDeque<>();
        	
        	// 1. 봄 : 자신의 나이만큼 양분을 먹고 나이 1 증가, 양분을 먹을 수 없는 나무는 죽음
        	int len = tree.size();
        	for(int i=0; i<len; i++) {
        		Tree t = tree.poll();
        		
        		if(nutrient[t.x][t.y] - t.age < 0) {
        			dead.add(t);
        			continue;
        		}
        		
        		nutrient[t.x][t.y] -= t.age;
        		t.age++;
        		tree.add(t);
        	}
        	
        	// 2. 여름 : 봄에 죽은 나무가 양분으로 변함
        	for(Tree t : dead) {
        		nutrient[t.x][t.y] += t.age / 2;
        	}
        	
        	// 3. 가을 : 나무 번식
        	List<Tree> list = new ArrayList<>(); // 원래 tree에 저장된 나무를 잠시 저장해두었다 새로운 나무가 생긴 뒤 추가
        	
        	len = tree.size();
        	for(int i=0; i<len; i++) {
        		Tree t = tree.poll();
        		list.add(t);
        		
        		if(t.age % 5 == 0) { // 나무 나이가 5의 배수인 것만 번식
        			for(int d=0; d<8; d++) {
            			int nx = t.x + dx[d];
            			int ny = t.y + dy[d];
            			
            			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
            				tree.add(new Tree(nx, ny, 1)); // 나이가 1인 나무가 생김
    					}
            		}
        		}
        	}
        	
        	// 나이가 1인 나무를 모두 tree 큐에 추가한 뒤, 기존의 tree를 추가 (자연스레 나이순으로 정렬됨)
        	for(Tree t : list) {
        		tree.add(t);
        	}
        	
        	// 4. 겨울 : 땅에 양분 추가
        	for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				nutrient[i][j] += A[i][j];
    			}
        	}
        }
        
        // K년이 지난 후 땅에 살아있는 나무의 개수
        System.out.println(tree.size());
	}
	
}
