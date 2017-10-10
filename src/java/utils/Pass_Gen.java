package utils;

import exception.CryptoException;
import java.security.NoSuchAlgorithmException;
import utils.Crypto;

/**
 *
 * @author ggarvanese
 */
public class Pass_Gen {
    public static void main(String[] args) throws NoSuchAlgorithmException, CryptoException {
        
        /// Create New Password
        String[] pwd = Crypto.createPassword("1234");
        
        System.out.println();
        System.out.println("HASH PASSWORD [0] : " + pwd[0]);
        System.out.println("SALT [1] : " + pwd[1]);
        
        System.out.println("\n********************************\n");
        
        //// Verify passwd
        String passwd = "12";
        String hash = "8516A65EF172D717D55B651F92DEF4573D41F9892D865D1F849FF1986C4521EF13BC8792FB9D0CC75FAF32CE03B59D845D6880C57219EA02DC848B5EA95CF178";
        String salt = "g58c324ce3cjsqnro6m2851oni";
        
        String result = Crypto.verifyPassword(salt, passwd);
        System.out.println("Does my passwd & salt match the hashed passwd ? " + result.equalsIgnoreCase(hash));
        System.out.println();
        
        
    }
}
