// 配列によるスタックの実装
class SampleDoubleStack{
	SampleDoubleStack(int stackMaxSize){
		this.stackMaxSize = stackMaxSize;
		stack = new double[stackMaxSize];
	}

	private final int stackMaxSize;
	private int stackTop = 0;
	private double stack[];

	public void push(double val){
		if(stackTop == stackMaxSize){
			// Javaの正しい姿ではないが、
			// Cのソースコードとの対比のためにこうしています。
			// 本来は、自分で新しい例外クラスを派生すべき。
			throw new RuntimeException("エラー：スタックがカラなのにpopが呼ばれました(Stack underflow)");
		}else{
			stack[stackTop] = val;
			++stackTop;
		}
	}

	public double pop(){
		if(stackTop == 0){
			throw new RuntimeException("エラー：スタックがからっぽ(Stack underflow)");
		}else{
			--stackTop;
			return stack[stackTop];
		}
	}
}
