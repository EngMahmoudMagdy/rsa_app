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
 * @author mariam
 */
public class func {
    
    
    public static BigInteger generateProbablePrime(int bitSize) {
        return BigInteger.probablePrime(bitSize, new Random());
    }

    public static BigInteger inverse(BigInteger a, BigInteger N) {
        BigInteger[] ans = EEA(a, N);

        if (ans[1].compareTo(BigInteger.ZERO) == 1) {
            return ans[1];
        } else {
            return ans[1].add(N);
        }
    }

    //EEA
    public static BigInteger[] EEA(BigInteger a, BigInteger N) {
        BigInteger[] ans = new BigInteger[3];
        BigInteger ax, yN;

        if (N.equals(BigInteger.ZERO)) {
            ans[0] = a;
            ans[1] = BigInteger.ONE;
            ans[2] = BigInteger.ZERO;
            return ans;
        }

        ans = EEA(N, a.mod(N));
        ax = ans[1];
        yN = ans[2];
        ans[1] = yN;
        BigInteger temp = a.divide(N);
        temp = yN.multiply(temp);
        ans[2] = ax.subtract(temp);
        return ans;
    }
    //Calculate GCD

    public static BigInteger gcd(BigInteger e, BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return e;
        }
        return gcd(n, e.mod(n));
    }

    public static BigInteger RSAEncrypt(BigInteger x, BigInteger d, BigInteger n) {
        return x.modPow(d, n);  //return y 
    }

    public static BigInteger RSADec(BigInteger y, BigInteger e, BigInteger n) {
        return y.modPow(e, n);
    }

    public static BigInteger CRT(BigInteger d, BigInteger p, BigInteger q, BigInteger cipher) {
        BigInteger dp, dq, qInverse, x1, x2, h, xxx;
        dp = d.mod(p.subtract(BigInteger.ONE));
        dq = d.mod(q.subtract(BigInteger.ONE));
        qInverse = inverse(q, p);
        x1 = cipher.modPow(dp, p);
        x2 = cipher.modPow(dq, q);
        h = qInverse.multiply(x1.subtract(x2)).mod(p);
        cipher = x2.add(h.multiply(q));

        return cipher;

    }
//decrypt using e
    public static BigInteger decrypt(BigInteger e, BigInteger p, BigInteger q,BigInteger m){
    return CRT(e,p,q,m);
}
       public static BigInteger sqAmul(BigInteger b ,BigInteger e ) {
		byte[] binary =e.toByteArray();
		BigInteger result=b;
		for(int i=1;i<binary.length;++i) {
			result=result.multiply(result); //square
			if(binary[i]=='1') {
				result=result.multiply(b); //multiply
			}
		}
		return result;
	}

    
}
