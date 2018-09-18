package com.ef;

import com.ef.utility.StringTools;

public class Parser {

    public static void main(String[] args) {
        String filePath,startDate = null,thresold = null,duration = null;
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("--startDate")) {
                    startDate = args[i].split("=")[1];
                } else if (args[i].startsWith("--duration")) {
                    duration = args[i].split("=")[1];
                } else if (args[i].startsWith("--thresold")) {
                    thresold = args[i].split("=")[1];
                } else if (args[i].startsWith("--accesslog")) {
                    filePath = args[i].split("=")[1];
                }
            }
        }catch (Exception ex){
            System.out.println("Error occured reading parameters please use --startDate  --duration --thresold --accesslog wisely");
            return;
        }
        if(StringTools.isEmpty(startDate)){
            System.out.println("--startDate parameter is required");
            return;
        }
        if(StringTools.isEmpty(duration)){
            System.out.println("--duration parameter is required");
            return;
        }
        if(StringTools.isEmpty(thresold)){
            System.out.println("--thresold parameter is required");
            return;
        }

    }

}
