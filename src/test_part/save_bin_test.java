package test_part;

import java.io.*;
/**
 * Created by multiangle on 2016/8/2.
 */
public class save_bin_test {
    public static void main(String[] args){

        File file = new File(".\\test.dat") ; // 建立文件
        try{
            TestClass tc = new TestClass(5,"akldsj") ;
            if (!file.exists()) file.createNewFile() ;
            FileOutputStream fos = new FileOutputStream(file) ;
            ObjectOutputStream oos = new ObjectOutputStream(fos) ;
            oos.writeObject(tc);
            System.out.println("文件已存入");
            oos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            FileInputStream fis = new FileInputStream(file) ;
            ObjectInputStream ois = new ObjectInputStream(fis) ;
            TestClass tc = (TestClass)ois.readObject() ;
            System.out.println(tc);
            ois.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

class TestClass implements  Serializable{
    private static final long serialVersionUID = 1L ;
    private int a ;
    private String b ;
    TestClass(int a, String b){
        this.a = a ;
        this.b = b ;
    }
    public String toString(){
        return String.valueOf(a) + b ;
    }
}