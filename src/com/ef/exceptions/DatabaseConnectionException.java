package com.ef.exceptions;

public class DatabaseConnectionException  extends  Exception{

    private static final String DB_CONNECTION_EXCEPTION_MESSAGE = "ERROR OCCURRED WHILE CONNECTION DATABASE WITH ADDRESS: ";
    private String dbAccessUrl;
    private String reason;

    public DatabaseConnectionException(String dbAccessUrl,String reason) {
        this.dbAccessUrl=dbAccessUrl;
        this.reason=reason;
    }

    public String getMessage(){
        return new StringBuffer(DB_CONNECTION_EXCEPTION_MESSAGE).append(dbAccessUrl).append(" Reason: ").append(reason).toString();
    }
}
