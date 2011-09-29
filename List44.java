// キュー
class List44{
	class IntVectorQueue{
		IntVevtorQueue(int size){
			this.size = size+1;
			queue = new int[size];
		}

		private final int size;
		private int[] queue;
		private int first = 0, last = 0;

		public void enqueue(int val){
			if((last+1)%size == first){
				// 以降は間違った使い方
				// 本来は自分で新しいクラスを派生すべき
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
	}
}
