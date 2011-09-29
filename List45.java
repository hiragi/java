// キューのプログラム例
import java.io.BufferedReader;

class IntVectorQueue{
	IntVectorQueue(int size){
		this.size = size + 1;
		queue = new int[size];
	}

	private final int size;
	private int[] queue;
	private int first = 0, last = 0;

	public void enqueue(int val){
		if((last+1)%size == first){
			throw new RuntimeException("エラー：キューが満杯です");
		}else{
			queue[last] = val;
			last = (last+1)%size;
		}
	}

	public int dequeue(){
		int ret;

		if(first == last){
			return -1;
		}else{
			ret = queue[first];
			first = (first+1)%size;
			return ret;
		}
	}

	public void printQueue(){
		for(int i=first; i != last; i=(i+1)%size){
			System.out.println(queue[i]);
		}
	}
}

class List45{
	public static void main(String args[]){
		IntVectorQueue queue = new IntVectorQueue(5);

		int i;

		do{
			System.out.println("現在のキュー：");
			queue.printQueue();
			System.out.println("コマンド 0:終了 1:ジョブをためる 2:ジョブを実行\n>");
			i = intReader();
			switch(i){
				case 1:
					{
						System.out.println("ジョブの識別番号(1-1000)を適当に入力:");
						int j = intReader();
						if(j >= 1 && j <= 1000){
							queue.enqueue(j);
						}
						break;
					}
				case 2:
					{
						int j = queue.dequeue();
						if(j == -1){
							System.out.println("ジョブがありません");
						}else{
							System.out.println("識別番号" + j + "ぼジョブを実行中");
						}
					}
			}
		}while(i != 0);
	}
	private static int intReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = reader.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
