import java.io.BufferedReader;

class ListNodeForList35{
	ListNodeForList35(int data){
		this.data = data;
	}

	// 前の要素
	public ListNodeForList35 prev;
	// 次の要素
	public ListNodeForList35 next;
	// この要素がもっているデータ
	public final int data;
}

class List35{
	public static void main(String args[]){
		ListNodeForList35 firstNode = null, lastNode = null;

		int buf;
		do{
			System.out.println("整数を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				// 新しいノードを作成
				ListNodeForList35 newNode = new ListNodeForList35(buf);
				newNode.next = null;
				if(lastNode != null){
					// すでにあるリストの末尾に新しいノードをつなげる
					lastNode.next = newNode;
					newNode.prev = lastNode;
					lastNode = newNode;
				}else{
					// これが最初の要素だった
					firstNode = lastNode = newNode;
					newNode.prev = null;
				}
			}
		}while(buf != 0);

		do{
			System.out.println("検索する値を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				ListNodeForList35 thisNode;
				// 最初に入力した値のなかから検索し、みつかったら削除
				for(thisNode=firstNode; thisNode != null; thisNode=thisNode.next){
					if(thisNode.data == buf){
						System.out.println("入力された値の中に" + buf + "が見つかりました。ノードを削除します");
						
						if(thisNode.prev != null){
							thisNode.prev.next = thisNode.next;
						}else{
							firstNode = thisNode.next;
						}

						if(thisNode.next != null){
							thisNode.next.prev = thisNode.prev;
						}else{
							lastNode = thisNode.prev;
						}
						break;
					}
				}

				if(thisNode == null){
					System.out.println(buf + "は入力されていないか、あるいは削除されています");
				}
			}
		}while(buf != 0);
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
