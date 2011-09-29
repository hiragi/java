// スタックによる式の計算
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class StackOverflowException extends RuntimeException{
	/* なぜList42と違ってRuntimeExceptionを継承しているのかというと、
	 * 基本的にException と RuntimeExceptionは、
	 * (Exception)
	 * 設計の範囲内のエラーであり、あらかじめ発生が予想されている
	 * (RuntimeException)
	 * バグなどに起因するエラーであり、回復できるかどうか不明
	 * となっています。
	 * 今回は Exception を使用するのがよいか、
	 * RuntimeException を使用するのがよいか
	 * 判断が難しいところですが、
	 * Exception のほうがただしいかもしれません。
	 *
	 * なお、Exceptionの場合はList42のように必ず関数にthrowsをつけ、
	 * 必ずcatchしなければなりませんが、RuntimeExceptionの場合は
	 * このリストのように必ずしもその必要はありません
	 */
}

class StackUnderflowException extends RuntimeException{
}

class DoubleStack{
	DoubleStack(int stackMaxSize){
		this.stackMaxSize = stackMaxSize;
		stack = new double[stackMaxSize];
	}

	private final int stackMaxSize;
	private int stackTop = 0;
	private double stack[];

	public void push(double val){
		if(stackTop == stackMaxSize){
			throw new StackOverflowException();
		}else{
			stack[stackTop] = val;
			++stackTop;
		}
	}

	public double pop(){
		if(stackTop == 0){
			throw new StackUnderflowException();
		}else{
			--stackTop;
			return stack[stackTop];
		}
	}

	public int size(){
		return stackTop;
	}

	public double doubleAt(int i){
		return stack[i];
	}

	public boolean isEmpty(){
		return (stackTop == 0);
	}
}

class List43{
	public static void main(String args[]){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DoubleStack stack = new DoubleStack(10);

		LOOP:
		while(true){
			System.out.println("現在のスタック(" + stack.size() + "):");
			for(int i=0; i<stack.size(); i++){
				System.out.println(stack.doubleAt(i));
			}
			System.out.println("\n>");
			try{
				String buf = reader.readLine();
				double cal1, cal2;
				switch(buf.charAt(0)){
					case '+':
						cal1 = stack.pop();
						cal2 = stack.pop();
						stack.push(cal2+cal1);
						break;
					case '-':
						cal1 = stack.pop();
						cal2 = stack.pop();
						stack.push(cal2-cal1);
						break;
					case '*':
						cal1 = stack.pop();
						cal2 = stack.pop();
						stack.push(cal2*cal1);
						break;
					case '/':
						cal1 = stack.pop();
						cal2 = stack.pop();
						stack.push(cal2/cal1);
						break;
					case '=':
						break LOOP;
					default:
						stack.push(Integer.parseInt(buf));
						break;
				}
			}catch(IOException e){
				System.err.println("IOException");
				return;
			}catch(NumberFormatException e){
				System.err.println("判断できない文字が入力されました");
				return;
			}
		}
		System.out.println("計算結果は " + stack.pop() + "です");
		if(!stack.isEmpty()){
			System.err.println("スタックにまだ数が残っています");
		}
	}
}
