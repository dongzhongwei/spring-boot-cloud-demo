package com.ddw.demo.domain;

import java.util.HashMap;
import java.util.Map;

public enum Color implements BaseEnum {

    RED(1, "红色"),
    GREEN(2, "绿色"),
    WHITE(3, "白色"),
    YELLOW(4, "黄色");

    private Integer code;
    private String name;

    Color(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, Color> codeKeyMap = new HashMap<>(Color.values().length);
    private static final Map<String, Color> nameKeyMap = new HashMap<>(Color.values().length);

    static {
        for (Color c : Color.values()) {
            codeKeyMap.put(c.getCode(), c);
            nameKeyMap.put(c.getName(), c);
        }
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.name;
    }

    public static Color getByCode(Integer code) {
        return codeKeyMap.getOrDefault(code, null);
    }

    public static Color getByName(String name) {
        return nameKeyMap.getOrDefault(name, null);
    }

    public static String getNameByCode(Integer code) {
       return getByCode(code) == null ? null : getByCode(code).getName();
    }

    public static Integer getCodeByName(String name) {
       return getByName(name) == null ? null : getByName(name).getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        System.out.println(Color.valueOf("WHITE").getName());
    }

}