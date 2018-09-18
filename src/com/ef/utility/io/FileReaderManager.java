package com.ef.utility.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderManager {

    public static List<String> readLogFile(String filePath){
        List<String> logLines=new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                logLines.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logLines;
    }
}
