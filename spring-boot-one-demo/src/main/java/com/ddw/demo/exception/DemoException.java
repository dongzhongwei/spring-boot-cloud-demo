package com.ddw.demo.exception;

/**
 * All rights Reserved, Designed By 特斯联观翼
 * Copyright:    Copyright(C) 2016-2018
 * Company       特斯联观翼(北京)智能科技有限公司
 *
 * @author ddw
 * @version 1.0
 * @date 2019-01-02 21:53
 * @Description
 */
public class DemoException {

    public static void main(String[] args) {
        Object object = null;
        if (object == null) {
            throw new NullPointerException();
        }
    }
}
