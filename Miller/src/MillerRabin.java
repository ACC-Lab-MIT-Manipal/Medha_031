
/*public class MillerRabin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}*/

/*import java.util.Random;

public class MillerRabin {

    // Miller-Rabin primality test
    public static boolean isPrime(long n, int k) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        // Write n as 2^r * d + 1
        long r = 0;
        long d = n - 1;
        while ((d & 1) == 0) {
            r++;
            d >>= 1;
        }

        // Witness loop
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + random.nextLong() % (n - 3); // Random integer between 2 and n-2
            long x = modPow(a, d, n);

            if (x == 1 || x == n - 1)
                continue;

            for (int j = 0; j < r - 1; j++) {
                x = modPow(x, 2, n);
                if (x == n - 1)
                    break;
            }

            if (x != n - 1)
                return false;
        }

        return true;
    }

    // Modular exponentiation: (a^b) % mod
    public static long modPow(long a, long b, long mod) {
        long result = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1) // If b is odd, multiply a with result
                result = (result * a) % mod;
            b >>= 1; // b = b / 2
            a = (a * a) % mod;
        }

        return result;
    }

    public static void main(String[] args) {
        long number = 101; // Change this number to the one you want to test
        int iterations = 5; // Number of iterations (witnesses)

        if (isPrime(number, iterations))
            System.out.println(number + " is probably prime.");
        else
            System.out.println(number + " is not prime.");
    }
}*/





/*import java.util.Random;
import java.util.Scanner;

public class MillerRabin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check if it's prime: ");
        long n = scanner.nextLong();

        if (isPrime(n, 5)) {  // You can adjust the number of iterations as needed
            System.out.println(n + " is likely prime.");
        } else {
            System.out.println(n + " is composite.");
        }

        scanner.close();
    }

    // Miller-Rabin primality test
    public static boolean isPrime(long n, int k) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        long r = 0;
        long d = n - 1;

        while (d % 2 == 0) {
            d /= 2;
            r++;
        }

        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + rand.nextLong() % (n - 2);
            if (!millerRabinTest(a, d, n, r)) {
                return false;
            }
        }

        return true;
    }

    // Miller-Rabin test for a specific base
    public static boolean millerRabinTest(long a, long d, long n, long r) {
        long x = modPow(a, d, n);
        if (x == 1 || x == n - 1) {
            return true;
        }

        for (long i = 0; i < r - 1; i++) {
            x = modPow(x, 2, n);
            if (x == n - 1) {
                return true;
            }
        }

        return false;
    }

    // Modular exponentiation
    public static long modPow(long base, long exponent, long modulus) {
        long result = 1;
        base %= modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
    }
} */





import java.util.Random;
import java.util.Scanner;

public class MillerRabin{

    // Miller-Rabin primality test
    public static boolean isPrime(long n, int k) {
        if (n <= 1 || n == 4) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        // Find d such that n-1 = 2^r * d
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        // Witness loop
        for (int i = 0; i < k; i++) {
            if (!witness(n, d)) {
                return false;
            }
        }

        return true;
    }

    // Helper function to perform the Miller-Rabin test
    private static boolean witness(long n, long d) {
        Random rand = new Random();
        long a = 2 + rand.nextLong() % (n - 4);
        long x = power(a, d, n);

        if (x == 1 || x == n - 1) {
            return true;
        }

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1) {
                return false;
            }
            if (x == n - 1) {
                return true;
            }
        }

        return false;
    }

    // Helper function to calculate (a^b) % c efficiently
    private static long power(long a, long b, long c) {
        long result = 1;
        a = a % c;

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % c;
            }
            b /= 2;
            a = (a * a) % c;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check for primality: ");
        long number = scanner.nextLong();
        System.out.print("Enter the number of iterations (k): ");
        int k = scanner.nextInt();
        scanner.close();

        if (isPrime(number, k)) {
            System.out.println(number + " is likely a prime number.");
        } else {
            System.out.println(number + " is not prime.");
        }
    }
}



