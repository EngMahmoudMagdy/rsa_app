/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author marwa
 */
public class JavaApplication1 {
    func f1 = new func();
        static  BigInteger p, q, xx , p_1, q_1, N, phi, e, d, y , d2;
        static int bitlength = 512;
        static Random r = new Random();
        static long length = 64;

    /**
     * @param args the command line arguments
     */
    public JavaApplication1() {
        //key generation

    }

    public static void main(String[] args) {
        // TODO code application logic here
        func f1 = new func();
        gui g1 = new gui();
        g1.setVisible(true);
        
        //plain text generation
        xx = f1.generateProbablePrime((int) length);
        
            e = BigInteger.probablePrime(bitlength / 2, r);

        while (true) {
            p = f1.generateProbablePrime(bitlength);
            q = f1.generateProbablePrime(bitlength);

            p_1 = p.subtract(BigInteger.ONE); //p-1
            q_1 = q.subtract(BigInteger.ONE); //q-1
            N = p.multiply(q);
            phi = p.multiply(p_1).multiply(q_1);

            if (f1.gcd(e, phi).equals(BigInteger.ONE)) {
                break;
            }
        }

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {  //e>0 && e <phi
            e.add(BigInteger.ONE);
        }
        d = f1.inverse(e, phi);
        y = f1.RSAEncrypt(xx,e,N);
        //  BigInteger y2 = encrypt(jv.e,jv.p,jv.q,jv.xx);
        d2 = f1.decrypt(d, p, q, y);
      //  BigInteger d = RSADec(y2, jv.d, jv.N);

       /* System.out.println("msg befor enc");
        System.out.println(xx);

        System.out.println("Encyrption with CRT");
        System.out.println(y);

        System.out.println("decryprion");
        System.out.println(d2);
*/
    }
    static BigInteger encryptPlain(BigInteger x )
    {
        return func.RSAEncrypt(x,e,N);
    }
    static String decryptPlain(BigInteger x )
    {
        return String.valueOf(func.decrypt(d, p, q, x));
    }
}
