// 配列からあふれるときに配列のサイズを大きくする
import java.io.BufferedReader;

class List33{
	public static void main(String args[]){
		int array[];
		int size = 10;
		array = new int[size];

		int count = 0;
		int buf;
		do{
			System.out.println("整数を入力してください(0:END):");
			buf = intReader();
			if(buf != 0){
				array[count] = buf;
				++count;
				if(count == size){
					int temp[] = new int[size*2];
					System.arraycopy(array, 0, temp, 0, size);
					array = temp;
					size *= 2;
				}
			}
		}while(buf != 0);

		// 合計値を算出
		int sum = 0;
		System.out.println("入力されたのは以下");
		for(int i=0; i<count; i++){
			System.out.println(array[i]);
			sum += array[i];
		}

		System.out.println("SUM:" + sum);
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
