package PojoUtilDemo;

import java.security.*;

import static PojoJavaUtil.EncryptUtil.*;

public class RSADemo {
    public static void main(String[] args) throws Exception {

        //Key를 얻는다
        KeyPair keyPair = genRSAKeyPair();

        String message = "암호화 할 메세지입니다.";
        String isEncryptMessage = encryptRSA(message, keyPair.getPublic());
        String isDecryptMessage = decryptRSA(isEncryptMessage, keyPair.getPrivate());

        System.out.println("암호화 전 메세지: " + message);
        System.out.println("암호화 후 메세지: " + isEncryptMessage);
        System.out.println("복호화 후 메세지: " + isDecryptMessage);


    }


}
