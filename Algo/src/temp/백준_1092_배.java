package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_1092_배 {
	
	static int N;
	static int [] krain;
	static int M;
	
	static int [] sits;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		sits = new int[N];
		krain = new int[N];
		st = new StringTokenizer(br.readLine().trim(), " ");
		int total = 0;
		for (int i = 0; i < N; i++) {
			krain[i] = Integer.parseInt(st.nextToken());
			total += krain[i];
		}
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim(), " ");
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			boolean flag = false;
			for (int j = 0; j < N; j++) {
				if(temp <= krain[j]) {
					sits[j]++;
					flag = true;
					break;
				}
			}
			if(!flag) {
				// 입력부터 크레인 용량을 벗어난 박스가 들어온경우 
				System.out.println("-1");
				return;
			}
		}
		/* 디버그
		for (int i = 0; i < N; i++) {
			System.out.println(i + " 번째 " + sits[i]);
		}
		*/
		int time = 0;
		int top = N-1;
		while(total != 0) {
			time++;
			total = 0;
			
			for(int i = top; i >= 0; i--) {
				sits[i] -= 1;
			}
			
			sits[top] -= (N-1 - top);
			for(int i = top; i >= 1; i--) {
				if(sits[i] < 0 ) {
					sits[i-1] += sits[i];
					if(i == top) {
						top--;
					}
				} else if(sits[i] > 0 ){
					total += sits[i];
				}
			}
			
			if(sits[0] > 0) {
				total += sits[0];
			}
			
		}
		System.out.println(time);
		
	}
}
