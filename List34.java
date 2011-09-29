import java.io.BufferedReader;

class ListNodeForList34{
	ListNodeForList34(int data){
		this.data = data;
	}

	// 前の要素
	public ListNodeForList34 prev;
	// 次の要素
	public ListNodeForList34 next;
	// この要素がもっているデータ
	public final int data;
}

class List34{
	public static void main(String args[]){
		ListNodeForList34 firstNode = null, lastNode = null;

		int buf;
		do{
			System.out.println("整数を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				// 新しいノードを作成
				ListNodeForList34 newNode = new ListNodeForList34(buf);
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

		// 合計値を算出
		int sum = 0;
		System.out.println("入力されたのは以下");
		for(ListNodeForList34 thisNode = firstNode; thisNode != null; thisNode = thisNode.next){
			System.out.println(thisNode.data);
			sum += thisNode.data;
		}
		System.out.println("Sum:" + sum);
	}

	private static int intReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = reader.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.err.println("IO Exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
