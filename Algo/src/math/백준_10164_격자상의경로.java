package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_10164_격자상의경로 {
	
	public static long combi(long a, long b) {
		long result = 1;
		long count = a;
		for (long i = b+1; i <= a+b; i++) {
			result *= i;
//			if(result % count == 0) {
//				result /= count;
//				count--;
//			}
			
		}
		for (long i = 2; i <= a; i++) {
			result /= i;
			//System.out.println(result);
		}
		
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int N = 0;
		int M = 0;
		int target = 0;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken()) - 1;
		if(target == -1) {
			long result = combi(N-1, M-1);
			System.out.println(result);
			return;
		}
		int sy = (target)/M; //맞음
		int sx = target%M; // 맞음
		int fy = N-1 - sy;
		int fx = M -1 - sx;
		
//		System.out.println(sy);
//		System.out.println(sx);
//		System.out.println(fy);
//		System.out.println(fx);
		long result = combi(sy, sx) * combi(fy, fx);
		System.out.println(result);
		
	}
}
