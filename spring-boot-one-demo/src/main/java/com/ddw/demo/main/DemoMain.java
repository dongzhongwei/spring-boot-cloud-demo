package com.ddw.demo.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DemoMain {


    private static volatile long _longVal = 0;

    public static void main(String[] args) {

        _longVal++;

        Map<String, String> hashMap = new HashMap<>();

        Map<String, String> hashtable = new Hashtable<>();

        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();


        hashMap.put(null, null);
        hashtable.put("", "aa");
        System.out.println(hashtable.get(""));
        concurrentHashMap.put("", "");


//        int a = 1, b =2;
//        swap(a,b);
//        System.out.println("a="+a+",b="+b);
//
//        String key = "中华人民共和国";
//        int h;
//        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//        System.out.println("hashCode="+ (key.hashCode() & 15)+"----hash="+ (hash & 15));
//
//        Map<String, Integer> map = new HashMap<>(9);
//
//        map.put("1",1);
//        map.put("2",2);
//        map.put("3",3);

//        String s1 = "Programming";
//
//        String s2 = new String("Programming");
//
//        String s3 = "Program" + "ming";
//
//        System.out.println(s1 == s2);
//
//        System.out.println(s1 == s3);
//
//        System.out.println(s1 == s1.intern());
//
//        LocalDateTime today = LocalDateTime.now();
//        LocalDateTime yesterday = today.minusDays(1);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateTimeFormatter.format(yesterday));
    }

    private static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }
}
