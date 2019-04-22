package com.ddw.util.array;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-08 21:27
 * @Description
 */
public class Array<E> {

    private E[] data;

    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    //元素个数
     public int getSize(){
        return size;
    }

    /**
     * 获得数据的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){

        if (index < 0 || index > size){
            throw new IllegalArgumentException("索引不合法");
        }

        if (size == data.length){
            resize(2 * size);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public E get(int index){
        if (index <0 || index>=size){
            throw new IllegalArgumentException("索引非法");
        }
        return data[index];
    }

    public void set(int index , E e){
        if (index <0 || index>=size){
            throw new IllegalArgumentException("索引非法");
        }
        data[index] = e;
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i] == e){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if (index <0 || index>=size){
            throw new IllegalArgumentException("索引非法");
        }
        E ret = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size --;
//        System.arraycopy();
        data[size] = null;

        //如果不是偶数
        if (size == data.length / 2){
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size);
    }

    public void removeElement(E e){
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }



//    public int remove2(int index){
//        if (index <0 || index>=size){
//            throw new IllegalArgumentException("索引非法");
//        }
//        int ret = data[index];
//        for (int i = index+1; i < size; i++) {
//            data[i-1] = data[i];
//        }
//        size --;
//        return ret;
//    }

    @Override
    public String toString(){
        StringBuilder res =  new StringBuilder();

        res.append(String.format("Array: size=%d, capacity=%d\n",size, data.length));

        res.append("[");

        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size -1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
