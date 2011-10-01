// テンパズル
class List123{
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

	private static Bunsuu stack[] = new Bunsuu[4];
	private static int stackTop = 0;
	private static StringBuffer createdRPN;
	private static StringBuffer createdNum = new StringBuffer();

	private static void stackPush(Bunsuu val){
		stack[stackTop++] = val;
	}

	private static Bunsuu stackPop(){
		return stack[--stackTop];
	}

	private static boolean solve(){
		int c = 0;
		stackTop = 0;
		while(c != createdRPN.length()){
			char ch = createdRPN.charAt(c);
			if(Character.isDigit(ch)){
				// 数字だった場合は、
				// その数字をそのままスタックに入れる
				stackPush(new Bunsuu(1, Character.digit(ch, 10)));
			}else{
				// 数字でなかった場合は、
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
						if(st2.bunbo * st1.bunsi == 0){
							// 0で除算
							return false;
						}
						stackPush(new Bunsuu(st2.bunbo * st1.bunsi, st2.bunsi * st1.bunbo));
						break;
				}
			}
			c++;
		}
		Bunsuu st1 = stackPop();
		if(st1.bunbo * 10 == st1.bunsi){
			return true;
		}
		return false;
	}

	static boolean issued[] = {false, false, false, false};

	private static boolean makeRPN(int num, int exp){
		if(num+exp == 7){
			// 全体で7文字あれば、それを計算
			if(solve()){
				// 計算結果が10になれば、それ以後の再帰をとめる
				return true;
			}
			return false;
		}else{
			if(num-exp >= 2){
				// 数字が演算子より2つ以上多ければ、
				// 演算子をいれてもよい
				int n = createdRPN.length();
				createdRPN.append('+');
				if(makeRPN(num, exp+1)){
					return true;
				}
				createdRPN.deleteCharAt(n);
				createdRPN.append('-');
				if(makeRPN(num, exp+1)){
					return true;
				}
				createdRPN.deleteCharAt(n);
				createdRPN.append('*');
				if(makeRPN(num, exp+1)){
					return true;
				}
				createdRPN.deleteCharAt(n);
				createdRPN.append('/');
				if(makeRPN(num, exp+1)){
					return true;
				}
				createdRPN.deleteCharAt(n);
			}
			if(num <= 3){
				// 数が3対かであれば、数をいれてもよい
				for(int i=0; i<4; i++){
					if(!issued[i]){
						issued[i] = true;
						createdRPN.append(createdNum.charAt(i));
						if(makeRPN(num+1, exp)){
							issued[i] = false;
							return true;
						}
						createdRPN.deleteCharAt(createdRPN.length()-1);
						issued[i] = false;
					}
				}
			}
		}
		return false;
	}

	private static void makeNum(int keta, int num){
		if(keta == 4){
			createdRPN = new StringBuffer();
			// 桁が4桁になったらRPNを作成する
			if(makeRPN(0,0)){
				System.out.println(createdNum + ":" + createdRPN);
			}else{
				System.out.println(createdNum + ":解けません");
			}
			return;
		}
		for(int i=num; i<=9; i++){
			createdNum.append(i);
			makeNum(keta+1, i);
			createdNum.deleteCharAt(keta);
		}
	}

	public static void main(String args[]){
		makeNum(0,0);
	}
}


