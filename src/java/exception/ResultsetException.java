package exception;

import java.sql.SQLException;

/**
 *
 * @author ggarvanese
 */


public class ResultsetException extends SQLException{

    public ResultsetException() {
    }

    public ResultsetException(String reason) {
        super(reason);
    }

}
