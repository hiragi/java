// 2文木のデータ追加・サーチ・削除
import java.io.BufferedReader;

class List64{
	private static class aTreeNode{
		public int value;
		public aTreeNode left;
		public aTreeNode right;
	}

	private static aTreeNode treeRoot = null;

	private static aTreeNode createNewNode(int num){
		aTreeNode newTree = new aTreeNode();
		newTree.value = num;
		newTree.left = null;
		newTree.right = null;
		return newTree;
	}

	private static void insertTree(int num, aTreeNode node){
		// 1つも挿入されていない場合
		if(node == null){
			treeRoot = createNewNode(num);
			return;
		}
		if(node.value > num){
			// numが現在のnodeよりも小さい場合
			if(node.left != null){
				insertTree(num, node.left);
			}else{
				// 左側に追加
				node.left = createNewNode(num);
			}
		}else{
			// numが現在のnodeの値以上の場合
			if(node.right != null){
				insertTree(num, node.right);
			}else{
				// 右側に追加
				node.right = createNewNode(num);
			}
		}
		return;
	}

	private static aTreeNode findValue(aTreeNode node, int val){
		if(node.value > val){
			// 自分より小さい値ならば、左側
			if(node.left == null){
				// もし左側になければ、valはない
				return null;
			}
			return findValue(node.left, val);
		}
		if(node.value < val){
			// 自分より大きい値ならば、右側
			if(node.right == null){
				// もし右側になければ、valはない
				return null;
			}
			return findValue(node.right, val);
		}
		return node;
	}

	private static boolean deleteTree(int val){
		aTreeNode node = treeRoot;
		aTreeNode parentNode = null;
		int direction = 0;
		// while文で削除すべき対象をみつける(find valueと同じ)
		while(node != null && node.value != val){
			if(node.value > val){
				parentNode = node;
				node = node.left;
				direction = -1;
			}else{
				parentNode = node;
				node = node.right;
				direction = 1;
			}
		}
		if(node == null){
			return false;
		}
		if(node.left == null || node.right == null){
			// 左か右、どちらかがNULLであった場合
			if(node.left == null){
				// 親のポインタを変更する
				if(direction == -1){
					parentNode.left = node.right;	
				}else if(direction == 1){
					parentNode.right = node.right;
				}else if(direction == 0){
					treeRoot = node.right;
				}
			}else{
				// 親のポインタを変更する
				if(direction == -1){
					parentNode.left = node.left;
				}else if(direction == 1){
					parentNode.right = node.left;
				}else if(direction == 0){
					treeRoot = node.left;
				}
			}
		}else{
			// 両者ともNULLでなかったばあい
			// nodeの左側の、もっとも大きな値(もっとも右側の値)を取得する
			aTreeNode leftBiggest = node.left;
			parentNode = node;
			direction = -1;
			while(leftBiggest.right != null){
				parentNode = leftBiggest;
				leftBiggest = leftBiggest.right;
				direction = 1;
			}

			// leftBiggestの値をnodeに代入し、
			// leftBiggestは左側の枝をいれる
			node.value = leftBiggest.value;
			if(direction == -1){
				parentNode.left = leftBiggest.left;
			}else{
				parentNode.right = leftBiggest.left;
			}
		}
		return true;
	}

	private static void printTree(int depth, aTreeNode node){
		if(node == null){
			return;
		}
		printTree(depth+1, node.left);
		for(int i=0; i<depth; i++){
			System.out.print("  ");
		}
		System.out.println(node.value);
		printTree(depth+1, node.right);
	}

	public static void main(String args[]){
		java.util.Random rand = new java.util.Random();
		for(int i=0; i<10; i++){
			insertTree(rand.nextInt(99)+1, treeRoot);
		}
		int action;
		do{
			printTree(0, treeRoot);
			System.out.print("実行する操作のタイプを入力してください\n1:追加\t2:検索\t3:削除\t0:終了>");
			action = intReader();
			switch(action){
				case 1:
					System.out.print("1-100の範囲で、追加する数字を入力せよ\n");
					int i = intReader();
					if(i < 1 || i > 100){
						continue;
					}
					insertTree(i, treeRoot);
					break;
				case 2:
					System.out.print("検索する数字を入力してください");
					i = intReader();
					if(findValue(treeRoot, i) != null){
						System.out.println(i + "を発見しました");
					}else{
						System.out.println(i + "は見つかりませんでした");
					}
					break;
				case 3:
					System.out.print("削除する数字を入力してください");
					i = intReader();
					if(deleteTree(i)){
						System.out.println(i + "を削除しました");
					}else{
						System.out.println(i + "は見つかりませんでした");
					}
					break;
			}
		}while(action != 0);
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
