package com.sndi.report;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
public class OracleConnUtils {
 
    public static Connection getOracleConnection()
            throws ClassNotFoundException, SQLException {
    	 
         
        return getOracleConnection();
    }
 
    public static Connection getConnection() throws ClassNotFoundException,
            SQLException, NamingException {
 
    	InitialContext initialContext = new InitialContext();
    	DataSource dataSource = (DataSource)initialContext.lookup("java:/jdbc/sigteDS");
    	Connection conn = dataSource.getConnection();
        return conn;
    }
}
