package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2531_회전초밥 {
	
	static Queue <Integer> q;
	static int N;
	static int d;
	static int k;
	static int c;
	static int [] qcount;
	
	/**
	 *     1   4   6  10
	 *     
	 *     qcount [1] ++;
	 *     			4 ++;
	 *     			6 ++;
	 *     			10 ++;
	 *
	 *
	 */
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		q = new LinkedList<Integer>();
		qcount = new int[d + 1];
		ans = 0;
		
		int [] temp = new int[k];
		int kindOfchobab = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int input = Integer.parseInt(st.nextToken());
			if(qcount[input]++ == 0) {
				if(input != c) {
					kindOfchobab++;
				}
				if(ans < kindOfchobab) {
					ans = kindOfchobab;
				}
			}
			q.offer(input);
			temp[i] = input;
		}
		ans = kindOfchobab;
		for (int i = k; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int input = Integer.parseInt(st.nextToken());
			int output = q.poll();
			if(qcount[output]-- == 1) {
				if(output != c)
					kindOfchobab--;
			}
			if(qcount[input]++ == 0) {
				if(input != c) {
					kindOfchobab++;
				}
				if(ans < kindOfchobab) {
					ans = kindOfchobab;
				}
			}
			q.offer(input);

		}
		
		for (int i = 0; i < k; i++) {
			int input = temp[i];
			int output = q.poll();
			if(qcount[output]-- == 1) {
				if(output != c)
					kindOfchobab--;
			}
			if(qcount[input]++ == 0) {
				if(input != c) {
					kindOfchobab++;
				}
				if(ans < kindOfchobab) {
					ans = kindOfchobab;
				}
			}
		}
		System.out.println(ans+1);
	}
}
