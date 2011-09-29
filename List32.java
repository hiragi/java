// 入力する数値の数を最初に確認する
import java.io.BufferedReader;

class List32{
	public static void main(String args[]){
		int array[];
		int count = 0;

		System.out.println("何個の数値を入力しますか");
		count = intReader();
		array = new int[count];

		int i = 0;
		int buf;
		do{
			System.out.println("数値を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				array[i] = buf;
				i++;
			}
		}while(buf != 0);

		// 合計値を算出
		int sum = 0;
		System.out.println("入力されたのは以下の数です");
		for(int n=0; n<i; n++){
			System.out.println(array[n]);
			sum += array[n];
		}
		System.out.println("合計値:" + sum);
	}

	private static int intReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
