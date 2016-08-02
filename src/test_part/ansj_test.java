package test_part;

/**
 * Created by multiangle on 2016/8/1.
 */

import org.ansj.splitWord.analysis.ToAnalysis ;
import org.bson.Document ;
import java.util.ArrayList;
import java.util.Map;

import com.huaban.analysis.jieba.JiebaSegmenter ;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode ;

public class ansj_test {
    public static void main(String[] args){
        ArrayList<Document> content = mongo_test.getBsonText() ;
//        for(int i=0; i<content.size(); i++){
//            Document doc = content.get(i) ;
//            ArrayList<String> text = (ArrayList)doc.get("text") ;
//            for (String x:text){
//                System.out.println("---------------------------------------");
//                System.out.println(x);
//                System.out.println(ToAnalysis.parse(x));
//            }
//        }
        String words = "中国是世界四大文明古国之一，有着悠久的历史，距今约5000年前，以中原地区为中心开始出现聚落组织进而成国家和朝代，后历经多次演变和朝代更迭，持续时间较长的朝代有夏、商、周、汉、晋、唐、宋、元、明、清等。中原王朝历史上不断与北方游牧民族交往、征战，众多民族融合成为中华民族。20世纪初辛亥革命后，中国的君主政体退出历史舞台，取而代之的是共和政体。1949年中华人民共和国成立后，在中国大陆建立了人民代表大会制度的政体。中国有着多彩的民俗文化，传统艺术形式有诗词、戏曲、书法和国画等，春节、元宵、清明、端午、中秋、重阳等是中国重要的传统节日。";
        JiebaSegmenter segmenter = new JiebaSegmenter();
//        String[] sentences =
//                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
//                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
        String res = segmenter.process(words,SegMode.SEARCH).toString() ;
        System.out.println(res);
    }
}
