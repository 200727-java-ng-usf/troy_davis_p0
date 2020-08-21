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
        if (logFile.exists()){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile))){
                Date date = new Date();
                writer.write("\n"+ "[LOG] - " + date.toInstant() + " " + "Initializing application");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //log in file
    public static void log(String aStringToLog){
        //using try with resources block
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            Date date = new Date();
            writer.write("\n"+ "[LOG] - " + date.toString() + " - " + aStringToLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void log(Object o){
        //using try with resources block
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            Date date = new Date();
            writer.write("\n"+ "[OBJECT - LOG] - " + date.toString() + " - " + o.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
