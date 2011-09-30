// ナップザック問題
class List111{
	static class Product{
		public final int size;
		public final int value;

		public Product(int size, int value){
			this.size = size;
			this.value = value;
		}
	}

	private static Product[] products = {
		new Product(2,2), new Product(3,4),
		new Product(5,7), new Product(6,10),
		new Product(9,14)
	};

	public static void main(String args[]){
		// ナップザックの現時点での価値
		int napValue[] = new int[products.length+1];

		System.out.print("ナップザックの大きさ:");
		for(int i=0; i<products.length; i++){
			napValue[i] = 0;
			String num = (i+1) + " ";
			if(num.length() == 2){
				num = " " + num;
			}
			System.out.print(num);
		}
		System.out.print("\n\n");

		// 扱う品物を、1つずつ増やしていく
		for(int i=0; i<products.length; i++){
			// ナップザックの大きさがjのものに対して、
			// 品物i番を入れてみる
			for(int j=products[i].size; j<products.length+1; ++j){
				// 品物iを入れてみた場合、新しい価値はどうなるか
				int newValue = napValue[j-products[i].size] + products[i].value;
				if(newValue > napValue[j]){
					napValue[j] = newValue;
				}
			}
			System.out.print("	品物" + (i+1) + "まで使う:");
			for(int j=1; j<products.length+1; j++){
				String num = napValue[j] + " ";
				if(num.length() == 2){
					num = " " + num;
				}
				System.out.print(num);
			}
			System.out.println();
		}
	}
}
