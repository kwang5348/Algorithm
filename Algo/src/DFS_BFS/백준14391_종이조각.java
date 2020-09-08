package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ����14391_�������� {
	static int N;
	static int M;
	
	static int [][] map;
	static boolean [][] visit;
	static int ans;
	
	/*
	 * ���� ��ܺ��� ������ �ϴܱ��� map�� ��ĭ�� ���� �̵��ϸ鼭
	 * 
	 * �̵��Ҷ����� ���ο� ��� ����, ���ο� ��� ���� �����Ѵ�.
	 * 
	 * ��ġ�� ��츦 �н��ϸ� ����� �ݺ��Ѵ�.
	 * 
	 */
	public static void dfs(int y, int x, int result) {
		if(x == M) {
			// �ڵ������ map�� ������ ���� üũ���� ���� ��쿡�� return �ϰ� �Ǵµ�
			// ��¥�� ����ϱ� �ʿ������� ���� üũ���� ���� ��캸�� ����� ū ��찡 �߻��ϱ� ������
			// �信�� ������ ���� �ʴ´�.
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
		
		//�Ʒ��� ��ĭ�� �÷����� �ݺ���
		for (int i = y; i < N; i++) {
			// �Ѱ��� ��ġ�� �� ���ư� �� ���⶧���� break;
			if(visit[i][x]) {
				break;
			}
			
			//��ĭ �þ ������ ������� ����
			yTemp *= 10;
			yTemp += map[i][x];
			
			//��ĭ �þ ������ �ʱ�ȭ �ƴ�(*) visit ���� �ٽ� true�� �ٲ���
			for (int j = y; j <= i; j++) {
				visit[j][x] = true;				
			}
			dfs(y, x+1, result + yTemp);
			
			// (*) �ش� �ڽ� dfs�� ������ �ʱ�ȭ�� ������ �����ߴ� ĭ���� ��� ���� ���Ѱ����� �ʱ�ȭ
			for (int j = y; j <= i; j++) {
				visit[j][x] = false;				
			}
			//yTemp /= 10;
		}
		
		// �����ſ��� ���⸸ �ٲ����
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
