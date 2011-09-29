import java.util.Random;
import java.io.BufferedReader;

class List21{
	private static int lenearSearch(int x, int[] a){
		int n = 0;

		// 配列の範囲内で目的の値をさがす
		while(n < a.length && a[n] != x){
			n++;
		}

		if(n < a.length){
			return n;
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
		System.out.print("何を探しますか？");
		int x = intReader();

		int r = lenearSearch(x, array);

		if(r == -1){
			System.out.println(x + "は見つかりません");
		}else{
			System.out.println(x + "は" + r + "番目に見つかりました");
		}
	}

	private static int intReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.out.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
