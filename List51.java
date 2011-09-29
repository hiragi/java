// 数値の中に1がいくつ含まれているか数えるプログラム
import java.io.BufferedReader;

class List51{
	public static void main(String args[]){
		int i = intReader();
		System.out.println(i + "の中に１は" + numOfOne(i) + "子ふくまれています");
	}

	private static int numOfOne(int value){
		int ret;
		// valueを次々に10で割って桁をずらしながら、
		// 再開の桁が1であるかどうかをチェック
		for(ret=0; value>0; value/=10){
			if(value%10 == 1){
				++ret;
			}
		}
		return ret;
	}

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
