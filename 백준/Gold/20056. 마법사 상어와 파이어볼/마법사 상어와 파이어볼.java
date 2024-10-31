import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_20056_마법사상어와파이어볼>
 * 1. 모든 파이어볼 이동
 * 2. 2개 이상의 파이어볼이 한 칸에 있다면 파이어볼 분열
 * 
 * 주의 : 문제 잘못 해석 ㅜㅜ
 * 파이어볼이 나눠지고 상하좌우로 이동까지 하는게 아니라 그냥 arr[i][j] 칸에서 네개로 나누어지는 것이다.
 * 
 * 힌트
 * 한 칸에 두 개 이상의 파이어볼이 있을 때에만 분열
 * 분열될 파이어볼만 리스트에서 제거하고 (분열되지 않고 한칸에 하나만 있는 파이어볼은 리스트에 그대로 남아있음)
 * 분열시킨 후 다시 리스트에 저장
 * 
 * 놓친 부분
 * spread() 메서드 내 arr[i][j].clear(); 의 위치
 * 분열된 파이어볼의 질량이 0인 경우 continue 되므로 그 전에 초기화를 시켜야 한다!!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // NxN 격자
	static int M; // 파이어볼의 개수
	static int K; // 명령 횟수
	static List<Fireball>[][] arr; // 한 칸에 여러 파이어볼 저장하기 위해
	static List<Fireball> list; // 파이어볼의 정보를 저장
	static int answer = 0; // 남아있는 파이어볼 질량의 합
	
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	
	static class Fireball {
		int x;
		int y;
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		Fireball(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        arr = new ArrayList[N][N];
        list = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		arr[i][j] = new ArrayList<>();
        	}
        }
        
        // 리스트에 파이어볼의 정보 저장
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	
        	list.add(new Fireball(x-1, y-1, m, s, d));
        }
		
        for(int i=0; i<K; i++) {
        	move(); // 1. 모든 파이어볼 이동
        	spread(); // 2. 한 칸에 파이어볼이 여러 개인 경우 분열
        }
        
        for(Fireball fireball : list) {
        	answer += fireball.m;
        }
        
        System.out.println(answer);
	}
	
	static void move() {
		for(Fireball fireball : list) {
			int nx = (fireball.x + dx[fireball.d] * (fireball.s % N) + N) % N;
			int ny = (fireball.y + dy[fireball.d] * (fireball.s % N) + N) % N;
			
			// list에 들어있는 fireball 안의 x,y 좌표 값을 안바꿔줬음..! 주의!!
			// 이동한 후의 좌표로 갱신한 후, arr[][]에 저장해야 분열할 때 이동 후의 좌표값으로 다시 list에 저장함
			fireball.x = nx;
			fireball.y = ny;
			arr[nx][ny].add(fireball);
		}
	}
	
	static void spread() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 파이어볼이 하나만 있으면 분열x, arr[][] 초기화
				if(arr[i][j].size() < 2) {
					arr[i][j].clear();
					continue;
				}
				
				// 2개 이상의 파이어볼이 있는 칸에서만 분열
				if(arr[i][j].size() >= 2) {
					int m_sum = 0; // 질량의 합
					int s_sum = 0; // 속력의 합
					int even = 0; // 짝수인 방향의 개수
					int odd = 0; // 홀수인 방향의 개수
					int size = arr[i][j].size();
					
					for(Fireball fireball : arr[i][j]) {
						m_sum += fireball.m;
						s_sum += fireball.s;
						if(fireball.d % 2 == 0) even++;
						else odd++;
						
						list.remove(fireball); // 분열하는 파이어볼은 리스트에서 제거하고 분열된 파이어볼을 다시 추가해야 함
					}
					
					arr[i][j].clear(); // 다음을 위해 초기화 (주의사항 : 분열된 파이어볼의 질량이 0인 경우 continue 되므로 그 전에 초기화를 시켜야 한다!!!)
					
					int nm = m_sum / 5;
					if(nm == 0) continue; // 질량이 0인 파이어볼은 소멸 (따라서 분열된 파이어볼의 질량이 0이면 만들 필요가 없음)
					int ns = s_sum / size;
					
					// 방향이 모두 짝수거나 홀수면 -> 방향은 0 2 4 6
					if(even == 0 || odd == 0) {
						for(int k=0; k<4; k++) {
							list.add(new Fireball(i, j, nm, ns, k*2));
						}
					}
					// 그게 아니라면 -> 방향은 1 3 5 7
					else {
						for(int k=0; k<4; k++) {
							list.add(new Fireball(i, j, nm, ns, k*2+1));
						}
					}
				}
			}
		}
	}

}
