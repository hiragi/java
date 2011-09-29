import java.io.BufferedReader;

class List73{
	static class Wordset{
		Wordset(String english, String japanese){
			this.english = english;
			this.japanese = japanese;
		}

		public String english;
		public String japanese;
	}

	static class MyHashTable{
		public Wordset[] data;
		public int size;
	}

	private static int makeHash2(String str, int hashMax){
		int hash = 0, weight = 0;

		for(int n=0; n<str.length(); ++n, ++weight){
			if(weight>7){
				// 空率が256^7まで到達したら、一巡して再び元に戻す
				weight = 0;
			}
			// << (4*weight)は256^weightと同じ意味
			hash += (int) str.charAt(n) << (4*weight);
		}

		return hash%hashMax;
	}

	private static int reHash(MyHashTable hashTable, int firstHash){
		int hashValue = firstHash;
		// firstvalからk^2だけ後ろにある秋位置を探す
		// k > ハッシュ表サイズの半分となったら、
		// それ以降の探索は無駄
		for(int k=1; k<hashTable.size/2; k++){
			hashValue = (firstHash+k*k)%hashTable.size;
			if(hashTable.data[hashValue] == null){
				return hashValue;
			}
		}
		return -1;
	}

	private static void addDataToMap(MyHashTable hashTable, Wordset newData){
		// 英単語を元にハッシュ値を作成
		int hashValue = makeHash2(newData.english, hashTable.size);

		// もしもhashの位置がすでに埋まっていたら、再ハッシュを行う
		if(hashTable.data[hashValue] != null){
			hashValue = reHash(hashTable, hashValue);
			// 再ハッシュ結果が-1であれば、
			// 空き位置がみつからなかった(mapが満杯)
			if(hashValue == -1){
				System.out.println(newData.english + "をマップに挿入しようと試みましたが、空き位置がありませんでした");
				return;
			}
		}
		// hashvalの位置にnewdataへのポインタを格納
		hashTable.data[hashValue] = newData;
	}

	private static String getDataFromMap(MyHashTable hashTable, String key){
		// 探索を開始するハッシュ値を求める
		int hashValue = makeHash2(key, hashTable.size);
		Wordset word;
		// その位置から順番に、
		// keyと同じ値をもつデータが現れるまでたんさくを行う
		for(int k=0; k<=hashTable.size/2; k++){
			word = hashTable.data[(hashValue+k*k)%hashTable.size];
			if(word != null){
				if(key.equals(word.english)){
					return word.japanese;
				}
			}
		}

		// ハッシュ表サイズの半分に相当する回数
		// 探し続けても見つからない場合は、
		// 該当するデータがハッシュ表のなかにないことを意味する
		return null;
	}

	private static Wordset deleteDataFromMap(MyHashTable hashTable, String key){
		// 探索を開始するハッシュ値を求める
		int hashValue = makeHash2(key, hashTable.size);
		Wordset word;

		// その位置から順番に、keyと同じ値を持つデータが
		// 現れるまで探索を行う
		for(int k=0; k<=hashTable.size/2; k++){
			word = hashTable.data[(hashValue+k*k)%hashTable.size];
			if(word != null){
				if(key.equals(word.english)){
					hashTable.data[(hashValue+k*k)%hashTable.size] = null;
					// ハッシュテーブルから取り除いたデータを返す
					return word;
				}
			}
		}

		// ハッシュ表サイズの半分に相当する回数
		// 探し続けてもみつからないばあい、
		// 該当するデータがハッシュ表のなかにない
		return null;
	}

	private static void initHashTable(MyHashTable hashTable, int size){
		hashTable.data = new Wordset[size];
		for(int i=0; i<size; i++){
			hashTable.data[i] = null;
		}
		hashTable.size = size;
	}

	private static void printAllData(MyHashTable hashTable){
		for(int n=0; n<hashTable.size; n++){
			if(hashTable.data[n] != null){
				System.out.println(n + ":\t" + hashTable.data[n].english + "\t" + hashTable.data[n].japanese);
			}
		}
	}

	public static void main(String args[]){
		Wordset words[] = {
			new Wordset("dolphin", "イルカ"),
			new Wordset("beluga", "シロイルカ"),
			new Wordset("grampus", "シャチ"),
			new Wordset("medusa", "海月"),
			new Wordset("otter", "カワウソ")
		};
		MyHashTable hashTable = new MyHashTable();
		initHashTable(hashTable, 503);
		for(int n=0; n<words.length; n++){
			addDataToMap(hashTable, words[n]);
		}

		int action;
		do{
			System.out.println("1:Search 2:Delete 3:Print 0:End\n>");
			action = intReader();
			switch(action){
				case 1:
					System.out.println("検索する英単語を入力せよ");
					String key = stringReader();
					String japanese = getDataFromMap(hashTable, key);
					if(japanese != null){
						System.out.println(key + "の和訳は" + japanese + "です");
					}else{
						System.out.println(key + "がマップのなかに見つかりませんでした");
					}
					break;
				case 2:
					System.out.println("削除する英単語を入力してください");
					key = stringReader();
					Wordset wordFound = deleteDataFromMap(hashTable, key);
					if(wordFound != null){
						System.out.println(key + "をマップから削除");
					}else{
						System.out.println(key + "がマップのなかにみつからない");
					}
					break;
				case 3:
					printAllData(hashTable);
					break;
			}
		}while(action != 0);
	}

	private static String stringReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			return reader.readLine();
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return "";
		}
	}

	private static int intReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = read.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
