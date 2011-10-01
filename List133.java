// 隣接行列を利用するプログラム

public class List133{
	private static final int STATION_NUMBER = 6;
	private static String[] stations = {"鎌倉", "藤沢", "横浜", "横須賀", "茅ヶ崎", "東京"};

	private static int[][] adjacencyMatrix = {
		new int[]{0,1,1,1,0,0},
		new int[]{1,0,1,0,1,0},
		new int[]{1,1,0,0,0,1},
		new int[]{1,0,0,0,0,0},
		new int[]{0,1,0,0,0,0},
		new int[]{0,0,1,0,0,0}
	};

	public static void main(String args[]){
		for(int i=0; i<STATION_NUMBER; i++){
			System.out.print(stations[i] + ":");
			for(int j=0; j<STATION_NUMBER; j++){
				if(adjacencyMatrix[i][j] == 1){
					System.out.print("→" + stations[j] + " ");
				}
			}
			System.out.println();
		}
	}
}
