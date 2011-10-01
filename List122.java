// 4つの数からすべての数式を作りだすプログラム
class List122{
	// 与えられた数
	private static final String number = "1234";
	private static StringBuffer createdNum = new StringBuffer();

	static boolean issued[] = {false, false, false, false};

	private static void makeRPN(int num, int exp){
		if(num+exp == 7){
			// 全体で7文字あれば表示
			System.out.println(createdNum);
			return;
		}else{
			if(num-exp >= 2){
				// 数字が演算子より2つ以上多ければ、
				// 演算子をいれてもよい
				int n = createdNum.length();
				createdNum.append('+');
				makeRPN(num, exp+1);
				createdNum.deleteCharAt(n);
				createdNum.append('-');
				makeRPN(num, exp+1);
				createdNum.deleteCharAt(n);
				createdNum.append('*');
				makeRPN(num, exp+1);
				createdNum.deleteCharAt(n);
				createdNum.append('/');
				makeRPN(num, exp+1);
				createdNum.deleteCharAt(n);
			}
			if(num <= 3){
				// 数が3つ以下であれば、数をいれてもよい
				for(int i=0; i<4; i++){
					if(!issued[i]){
						issued[i] = true;
						createdNum.append(number.charAt(i));
						makeRPN(num+1, exp);
						createdNum.deleteCharAt(createdNum.length()-1);
						issued[i] = false;
					}
				}
			}
		}
	}

	public static void main(String args[]){
		makeRPN(0,0);
	}
}
