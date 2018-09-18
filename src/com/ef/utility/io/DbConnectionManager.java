package com.ef.utility.io;

import com.ef.config.Constants;
import com.ef.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnectionManager {

    public static Connection getConnection() throws DatabaseConnectionException {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection(Constants.DB_ACCESS_URL, Constants.DB_ACCESS_USER, Constants.DB_ACCESS_PASS);
            return connection;
        } catch (SQLException e) {
            throw new DatabaseConnectionException(Constants.DB_ACCESS_URL, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException(Constants.DB_ACCESS_URL, "com.mysql.jdbc.Driver not installed");
        }

    }

    public static PreparedStatement createPreparedStatement(Connection connection,String query,Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for(int i=0;i<args.length;i++){
            preparedStatement.setObject(i,args[i]);
        }
        return preparedStatement;
    }


    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException se2) {
        }
    }
}

