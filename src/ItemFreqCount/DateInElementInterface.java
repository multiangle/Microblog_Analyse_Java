package ItemFreqCount;

import java.util.Date ;
/**
 * Created by multiangle on 2016/7/23.
 */
public interface DateInElementInterface {
    Date getDate() ;  // 获取element中的时间对象，用于具体的操作
    long getTimestamp() ;
}
