package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_2212_센서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int ans = 0;
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim(), " ");
		int K = Integer.parseInt(st.nextToken());
		int []arr = new int [N];
		st = new StringTokenizer(br.readLine().trim(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		if(N == 1 || K >= N) {
			System.out.println(ans);
			return;
		}
		
		ans = arr[N-1] - arr[0];
		
		int [] brr = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			brr[i] = arr[i+1] - arr[i];
		}
		Arrays.sort(brr);
		
		for (int i = 0; i < K - 1; i++) {
			ans -= brr[N-2-i];
		}
		System.out.println(ans);
	}
}
