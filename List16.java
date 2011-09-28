import java.util.Random;

class List16{
	// データの件数
	private static final int N = 10;
	private static int[] sort = new int[N];

	private static void combSort(){
		boolean flag;
		int gap = N;

		do{
			// 収縮率は1.3(gapが毎回1/1.3になる)
			gap = (gap * 10) / 13;

			if(gap == 0){
				gap = 1;
			}
			flag = true;

			// 先頭から順に見ていって
			for(int i=0; i<N-gap; i++){
				// 距離がgapだけ離れた要素を比較し、
				// 値ラビがおかしければ入れ替える
				if(sort[i] > sort[i+gap]){
					flag = false;
					int temp = sort[i];
					sort[i] = sort[i+gap];
					sort[i+gap] = temp;
				}
			}
			// 1度も並び変えずに gap == 1 で見終わったら終了
		}while((gap > 1) || !flag);
	}

	public static void main(String args[]){
		Random rand = new Random();
		System.out.println("ソート準備");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i]);
		}
		System.out.println("ソート中\n");
		combSort();
		System.out.println("ソート終了\n");

		for(int i=0; i<N; i++){
			System.out.println(sort[i]);
		}
	}
}
