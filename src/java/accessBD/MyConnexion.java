
package accessBD;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MyConnexion implements Serializable{
    
    private DataSource ds;

    public MyConnexion()throws NamingException  {
        InitialContext ct =new InitialContext();
        ds = (DataSource) ct.lookup("jdbc/library");
    }

    public Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
}
