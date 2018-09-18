package com.ef.utility.helper;

import com.ef.exceptions.DatabaseConnectionException;
import com.ef.objects.LogLine;
import com.ef.utility.io.DbConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbOperationsHelper {

    private static final String INSERT_LOG_QUERY = "INSERT INTO APP_ACCESS_LOGS ( IP,REQUEST,USER_AGENT,LOG_DATE,STATUS) VALUES(?,?, ?, ?, ?)";
    private static final String INSERT_BLOCKED_QUERY = "INSERT INTO BLOCKED_APP_ACCESS_LOGS ( IP,BLOCK_REASON) VALUES(?,?)";
    private static final String IP_SEARCH_QUERY = "SELECT ( IP,REQUEST,USER_AGENT,LOG_DATE,STATUS) FROM APP_ACCESS_LOG WHERE IP=?";
    private static final String THRESOLD_SEARCH_QUERY = "SELECT IP,COUNT(1) FROM APP_ACCESS_LOG  WHERE LOG_DATE BETWEEN ? and ? GROUP BY IP HAVING COUNT(1)> ?";


    public static void insertLog(LogLine log) throws DatabaseConnectionException {
        executeInsert(INSERT_LOG_QUERY, log.getIp(), log.getRquest(), log.getUserAgent(), log.getDate(), log.getStatus());
    }

    public static void insertBlocked(String ip, String blockReason) throws DatabaseConnectionException {
        executeInsert(INSERT_BLOCKED_QUERY, ip, blockReason);
    }

    public static ArrayList<String> searhIpAdressesOverThresold(int thresold, String fromDate, String toDate) throws DatabaseConnectionException {
        ArrayList<String> ipAdresses = new ArrayList<>();
        Connection connection = DbConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnectionManager.createPreparedStatement(connection, THRESOLD_SEARCH_QUERY, fromDate, toDate, thresold);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String ipAdress = rs.getString("IP");
                ipAdresses.add(ipAdress);

            }
            DbConnectionManager.closePreparedStatement(preparedStatement);
        } catch (SQLException se) {
            //TODO DO STH
        } finally {
            closeConnections(connection, preparedStatement);

        }
        return ipAdresses;
    }

    public static ArrayList<LogLine> getLogsWithIp(String ip) throws DatabaseConnectionException {
        ArrayList<LogLine> logLines = new ArrayList<>();
        Connection connection = DbConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnectionManager.createPreparedStatement(connection, IP_SEARCH_QUERY, ip);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String ipAdress = rs.getString("IP");
                String request = rs.getString("REQUEST");
                String agent = rs.getString("USER_AGENT");
                String logDate = rs.getString("LOG_DATE");
                String status = rs.getString("STATUS");
                LogLine logLine = new LogLine(logDate, ipAdress, request, status, agent);
                logLines.add(logLine);

            }
            DbConnectionManager.closePreparedStatement(preparedStatement);
        } catch (SQLException se) {
            //TODO DO STH
        } finally {
            closeConnections(connection, preparedStatement);

        }
        return logLines;
    }

    private static void closeConnections(Connection connection, PreparedStatement preparedStatement) {
        try {
            DbConnectionManager.closePreparedStatement(preparedStatement);
        } catch (Exception e) {
        }

        try {
            DbConnectionManager.releaseConnection(connection);
        } catch (Exception e) {
        }
    }

    private static void executeInsert(String query, Object... args) throws DatabaseConnectionException {
        Connection connection = DbConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnectionManager.createPreparedStatement(connection, query, args);
            preparedStatement.executeUpdate();
            DbConnectionManager.closePreparedStatement(preparedStatement);
        } catch (SQLException se) {
            //TODO DO STH
        } finally {
            closeConnections(connection, preparedStatement);

        }
    }

}
