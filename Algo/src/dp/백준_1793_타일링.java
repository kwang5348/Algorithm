package dp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


public class 백준_1793_타일링 {
	static BigInteger [] dp;
	// a > b 라는 조건을 넣고 시작

	public static BigInteger dp(int index) {
		if(dp[index] != null) {
			return dp[index];
		}
		dp [index] = dp(index-1).add(dp(index-2).add(dp(index-2))); 
		return dp[index];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		dp = new BigInteger[251];		
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");
		
		while(sc.hasNextInt()) {
			int index = sc.nextInt();	
			System.out.println(dp(index));
			
		}
		
	}
}
