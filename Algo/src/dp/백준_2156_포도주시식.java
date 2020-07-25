package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 앞에서 마신 음료가 연속인가 연속이 아닌가
 * 
 * 연속이면 다음 음료 못마심
 * 연속이 아니면 다음 음료 마심
 * 
 */
public class 백준_2156_포도주시식 {

	static int[] arr;
	static int[][] dp;
	static int N;
	static int ans;

	public static int findMax(int a, int b, int c) {
		int result = Math.max(a, b);
		return Math.max(c, result);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (N == 1) {
			ans = arr[0];
		} else if (N ==2) {
			ans = arr[0] + arr[1];
		} else {
			dp[0][0] = 0;
			dp[0][1] = arr[0];
			dp[0][2] = arr[0];
			
			dp[1][0] = arr[0];
			dp[1][1] = arr[1];
			dp[1][2] = arr[0] + arr[1];
			for (int i = 2; i < N; i++) {
				dp[i][0] = findMax(dp[i-1][0], dp[i-1][1], dp[i-1][2]);
				dp[i][1] = Math.max(dp[i-2][0] + arr[i],dp[i-1][0] + arr[i]);
				dp[i][2] = dp[i-1][1] + arr[i];
				
			}
			ans = findMax(dp[N-1][0], dp[N-1][1], dp[N-1][2]);
		}
		
		System.out.println(ans);
	}

}
