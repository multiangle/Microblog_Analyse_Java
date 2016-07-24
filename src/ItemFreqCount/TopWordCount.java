package ItemFreqCount;

import com.sun.webkit.event.WCChangeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date ;

/**
 * Created by multiangle on 2016/7/24.
 */
public class TopWordCount {
    private ArrayList<TopWordCountArrayElement> top_word_as_day ;
    private ArrayList<TopWordCountArrayElement> top_word_as_mon ;
    private ArrayList<TopWordCountArrayElement> top_word_as_year ;

    TopWordCount(){
        this.top_word_as_day = new ArrayList<TopWordCountArrayElement>() ;
        this.top_word_as_mon = new ArrayList<TopWordCountArrayElement>() ;
        this.top_word_as_year = new ArrayList<TopWordCountArrayElement>() ;
    }

    public void addSingleWord(WordCountElement item){
        long timestamp = item.getTimestamp() ;
        Date[] certain_dates = WordCountElement.getCertainDates(timestamp) ;
        Date day = certain_dates[0] ;
        Date mon = certain_dates[1] ;
        Date year = certain_dates[2] ;

        // 在按天，月，年计算的TOPN词中，各加入对应的值
        _findAndReturn(top_word_as_day,day).checkAndAdd(item);
        _findAndReturn(top_word_as_mon,mon).checkAndAdd(item);
        _findAndReturn(top_word_as_year,year).checkAndAdd(item);
    }

    private TopWordCountArrayElement _findAndReturn(ArrayList<TopWordCountArrayElement> list, long timestamp){
        int index = BinaryFindAsDate.findAsTimestamp(list, timestamp) ;
        if (index == list.size()){ // 如果查找到的结果超出范围，则新增一个节点
            TopWordCountArrayElement one = new TopWordCountArrayElement(timestamp) ;
            list.add(one);
            return one ;
        } else if (list.get(index).getTimestamp()==timestamp){
            return list.get(index) ;
        } else{
            TopWordCountArrayElement one = new TopWordCountArrayElement(timestamp) ;
            list.add(index,one);
            return one;
        }
    }
    private TopWordCountArrayElement _findAndReturn(ArrayList<TopWordCountArrayElement> list, Date date){
        return _findAndReturn(list,date.getTime()) ;
    }
}

class TopWordCountArrayElement implements DateInElementInterface{
    private long timestamp ;
    private Date date ;
    private HashMap<Integer, WordCountElement> freq_data ;

    // 初始化方法
    TopWordCountArrayElement(Date date){
        this(date.getTime()) ;
    }
    TopWordCountArrayElement(long timestamp){
        this.timestamp = timestamp ;
        this.date = new Date(timestamp) ;
        this.freq_data = new HashMap<Integer, WordCountElement>() ;
    }

    public boolean check(WordCountElement item){
        return this.freq_data.containsKey(item.getDict_id()) ;
    }

    public void checkAndAdd(WordCountElement item){
        if (! this.freq_data.containsKey(item.getDict_id())){ // 如果hashmap中没有当前的元素，则加入这个
            this.freq_data.put(item.getDict_id(),item) ;
        }
    }

    // 实现DateInElementInterface接口的相关方法
    public Date getDate(){
        return this.date ;
    }
    public long getTimestamp(){
        return this.timestamp ;
    }
}
