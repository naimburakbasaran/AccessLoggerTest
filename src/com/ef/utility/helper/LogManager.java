package com.ef.utility.helper;

import com.ef.objects.LogLine;
import com.ef.utility.StringTools;

import java.util.ArrayList;

public class LogManager {

    public static void saveAndManageAccessLogs(String logFilePath,String date,String duration,String thresold){

        if(!StringTools.isEmpty(logFilePath))
        {
            ArrayList<LogLine> logLines=LogParsingHelper.readLogsFromLine(logFilePath);
            DbOperationsHelper.insertLogList(logLines);
        }

    }
}
