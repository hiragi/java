// 入力したいくつかの数値とその合計を出力する
import java.io.BufferedReader;

class List31{
	public static void main(String args[]){
		int count, buf, sum;
		int[] array = new int[10];
		count = 0;
		sum = 0;
		do{
			System.out.println("数値を入力してください");
			buf = intReader();
			if(buf != 0){
				array[count] = buf;
				count++;
			}
		}while(buf != 0);

		System.out.println("入力された値は以下のとおり");
		for(int i=0; i<count; i++){
			System.out.println(array[i]);
			sum += array[i];
		}

		System.out.println("合計は" + sum + "です");
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
