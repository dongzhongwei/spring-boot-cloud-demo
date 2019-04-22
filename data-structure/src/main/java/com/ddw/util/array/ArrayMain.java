package com.ddw.util.array;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-08 21:14
 * @Description
 */
public class ArrayMain {

    public static void main(String[] args) {
       Array<Integer> array = new Array<>(10);

        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array.toString());

        array.add(1,100);

        System.out.println(array.toString());

        array.addFirst(-1);

        System.out.println(array);

        array.remove(0);

        System.out.println(array);

    }
}
