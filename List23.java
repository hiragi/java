// バイナリサーチ
import java.util.Random;
import java.util.Arrays;
import java.io.BufferedReader;

class List23{
	private static int binarySearch(int x, int[] a){
		int left = 0, right = a.length-1, mid;

		while(left <= right){
			mid = (right+left) / 2;
			if(a[mid] == x){
				return mid;
			}

			if(a[mid] < x){
				// midより左側に、xは存在しない
				left = mid+1;
			}else{
				right = mid-1;
			}
		}

		// サーチ範囲がなくなっても一致するものはなかった
		return -1;
	}

	public static void main(String args[]){
		int[] array = new int[20];
		Random rand = new Random();
		for(int i=0; i<array.length; i++){
			array[i] = rand.nextInt(100);
		}
		Arrays.sort(array);
		for(int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}

		System.out.println("何をさがしますか");
		int x = intReader();

		int r= binarySearch(x, array);

		if(r == -1){
			System.out.println(x + "はみつかりません");
		}else{
			System.out.println(x + "は" + r + " 番目にみつかりました");
		}
	}

	private static int intReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch (java.io.IOException e){
			System.out.println("IOException");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
