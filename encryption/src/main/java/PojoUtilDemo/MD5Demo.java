package PojoUtilDemo;

import static PojoJavaUtil.EncryptUtil.decryptMD5;

public class MD5Demo {
    public static void main(String[] args) {
        String message = "안녕하세요";
        String isDecryptMessage = decryptMD5("temp");

        System.out.println("암호화 전: " + message);
        System.out.println("암호화 후: " + isDecryptMessage);
    }
}
