package ItemFreqCount;

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

    SingleWordFreq(String word, int dict_id){
        this.word = word ;
        this.dict_id = dict_id ;
        this.total_freq = 0 ;
        this.freq_day = new ArrayList<WordCountElement>() ;
        this.freq_month = new ArrayList<WordCountElement>() ;
        this.freq_year = new ArrayList<WordCountElement>() ;
    }

    // 这一部分实现的是在array中查找到规定的时间戳对象，并加相应值
    private void _findAndAdd(ArrayList<WordCountElement> list, Date date, int N){
        _findAndAdd(list, date.getTime(), N);
    }
    private void _findAndAdd(ArrayList<WordCountElement> list, long timestamp, int N){
        int index = BinaryFindAsDate.findAsTimestamp(list,timestamp) ;
        if (index == list.size()){ // 如果查找到的结果超出范围，则新增一个节点
            WordCountElement one = new WordCountElement(this.dict_id,timestamp) ;
            list.add(one);
        } else if (list.get(index).getTimestamp()==timestamp){
            list.get(index).addFreq(N);
        } else{
            list.add(index,new WordCountElement(this.dict_id,timestamp));
        }
    }

    // 当输入相应的时间戳，则对对象中各个值做相应处理。包括total_freq,freq_day/mon/year 等
    public void addByPyTimestamp_sec(int timestamp){
        addByTimestamp_sec(timestamp, 1);
    }
    public void addByTimestamp_sec(int timestamp, int N){
        // 这里的timestamp是python格式的,即以秒为单位，这里需要改成java格式的，即以毫秒为单位
        timestamp *= 1000 ;
        Date t = new Date(timestamp) ;
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(t);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY,0) ;
        Date day = calendar.getTime() ;
        calendar.set(Calendar.DATE,1);
        Date mon = calendar.getTime() ;
        calendar.set(Calendar.MONTH,0);;
        Date year = calendar.getTime() ;

        this.total_freq += N ;
        _findAndAdd(this.freq_day, day, N);
        _findAndAdd(this.freq_month, mon, N);
        _findAndAdd(this.freq_year, year, N);

    }

}
