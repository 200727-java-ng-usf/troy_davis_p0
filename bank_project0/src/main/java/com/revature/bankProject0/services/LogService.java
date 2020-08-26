package com.revature.bankProject0.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogService {

    //store the file location
    private static File  logFile =new File("src/main/resources/log.txt");

    public LogService(){
        super();
    }

    /**
     * Create New Log
     * @param aStringToLog
     */
    public static void log(String aStringToLog){
        //using try with resources block
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            Date date = new Date();
            writer.write("\n"+ "[LOG] - " + date.toString() + " - " + aStringToLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log an Error with a highlighted designation
     * @param anErrToLog
     */
    public static void logErr(String anErrToLog){
        //using try with resources block
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            Date date = new Date();
            writer.write("\n"+ "[!ERROR!] - " + date.toString() + " - " + anErrToLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void log(Object o){
//        //using try with resources block
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
//            Date date = new Date();
//            writer.write("\n"+ "[OBJECT - LOG] - " + date.toString() + " - " + o.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
