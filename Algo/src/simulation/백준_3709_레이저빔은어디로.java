package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_3709_레이저빔은어디로 {

	static int ans;
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");

		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N + 2][N + 2];
			visit = new boolean[N + 2][N + 2][4];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}

			st = new StringTokenizer(br.readLine().trim(), " ");
			int ly = Integer.parseInt(st.nextToken());
			int lx = Integer.parseInt(st.nextToken());
			int ldir = -1;

			if (ly == 0) {
				ldir = 2;
			} else if (ly == N + 1) {
				ldir = 0;
			} else if (lx == 0) {
				ldir = 1;
			} else if (lx == N + 1) {
				ldir = 3;
			}

			while (true) {

				ly += dy[ldir];
				lx += dx[ldir];
//				if (visit[ly][lx] && map[ly][lx] == 1) {
//					ly = 0;
//					lx = 0;
//					break;
//				}

				if (ly == 0 || lx == 0 || ly == N + 1 || lx == N + 1) {
					break;
				}

				if (map[ly][lx] == 1) {
					if(visit[ly][lx][ldir]) {
						ly = 0;
						lx = 0;
						break;
					}
					visit[ly][lx][ldir] = true;
					ldir++;
					ldir %= 4;
				}
			}
			System.out.println(ly + " " + lx);
		}
	}
}
