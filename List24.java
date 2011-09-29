// 同じ値が続く場合にその先頭を返すバイナリサーチ

import java.util.Random;
import java.util.Arrays;
import java.io.BufferedReader;

class List24{
	private static int binarySearch(int x, int[] a){
		int left = 0, right = a.length-1, mid;

		while(left < right){
			mid = (left+right) / 2;

			if(a[mid] < x){
				left = mid + 1;
			}else{
				right = mid;
			}
		}

		if(a[left] == x){
			return left;
		}

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
		System.out.println("何を探しますか");
		int x = intReader();

		int r = binarySearch(x, array);

		if(r == -1){
			System.out.println(x + "はみつかりません");
		}else{
			System.out.println(x + "は" + r + "番目にあります");
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
