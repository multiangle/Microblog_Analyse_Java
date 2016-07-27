package ItemFreqCount;

import com.sun.webkit.event.WCChangeEvent;

import java.util.ArrayList;
import java.util.Date ;
import java.util.Calendar ;

/**
 * Created by multiangle on 2016/7/24.
 */

public class SingleWordFreq {
    private String word ;  // 该对象所对应的单词
    private int dict_id ; // 该对象在词典中的id

    private int total_freq ; // 总的出现次数

    private ArrayList<WordCountElement> freq_day ;
    private ArrayList<WordCountElement> freq_month ;
    private ArrayList<WordCountElement> freq_year ;

    private TopWordCount top_word_counter ; // 统计TOPN

    SingleWordFreq(String word, int dict_id, TopWordCount top_word_counter){
        this.word = word ;
        this.dict_id = dict_id ;
        this.total_freq = 0 ;
        this.freq_day = new ArrayList<WordCountElement>() ;
        this.freq_month = new ArrayList<WordCountElement>() ;
        this.freq_year = new ArrayList<WordCountElement>() ;
        this.top_word_counter = top_word_counter ;
    }

    // 这一部分实现的是在array中查找到规定的时间戳对象，并加相应值
    private void _findAndAdd(ArrayList<WordCountElement> list, Date date, int N){
        _findAndAdd(list, date.getTime(), N);
    }
    private void _findAndAdd(ArrayList<WordCountElement> list, long timestamp, int N){
        int index = BinaryFindAsDate.findAsTimestamp(list,timestamp) ;
        if (index == list.size()){ // 如果查找到的结果超出范围，则新增一个节点
            WordCountElement wce = new WordCountElement(this.dict_id,timestamp) ;
            list.add(wce);
            this.top_word_counter.addSingleWord(wce);
        } else if (list.get(index).getTimestamp()==timestamp){ // 如果节点已经存在，则自加N
            list.get(index).addFreq(N);
        } else{
            WordCountElement wce = new WordCountElement(this.dict_id,timestamp) ;
            list.add(index,wce);
            this.top_word_counter.addSingleWord(wce);
        }
    }

    // 当输入相应的时间戳，则对对象中各个值做相应处理。包括total_freq,freq_day/mon/year 等
    public void addByTimestamp_sec(long timestamp){
        addByTimestamp_sec(timestamp, 1);
    }
    public void addByTimestamp_sec(long timestamp, int N){
        Date[] certain_dates = WordCountElement.getCertainDates(timestamp) ;
        Date day = certain_dates[0] ;
        Date mon = certain_dates[1] ;
        Date year = certain_dates[2] ;
        this.total_freq += N ;
        _findAndAdd(this.freq_day, day, N);
        _findAndAdd(this.freq_month, mon, N);
        _findAndAdd(this.freq_year, year, N);
    }

}
