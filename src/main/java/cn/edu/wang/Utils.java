package cn.edu.wang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by wangdechang on 2017/5/25.
 */
public class Utils {
    public static void write(String data,boolean newLine){
        try{
            File file =new File("result.txt");

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
