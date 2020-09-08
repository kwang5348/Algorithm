package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot {
	int x;
	int y;
	int z;
	public Dot(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Line {
	Dot start;
	Dot end;
	public Line(Dot start, Dot end) {
		super();
		this.start = start;
		this.end = end;
	}
	
}

public class 백준_1828_세금_풀이중 {
	static int C;
	static ArrayList<Integer> [] graph;
	static ArrayList<Line> [] lineMap;
	static int [][][] map;
	
	public static int bfs(int start, int end) {
		// 왜 국경이 이어지지 않는 경우가 예시가 없는걸까
		int result = 0;
		boolean [] visit = new boolean [C];
		Queue <Integer> q = new LinkedList<Integer>();
		
		q.add(start);
		while(!q.isEmpty()) {
			int size = q.size();
			result++;
			while(size-- > 0) {
				int temp = q.poll();
				for (int i = 0; i < graph[temp].size(); i++) {
					int index = graph[temp].get(i);
					if(visit[index]) {
						continue;
					}
					if(index == end) {
						return result;
					}
					visit[index] = true;
					q.offer(index);
				}
			}
			
		}
		
		
		
		return 0;
	}
	
	public static boolean checkEqualDot(Dot a, Dot b) {
		if(a.x == b.x) {
			if(a.x == b.y) {
				if(a.z == b.z) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkEqualLine(Line a, Line b) {
		boolean result = false;
		if(checkEqualDot(a.start, b.start) && checkEqualDot(a.end, b.end)) {
			result = true;
		}
		
		if(checkEqualDot(a.start, b.end) && checkEqualDot(a.end, b.start)) {
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		C = Integer.parseInt(st.nextToken());
		map = new int [C][20][3];
		graph = new ArrayList[C];
		
		lineMap = new ArrayList[C];
		
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				map[i][j*3][0] = Integer.parseInt(st.nextToken());
				map[i][j*3+1][1] = Integer.parseInt(st.nextToken());
				map[i][j*3+2][2] = Integer.parseInt(st.nextToken());
			}
			
			lineMap[i] = new ArrayList<Line>();
			graph[i] = new ArrayList<Integer>();
			
			for (int j = 0; j < n-1; j++) {
				Dot startDot = new Dot(map[i][j][0], map[i][j][1], map[i][j][2]);
				Dot endDot = new Dot(map[i][j+1][0], map[i][j+1][1], map[i][j+1][2]);
				Line temp = new Line(startDot, endDot);
				lineMap[i].add(temp);
				boolean flag = true;
				for (int k = 0; k < i; k++) {
					if(!flag) {
						break;
					}
					for (int m = 0; m < lineMap[k].size(); m++) {
						if(!flag) {
							break;
						}
						if(checkEqualLine(temp, lineMap[k].get(m))) {
							graph[i].add(k);
							graph[k].add(i);
							flag = false;
						}
					}
				}
			}
			Dot startDot = new Dot(map[i][n-1][0], map[i][n-1][1], map[i][n-1][2]);
			Dot endDot = new Dot(map[i][0][0], map[i][0][1], map[i][0][2]);
			Line temp = new Line(startDot, endDot);

			lineMap[i].add(temp);
			for (int k = 0; k < i; k++) {
				for (int m = 0; m < lineMap[k].size(); m++) {
					if(checkEqualLine(temp, lineMap[k].get(m))) {
						graph[i].add(k);
						graph[k].add(i);
					}
				}
			}
			
		}
		
		for (int i = 0; i < C; i++) {
			System.out.print("graph " + i + " : ");
			for (int j = 0; j < graph[i].size(); j++) {
				System.out.print(graph[i].get(j) + " ");
			}
			System.out.println();
		}
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		int Q = Integer.parseInt(st.nextToken());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			System.out.println(bfs(start-1, end-1));
		}
	}
}
