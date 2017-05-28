package cn.edu.wang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by wangdechang on 2017/5/25.
 */
public class Utils {
    public static void write(String data,boolean newLine){
        //String data = " This content will append to the end of the file";
        try{
            File file =new File("result_realEnv.txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            if (newLine)bufferWritter.write("\r\n");
            bufferWritter.write(data);
            bufferWritter.close();

        }catch (Exception e){

        }


    }
}
