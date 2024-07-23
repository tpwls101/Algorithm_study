import java.io.*;
import java.util.*;

/**
 * <ST_성적평가_방법2>
 * 정렬을 이용한 풀이!
 * 
 * 배열에 값을 받은 뒤 점수로 내림차순 정렬
 * rank=1, cnt=1
 * for문 돌려서 이전 점수와 같으면 result[i][j]에 rank 값 그대로 저장하고 cnt++
 * 이전 점수와 같지 않으면 rank+=cnt;(현재 랭크에 cnt만큼 더해야 같은 등수 고려하여 rank 계산) result[i][j]=rank; cnt=1(초기화)
 * 
 * ### 내가 계속 놓친 부분 ###
 * 내림차순 정렬해서 result 배열에 등수를 차례대로 저장하는게 아니라
 * result 배열에 등수의 자리배치를 어떻게 할 것인가???
 * -> idx를 사용!
 * 
 * idx의 역할
 * 학생의 인덱스를 나타내기도 하지만
 * 원래 있던 배열에서의 위치를 나타낸다!!!
 * 
 * @author YooSejin
 *
 */

public class Main  {
	
	static int N; // 참가자의 수
	static Student arr[][]; // 학생의 인덱스와 점수 정보를 저장하여 내림차순 정렬할 배열
	static int result[][]; // 등수를 저장할 배열
	
	static class Student {
		int idx; // 학생의 인덱스
		int score; // 점수
		
		Student(int idx, int score) {
			this.idx = idx;
			this.score = score;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        arr = new Student[4][N];
        result = new int[4][N];
        
        // 입력값 받기
        for(int contest=0; contest<3; contest++) {
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<N; i++) {
        		int score = Integer.parseInt(st.nextToken());
        		arr[contest][i] = new Student(i, score);
        	}
        }
        
        // 점수의 합 배열에 저장
        for(int i=0; i<N; i++) {
        	int sum = 0;
        	for(int j=0; j<3; j++) {
        		sum += arr[j][i].score;
        	}
        	arr[3][i] = new Student(i, sum);
        }
        
        // 점수로 내림차순 정렬 
        for(Student[] s : arr) {
        	Arrays.sort(s, (o1, o2) -> o2.score - o1.score);
        }
        
        // result 배열에 등수 저장
        for(int i=0; i<4; i++) {
        	int rank = 1;
            int cnt = 1; // 같은 점수일 때 다음 등수를 계산하기 위해 사용
            
            // 처음은 무조건 1등
            int loc = arr[i][0].idx; // 내가 놓친 중요 포인트!! arr[][].idx가 등수를 저장할 위치가 된다!!
            result[i][loc] = 1;
        	
            for(int j=1; j<N; j++) {
        		loc = arr[i][j].idx;
        		// 이전과 점수가 같다면 현재 랭크를 저장
            	if(arr[i][j].score == arr[i][j-1].score) {
            		result[i][loc] = rank;
            		cnt++;
            	} 
            	// 이전과 점수가 다르다면 랭크에 cnt를 더한 값을 랭크로 저장
            	else {
            		rank += cnt;
            		result[i][loc] = rank;
            		cnt = 1; // cnt 1로 초기화
            	}
        	}
        }
        
        // 등수가 저장된 result 배열 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++) {
        	for(int j=0; j<N; j++) {
        		sb.append(result[i][j] + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
	}

}
