package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point3197 {
	int y;
	int x;
	public Point3197(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
}

public class 백준_3197_백조의호수 {
	
	static int N;
	static int M;
	
	static Queue <Point3197> facedIce;
	static Queue <Point3197> saveBakjo;
	static char [][] map;
	static Point3197[] entryPoint;
	static boolean [][] visitIce;
	static boolean [][] bfsVisit;

	static int [] dy = {0, 1, 0, -1};
	static int [] dx = {1, 0, -1, 0};
	
	public static boolean bfs() {
		boolean result = false;
		Queue <Point3197> q = new LinkedList<Point3197>();
		while(!saveBakjo.isEmpty()) {
			Point3197 temp = saveBakjo.poll();
			q.offer(temp);
			bfsVisit[temp.y][temp.x]= true; 
		}
		while(!q.isEmpty()) {
			Point3197 temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int py = temp.y + dy[i];
				int px = temp.x + dx[i];
				
				if(py >= 0 && py < N && px >= 0 && px < M) {
					if(map[py][px] == 'L' && !bfsVisit[py][px]) {
						return true;
					}
					
					if(map[py][px] == '.' && !bfsVisit[py][px]) {
						q.offer(new Point3197(py, px));
						bfsVisit[py][px] = true;
					}
					
					if(map[py][px] == 'X' && !bfsVisit[py][px]) {
						saveBakjo.offer(new Point3197(py, px));
						bfsVisit[py][px] = true;
					}
				}
			}
		}
		
		return result;
	}
	
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void melting() {
		int size = facedIce.size();
		
		for (int i = 0; i < size; i++) {
			Point3197 temp = facedIce.poll();
//			System.out.println(temp.y + " " + temp.x);
			map[temp.y][temp.x]= '.';
			facedIce.offer(temp);
		}
	}
	
	public static void nextMeltingPoint() {
		int size = facedIce.size();
		for (int i = 0; i < size; i++) {
			Point3197 temp = facedIce.poll(); 
			for (int j = 0; j < 4; j++) {
				int ty = temp.y + dy[j];
				int tx = temp.x + dx[j];
				if(ty >= 0 && ty < N && tx >= 0 && tx < M) {
					if(visitIce[ty][tx])
						continue;
					
					if(map[ty][tx] == 'X') {
						visitIce[ty][tx] = true;
						facedIce.offer(new Point3197(ty, tx));
						
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
		
		map = new char [N][M];
		visitIce = new boolean[N][M];
		entryPoint = new Point3197[2];
		facedIce = new LinkedList<Point3197>();
		saveBakjo = new LinkedList<Point3197>();
		bfsVisit = new boolean[N][M];

		int entryIndex = 0;
		for (int i = 0; i < N; i++) {
			String tempStr = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tempStr.charAt(j);
				if(map[i][j] == 'L')
					entryPoint[entryIndex++] = new Point3197(i, j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 'X') {
					continue;
				}
				for (int k = 0; k < 4; k++) {					
					int py = i + dy[k];
					int px = j + dx[k];
					
					if(py >= 0 && py < N && px >= 0 && px < M) {
						if(map[py][px] != 'X' && !visitIce[i][j]) {
							visitIce[i][j] = true;
							facedIce.add(new Point3197(i, j));
							
						}
						
					}
				}
			}
		}
		
		saveBakjo.offer(entryPoint[0]);
		bfsVisit[entryPoint[0].y][entryPoint[0].x] = true;
		
		int ans = 0;
		if(bfs()) {
			System.out.println(1);
			return;
		}
		while(true) {
			ans++;
			//print();
			melting();
			if(bfs()) {
				break;
			}
			nextMeltingPoint();
		}
		System.out.println(ans);
	}
}
