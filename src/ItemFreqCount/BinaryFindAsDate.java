package ItemFreqCount;

import java.util.ArrayList;
import java.util.Date ;
/**
 *  Created by multiangle on 2016/7/23.
 *  对能够实现DateInElementInterface的接口的array实现二分查找
 */
public class BinaryFindAsDate {
    public static int findAsTimestamp(ArrayList<? extends DateInElementInterface> data_list, long target){
        int low = 0 ;
        int high = data_list.size() ;
        while (low < high){
            int mid = (low + high) >> 1 ;
            if (data_list.get(mid).getTimestamp() == target){
                return mid ;
            }
            if (data_list.get(mid).getTimestamp() < target){
                low = mid + 1 ;
            }else{
                high = mid ;
            }
        }
        return low ;
    }

    public static int findAsDate(ArrayList<? extends DateInElementInterface> data_list, Date target){
        int low = 0 ;
        int high = data_list.size() ;
        while (low<high){
            int mid = (low + high) >> 1 ;
            if (data_list.get(mid).getDate().equals(target)){
                return mid ;
            }
            if (data_list.get(mid).getDate().before(target)){
                low = mid + 1 ;
            }else{
                high = mid ;
            }
        }
        return low ;
    }

    public static void swap(ArrayList<DateInElementInterface> data_list, int i1, int i2){
        DateInElementInterface temp = data_list.get(i1) ;
        data_list.set(i1,data_list.get(i2)) ;
        data_list.set(i2,temp) ;
    }
}
