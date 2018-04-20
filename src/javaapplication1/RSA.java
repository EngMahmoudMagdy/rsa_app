
package javaapplication1;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author engma
 */
public class RSA {
        static  BigInteger p, q,msg , p_1, q_1, N, phi, e, d, y , d2;
        static int bitlength = 512;
        static Random r = new Random();
        static long length = 64;

    /**
     * @param args the command line arguments
     */
    public RSA() {
        //key generation

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Form g1 = new Form();
        g1.setVisible(true);
        
        //plain text generation
        msg = BigInteger.probablePrime((int) length, new Random());
        e = BigInteger.probablePrime(bitlength / 2, r);

        while (true) {
            p = BigInteger.probablePrime((int) length, new Random());
            q = BigInteger.probablePrime((int) length, new Random());

            p_1 = p.subtract(BigInteger.ONE); //p-1
            q_1 = q.subtract(BigInteger.ONE); //q-1
            N = p.multiply(q);
            phi = p_1.multiply(q_1);

            if (Methods.gcd(e, phi).equals(BigInteger.ONE)) {
                break;
            }
        }

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {  //e>0 && e <phi
            e.add(BigInteger.ONE);
        }
        d = Methods.inverse(e, phi);
        y = Methods.RSAEncrypt(msg,e,N);
        d2 = Methods.decrypt(d, p, q, y);
      
    }
    static BigInteger encryptPlain(BigInteger x )
    {
        return Methods.RSAEncrypt(x,e,N);
    }
    static String decryptPlain(BigInteger x )
    {
        return String.valueOf(Methods.decrypt(d, p, q, x));
    }
}
