package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준14391_종이조각 {
	static int N;
	static int M;
	
	static int [][] map;
	static boolean [][] visit;
	static int ans;
	
	/*
	 * 왼쪽 상단부터 오른쪽 하단까지 map을 한칸씩 전부 이동하면서
	 * 
	 * 이동할때마다 가로에 몇개를 고를지, 세로에 몇개를 고를지 선택한다.
	 * 
	 * 겹치는 경우를 패스하며 통과를 반복한다.
	 * 
	 */
	public static void dfs(int y, int x, int result) {
		if(x == M) {
			// 코드상으로 map의 값들을 전부 체크하지 않은 경우에도 return 하게 되는데
			// 어짜피 양수니깐 필연적으로 값을 체크하지 않은 경우보다 결과가 큰 경우가 발생하기 때문에
			// 답에는 영향이 가지 않는다.
			dfs(y+1, 0, result);
			return;
		}
		
		if(y == N) {
			if(result > ans)
				ans = result;
			return;
		}
		
		if(visit[y][x]) {
			dfs(y, x+1, result);
		}
		
		
		int yTemp = 0;
		
		//아래로 한칸씩 늘려가는 반복문
		for (int i = y; i < N; i++) {
			// 한개라도 겹치면 더 나아갈 수 없기때문에 break;
			if(visit[i][x]) {
				break;
			}
			
			//한칸 늘어날 때마다 결과값이 증가
			yTemp *= 10;
			yTemp += map[i][x];
			
			//한칸 늘어날 때마다 초기화 됐던(*) visit 값을 다시 true로 바꿔줌
			for (int j = y; j <= i; j++) {
				visit[j][x] = true;				
			}
			dfs(y, x+1, result + yTemp);
			
			// (*) 해당 자식 dfs가 끝나고 초기화가 됐을때 선택했던 칸들을 모두 선택 안한것으로 초기화
			for (int j = y; j <= i; j++) {
				visit[j][x] = false;				
			}
			//yTemp /= 10;
		}
		
		// 위에거에서 방향만 바뀌었음
		int xTemp = 0;
		for (int i = x; i < M; i++) {
			if(visit[y][i]) {
				break;
			}
			xTemp *= 10;
			xTemp += map[y][i];
			for (int j = x; j <= i; j++) {
				visit[y][j] = true;				
			}
			dfs(y, x+1, result + xTemp);
			for (int j = x; j <= i; j++) {
				visit[y][j] = false;				
			}
			//xTemp /= 10;
			
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans= 0;
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		for (int[] maps : map) {
			System.out.println(Arrays.toString(maps));
		}
		dfs(0, 0, 0);

		System.out.println(ans);
		
	}
}
