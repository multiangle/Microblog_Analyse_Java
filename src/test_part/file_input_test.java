package test_part;

import java.io.*;

/**
 * Created by multiangle on 2016/8/3.
 */
public class file_input_test {
    public static void main(String[] args){
        File f = new File("D:\\multiangle\\DataSet\\网页搜索结果评价(SogouE)\\final") ;
        try{
            FileInputStream fis = new FileInputStream(f) ;
            BufferedInputStream bis = new BufferedInputStream(fis) ;
            InputStreamReader isreader = new InputStreamReader(bis,"gbk") ;
            BufferedReader bufferedReader = new BufferedReader(isreader) ;
            String line = bufferedReader.readLine() ;
            while(line!=null){
                System.out.println(line);

                line = bufferedReader.readLine() ;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
