package exception;

import java.security.GeneralSecurityException;

/**
 *
 * @author ggarvanese
 */

public class CryptoException extends GeneralSecurityException {

    public CryptoException() {
    }

    public CryptoException(String message) {
        super(message);
    }
    
    
    
}
