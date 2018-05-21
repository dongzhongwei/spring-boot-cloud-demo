package com.ddw.demo.encrypt;

import java.security.MessageDigest;

public class AlipayEncrypt {

    public static void main(String[] args) {
        String data = "展招";
        System.out.println(MD5(data));
        System.out.println(MD5_1(data));
        System.out.println(MD5_2(data));

    }

    public static String MD5(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return sb.toString().toUpperCase();
    }

    public static String MD5_1(String data) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        String s;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte tmp[] = md.digest();

            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
           s = new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }


    public static String MD5_2(String data) {
        // 把密文转换成十六进制的字符串形式
        StringBuffer hexString = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte tmp[] = md.digest();


            // 字节数组转换为 十六进制 数
            for (int i = 0; i < tmp.length; i++) {
                String shaHex = Integer.toHexString(tmp[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
        }catch (Exception e){

        }
       return hexString.toString();
    }
}
