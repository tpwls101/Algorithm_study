import java.util.*;

/*
플로이드워셜 알고리즘을 활용하면 쉽게 풀 수 있다.
이 문제는 정확하게 순위를 매길 수 있는 선수의 수를 구하는 문제이다.
즉, 한명이 나머지 사람들과 경기한 결과가 명확한 경우의 수가 N-1개이면 순위가 확정된다.
예를 들어 2번 선수의 경우, (1,3,4)번 선수에게 지고 (5)번 선수에게 이기므로 4번의 경기 결과가 확실하다. 따라서 4등이라는 순위가 확정된다.
그러면 최대한 경기의 결과를 알기 위해 플로이드워셜을 사용한다.
A가 B를 이기고, B가 C를 이긴다면 A는 C를 이기는 것과 같다.
따라서 A->C를 구하기 위해 중간점(B)를 거쳐 이기는지 확인하면 된다.
그러면 최대한 경기 결과를 확보한 뒤, 각 선수별로 확정된 경기 결과의 수를 세면 순위가 확정된 선수의 수가 나온다.
*/

class Solution {
    public int solution(int n, int[][] results) {
        int[][] arr = new int[n+1][n+1];
        
        // 1. 경기 결과 저장하기
        for(int i=0; i<results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];
            arr[win][lose] = 1;
            arr[lose][win] = -1;
        }
        
        // 2. 플로이드워셜을 이용해 최대한의 경기 결과 확보하기
        for(int k=1; k<=n; k++) { // 중간점
            for(int i=1; i<=n; i++) { // 행
                for(int j=1; j<=n; j++) { // 열
                    // A가 B를 이기고, B가 C를 이기면
                    if(arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1; // A도 C를 이긴다
                        arr[j][i] = -1; // C는 A에게 진다
                    }
                }
            }
        }
        
        // 3. 순위가 확정된 선수의 수 구하기
        int answer = 0;
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) {
                if(arr[i][j] == 1 || arr[i][j] == -1) {
                    cnt++;
                }
            }
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}