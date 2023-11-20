import java.util.*;
import java.math.BigInteger;
public class M{
	public static int gcd(int a,int b) {
		if(a==0)
			return b;
		return gcd(b%a,a);
    }
	public static int modinverse(int a,int q) {
		for(int i=0;i<q;i++) {
			if((a*i)%q==1)
				return i;
		}
		return -1;
	}
	public static int fast_expo(int a,int pow,int q) {
		int y=1;
		while(pow>0) {
			int last_bit=(pow&1);
			if(last_bit==0) {
				a=(a*a)%q;
			}else {
				y=(y*a)%q;
				a=(a*a)%q;
			}
			pow=pow>>1;
		}
		return y;
	}
	public static void rsa(int p,int q,int e) {
		int n=p*q;
		int tot=(p-1)*(q-1);
		int d=modinverse(e,tot);
		if(d==-1)
			System.out.println("Inverse desnot exist");
		System.out.println("d:"+d);
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the msg:");
		int msg=s.nextInt();
		int c=fast_expo(msg,e,n)%n;
		System.out.println("Encrypted msg:"+c);
		int decrypt=fast_expo(c,d,n)%n;
		System.out.println("Decrypted msg:"+decrypt);
		
	}
	public static void main(String[] args) {
		rsa(3,11,7);
	}
}
	
	
