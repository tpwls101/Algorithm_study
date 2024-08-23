import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1713_후보추천하기>
 * 큐를 이용하는 것도, HashMap 사용도 안된다 ;;
 * 걍 빡구현 문제였음
 * 
 * 이미 게시되어 있는지 여부를 판단할 때
 * if(student[num].isPosted) 로 판단한다면 nullPointerException이 발생할 수 있다.
 * if(student[num] != null) 로 판단한다면 예를 들어 이미 2번 학생이 사진틀에 올라갔다가 내려갔는데 null이 아니기 때문에 이미 게시되어 있는 것으로 판단할 수 있다.
 * 따라서, 추천받은 학생은 무조건 게시틀에 올려야하므로 판단하기 전에 일단 Student 객체를 생성하여 처리한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 사진틀의 개수
	static int M; // 전체 학생의 총 추천 횟수
	static Student[] student = new Student[101]; // 100명의 학생 정보를 저장할 배열
	
	static class Student {
		int num; // 학생번호
		int recommended; // 추천받은 횟수
		boolean isPosted; // 사진틀에 게시되었는지 여부
		int order; // 게시된 순서
		
		Student(int num, int recommended, boolean isPosted, int order) {
			this.num = num;
			this.recommended = recommended;
			this.isPosted = isPosted;
			this.order = order;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		List<Student> list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num; // 추천받은 학생 번호
		for(int i=0; i<M; i++) {
			num = Integer.parseInt(st.nextToken());
			
			// 처음 추천 입력받은 학생이면 Student 객체 생성
			if(student[num] == null) {
				student[num] = new Student(num, 0, false, 0);
			}
			
			// 1. 이미 게시되어 있는지 확인
			// 이미 게시되어 있으면 추천 수만 증가
			if(student[num].isPosted) {
				student[num].recommended++;
			}
			
			// 게시되어 있지 않으면
			else {
				// 2. 사진틀이 꽉 차있는지 확인
				// 사진틀이 꽉 차있으면 추천 수가 가장 적은 학생, 같다면 가장 먼저 게시된 한명을 내리기
				// 그리고 새로운 학생을 사진틀에 게시
				if(list.size() == N) {
					// list를 추천 횟수 기준으로 오름차순, 게시된 순서 기준으로 오름차순 정렬
					Collections.sort(list, new Comparator<Student>() {
						public int compare(Student o1, Student o2) {
							if(o1.recommended == o2.recommended) {
								return o1.order - o2.order;
							}
							return o1.recommended - o2.recommended;
						}
					});
					list.get(0).isPosted = false;
					list.get(0).recommended = 0;
					list.remove(0);
				}
				
				student[num].recommended++;
				student[num].isPosted = true;
				student[num].order = i;
				list.add(student[num]);
			}
		}
		
		// 학생 번호를 기준으로 오름차순 정렬
		Collections.sort(list, (o1, o2) -> o1.num - o2.num);
		for(Student s : list) {
			System.out.print(s.num + " ");
		}
		
	}

}