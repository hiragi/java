// 効率的な自己組織化探索
import java.io.BufferedReader;

class ListNodeForList36{
	ListNodeForList36(int data){
		this.data = data;
	}

	// 次の要素
	public ListNodeForList36 next;
	// この要素が持っているデータ
	public final int data;
}

class List36{
	public static void main(String args[]){
		ListNodeForList36 firstNode = null, lastNode = null;

		int buf;
		do{
			System.out.println("整数を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				// 新しいノードを作成
				ListNodeForList36 newNode = new ListNodeForList36(buf);
				newNode.next = null;
				if(lastNode != null){
					// すでにあるリストの末尾に新しいノードをつなげる
					lastNode.next = newNode;
					lastNode = newNode;
				}else{
					// これが最初の要素だった
					firstNode = lastNode = newNode;
				}
			}
		}while(buf != 0);

		do{
			System.out.println("現在の並び：");
			for(ListNodeForList36 thisNode=firstNode; thisNode != null; thisNode=thisNode.next){
				System.out.println(thisNode.data);
			}

			System.out.println("検索する値を入力してください");
			buf = intReader();
			if(buf != 0){
				ListNodeForList36 thisNode, tempNode = null;
				// 最初に入力した値のなかから検索し、見つかったら削除
				for(thisNode=firstNode; thisNode != null; tempNode=thisNode, thisNode=thisNode.next){
					if(thisNode.data == buf){
						System.out.println("入力された値のなかに" + buf + "が見つかりました");
						if(thisNode != firstNode){
							// 見つかったノードを先頭にもってくる
							tempNode.next = thisNode.next;
							if(lastNode == thisNode){
								lastNode = tempNode;
							}
							thisNode.next = firstNode;
							firstNode = thisNode;
						}
						break;
					}
				}
				if(thisNode == null){
					System.out.println(buf + "は入力されていません");
				}
			}
		}while(buf != 0);
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
