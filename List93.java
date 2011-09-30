//BM法による文字列検索
class List93{
	private static int bmSearch(String text, String pattern){
		// char型であらわされるあらゆる文字について、
		// 「その文字で不一致が生じたときの比較点の移動量」を
		// あらかじめ計算してキャッシュするテーブル
		int cacheTable[] = new int[256];

		// 不一致が生じた移動量をあらかじめ計算し、キャッシュする
		for(int i=0; i<256; i++){
			// パターンに含まれないすべての文字は、
			// パターン長だけ移動すればよい
			cacheTable[i] = pattern.length();
		}
		for(int i=0; i<pattern.length(); i++){
			// パターンに含まれている文字は、それにあわせてずらす
			// 同じ文字が複数含まれている場合は、
			// 最後に登場する文字にあわせる
			cacheTable[pattern.charAt(i)] = pattern.length()-i-1;
		}
		// 移動量のキャッシュここまで

		int textIndex = pattern.length()-1;

		while(textIndex < text.length()){
			System.err.print("	本文:" + text + "\nパターン:");
			for(int i=0; i<textIndex-pattern.length()+1; i++){
				System.err.print(" ");
			}
			System.err.println(pattern);

			// パターンの後ろから比較を始める
			int patternIndex = pattern.length()-1;

			while(text.charAt(textIndex) == pattern.charAt(patternIndex)){
				if(patternIndex == 0){
					// パターンの先頭文字まですべて比較成功すればOK
					return textIndex;
				}
				--textIndex;
				--patternIndex;
			}

			if(cacheTable[text.charAt(textIndex)] > pattern.length()-patternIndex){
				// その文字に応じて、比較点を移動
				textIndex += cacheTable[text.charAt(textIndex)];
			}else{
				textIndex += pattern.length() - patternIndex;
			}
		}

		return -1;
	}

	public static void main(String args[]){
		if(bmSearch("On a dark desert highway, cool wind in my hair", "wind") == -1){
			System.out.println("見つかりませんでした");
		}else{
			System.out.println("見つかりました");
		}
	}
}
