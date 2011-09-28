import java.util.Random;
import java.util.Arrays;

class List14{
	private static final int N = 10;
	private static int[] sort = new int[N];

	public static void main(String[] args){
		Random rand = new Random();
		System.out.println("ソート準備");
		for(int i=0; i<N; i++){
			sort[i] = rand.nextInt(100);
			System.out.println(sort[i] + " ");
		}
		System.out.println("\nソート準備");
		Arrays.sort(sort);
		System.out.println("ソート終了");

		for(int i=0; i<N; i++){
			System.out.println(sort[i] + " ");
		}
	}
}
