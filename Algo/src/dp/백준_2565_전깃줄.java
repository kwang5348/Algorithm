package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Eline implements Comparable<Eline>{
	int start;
	int end;
	
	public Eline(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Eline o) {
		return this.start - o.start;
	}
	
}
/*
 * �������� �ִ������� �������� �Ÿ��� ���� ����� �����ٺ��� ���ƾ� �Ұ��̴�!
 * greedy ��� ������?? -> �����ؾ� �ϴ� ������ ��Ž�� �ƴϱ� ������
 * 
 * �ͳ��� �߷����� ���� �����ҵ�??
 * 
 * �̰� ����Ǹ� �ִ� �ð����⵵ 10000 * a
 * 
 */
public class ����_2565_������ {
	
	static ArrayList<Eline> arr;
	static int N;
	static int ans;
//	public static boolean interact(Eline a, Eline b) {
//		if((a.start < b.start) && (b.end < a.end)) {
//			return true;
//		}
//		
//		if((a.end < b.end) && (b.start < a.start)) {
//			return true;
//		}
//		
//		return false;
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		arr = new ArrayList<Eline>();
		ans = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Eline temp = new Eline(start, end);
			arr.add(temp);
		}
		Collections.sort(arr);
		int [] lis = new int[N];
		int point = Integer.MAX_VALUE;
		for (int i = 0; i < arr.size(); i++) {
			lis[i] = 1;
			for (int j = 0; j < i; j++) {
				if(lis[j] + 1 > lis[i] && arr.get(i).end > arr.get(j).end)
					lis[i] = lis[j] + 1;
			}
			count = Math.max(count, lis[i]);
		}
		ans = N - count;
		System.out.println(ans);

	}
}
