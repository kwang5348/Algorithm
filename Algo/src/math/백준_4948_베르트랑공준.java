package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_4948_베르트랑공준 {
	
	static int N;
	static boolean [] visit;
	public static void checkPrimary (int input){
		
		for (int i = 2; i * i <= input; i++) {
			if(visit[i])
				continue;
			for (int j = i+1; j <= input; j++) {
				if(j % i == 0) {
					visit[j] = true;
				}
			}
		}
	}
	public static int count(int input) {
		int result = 0;
		for (int i = 2; i <= input; i++) {
			if(!visit[i]) result++;
 		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			if(N == 0) {
				flag = false;
				break;
			}
			visit = new boolean[2*N + 1];
			checkPrimary(2*N);
			System.out.println(count(2*N) - count(N));
		}
	}
}
