package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
	int y;
	int x;
	int dir;

	public Fish(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}

/***
 * 
 * @author kwang 상어의 배열에 위치, 방향을 저장
 * 
 */



public class 백준_19236_청소년상어_진행중 {

	static Fish[] fishs;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int ans;

	static void print(Fish[] fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("%2d ", isExist(i, j, fish) + 1);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int isExist(int sy, int sx, Fish[] fish) {

		for (int i = 0; i < 16; i++) {
			if (fish[i] != null) {
				if (fish[i].y == sy && fish[i].x == sx) {
					return i;
				}
			}
		}
		return -1;
	}

	static void dfs(int sy, int sx, int sdir, int count, Fish[] fish) {
		// 기저조건은 없음

		// temp 복사
		Fish[] input = new Fish[16];
		input = fish.clone();

		// 물고기의 이동
		for (int i = 0; i < 16; i++) {
			if (input[i] != null) {
				print(input);
				System.out.println((i+1) + "번째 =  " + input[i].dir);
				for (int j = 0; j < 8; j++) {
					int pdir = (input[i].dir + j) % 8;
					int py = input[i].y + dy[pdir];
					int px = input[i].x + dx[pdir];

					if (py >= 0 && py < 4 && px >= 0 && px < 4) {
						int index = isExist(py, px, input);
						if (index >= 0) {							
							Fish temp = new Fish(input[i].y, input[i].x, input[i].dir);
							input[i] = new Fish(py, px, pdir);
							input[index] = new Fish(temp.y, temp.x, temp.dir);
							
							break;
						} else {
							if(py != sy && px != sx) {
								input[i].y = py;
								input[i].x = px;
								input[i].dir = pdir;
								break;
							}
						}
					}
				}
			}
		} // end of fish move

		for (int i = 0; i < 3; i++) {
			int py = sy + dy[sdir] * i;
			int px = sx + dx[sdir] * i;

			if (py >= 0 && py < 4 && px >= 0 && px < 4) {
				int index = isExist(py, px, input);
				if (index >= 0) {
					int pdir = input[index].dir;
					int pcount = count + index + 1;
					if (pcount > ans) {
						ans = pcount;
					}
					input[index] = null;
					dfs(py, px, pdir, pcount, input);
					input[index] = new Fish(py, px, pdir);
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		fishs = new Fish[16];
		ans = 0;
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < 4; j++) {
				int y = i;
				int x = j;
				int index = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken()) - 1;
				fishs[index] = new Fish(y, x, dir);
			}
		}
		int sy = 0;
		int sx = 0;
		int count = 0;
		int sdir = -1;
		int index = isExist(sy, sx, fishs);
		count += index + 1;
		sdir = fishs[index].dir;
		fishs[index] = null;

		dfs(sy, sx, sdir, count, fishs);
		System.out.println(ans);

	}
}
