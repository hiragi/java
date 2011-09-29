// 文字列の検索

import java.io.BufferedReader;

class List65{
	private static class aTreeNode{
		public String key;
		public String value;
		public aTreeNode left;
		public aTreeNode right;
	}

	private static aTreeNode treeRoot = null;

	private static aTreeNode createNewNode(String key, String value){
		aTreeNode newTree = new aTreeNode();
		newTree.value = value;
		newTree.key = key;
		newTree.left = null;
		newTree.right = null;
		return newTree;
	}

	private static void insertTree(String key, String value, aTreeNode node){
		// 1つも挿入されていない場合
		if(node == null){
			treeRoot = createNewNode(key, value);
			return;
		}
		if(node.key.compareTo(key) > 0){
			// numが現在のnodeの値よりも小さい場合
			if(node.left != null){
				insertTree(key, value, node.left);
			}else{
				// 左側に追加する
				node.left = createNewNode(key, value);
			}
		}else{
			// numが現在のnodeの値以上の場合
			if(node.right != null){
				insertTree(key, value, node.right);
			}else{
				// 左側に追加する
				node.right = createNewNode(key, value);
			}
		}
		return;
	}

	private static aTreeNode findKey(aTreeNode node, String val){
		int cmp = node.key.compareTo(val);
		if(cmp > 0){
			// 自分より小さい値ならば、左側
			if(node.left == null){
				return null;
			}
			return findKey(node.left, val);
		}
		if(cmp < 0){
			// 自分より大きい値ならば、右側
			if(node.right == null){
				return null;
			}
			return findKey(node.right, val);
		}
		return node;
	}

	private static boolean deleteTree(String val){
		aTreeNode node = treeRoot;
		aTreeNode parentNode = null;
		int direction = 0;
		int cmp;

		// while文で削除すべき対象を見つける(findValueと同じ)
		while(node != null && (cmp = node.key.compareTo(val)) != 0){
			if(cmp > 0){
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
			// 左側化右側、どちらかがNULLであった場合
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
			// 両者ともNULLでなかった場合
			// nodeの左側の、もっとも大きな値を取得する
			aTreeNode leftBiggest = node.left;
			parentNode = node;
			direction = -1;
			while(leftBiggest.right != null){
				parentNode = leftBiggest;
				leftBiggest = leftBiggest.right;
				direction = 1;
			}

			// leftBiggestの値をnodeに代入し,
			// leftBiggestは左側の枝をいれる
			node.value = leftBiggest.value;
			node.key = leftBiggest.key;
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
		System.out.println(node.key + ":" + node.value);
		printTree(depth+1, node.right);
	}

	public static void main(String args[]){
		int action;
		do{
			printTree(0, treeRoot);
			System.out.println("実行する操作のタイプを入力してください\n1:Add\t2:Search\t3:Delete\t0:END\n>");
			action = intReader();
			switch(action){
				case 1:
					System.out.println("追加する文字列(key)を入力してください:");
					String key = stringReader();
					System.out.print("keyに対応する文字列(value)を入力してください");
					String value = stringReader();
					insertTree(key, value, treeRoot);
					break;
				case 2:
					System.out.println("検索する文字列(key)を入力してください:");
					key = stringReader();
					aTreeNode found;
					if((found = findKey(treeRoot, key)) != null){
						System.out.println(key + "を発見しました。対応するvalueは" + found.value + "です");
					}else{
						System.out.println(key + "は見つかりませんでした");
					}
					break;
				case 3:
					System.out.print("削除する文字列(key)を入力してください");
					key = stringReader();
					if(deleteTree(key)){
						System.out.println(key + "を削除しました");
					}else{
						System.out.println(key + "はみつかりませんでした");
					}
					break;
			}
		}while(action != 0);
	}

	private static String stringReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			return reader.readLine();
		}catch(java.io.IOException e){
			System.err.println("IO Exception");
			return "";
		}
	}

	private static int intReader(){
		try{
			BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
			String str = reader.readLine();
			return Integer.parseInt(str);
		}catch(java.io.IOException e){
			System.out.println("IO exception");
			return 0;
		}catch(NumberFormatException e){
			return 0;
		}
	}
}
