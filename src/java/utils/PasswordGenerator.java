package utils;


import java.security.NoSuchAlgorithmException;
import utils.Crypto;

/**
 *
 * @author ggarvanese
 */
public class PasswordGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        
        /// Type your new New Password to create
        String[] pwd = Crypto.createPassword("1234");
        
        System.out.println();
        System.out.println("HASH PASSWORD [0] : " + pwd[0]);
        System.out.println("SALT [1] : " + pwd[1]);
        
        System.out.println("\n********************************\n");
        
        //// Verify passwd
        String passwdToVerify = "1234";
        String storedSalt = "pdiuvrirgi56aalkrbgapku3un";
        String storedHash = "99E1DB01DF575E30761CB49F1F625D71F465B069243062352868419A23B9201F3C61E9C1470AD88BE8329C25CB63BA597BB3D773059C88C0CCBB4D56691F21E5";
        
        String hashedInput = Crypto.hashPassword(storedSalt, passwdToVerify);
        boolean result = hashedInput.equalsIgnoreCase(storedHash);
        System.out.println("Does my passwd (" + passwdToVerify + ") & salt match the hashed passwd ? ---> " + result);
        System.out.println();
        
        
    }
}
