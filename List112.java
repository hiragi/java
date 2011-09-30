// バックトラック法による数列分割問題
class List112{
	private static final int[] value = {15,3,7,6,10,4,13,2,3,6};
	private static final int separator = 3;
	private static int sepPos[] = new int[separator];
	// 最適な分割と、その中のグループの最大和
	private static int[] bestSepPos = new int[separator];
	private static int bestSepMaxValue = 10000;

	static{
		for(int i=0; i<separator; i++){
			sepPos[i] = bestSepPos[i] = 0;
		}
	}

	private static void separate(int pos, int num){
		// 新しい分割場所を設定
		sepPos[num++] = pos;

		// 分割が全て決まったら
		if(num == separator){
			int max = 0;
			for(int i=0; i<=separator; i++){
				int start = (i==0)? 0 : sepPos[i-1];
				int end = (i == separator)? value.length : sepPos[i];
				int k = 0;
				for(int j = start; j < end; j++){
					k += value[j];
				}
				if(k > max){
					max = k;
				}
			}

			// 最大のグループ和が保存されている和より小さければ
			if(max < bestSepMaxValue){
				// 現在の分割を保存する
				bestSepMaxValue = max;
				for(int i=0; i<separator; i++){
					bestSepPos[i] = sepPos[i];
				}
			}
			return;
		}

		// 次の分割場所を設定する
		for(int i=pos+1; i<value.length-separator+num+1; i++){
			separate(i, num);
		}
	}

	public static void main(String args[]){
		// 最初の分割場所を設定して再帰を呼び出す
		for(int i=0; i<value.length-separator+1; i++){
			separate(i, 0);
		}

		// 保存された分割場所を表示する
		for(int i=0; i<=separator; i++){
			int start = (i==0)? 0 : bestSepPos[i-1];
			int end = (i==separator)? value.length : bestSepPos[i];

			for(int j=start; j<end; j++){
				System.out.print(value[j] + " ");
			}
			if(end != value.length){
				System.out.print("| ");
			}
		}
	}
}
