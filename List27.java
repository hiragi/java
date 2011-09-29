// 自己組織化探索
import java.util.Random;
import java.io.BufferedReader;

class List27{
	private static int organizationSearch(int x, int[] a){
		int n = 0;

		// 配列の範囲内で目的の値を探す
		while(n < a.length && a[n] != x){
			n++;
		}

		if(n < a.length){
			if(n > 0){
				// １つ前と入れ替える
				int t = a[n-1];
				a[n-1] = a[n];
				a[n] = t;
				return n-1;
			}
			return n;
		}

		return -1;
	}

	public static void main(String args[]){
		int[] array = new int[20];
		Random rand = new Random();
		for(int i=0; i<array.length; ++i){
			array[i] = rand.nextInt(100);
		}
		while(true){
			for(int i=0; i<array.length; ++i){
				System.out.println(array[i]);
			}
			System.out.println("なにを探しますか(-1:END):");
			int x = intReader();
			if(x == -1){
				break;
			}

			int r = organizationSearch(x, array);

			if(r == -1){
				System.out.println(x + "はみつかりません");
			}else{
				System.out.println(x + "は" + r + "番目にあります");
			}
		}
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
