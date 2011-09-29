// 方程式の解を求めるプログラム
class List85{
	public static double func(double x){
		return x*x*x*x*x-10.0*x*x*x*x+25.0*x*x*x+40.0*x*x+200*x-500;
	}

	public static double binarySearch(){
		double epsilon = 0.00001;

		double left = 1.0, right = 3.0;

		while(Math.abs(right-left) > epsilon && Math.abs(func(left)) > epsilon){
			double mid = (left+right) / 2.0;
			if(func(left) * func(mid) >= 0.0){
				left = mid;
			}else{
				right = mid;
			}
		}
		return left;
	}

	public static void main(String args[]){
		double d = binarySearch();
		System.out.println("方程式の解は" + d + "そのときのfunc(x)は" + func(d) + "です");
	}
}
