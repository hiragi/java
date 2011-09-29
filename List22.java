import java.util.Random;
import java.io.BufferedReader;

class List22{
	private static int lenearSearch(int x, int[] a){
		// javaにおちて、番兵方式はあまりよい選択肢ではない

		int n = 0;

		// 最後の値をxに入れ替える
		int t = a[a.length-1];
		a[a.length-1] = x;

		// 配列の範囲内で目的の値を探す
		while(a[n] != x){
			n++;
		}

		// 配列最後の値をもとにもどす
		a[a.length-1] = t;

		if(n < a.length-1){
			return n; // 一番最後以外で一致
		}
		if(x == t){
			return n; // 一番最後で一致
		}

		return -1;
	}

	public static void main(String args[]){
		int[] array = new int[20];
		Random rand = new Random();
		for(int i=0; i<array.length; i++){
			array[i] = rand.nextInt(100);
			System.out.println(array[i]);
		}
		System.out.println("何を探しますか");
		int x = intReader();

		int r = lenearSearch(x, array);

		if(r == -1){
			System.out.println(x + "はみつかりませんでした");
		}else{
			System.out.println(x + "は" + r + "番目にみつかりました");
		}
	}

	private static int intReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.out.println("IO Exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
