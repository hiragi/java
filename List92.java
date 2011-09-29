// KMP法による文字列検索
class List92{
	private static int kmpSearch(String text, String pattern){
		int textIndex = 1;
		int patternIndex = 0;
		int cacheTable[] = new int[pattern.length()+1];
		cacheTable[0] = cacheTable[1] = 0;
		// KMP検索に必要な情報をあらかじめ計算し、
		// 配列にキャッシュする
		while(pattern.length() > textIndex){
			if(pattern.charAt(textIndex) == pattern.charAt(patternIndex)){
				// 一致したら再比較は
				// pattern_index 文字からはじめればよい
				textIndex++;
				patternIndex++;
				cacheTable[textIndex] = patternIndex;
			}else if(patternIndex == 0){
				// パターン1文字目で不一致ならば、
				// 再検索の位置は先頭から
				textIndex++;
				cacheTable[textIndex] = 0;
			}else{
				// パターン1文字目以外で不一致ならば、
				// 再検索の位置はcache_tableから
				patternIndex = cacheTable[patternIndex];
			}
		}
		// 以上でキャッシュテーブルが完成

		// ここから実際の再検索
		int i = 0;
		int n = 0;
		while(n < text.length()){
			System.err.print("	本文:" + text + "\nパターン:");
			for(int l=0; l<n; l++){
				System.err.print(" ");
			}
			System.err.println(pattern.charAt(i));

			if(text.charAt(n) == pattern.charAt(i)){
				// テキストとパターンが一致していれば、
				// 1時ずつ比較をつづける
				++n;
				++i;
				if(pattern.length() == i){
					// すべて一致すればOK
					return n-pattern.length();
				}
			}else if(i == 0){
				// パターン最初の文字で失敗した場合は、
				// 比較場所を1つ勧める
				++n;
			}else{
				// 最初の文字でない場合は、
				// テーブルから比較位置を取得する
				i = cacheTable[i];
			}
		}

		return -1;
	}

	public static void main(String args[]){
		if(kmpSearch("a eighty-eighty-eighth key", "eighty-eighth") == -1){
			System.out.println("Not Found");
		}else{
			System.out.println("見つかりました");
		}
	}
}
