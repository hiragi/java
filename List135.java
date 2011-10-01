// 重みつきグラフのプログラム
public class List135{
	private static final int STATION_NUMBER = 6;
	private static String[] stations = {"横浜", "武蔵小杉", "品川", "渋谷", "新橋", "溜池山王"};

	private static int[][] adjacencyMatrix = {
		new int[]{0,12,28,0,0,0},
		new int[]{12,0,10,13,0,0},
		new int[]{28,10,0,11,7,0},
		new int[]{0,13,11,0,0,9},
		new int[]{0,0,7,0,0,4},
		new int[]{0,0,0,9,4,0}
	};

	public static void main(String args[]){
		for(int i=0; i<STATION_NUMBER; i++){
			System.out.print(stations[i] + ":");
			for(int j=0; j<STATION_NUMBER; j++){
				if(adjacencyMatrix[i][j] > 0){
					System.out.print("→" + stations[j] + "(" + adjacencyMatrix[i][j] + "分)" + " ");
				}
			}
			System.out.println();
		}
	}
}
