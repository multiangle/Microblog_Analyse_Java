package ItemFreqCount;

/**
 * Created by multiangle on 2016/7/24.
 */

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date ;

public class WordCountElement implements DateInElementInterface{
    private Date date ;
    private long timestamp ;
    private int dict_id ;
    private int freq ;

    WordCountElement(int dict_id,long timestamp){
        this.timestamp = timestamp ;  // 这里假设timestamp已经以毫秒为单位
        this.date = new Date(timestamp) ;
        this.dict_id = dict_id ;
        this.freq = 1 ;  // 初始的频率为 1
    }

    // freq 相关操作
    public int getFreq(){
        return this.freq ;
    }
    public void setFreq(int N){
        this.freq = N ;
    }
    public void addFreq(){
        addFreq(1);
    }
    public void addFreq(int N){
        this.freq += N ;
    }

    // Date, timestamp相关操作，实现了DateInElementInterface接口
    public Date getDate(){
        return this.date ;
    }
    public long getTimestamp(){
        return this.timestamp ;
    }

    public int getDict_id(){
        return this.dict_id ;
    }
    public WordCountElement clone(){
        WordCountElement alter = new WordCountElement(this.dict_id,this.timestamp/1000) ;
        alter.setFreq(this.freq);
        return alter ;
    }

    // 计算按天，月，年统计的时间
    public static Date[] getCertainDates(long timestamp){
        Date[] ret = new Date[3] ;
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
        ret[0] = day ;
        ret[1] = mon ;
        ret[2] = year ;
        return ret;
    }
}
