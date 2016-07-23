package ItemFreqCount;

/**
 * Created by multiangle on 2016/7/23.
 */

import java.sql.Timestamp;
import java.util.Date ;

public class ItemFreqCountElement implements DateInElementInterface{

    private Date date ;
    private long timestamp ;
    private int dict_id ;
    private int freq ;

    ItemFreqCountElement(int dict_id,long timestamp){
        this.timestamp = timestamp*1000 ;  // 这边*1000， 是因为原数据以秒为单位，这里以毫秒为单位
        this.date = new Timestamp(this.timestamp) ;
        this.dict_id = dict_id ;
        this.freq = 1 ;  // 初始的频率为 1
    }

    // freq 相关操作
    public int getFreq(){
        return this.freq ;
    }
    public void addFreq(){
        this.freq += 1 ;
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

    public ItemFreqCountElement clone(){
        ItemFreqCountElement alter = new ItemFreqCountElement(this.dict_id,this.timestamp/1000) ;
        alter.addFreq(this.freq-1);
        return alter ;
    }
}
