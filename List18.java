import java.util.Random;

class List18{
	private static final int N = 10;
	private static int[] sort = new int[N];

	private static void binaryInsert(){
		// 最初から最後まですべてソート済みになるまで繰り返す
		for(int sorted=1; sorted<N; sorted++){
			// ソート済みの領域の直後の値を取りだす
			int insert = sort[sorted];
			// 挿入する場所をみつける(バイナリサーチ)
			int i, left, right;
			left = 0;
			right = sorted;
			while(left < right){
				int mid = (right+left) / 2;
				if(sort[mid] < insert){
					left = mid+1;
				}else{
					right = mid;
				}
			}
			i = left;
			// ソート済み領域直後の値を挿入する
			while(i <= sorted){
				int temp = sort[i];
				sort[i] = insert;
				insert = temp;
				i++;
			}
		}
	}

	public static void main(String args[]){
		Random rand = new Random();
		System.out.println("ソート準備\n");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i]);
		}
		System.out.println("ソート中");
		binaryInsert();
		for(int i=0; i<N; i++){
			System.out.println(sort[i]);
		}
	}
}
