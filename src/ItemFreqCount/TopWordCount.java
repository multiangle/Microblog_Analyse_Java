package ItemFreqCount;

import com.sun.webkit.event.WCChangeEvent;

import java.lang.reflect.Array;
import java.util.*;

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

    public ArrayList<WordCountElement> topN(int N){
        WordCountElement[] temp_queue ;
        if (freq_data.size()<=N){
            Collection<WordCountElement> v = freq_data.values() ;
            temp_queue = v.toArray(new WordCountElement[freq_data.size()]) ; // 把所有数据一咕噜放进去
        }else {
            temp_queue = new WordCountElement[N];
            for (int i = 0; i < N; i++) { // 先将前N个放进去
                temp_queue[i] = freq_data.get(i);
            }
            int min_index = 0; // 找到最小的节点
            for (int i = 1; i < N; i++) {
                if (temp_queue[i].getFreq() < temp_queue[min_index].getFreq()) {
                    min_index = i;
                }
            }
            for (int i = N; i < freq_data.size(); i++) {
                if (freq_data.get(i).getFreq() > temp_queue[min_index].getFreq()) {
                    temp_queue[min_index] = freq_data.get(i); // 把原最小节点替换成新节点
                    min_index = 0; // 找到新的最小的节点
                    for (int j = 1; j < N; i++) {
                        if (temp_queue[j].getFreq() < temp_queue[min_index].getFreq()) {
                            min_index = j;
                        }
                    }
                }
            }
        }
        // 到此时为止，已经将前N个最大的数据都放在temp_queue里面，但是是无序的
        ArrayList<WordCountElement> temp_array = new ArrayList<WordCountElement>() ;
        for (WordCountElement x:temp_queue){
            temp_array.add(x) ;
        }
        temp_array.sort(new Comparator<WordCountElement>() { // array进行排序
            @Override
            public int compare(WordCountElement o1, WordCountElement o2) {
                return o2.getFreq()-o1.getFreq(); // 进行倒叙排序
            }
        });

        return temp_array ;



    }
}
