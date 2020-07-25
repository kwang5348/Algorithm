package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라를 통해 a -> b 로 가는 최단경로 메서드를 구현한다
 * 
 * 1-> a, b 최단경로
 * a -> b 혹은 b-> a 최단경로
 * b , a -> N 으로 가는 최단경로를 구한다.
 * 
 */
class Dot implements Comparable<Dot>{
	int index;
	int weight;
	int dist;
	public Dot(int index, int weight, int dist) {
		super();
		this.index = index;
		this.weight = weight;
		this.dist = dist;
	}

	@Override
	public int compareTo(Dot o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}
}

public class 백준_1504_특정한최단경로 {
	static int N;
	static int E;
	static int ans;
	static ArrayList <Dot> [] graph;
	static final int MAX = 98765432;
	public static int Dijkstra(int start, int end) {
		PriorityQueue<Dot> Q = new PriorityQueue<Dot>();
		int [] dist = new int [N + 1];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		Q.offer(graph[start].get(0));
		dist[start] = 0;
		while(!Q.isEmpty()) {
			Dot temp = Q.poll();
			
			for (int i = 1; i < graph[temp.index].size(); i++) {
				int to = graph[temp.index].get(i).index;
				int weight = graph[temp.index].get(i).weight;
				
				if(dist[temp.index] + weight < dist[to]) {
					dist[to] = dist[temp.index] + weight ;
					Q.offer(new Dot(to, weight, dist[to]));
				}
				
			}
		}
		
		return dist[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		ans = 0;
		graph = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Dot>();
			graph[i].add(new Dot(i, 0, 0));
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int index = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[index].add(new Dot(to, weight, 0));
			graph[to].add(new Dot(index, weight, 0));
		}
		st = new StringTokenizer(br.readLine().trim(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int startToA = Dijkstra(1, a);
		int startToB = Dijkstra(1, b);
		int AToB = Dijkstra(a, b);
		int AToEnd = Dijkstra(a, N);
		int BToEnd = Dijkstra(b, N);
		int startToEnd = Dijkstra(1, N);
//		System.out.println("1 to a  " + startToA);
//		System.out.println("1 to b  " + startToB);
//		System.out.println("a to b  " + AToB);
//		System.out.println("a to n  " + AToEnd);
//		System.out.println("b to n  " + BToEnd);
		int resultA = startToA + AToB + BToEnd;
		int resultB = startToB + AToB + AToEnd;
		
//		System.out.println(resultA);
//		System.out.println(resultB);
		ans = Math.min(resultA, resultB);
		if(ans > 12345678 ) {
			ans = -1;
		}
		System.out.println(ans);
	}
}
