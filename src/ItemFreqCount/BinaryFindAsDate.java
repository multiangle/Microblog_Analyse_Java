package ItemFreqCount;

import java.util.ArrayList;
import java.util.Date ;
/**
 * Created by multiangle on 2016/7/23.
 */
public class BinaryFindAsDate {
    public static void findAsTimestamp(ArrayList<DateInElementInterface> data_list, long timestamp){

    }

    public static void findAsDate(ArrayList<DateInElementInterface> data_list, Date date){

    }

    public static void swap(ArrayList<DateInElementInterface> data_list, int i1, int i2){
        DateInElementInterface temp = data_list.get(i1) ;
        data_list.set(i1,data_list.get(i2)) ;
        data_list.set(i2,temp) ;
    }
}
