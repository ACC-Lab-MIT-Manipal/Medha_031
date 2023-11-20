#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}


long long fastExponentiation(long long base, long long exponent, long long modulus) {
    long long result = 1;

    base %= modulus;

    while (exponent > 0) {
        if (exponent % 2 == 1)
            result = (result * base) % modulus;

        base = (base * base) % modulus;
        exponent /= 2;
    }

    return result;
}


int findModularInverse(int a, int m) {
   for (int x = 1; x < m; x++) {
        if ((a * x) % m == 1) {
            return x;
        }
    }
    return -1;
}

int main() {
    long long p, e1,e2, d;


     printf("Enter the prime number (p): ");
    scanf("%lld", &p);

       printf("Enter the private key (d): ");
    scanf("%lld", &d);



    for (e1 = 2; e1 < p; e1++) {
        if (gcd(e1, p) == 1)
            break;
    }


    e2 = fastExponentiation(e1,d, p);

    // Display public and private keys
    printf("\nPublic Key (e1, e2, p): (%lld, %lld , %lld)\n", e1, e2,p);
    printf("\nPrivate Key (d): (%lld)\n", d);

    // Encryption
    int plaintext;
    printf("\nEnter plaintext to be encrypted: ");
    scanf("%d", &plaintext);

    long long c1, c2;
    int r;
    for (r = 2; r < p; r++) {
        if (gcd(r, p) == 1)
            break;
    }

    printf("\nrandom no: %d\n", r);

    c1 = fastExponentiation(e1,r, p);
    c2 = ( plaintext * fastExponentiation(e2,r, p) ) % p;

    printf("\nCiphertexts (C1,C2): (%lld, %lld)\n", c1,c2);

    // Decryption
    findModularInverse(fastExponentiation(c1, d, p),p);
    long long decryptedText = (c2 * findModularInverse(fastExponentiation(c1, d, p),p)) % p;
    printf("\nDecrypted Text: %lld\n", decryptedText);

    return 0;
}
