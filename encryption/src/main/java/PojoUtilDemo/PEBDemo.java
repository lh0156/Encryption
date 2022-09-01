package PojoUtilDemo;

import static PojoJavaUtil.EncryptUtil.*;

public class PEBDemo {

    public static void main(String[] args) throws Exception {

        /*
            PBE 클래스에서 더이상 알고리즘을 지원하지 않는 듯 하다.
            java.security.NoSuchAlgorithmException: Cannot find any provider supporting PBEWithSHAAndTwoFish-CBC

        */
        String message = "안녕하세요";
        String isDecryptMessage = decryptPBE(message);

        System.out.println("PBE 완료: " + isDecryptMessage);
    }

}
