// 3つ以上の整数の最大公約数を求めるプログラム
import java.util.Arrays;

class List54{
	private static int gcd(int a, int b){
		int i;
		for(i=a; i>0; i--){
			if(a%i==0 && b%i==0){
				break;
			}
		}
		return i;
	}

	private static int multi_gcd(int[] n, int use){
		//use==2(数が2つしかない)の場合は、普通にgcdを呼ぶだけ
		if(use==2){
			return gcd(n[0], n[1]);
		}

		// use>2の場合は、N[n]とN[0]..N[n-1]のgcdを呼び出す
		return gcd(n[n.length-1], multi_gcd(n, use-1));
	}

	public static void main(String args[]){
		int array[] = {98,140,84,28,42,126};
		System.out.println("配列Nの最大公約数は " + multi_gcd(array, array.length) + "です");
	}
}
