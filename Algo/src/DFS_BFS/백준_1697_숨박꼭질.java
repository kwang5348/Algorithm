package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * @author user
 * BFS 로 풀어야 한다 판단됨
 * -> DFS 로 했을때 경로 수가 너무 많이 나옴
 * 이동한 위치마다 몇초만에 왔는지 갱신시켜주면 시간 최적화를 노릴수 있을것 같다
 * 
 * 수빈이가 할 수 있는 행동 3가지
 * 3가지 행동에 대해 결과를 큐에 넣는 BFS 진행하고 time을 체크하면서 최초에 찾으면 함수 종료
 * 
 * 
 */
public class 백준_1697_숨박꼭질 {
	static int N;
	static int K;
	
	
	public static int bfs() {
		int time = 0;
		Queue<Integer> Q = new LinkedList<Integer>();
		int [] visit = new int[100001];
		Arrays.fill(visit, Integer.MAX_VALUE);
		Q.offer(N);
		while(!Q.isEmpty()) {
			int size = Q.size();
			while(size -- > 0) {
				int temp = Q.poll();
				if(temp == K) {
					return time;
				}
				// 수빈이가 왼쪽으로 움직일때
				if(temp-1 >= 0) {
					if(time < visit[temp-1]) {
						visit[temp-1] = time;
						Q.offer(temp-1);
					}
				}
				// 수빈이가 오른쪽으로 움직일때
				if(temp+1 <= 100000) {
					if(time < visit[temp+1]) {
						visit[temp+1] = time;
						Q.offer(temp+1);
					}
				}
				// 수빈이가 순간이동 할때
				if(2*temp <= 100000) {
					if(time < visit[2*temp]) {
						visit[2*temp] = time;
						Q.offer(2*temp);
					}
				}
			} // end of second while
			time++;
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(bfs());
		
			
	}
}
