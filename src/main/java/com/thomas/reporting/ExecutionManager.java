package com.thomas.reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutionManager {

    private static String executionFolder;
    public static String  timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                                            .format(new Date());

    public static String getExecutionFolder(){
        if(executionFolder == null){
            executionFolder = System.getProperty("user.dir")
                    + "\\reports\\" + timeStamp;
            new File(executionFolder).mkdirs();
        }
        return executionFolder;
    }
}
