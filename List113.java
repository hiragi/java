// 動的計画法による数列分割問題
import java.text.DecimalFormat;

class List113{
	private static final int array[] = {15, 3, 7, 6, 10, 4, 13, 2, 3, 6};
	private static final int separator = 3;

	static class Cell{
		public int sol;
		public int num;
	}

	public static void main(String args[]){
		Cell solutions[][] = new Cell[array.length][separator+1];

		// 表の後ろのほうから順に埋めていく
		for(int i=array.length-1; i>=0; i--){
			for(int j=0; j<separator+1; j++){
				solutions[i][j] = new Cell();
				solutions[i][j].num = 0;
				solutions[i][j].sol = 0;
				int sum = 0;
				for(int s=i; s<array.length; s++){
					sum += array[s];
					if(j==0 || i==array.length-1 ||solutions[i][j].num == 0 || (s != array.length-1 && solutions[i][j].sol > Math.max(sum, solutions[s+1][j-1].sol))){
						if(j==0 || i==array.length-1){
							// 1行目か最終列ならば、処理なし
							solutions[i][j].sol = sum;
						}else{
							// よりよい解決法を記録する
							solutions[i][j].sol = Math.max(sum, solutions[s+1][j-1].sol);
						}
						solutions[i][j].num = s-i+1;
					}
				}
			}
		}

		// デバッグ用にテーブルを表示
		DecimalFormat format = new DecimalFormat("00");
		for(int j=0; j<separator+1; j++){
			for(int i=0; i<array.length; i++){
				System.out.print(format.format(solutions[i][j].num) + "," + format.format(solutions[i][j].sol) + " ");
			}
			System.out.println();
		}
		System.out.print("\n最大の和:" + solutions[0][separator].sol + "\n分割方法:");
		for(int i=0, j=separator; j>=0 && i != array.length; --j){
			System.out.print("[" + solutions[i][j].num + "個] ");
			i += solutions[i][j].num;
		}
	}
}
