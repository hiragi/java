import java.util.Random;

class List13{
	private static final int N = 10;
	private static int[] sort = new int[N];

	private static void quickSort(int bottom, int top, int[] data){
		int lower, upper;

		if(bottom >= top){
			return;
		}
		// 先頭の値を適当な値とする
		int div = data[bottom];
		for(lower=bottom, upper=top; lower<=upper;){
			while(lower <= upper && data[lower] <= div){
				lower++;
			}
			while(lower <= upper && data[upper] > div){
				upper--;
			}
			if(lower < upper){
				int temp = data[lower];
				data[lower] = data[upper];
				data[upper] = temp;
			}
		}

		// 最初に選択した値を中央に移動する
		int temp = data[bottom];
		data[bottom] = data[upper];
		data[upper] = temp;

		quickSort(bottom, upper-1, data);
		quickSort(upper+1, top, data);
	}

	public static void main(String args[]){
		Random rand = new Random();
		System.out.println("ソート準備\n");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i] + " ");
		}
		System.out.println("ソート開始\n");
		quickSort(0, N-1, sort);
		System.out.println("ソート終了\n");

		for(int i=0; i<N; i++){
			System.out.println(sort[i] + " ");
		}
	}
}
