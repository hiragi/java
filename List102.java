// 7パズルを解く
import java.util.Vector;

class List102{
	// いままでにあらわれた局面を記録するクラス
	// このクラスの配列はキュー代わりにも使われる
	static class Pattern{
		public final long hash;
		public final int patternFrom;

		Pattern(long hash, int patternFrom){
			this.hash = hash;
			this.patternFrom = patternFrom;
		}
	}

	private static Vector history = new Vector();

	// キューの末尾位置を表す添え字
	private static int queueBottom = 0;

	// 局面と対応する数字を作り出す関数
	private static long makeHash(char[] pattern){
		long hash = 0;
		for(int i=0; i<8; i++){
			hash |= (long)(pattern[7-i]) << (i*4);
		}
		return hash;
	}

	// 数字から局面を復元する関数
	private static void patternFromHash(char[] pattern, long hash){
		for(int i=0; i<8; i++){
			pattern[7-i] = (char)((hash >> (i*4)) & 0xf);
		}
	}

	private static void saveHistory(char[] pattern, int patternFrom){
		long hash = makeHash(pattern);

		for(int i=0; i<history.size(); i++){
			if(((Pattern)history.elementAt(i)).hash == hash){
				return;
			}
		}
		history.add(new Pattern(hash, patternFrom));
	}

	private static boolean solve7Puzzle(){
		char pattern[] = new char[8];
		// 添え字と場所の対応は以下の通り
		// 0 1 2 3
		// 4 5 6 7

		queueBottom = 0;
		while(queueBottom != history.size()){
			long hash = ((Pattern)history.elementAt(queueBottom)).hash;
			if(hash == 0x01234567){
				// 解にたどり着いたら終了
				return true;
			}
			patternFromHash(pattern, hash);
			int blankPos;
			for(blankPos=0; blankPos<8; blankPos++){
				if(pattern[blankPos] == 0){
					break;
				}
			}
			if(blankPos > 3){
				// 上からあいている場所へ移動
				pattern[blankPos] = pattern[blankPos-4];
				pattern[blankPos-4] = 0;
				// 新しいパネル配置を保存した後、元の配置に戻す
				saveHistory(pattern, queueBottom);
				patternFromHash(pattern, hash);
			}
			if(blankPos < 4){
				// 下からあいている場所へ移動
				pattern[blankPos] = pattern[blankPos+4];
				pattern[blankPos+4] = 0;
				saveHistory(pattern, queueBottom);
				patternFromHash(pattern, hash);
			}
			if(blankPos != 0 && blankPos != 4){
				// 左からあいている場所へ移動
				pattern[blankPos] = pattern[blankPos-1];
				pattern[blankPos-1] = 0;
				saveHistory(pattern, queueBottom);
				patternFromHash(pattern, hash);
			}
			if(blankPos != 3 && blankPos != 7){
				// 右からあいている場所へ移動
				pattern[blankPos] = pattern[blankPos+1];
				pattern[blankPos+1] = 0;
				saveHistory(pattern, queueBottom);
			}
			++queueBottom;
		}
		return false;
	}

	public static void main(String args[]){
		char pattern[] = {2,7,4,1,5,0,3,6};
		// 最初の1つ目のパターンを記録
		saveHistory(pattern, -1);

		if(solve7Puzzle()){
			// 解を表示する
			int last = -1;
				while(last != queueBottom){
				int i;
				for(i=queueBottom; ((Pattern)history.elementAt(i)).patternFrom != last;){
					i=((Pattern)history.elementAt(i)).patternFrom;
				}
				last = i;

				// パネルを表示
				patternFromHash(pattern, ((Pattern)history.elementAt(last)).hash);
				for(i=0; i<8; i++){
					System.out.print(((pattern[i] != 0)? (int)(pattern[i]) + "" : " ") + " ");
					if(i%4 == 3){
						System.out.println();
					}
				}
				// リターンキーで次を表示
				try{
					new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine();
				}catch(java.io.IOException e){
				}
			}
		}else{
			System.out.println("全パターンを試しましたが、解が見つかりませんでした");
		}
	}
}
