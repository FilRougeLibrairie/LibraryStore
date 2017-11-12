package utils;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

/**
 * The {@code Crypto} static class is used to create and verify passwords.<br>
 * A SecureRandom generated salt is added to the Password String. Final String
 * is hashed using SHA-512 algorithm
 *
 * @return String[password, salt]
 * @author ggarvanese
 */
public class Crypto {

    private final static String HASH_ALGORITHM = "SHA-512";

    public static String hashPassword(String salt, String clearPassword) throws NoSuchAlgorithmException, NoSaltException {
        String userPassword = new String(clearPassword);
        String userSalt;

        if (salt == null) {
            throw new NoSaltException("Salt missing");
        } else {
            userSalt = salt;
        }

        String concatedPassword = userSalt + userPassword;
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] hash = digest.digest(concatedPassword.getBytes(StandardCharsets.UTF_8));
        String hashStr = DatatypeConverter.printHexBinary(hash);
        return hashStr;
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    /**
     *
     * @param password
     * @return String[hashed password, salt]
     * @throws NoSuchAlgorithmException
     * @throws CryptoException
     */
    public static String[] createPassword(String password) throws Exception {
        String salt = generateSalt();
        String hash = hashPassword(salt, password);
        return new String[]{hash, salt};
    }

}
