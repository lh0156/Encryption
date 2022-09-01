package PojoUtilDemo;

import static PojoJavaUtil.EncryptUtil.decryptMD5;

public class Md5Demo {
    public static void main(String[] args) {
        String temp = "안녕하세요";
        String isDecryptTemp = decryptMD5("temp");

        System.out.println("암호화 전: " + temp);
        System.out.println("암호화 후: " + isDecryptTemp);
    }
}
