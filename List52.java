// 再帰を使って書き換えたnum_of_one
import java.io.BufferedReader;

class List52{
	public static void main(String args[]){
		int i = intReader();
		System.out.println(i + "のなかに1は" + numOfOne(i) + "個含まれています");
	}

	private static int numOfOne(int value){
		int ret;
		if(value == 0){
			// valueが0ケタ(もうこれ以上解析する桁がない)
			return 0;
		}
		if(value%10 == 1){
			ret = 1;
		}else{
			ret = 0;
		}

		// 10で割って桁を１つずつずらし、再びnumOfOneでしらべる
		return ret + numOfOne(value/10);
	}

/*	三項演算子を使った場合
	int numOfOne(int value){
		if(value == 0){
			return 0;
		}
		return (((value%10)==1) ? 1 : 0) + numOfOne(value/10);
	}
*/
	private static int intReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = reader.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
