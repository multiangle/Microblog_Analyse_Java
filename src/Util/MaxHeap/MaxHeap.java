package Util.MaxHeap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by multiangle on 2016/7/27.
 */
public class MaxHeap<T> {

    private int size ;
    private ArrayList<T> data ; // 存放数据的地方
    private Comparator<T> comparator ; // 用来比对的类

    // 构造函数
    MaxHeap(Comparator<T> comparator){
        this(-1, comparator) ; // 如果没有指定规模，则默认规模为无限，size值为-1
    }
    MaxHeap(int size, Comparator<T> comparator){
        this.size = size ;
        this.comparator = comparator ;
    }

    // 插入新内容
    // 如果没达到上限或没有上限，则沿着Array往下
    // 如果得到上线了，则从上往下，踢掉最小的那个值
    public void insert(T element){
        if (((size>=0)&&(data.size()<size)) || (size<0))
        { // 如果尺寸有限制且当前尺寸没有达到限制, 或者没有尺寸限制
            data.add(element) ;
            int i = data.size()-1 ;
            percolateUp(i);
        }else{ // 如果达到了尺寸限制
            replaceDown(element,0);
        }
    }

    public T getMax(){
        return data.get(0) ;
    }
    public T delMax(){
        T max_node = data.get(0) ;
        data.set(0, data.remove(data.size()-1)) ;
        percolateDown(0);
        return max_node ;
    }

    public void replaceDown(T v, int index){
        while (index*2+1 < data.size()){
            if (index*2+2 < data.size()){ // 如果index有左右节点
                T left = data.get(index*2+1) ;
                T right = data.get(index*2+2) ;
                if (comparator.compare(left,right)<0) { // if left<right
                    if (comparator.compare(v, data.get(index))<=0){ // node<=top
                        index = index*2+1 ;
                    }else{ // node>top
                        T temp = data.get(index) ;
                        data.set(index, v) ;
                        v = temp ;
                        index = index*2+1 ;
                    }
                }else{ // if left >= right
                    if (comparator.compare(data.get(index),right)<=0) { // node<=top
                        index = index*2+2 ;
                    }else{
                        T temp = data.get(index) ;
                        data.set(index, v) ;
                        v = temp ;
                        index = index*2+2 ;
                    }
                }
            }else{ // 如果只有左节点
                if (comparator.compare(data.get(index),data.get(index*2+1))<0){
                    swapNode(index, index*2+1);
                }
                break ;
            }
        }
    }
    // 上滤操作
    public void percolateUp(int index){
        while(index>0){
            int p_index = (index-1)>>1 ;
            T p = data.get(p_index) ;
            T n = data.get(index) ;
            if (comparator.compare(n,p)>0){
                swapNode(p_index,index);
                index = p_index ;
            }else{
                break ;
            }
        }
    }
    // 下滤操作
    public void percolateDown(int index){
        while (index*2+1 < data.size()){
            if (index*2+2 < data.size()){ // 如果index有左右节点
                T left = data.get(index*2+1) ;
                T right = data.get(index*2+2) ;
                if (comparator.compare(left,right)>0) { // if left>right
                    if (comparator.compare(data.get(index),left)<0){ // node<max
                        swapNode(index, index*2+1);
                        index = index*2+1 ;
                    }else{
                        break ;
                    }
                }else{ // if left <= right
                    if (comparator.compare(data.get(index),right)<0) { // node<max
                        swapNode(index, index*2+2);
                        index = index*2+2 ;
                    }else{
                        break ;
                    }
                }
            }else{ // 如果只有左节点
                if (comparator.compare(data.get(index),data.get(index*2+1))<0){
                    swapNode(index, index*2+1);
                }
                break ;
            }
        }
    }

    public void swapNode(int a, int b){
        T temp = data.get(a) ;
        data.set(a, data.get(b)) ;
        data.set(b, temp) ;
    }

    public static void main(String[] args){
        MaxHeap<Integer> mh = new MaxHeap<Integer>(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2 ;
            }
        });
        for (int i=0;i<10;i++){
            mh.insert(i);
        }
        System.out.println(mh);

    }

    public String toString(){
        return this.data.toString() ;
    }
}
