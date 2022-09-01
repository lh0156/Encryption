package PojoUtilDemo;

import PojoJavaUtil.EncryptUtil;

import java.security.NoSuchAlgorithmException;

public class SHA256Demo {
    public static void main(String[] args) throws Exception {

        String temp = "암호화 할 메세지입니다";
        String isEncrypt = EncryptUtil.decryptSHA256("테스트");

        System.out.println("암호화 전: " + temp);
        System.out.println("암호화 후 메세지: " + isEncrypt);

    }
}