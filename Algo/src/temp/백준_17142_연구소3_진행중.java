package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class 백준_17142_연구소3_진행중 {
	static int N;
	static int M;
	static int [][] map;
	static int ans;
	static int remain;
	static Queue<Birus> birus = new LinkedList<Birus>();
	static int [] dy = {0, 0, 1, -1};
	static int [] dx = {1, -1, 0, 0};
	public void bfs() {
		int time = 0;
		if(remain == 0) {
			ans = 0;
			return;
		}
		while(!birus.isEmpty()) {
			int size = birus.size();
			time++;
			while(size-- > 0) {
				Birus temp = birus.poll();
				for (int i = 0; i < 4; i++) {
					int py = temp.y + dy[i];
					int px = temp.x + dx[i];
					
					if(py >= 0 && py < N && px >= 0 && py < N) {
						if(map[py][px] == 0) {
							
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = -1;
		remain = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					remain++;
				} else if (map[i][j] == 2) {
					birus.offer(new Birus(i, j));
				}
				
			}
		}
		bfs();
		System.out.println(ans);
	}
	
}
