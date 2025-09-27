import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 크레인 개수
        
        Integer[] limit = new Integer[N]; // 각 크레인의 무게 제한
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	limit[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine()); // 박스의 수
        
        List<Integer> box = new ArrayList<>(); // 각 박스의 무게
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
        	box.add(Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(limit, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        
        // 모든 박스를 배로 옮길 수 없으면 -1 출력
        if(box.get(0) > limit[0]) {
        	System.out.println(-1);
        	return;
        }
        
        int time = 0;
        
        // 가장 무거운 박스도 무게 제한을 넘지 않으므로 모든 박스를 다 이동시킬 수는 있음
        while(!box.isEmpty()) {
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<box.size(); j++) {
        			if(box.get(j) <= limit[i]) { // 크레인에 박스를 실을 수 있는 경우
        				box.remove(j);
        				break;
        			} else { // 박스가 더 무거워 크레인에 실을 수 없으면 다음 타임에 확인
        				continue;
        			}
        		}
        	}
        	
        	time++;
        }
        
        System.out.println(time);
	}

}
