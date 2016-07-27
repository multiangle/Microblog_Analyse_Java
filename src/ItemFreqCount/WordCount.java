package ItemFreqCount;

import java.util.HashMap;

/**
 * Created by multiangle on 2016/7/24.
 */
public class WordCount {
    private TopWordCount top_word_counter ;
    private HashMap<String, Integer> word_dict ;
    private HashMap<String, SingleWordFreq> word_freq ;

    WordCount(){
        top_word_counter = new TopWordCount() ; // 每日流行词统计
        word_dict = new HashMap<String, Integer>() ; // 单词-id映射对
        word_freq = new HashMap<String, SingleWordFreq>() ; // 存放单个词语的各天趋势变化
    }

    public void addWordWithTimestamp(String word, long timestamp){ //这里假设 timestamp已经是Java形式
        Integer id = word_dict.get(word) ;
        int dict_id ;
        SingleWordFreq swf ;
        if (id!=null) {
            dict_id = id ;
            swf = word_freq.get(word) ;
        }
        else{
            dict_id = word_dict.size() ;
            word_dict.put(word,dict_id) ;
            swf = new SingleWordFreq(word,dict_id,this.top_word_counter) ;
            word_freq.put(word,swf) ;
        }
        swf.addByTimestamp_sec(timestamp); // 向single word freq 加入该时间戳
    }
}
