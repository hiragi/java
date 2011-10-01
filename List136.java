// ダイクストラ法のプログラム
public class List136{
	private static final int STATION_NUMBER = 6;
	private static final int START_STATION = 0;

	private static String[] stations = {"横浜", "武蔵小杉", "品川", "渋谷", "新橋", "溜池山王"};
	private static int currentCost[];
	private static boolean fix[];

	private static int[][] adjacencyMatrix = {
		new int[]{0,12,28,0,0,0},
		new int[]{12,0,10,13,0,0},
		new int[]{28,10,0,11,7,0},
		new int[]{0,13,11,0,0,9},
		new int[]{0,0,7,0,0,4},
		new int[]{0,0,0,9,4,0}
	};

	public static void main(String args[]){
		// 初期化する
		currentCost = new int[STATION_NUMBER];
		fix = new boolean[STATION_NUMBER];
		for(int i=0; i<STATION_NUMBER; i++){
			currentCost[i] = -1; // 無限大
			fix[i] = false;
		}
		currentCost[START_STATION] = 0; // スタート地点(横浜)の所要時間を0とする
		
		for(;;){
			int minStation = -1, minTime = -1;
			for(int i=0; i<STATION_NUMBER; i++){
				if(!fix[i] && currentCost[i] != -1){
					if(minTime == -1 || minTime > currentCost[i]){
						// 確定しておらず、もっとも所要時間の小さい駅を調べる
						minTime = currentCost[i];
						minStation = i;
					}
				}
			}
			if(minTime == -1){
				// すべての駅が確定したが、最小の所要時間が無限大だったので終了
				break;
			}
			// 自分の駅から伸びているすべての駅の所要時間を調べる
			for(int i=0; i<STATION_NUMBER; i++){
				if(!fix[i] && adjacencyMatrix[minStation][i] > 0){
					int newTime = minTime + adjacencyMatrix[minStation][i];

					// 自分の駅経由で移動する場合の必要時間
					if(currentCost[i] == -1 || currentCost[i] > newTime){
						// 今登録されている時間よりも、この駅経由で移動した
						// 時間が早いので、新しい時間を登録する
						currentCost[i] = newTime;
					}
				}
			}
			// 自分の駅を確定する
			fix[minStation] = true;
		}

		for(int i=0; i<STATION_NUMBER; i++){
			System.out.println(stations[START_STATION] + "→" + stations[i] + ":" + currentCost[i] + "分");
		}
	}
}
