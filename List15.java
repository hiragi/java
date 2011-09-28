import java.util.Random;

class List15{
	private static final int N = 10;
	private static int[] sort = new int[N];

	private static void mergeSort(int n, int[] x, int offset){
		if(n <= 1){
			return;
		}
		int m = n / 2;

		// ブロックを前半と後半に分ける
		mergeSort(m, x, offset);
		mergeSort(n-m, x, offset+m);

		int[] buffer = new int[m];
		// マージの操作
		for(int i=0; i<m; i++){
			buffer[i] = x[offset+i];
		}

		int j = m;
		int i = 0;
		int k = 0;
		while(i<m && j<n){
			if(buffer[i] <= x[offset+j]){
				x[offset + k++] = buffer[i++];
			}else{
				x[offset + k++] = x[offset+ j++];
			}
		}

		while(i < m){
			x[offset + k++] = buffer[i++];
		}
	}

	public static void main(String args[]){
		Random rand = new Random();
		System.out.println("ソート準備");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i] + " ");
		}
		System.out.println("ソート開始");
		mergeSort(N, sort, 0);
		System.out.println("ソート終了");

		for(int i=0; i<N; i++){
			System.out.println(sort[i] + " ");
		}
	}
}

