import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_20327_배열돌리기6>
 * 못풀 문제는 아닌데 구현이 너무 복잡해서 헷갈리는 문제ㅜㅜ
 * 식을 만드는게 헷갈렸다.
 * for문 돌릴 때 for(int i=0; i<len/subLen; i++) 쓴 다음 식에 규칙을 쓰는 것보다
 * for(int i=0; i<len; i+=subLen)으로 써버리면 바로 좌표가 나와서 훨씬 편리하다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int R;
	static int len; // 배열의 가로/세로 길이
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        len = (int) Math.pow(2, N);
        
        arr = new int[len][len];
        
        for(int i=0; i<len; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<len; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // R개의 연산 수행
        for(int i=0; i<R; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken()); // 연산 번호
        	int level = Integer.parseInt(st.nextToken()); // 부분 배열 나누는 단계
        	
        	int subLen = (int) Math.pow(2, level); // 부분 배열의 길이
        	
        	switch(num) {
        	case 1:
        		sign1(subLen);
        		break;
        	case 2:
        		sign2(subLen);
        		break;
        	case 3:
        		sign3(subLen);
        		break;
        	case 4:
        		sign4(subLen);
        		break;
        	case 5:
        		sign5(subLen);
        		break;
        	case 6:
        		sign6(subLen);
        		break;
        	case 7:
        		sign7(subLen);
        		break;
        	case 8:
        		sign8(subLen);
        		break;
        	default:
        		break;
        	}
        }
		
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 부분 배열을 상하 반전
	static void sign1(int subLen) {
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen/2; a++) {
					for(int b=0; b<subLen; b++) {
						int tmp = arr[i+a][j+b];
						arr[i+a][j+b] = arr[i+subLen-1-a][j+b];
						arr[i+subLen-1-a][j+b] = tmp;
					}
				}
			}
		}
	}
	
	// 부분 배열을 좌우 반전
	static void sign2(int subLen) {
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen/2; a++) {
					for(int b=0; b<subLen; b++) {
						int tmp = arr[i+b][j+a];
						arr[i+b][j+a] = arr[i+b][j+subLen-1-a];
						arr[i+b][j+subLen-1-a] = tmp;
					}
				}
			}
		}
	}
	
	// 부분 배열을 오른쪽으로 90도 회전
	static void sign3(int subLen) {
		int[][] result = new int[len][len];
		
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						result[i+b][j+subLen-1-a] = arr[i+a][j+b];
					}
				}
			}
		}
		
		arr = result;
	}
	
	// 부분 배열을 왼쪽으로 90도 회전
	static void sign4(int subLen) {
		int[][] result = new int[len][len];
		
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						result[i+subLen-1-b][j+a] = arr[i+a][j+b];
					}
				}
			}
		}
		
		arr = result;
	}
	
	// 5~8번 연산은 부분 배열을 한 칸으로 생각하고 적용
	// 상하 반전
	static void sign5(int subLen) {
		for(int i=0; i<len/2; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						int tmp = arr[i+a][j+b];
						arr[i+a][j+b] = arr[len-(i+subLen)+a][j+b];
						arr[len-(i+subLen)+a][j+b] = tmp;
					}
				}
			}
		}
	}
	
	// 좌우 반전
	static void sign6(int subLen) {
		for(int i=0; i<len/2; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						int tmp = arr[j+a][i+b];
						arr[j+a][i+b] = arr[j+a][len-(i+subLen)+b];
						arr[j+a][len-(i+subLen)+b] = tmp;
					}
				}
			}
		}
	}
	
	// 오른쪽으로 90도 회전
	static void sign7(int subLen) {
		int[][] result = new int[len][len];
		
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						result[j+a][len-subLen-i+b] = arr[i+a][j+b];
					}
				}
			}
		}
		
		arr = result;
	}
	
	// 왼쪽으로 90도 회전
	static void sign8(int subLen) {
		int[][] result = new int[len][len];
		
		for(int i=0; i<len; i+=subLen) {
			for(int j=0; j<len; j+=subLen) {
				
				for(int a=0; a<subLen; a++) {
					for(int b=0; b<subLen; b++) {
						result[len-subLen-j+a][i+b] = arr[i+a][j+b];
					}
				}
			}
		}
		
		arr = result;
	}

}
