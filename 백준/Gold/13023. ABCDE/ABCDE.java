import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_13023_ABCDE>
 * 
 * 
 * @author 유세진
 *
 */

public class Main {
    
    static Node[] adjList; // 정점(N명의 친구) 개수만큼 인접리스트 생성
    static boolean[] visited; // 방문체크 배열
    
    static class Node {
        int vertex; // 정점
        Node next; // 연결리스트에서 다음 노드의 주소 참조
        
        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 사람의 수 (5~2000)
        int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수 (1~2000)
        
        adjList = new Node[N]; // 정점(N명의 친구) 개수만큼 인접리스트 생성
        visited = new boolean[N]; // 방문체크 배열 초기화
        
        // 친구 관계 입력받아 인접리스트 생성
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); // 시작 정점
            int to = Integer.parseInt(st.nextToken()); // 도착 정점
            
            adjList[from] = new Node(to, adjList[from]); // 0 1 입력 받을 때 0번 노드에 1번 노드를 연결
            adjList[to] = new Node(from, adjList[to]); // 1번 노드에도 0번 노드를 연결해줘야 함
        }
        
        //boolean flag = true;
        for(int i=0; i<N; i++) {
        	dfs(i, 1); // 시작정점과 방문한 친구 정점의 개수 전달 -> Node가 아니라 정점번호를 전달할 것!!!!!!!! (Node를 보내면 주소값을 가지고 있어 인접정점을 나타냄)
        	visited[i] = false;
        }
        
        System.out.println("0");
        
//        if(flag) { // 친구관계 조건을 만족하면 flag는 true -> 1출력
//        	System.out.println("1");
//        } else { // 친구관계 조건을 만족하지 못하면 flag는 false -> 0출력
//        	System.out.println("0");
//        }
        
    }
    
    // current : 현재 정점
    // count : 방문한 친구 정점의 개수
    private static void dfs(int current, int count) {
    	// 방문한 친구 정점이 다섯개이면
        if(count == 5) {
            // 더 이상 탐색하지 않아도 이미 조건과 같은 친구 관계가 성립되고 true 반환
        	System.out.println("1");
        	System.exit(0);
        	//return true;
        }
        
        visited[current] = true; // 현재 정점 방문 처리
        
        // 현재 정점에 인접한 정점들 탐색
        for(Node temp=adjList[current]; temp!=null; temp=temp.next) {
            // 인접한 정점들 중 아직 방문하지 않았으면
            if(!visited[temp.vertex]) {
                // 정점 방문
                dfs(temp.vertex, count+1);
                visited[temp.vertex] = false;
            }
        }
        
        
        
        //return false; // 모든 친구 정점들을 다 탐색했는데 조건을 만족하지 못하면 false 반환
        
    }

}