package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 바이너리 서치 개념을 사용해보자
 * 
 */
public class 백준_2343_기타레슨 {
	
	static int [] arr;
	static int N;
	static int M;
	static int ans;
	// 
	static void binarySearch(int start, int end) {
		if(start >= end)
			return;
		int mid = (start + end) / 2;
		//System.out.println(mid);
		if(checkPossible(mid)) {
			ans = mid;
			binarySearch(start, mid);
		} else {
			binarySearch(mid+1, end);
		}
	}
	
	// 시간복잡도 N
	// 최대 블루레이길이 maxValue 가 정해졌을때, 가능한지 확인
	static boolean checkPossible(int maxValue) {
		int save = 0;
		int count = 1;
		for (int i = 0; i < N; i++) {
			if(arr[i] > maxValue) {
				return false;
			} else if(arr[i] + save <= maxValue) {
				save+= arr[i];
			} else {
				count++;
				save = arr[i];
				if(count > M) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ans = 0;
		st = new StringTokenizer(br.readLine().trim(), " ");
		int total = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		binarySearch(0, total+1);
		
		System.out.println(ans);
		
	}
}
