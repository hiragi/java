// エイトクイーン問題
class List101{
	static boolean board[][] = new boolean[8][8];

	static{
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j] = false;
			}
		}
	}

	// (x,y)にクイーンがおけるかチェック
	public static boolean check(int x, int y){
		// 左方向にすでにクイーンがあるかチェック
		// 右側には絶対存在しない
		for(int p=0; p<x; p++){
			if(board[p][y]){
				return false;
			}
		}
		// 左上方向をチェック
		int p = x;
		int q = y;
		while(p > 0 && q > 0){
			if(board[--p][--q]){
				return false;
			}
		}
		// 左下方向をチェック
		p = x;
		q = y;
		while(p > 0 && q < 7){
			if(board[--p][++q]){
				return false;
			}
		}
		return true;
	}

	public static void showBoard(){
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){
				System.out.print(board[x][y]? "Q" : ". ");
			}
			System.out.println();
		}
	}

	public static boolean solve(int x){
		if(x == 8){
			// すべての列にクイーンをおけたら
			return true;
		}

		for(int i=0; i<8; i++){
			if(check(x, i)){
				// (x,i)にクイーンがおけたら
				// 実際におく
				board[x][i] = true;
				if(solve(x+1)){
					// 次の列以降が成功ならこの列も成功
					return true;
				}else{
					// 次の列以降が失敗ならクイーンを置きなおす
					board[x][i] = false;
				}
			}
		}
		// 結局全部失敗した
		return false;
	}

	public static void main(String args[]){
		// 最初の列からスタート
		if(solve(0)){
			showBoard();
		}
	}
}
