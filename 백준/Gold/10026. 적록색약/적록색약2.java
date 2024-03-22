import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <BJ_10026_적록색약>
 * 적록색약일 때나 아닐 때나 같은 영역을 탐색하는 bfs 함수는 똑같다.
 * 다만, 적록색약일 때는 R과 G를 같은 문자로 처리하고 탐색하면 된다.
 *
 * 주의사항 : 같은 visited 배열을 사용하므로 1차 탐색이 끝나면 반드시 초기화 해줄 것!!
 */

public class BJ_10026_적록색약 {

    static int N;
    static String[][] arr;
    static boolean[][] visited;
    static int count1 = 0; // 적록색약이 아닌 사람이 봤을 때 구역 수
    static int count2 = 0; // 적록색약인 사람이 봤을 때 구역 수

    static int[] dx = { 0, 1, 0, -1 }; // 행
    static int[] dy = { 1, 0, -1, 0 }; // 열

    static class Node {
        int x;
        int y;
        String color;
        public Node(int x, int y, String color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new String[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] s = br.readLine().split("");
            for(int j=0; j<N; j++) {
                arr[i][j] = s[j];
            }
        }

//        for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 1. 적록색약이 아닌 사람이 봤을 때 구역의 개수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    bfs(new Node(i, j, arr[i][j]));
                    count1++;
                }
            }
        }

        // 2. 적록색약인 사람이 봤을 때 구역의 개수 구하기
        // R을 모두 G로 바꾸기 (두 색상은 구분이 안되니 같은걸로 봐도 무방하다.)
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j].equals("R")) {
                    arr[i][j] = "G";
                }
            }
        }
        
        // 방문배열 초기화
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    bfs(new Node(i, j, arr[i][j]));
                    count2++;
                }
            }
        }

        System.out.print(count1 + " " + count2);
    }

    public static void bfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node.x][node.y] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            for(int i=0; i<4; i++) {
                int next_x = current.x + dx[i];
                int next_y = current.y + dy[i];

                if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < N) {
                    // 다음 칸을 아직 방문안했고 색상이 같다면 하나의 영역으로 인지
                    if(!visited[next_x][next_y] && arr[next_x][next_y].equals(current.color)) {
                        queue.add(new Node(next_x, next_y, arr[next_x][next_y]));
                        visited[next_x][next_y] = true;
                    }
                }
            }
        }
    }
}
