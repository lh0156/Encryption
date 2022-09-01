package PojoUtilDemo;

import PojoJavaUtil.EncryptUtil;

import java.security.Key;

import static PojoJavaUtil.EncryptUtil.decryptDES;

public class DESDemo {
    public static void main(String[] args) throws Exception {
        Key key = EncryptUtil.getKey();

        String message = "안녕하세요";
        String isDecryptMessage = decryptDES(key, "하하");

        System.out.println("암호화 전: " + message);
        System.out.println("암호화 후: " + isDecryptMessage);

    }
}
