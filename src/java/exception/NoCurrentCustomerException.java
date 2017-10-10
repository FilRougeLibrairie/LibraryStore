package exception;

/**
 *
 * @author ggarvanese
 */


public class NoCurrentCustomerException extends NullPointerException {
    
    public NoCurrentCustomerException() {
    }
    
    public NoCurrentCustomerException(String s) {
        super(s);
    }

    
}
