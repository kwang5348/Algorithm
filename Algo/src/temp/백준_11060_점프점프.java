package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * bfs 로 가즈아
 */
public class 백준_11060_점프점프 {
	
	static int N;
	static int [] arr;
	static int ans;
	public static void bfs() {
		int [] visit = new int[N];
		for (int i = 0; i < visit.length; i++) {
			visit[i] = Integer.MAX_VALUE;
		}
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(0);
		visit[0] = 0;
		int time = 0;
		while(!Q.isEmpty()) {
			int size = Q.size();
			time++;
			while(size-- > 0) {
				int tp = Q.poll();
				int tvalue = arr[tp];
				for (int i = 1; i <= tvalue; i++) {
					int point = tp + i;
					if(point == N - 1) {
						ans = time;
						return;
					}
					if(visit[point] > time) {
						visit[point] = time;
						Q.offer(tp + i);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ans = -1;
		st = new StringTokenizer(br.readLine().trim(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if(N == 1) {
			ans = 0;
		} else {
			bfs();
		}
		
		System.out.println(ans);
	}
}
