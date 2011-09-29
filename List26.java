// 蔵書検索プログラム２
import java.util.Arrays;
import java.io.BufferedReader;

class ComparableBook implements Comparable{
	public final String title;
	public final String author;
	public final int bookID;
	public final boolean available;

	ComparableBook(String title, String author, int bookID, boolean available){
		this.title = title;
		this.author = author;
		this.bookID = bookID;
		this.available = available;
	}

	ComparableBook(int bookID){
		title = author = null;
		this.bookID = bookID;
		available = false;
	}

	public int compareTo(Object o){
		return bookID - ((ComparableBook) o).bookID;
	}
}

class List26{
	private static ComparableBook[] bookArray;

	private static void initData(){
		bookArray = new ComparableBook[5];

		bookArray[0] = new ComparableBook("book0", "author0", 1000, true);
		bookArray[1] = new ComparableBook("book1", "author1", 502, false);
		bookArray[2] = new ComparableBook("book2", "author2", 731, false);
		bookArray[3] = new ComparableBook("book3", "author3", 628, true);
		bookArray[4] = new ComparableBook("book4", "author4", 1, true);
	}

	public static void main(String args[]){
		initData();
		Arrays.sort(bookArray);

		while(true){
			System.out.println("検索する本の番号を入力してください(0:END):");
			int key = intReader();
			if(key == 0){
				break;
			}

			// 検索して結果を表示
			int position = Arrays.binarySearch(bookArray, new ComparableBook(key));

			if(position >= 0){
				System.out.println("次の本が見つかりました");
				System.out.println("[タイトル]" + bookArray[position].title);
				System.out.println("[ちょしゃ]" + bookArray[position].author);
				System.out.println("[管理番号]" + bookArray[position].bookID);
				if(bookArray[position].available){
					System.out.println("この本は貸し出し中です");
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
			System.err.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
