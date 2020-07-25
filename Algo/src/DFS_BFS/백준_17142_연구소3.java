package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Birus {
	int y;
	int x;
	public Birus(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
}

public class 백준_17142_연구소3 {
	static int N;
	static int M;
	static int [][] map;
	static int ans;
	static int remainBirus;
	static Birus [] birus;
	static int [] dy = {0, 0, 1, -1};
	static int [] dx = {1, -1, 0, 0};
	static boolean [] active; 
	static int check = 0;
	public static void dfs(int depth, int point) {
		if(depth >= M) {
			int result = bfs();

			if(result == -1) {
				return;
			}
			if(ans > result) {
				ans = result;
			}
			return;
		}
		
		for (int i = point+1; i < active.length; i++) {
			active[i] = true;
			dfs(depth+1, i);
			active[i] = false;
		}
	}
	
	public static int bfs() {
		int time = 0;
		int remain = remainBirus;
		boolean [][] visit = new boolean[N][N];
		Queue<Birus> q = new LinkedList<Birus>(); 
		for (int i = 0; i < active.length; i++) {
			if(active[i]) {
				q.offer(birus[i]);
				visit[birus[i].y][birus[i].x] = true;
			}
		}
		if(remain == 0) {
			ans = 0;
			return 0;
		}
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			while(size-- > 0) {
				Birus temp = q.poll();
				//System.out.println("(y, x) => " + temp.y + " , " + temp.x);
				for (int i = 0; i < 4; i++) {
					int py = temp.y + dy[i];
					int px = temp.x + dx[i];
					
					if(py >= 0 && py < N && px >= 0 && px < N) {
						if(map[py][px] != 1 && !visit[py][px]) {
							if(map[py][px] == 0)
								remain--;
							if(remain == 0) {
								return time;
							}
							q.offer(new Birus(py, px));
							visit[py][px] = true;
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		remainBirus = 0;
		
		birus = new Birus[10];
		map = new int[N][N];
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					remainBirus++;
				} else if (map[i][j] == 2) {
					birus[count++] = new Birus(i, j);
				}
				
			}
		}
		active = new boolean[count];
		dfs(0, -1);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
	
}
