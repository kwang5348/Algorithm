package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * @author user
 * BFS �� Ǯ��� �Ѵ� �Ǵܵ�
 * -> DFS �� ������ ��� ���� �ʹ� ���� ����
 * �̵��� ��ġ���� ���ʸ��� �Դ��� ���Ž����ָ� �ð� ����ȭ�� �븱�� ������ ����
 * 
 * �����̰� �� �� �ִ� �ൿ 3����
 * 3���� �ൿ�� ���� ����� ť�� �ִ� BFS �����ϰ� time�� üũ�ϸ鼭 ���ʿ� ã���� �Լ� ����
 * 
 * 
 */
public class ����_1697_���ڲ��� {
	static int N;
	static int K;
	
	
	public static int bfs() {
		int time = 0;
		Queue<Integer> Q = new LinkedList<Integer>();
		int [] visit = new int[100001];
		Arrays.fill(visit, Integer.MAX_VALUE);
		Q.offer(N);
		while(!Q.isEmpty()) {
			int size = Q.size();
			while(size -- > 0) {
				int temp = Q.poll();
				if(temp == K) {
					return time;
				}
				// �����̰� �������� �����϶�
				if(temp-1 >= 0) {
					if(time < visit[temp-1]) {
						visit[temp-1] = time;
						Q.offer(temp-1);
					}
				}
				// �����̰� ���������� �����϶�
				if(temp+1 <= 100000) {
					if(time < visit[temp+1]) {
						visit[temp+1] = time;
						Q.offer(temp+1);
					}
				}
				// �����̰� �����̵� �Ҷ�
				if(2*temp <= 100000) {
					if(time < visit[2*temp]) {
						visit[2*temp] = time;
						Q.offer(2*temp);
					}
				}
			} // end of second while
			time++;
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(bfs());
		
			
	}
}
