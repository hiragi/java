import java.util.Random;

class List12{
	private static final int N = 10;
	private static int[] sort = new int[N];

	private static void bubbleSort(){
		boolean flag;
		do{
			flag = false;
			int k = 0;
			for(int i=0; i<N-1-k; i++){
				if(sort[i] > sort[i+1]){
					flag = true;
					int j = sort[i];
					sort[i] = sort[i+1];
					sort[i+1] = j;
				}
			}
			k++;
		}while(flag);
	}

	public static void main(String args[]){
		Random rand = new Random();
		System.out.println("ソート準備\n");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i] + " ");
		}
		System.out.println("ソート開始\n");
		bubbleSort();
		System.out.println("ソート終了\n");

		for(int i=0; i<N; i++){
			System.out.println(sort[i] + " ");
		}
	}
}
