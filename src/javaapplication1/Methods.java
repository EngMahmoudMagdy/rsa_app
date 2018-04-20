package javaapplication1;

import java.math.BigInteger;

public class Methods {
    
    
    public static BigInteger inverse(BigInteger a, BigInteger N) {
        BigInteger[] ans = EEA(a, N);

        if (ans[1].compareTo(BigInteger.ZERO) == 1) {
            return ans[1];
        } else {
            return ans[1].add(N);
        }
    }

    //EEA
    public static BigInteger[] EEA(BigInteger r0, BigInteger r1) {
        BigInteger[] ans = new BigInteger[3];
        BigInteger ax, yN;

        if (r1.equals(BigInteger.ZERO)) {
            
            //s
            ans[1] = BigInteger.ONE;
            //t
            ans[2] = BigInteger.ZERO;
            return ans;
        }

        ans = EEA(r1, r0.mod(r1));
        ax = ans[1];
        yN = ans[2];
        System.out.println(yN);
        ans[1] = yN;
        BigInteger temp = r0.divide(r1);
        temp = yN.multiply(temp);
        ans[2] = ax.subtract(temp);
        return ans;
    }
    //Calculate GCD

    public static BigInteger gcd(BigInteger e, BigInteger phi) {
        if (phi.equals(BigInteger.ZERO)) {
            return e;
        }
        return gcd(phi, e.mod(phi));
    }

    public static BigInteger RSAEncrypt(BigInteger x, BigInteger d, BigInteger n) {
        return x.modPow(d, n);  //return y 
    }

    public static BigInteger RSADec(BigInteger y, BigInteger e, BigInteger n) {
        return y.modPow(e, n);
    }

    public static BigInteger CRT(BigInteger d, BigInteger p, BigInteger q, BigInteger cipher) {
        BigInteger dp, dq, cq , cp, xp, xq, h , yp , yq;
        dp = d.mod(p.subtract(BigInteger.ONE));
        dq = d.mod(q.subtract(BigInteger.ONE));
        yp = cipher.mod(p);
        yq = cipher.mod(q);
        //cq & cp :
        cp = inverse(q, p);
        cq = inverse(p, q);
        xp = yp.modPow(dp, p);
        xq = yq.modPow(dq, q);
         
        return (xp.multiply(cp).multiply(q)).add(xq.multiply(cq).multiply(p)).mod((p.multiply(q)));
    }
//decrypt using e
    public static BigInteger decrypt(BigInteger e, BigInteger p, BigInteger q,BigInteger m){
    return CRT(e,p,q,m);
}
    public static BigInteger sqrMul (BigInteger b ,BigInteger e ) {
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
