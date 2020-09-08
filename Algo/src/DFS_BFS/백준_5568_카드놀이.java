package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 백준_5568_카드놀이 {
	
	static int N;
	static int K;
	static String [] cards;
	static int ans;
	static HashSet <String> set;
	static boolean [] visit;
	public static void dfs(int depth, StringBuilder str, int max) {
		if(depth >= max) {
			set.add(str.toString());
			//System.out.println(str.toString());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visit[i])
				continue;
			StringBuilder temp = new StringBuilder();
			temp.append(str.toString());
			temp.append(cards[i]);
			visit[i] = true;
			dfs(depth+1, temp, max);
			visit[i] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = 0;
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		cards = new String[N];
		set = new HashSet<String>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cards[i] = st.nextToken();
		}
		
		StringBuilder str = new StringBuilder();
		for (int j = 2; j <= K; j++) {			
			visit = new boolean[N];
			dfs(0, str, j);
		}
		ans = set.size();
		System.out.println(ans);
	}
}
