// 単純な文字列検索
class List91{
	// 見つかった文字数を返す。見つからなければ-1
	private static int simpleSearch(String text, String pattern){
		label:
		for(int n=0; n<text.length(); n++){
			// 確認のための表示
			System.err.print("	本文:" + text + "\nパターン:");
			for(int i=0; i<n; i++){
				System.err.print("  ");
			}
			System.err.println(pattern);

			for(int i=0; i<pattern.length(); i++){
				if(pattern.charAt(i) != text.charAt(n+i)){
					// 一致しなかったので、最初のループへ
					continue label;
				}
			}
			// 一致した
			return n;
		}
		// 最終的に一致しなかった
		return -1;
	}

	public static void main(String args[]){
		if(simpleSearch("TeamSwift", "if") == -1){
			System.out.println("Not Found");
		}else{
			System.out.println("見つかりました");
		}
	}
}
