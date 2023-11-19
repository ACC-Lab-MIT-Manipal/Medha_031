import java.util.HashMap;

public class Pollard_discrete {
	public static int inv(int a,int n)
	{
		int neg_flag = 0; 
		if(a<0)
		{
			neg_flag = 1;
			a = Math.abs(a);
		}
		for(int i=1;i<n;i++)
		{
			if(a*i%n==1)
				if(neg_flag==0)
					return i;
				else
					return -i;
		}
		System.out.println("Inverse doesnt exist...May get wrong output..!!");
		return -1;
	}
	public static int gcd(int a,int b)
	{
		if(a==0)
			return b;
		return gcd(b%a,a);
	}
	
	public static int polard_rho(int g,int h,int p)
	{
		int x[] = new int[200];
		int a[] = new int[200];
		int b[] = new int [200];
		
		x[1] = 1;
		a[1] = 0;
		b[1] = 0;
		System.out.println("For i: "+1+"\tx: "+x[1]+"\ta: "+a[1]+"\tb: "+b[1]);
		int flag = 0, res = 0;
		for(int i=2;i<200;i++) {
			switch(x[i-1]%3)
			{
			case 0:
				x[i] = (x[i-1]*x[i-1])%p;
				a[i] = (2*a[i-1])%p;
				b[i] = (2*b[i-1])%p;
				break;
			case 1:
				x[i] = (g*x[i-1])%p;
				a[i] = (a[i-1]+1)%p;
				b[i] = (b[i-1])%p;
				break;
			case 2:
				x[i] = (h*x[i-1])%p;
				a[i] = (a[i-1])%p;
				b[i] = (b[i-1]+1)%p;
				break;		
			}
			System.out.println("For i: "+i+"\tx: "+x[i]+"\ta: "+a[i]+"\tb: "+b[i]);
			if(x[i]==x[i/2])
			{
				int num = (a[i] - a[i/2]);
				int den = (b[i/2] - b[i]);
				num = num/(gcd(num,den));
				den = den/(gcd(num,den));
				
				System.out.println("Num: "+num+" Den: "+den);		 //testing purpose printing values
				System.out.println("Inv: "+inv(Math.abs(den),p-1));  //testing purpose printing values
				
				res = num*inv(den, p-1)%(p-1);
				break;
			}
		}
		return res<0 ? (res+(p-1))%(p-1):res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("   Polard Rho for Dsicrete Logarithm   ");
		System.out.println("---------------------------------------");
		int ans = polard_rho(5, 8, 43);
		System.out.println("\nResult: "+ans);
		
	}

}
