// 蔵書検索プログラム
import java.io.BufferedReader;

class Book{
	public final String title;
	public final String author;
	public final int bookID;
	public final boolean available;

	Book(String title, String author, int bookID, boolean available){
		this.title = title;
		this.author = author;
		this.bookID = bookID;
		this.available = available;
	}
}

class List25{
	private static Book[] bookArray;

	private static void sortBook(int bottom, int top, Book[] books){
		int lower, upper, div;

		if(bottom >= top){
			return;
		}

		// 適当な基準値を選択
		div = books[bottom].bookID;
		for(lower=bottom, upper=top; lower<upper;){
			while(/*lower<upper &&*/ books[lower].bookID < div){
				++lower;
			}
			while(/*lower<upper &&*/ books[upper].bookID > div){
				--upper;
			}
			if(lower < upper){
				// データ(へのポインタ)の順番を入れ替える
				Book temp = books[lower];
				books[lower] = books[upper];
				books[upper] = temp;
				++lower;
				--upper;
			}
		}
		sortBook(bottom, upper, books);
		sortBook(upper+1, top, books);
	}

	private static int searchBook(Book[] books, int key){
		int left, mid, right;

		left = 0;
		right = books.length-1;
		while(left < right){
			mid = (left+right) / 2;
			// bookIDの大小を比較 
			if(books[mid].bookID < key){
				left = mid+1;
			}else{
				right = mid;
			}
		}
		if(books[left].bookID == key){
			return left;
		}

		return -1;
	}

	public static void initData(){
		bookArray = new Book[5];

		bookArray[0] = new Book("book0", "author0", 1000, true);
		bookArray[1] = new Book("book1", "author1", 502, false);
		bookArray[2] = new Book("book2", "author2", 731, false);
		bookArray[3] = new Book("book3", "author3", 628, true);
		bookArray[4] = new Book("book4", "author4", 1, true);
	}

	public static void main(String args[]){
		initData();
		sortBook(0, bookArray.length-1, bookArray);
		while(true){
			System.out.println("検索する本の番号を入力してください(0:END):");
			int key = intReader();
			if(key == 0){
				break;
			}

			// 検索して結果を表示
			int position = searchBook(bookArray, key);
			if(position != -1){
				System.out.println("次の本が見つかりました");
				System.out.println("[タイトル]" + bookArray[position].title);
				System.out.println("[ちょしゃ]" + bookArray[position].author);
				System.out.println("[管理番号]" + bookArray[position].bookID);
				if(bookArray[position].available){
					System.out.println("この本は貸し出し可能です");
				}else{
					System.out.println("この本は貸し出し中です");
				}
			}else{
				System.out.println("お探しの本は見つかりませんでした");
			}
		}
	}

	private static int intReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = reader.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.out.println("IO Exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
