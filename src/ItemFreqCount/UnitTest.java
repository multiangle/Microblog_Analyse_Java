package ItemFreqCount;

import java.io.*;
import java.lang.reflect.Array;


import org.bson.Document ;
import java.util.*;

import org.omg.CORBA.WCharSeqHelper;
import test_part.mongo_test ;

import com.huaban.analysis.jieba.JiebaSegmenter ;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode ;
import com.huaban.analysis.jieba.SegToken;

/**
 * Created by multiangle on 2016/7/23.
 */
public class UnitTest {
    private int id ;

    public static Comparator<UnitTest> comparator = new Comparator<UnitTest>() {
        @Override
        public int compare(UnitTest o1, UnitTest o2) {
            return o1.getId()-o2.getId();
        }
    } ;

    public int getId(){
        return this.id ;
    }

    UnitTest(int id){
        this.id = id ;
    }

    public static void main(String[] args){
//        saveHongLouMeng();
        loadHongLouMeng();
    }

    public static void saveHongLouMeng(){
        WordCount wc = new WordCount() ;
        JiebaSegmenter segmenter = new JiebaSegmenter() ;

        ArrayList<Document> content = mongo_test.getBsonText() ;
        int count = 0 ;
        for (Document d:content){
            ArrayList<String> str_array = (ArrayList)d.get("text") ;
            long timestamp = (Integer)d.get("time") ;
//            System.out.println(d.get("time").getClass());
//            System.out.println(str_array.toString());
//            System.out.println(timestamp);
            for (String sentence: str_array){
                List<SegToken> res = segmenter.process(sentence,SegMode.SEARCH) ;
                for(SegToken seg:res){
                    wc.addWordWithTimestamp(seg.word, timestamp);
                }
            }
            count++ ;
            System.out.println(count);
        }
        try{
            File f = new File(".\\static") ;
            if (!f.exists()) f.mkdir() ;
            f = new File(".\\static\\word_count.dat") ;
            if (!f.exists()) f.createNewFile() ;
            FileOutputStream fos = new FileOutputStream(f) ; ;
            ObjectOutputStream oos = new ObjectOutputStream(fos) ;
            oos.writeObject((Object)wc);
            oos.close();
            fos.close();
        }catch (FileNotFoundException e){
            System.out.println("file not exists");
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
    public static void loadHongLouMeng(){
        File f = new File(".\\static\\word_count.dat") ;
        WordCount wc = null ;
        try{
            FileInputStream fis = new FileInputStream(f) ;
            ObjectInputStream ois = new ObjectInputStream(fis) ;
            wc = (WordCount)ois.readObject() ;
            Set<String> dict_value = wc.word_dict.keySet() ;
            for(String x:dict_value){
                System.out.println(x);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.toString());
        }catch (IOException e){
            System.out.println(e.toString());
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public String toString(){
        return String.valueOf(this.id) ;
    }

}
