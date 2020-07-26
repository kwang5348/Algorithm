package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 0 -> 013 -> 11
 * 1 -> 0124 -> 23
 * 2 -> 125 -> 38
 * 3 -> 0346 -> 89
 * 4 -> 13457 -> 186
 * 5 -> 2458 -> 116
 * 6 -> 367 -> 200
 * 7 -> 4678 -> 464
 * 8 -> 578 -> 416
 */
public class 백준_10472_십자뒤집기 {
	static int [] dp;
	static final int [] bitmap = {11, 23, 38, 89, 186, 308, 200, 464, 416};
	static final int [] binary = {1, 2, 4, 8, 16, 32, 64, 128, 256};
	static int ans;
	public static void setBitmap() {
		System.out.println(binary[0] + binary[1] + binary[3]);
		System.out.println(binary[0] + binary[1] + binary[2] + binary[4]);
		System.out.println(binary[1] + binary[2] + binary[5]);
		System.out.println(binary[0] + binary[3] + binary[4] + binary[6]);
		System.out.println(binary[1] + binary[3] + binary[4] + binary[5] + binary[7]);
		System.out.println(binary[2] + binary[4] + binary[5] + binary[8]);
		System.out.println(binary[3] + binary[6] + binary[7]);
		System.out.println(binary[4] + binary[6] + binary[7] + binary[8]);
		System.out.println(binary[5] + binary[7] + binary[8]);
	}
	
	
	public static void dfs(int start, int depth) {
		if(depth > ans) {
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			int next = start ^ bitmap[i];
			if(dp[next] <= depth) {
				continue;
			}
			dp[next] = depth;
			if(next == 0) {
				if(depth < ans) {
					ans = depth;
				}
				return;
			}
			dfs(next, depth+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < TC; tc++) {
			dp = new int[512];
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < dp.length; i++) {
				dp[i] = Integer.MAX_VALUE;
			}
			int start = 0;
			for (int i = 0; i < 3; i++) {
				String token = br.readLine();
				for (int j = 0; j < 3; j++) {
					if(token.charAt(j) == '*') {
						start += binary[i*3 + j];
					} 
				}
			}
			if(start == 0) {
				ans = 0;
			} else {
				dfs(start, 1);
			}
			System.out.println(ans);
		}
	}
}
