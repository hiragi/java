// 逆ポーランド記法をとく
import java.io.BufferedReader;

class List121{
	static class Bunsuu{
		public final int bunbo;
		public final int bunsi;

		Bunsuu(int bunbo, int bunsi){
			this.bunbo = bunbo;
			this.bunsi = bunsi;
			if(bunbo == 0){
				throw(new RuntimeException("0で除算しました"));
			}
		}
	}

	private static Bunsuu stack[] = new Bunsuu[10];
	private static int stackTop = 0;

	private static void stackPush(Bunsuu val){
		stack[stackTop++] = val;
	}

	private static Bunsuu stackPop(){
		return stack[--stackTop];
	}

	public static void main(String args[]){
		System.out.print("逆ポーランド記法で数式を入力してください");
		String line = stringReader();
		int c = 0;
		while(c != line.length()){
			char ch = line.charAt(c);
			if(Character.isDigit(ch)){
				// 数字だった場合は、
				// その数字をそのままスタックに入れる
				stackPush(new Bunsuu(1, Character.digit(ch, 10)));
			}else{
				// 数字で無かった場合は、
				// スタックの上2つの数字をとってきて演算する
				Bunsuu st1 = stackPop();
				Bunsuu st2 = stackPop();
				switch(ch){
					case '+':
						stackPush(new Bunsuu(st1.bunbo * st2.bunbo, st2.bunsi * st1.bunbo + st1.bunsi * st2.bunbo));
						break;
					case '-':
						stackPush(new Bunsuu(st1.bunbo * st2.bunbo, st2.bunsi * st1.bunbo - st1.bunsi * st2.bunbo));
						break;
					case '*':
						stackPush(new Bunsuu(st1.bunbo * st2.bunbo, st2.bunsi * st1.bunsi));
						break;
					case '/':
						stackPush(new Bunsuu(st2.bunbo * st1.bunsi, st2.bunsi * st1.bunbo));
						break;
					default:
						System.err.println("無効な文字が入っています");
						return;
				}
			}
			c++;
		}
		Bunsuu st1 = stackPop();
		if(stackTop != 0){
			// スタックに数字がまだ残っている場合
			System.err.println("正しくない数式です。数字が多すぎます");
			return;
		}
		System.out.println("解は" + ((double) st1.bunsi) / ((double)st1.bunbo) + "です");
	}

	private static String stringReader(){
		try{
			BufferedReader read = new BufferedReader(new java.io.InputStreamReader(System.in));
			return read.readLine();
		}catch(java.io.IOException e){
			System.err.println("IO exception");
			return "";
		}
	}
}
