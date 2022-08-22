package jasypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt {
    public static void main(String[] args) {
        try {
            // 암호화에 사용할 키, 디폴트로 128bit(16Byte)
            String encryptionKey = "happyprogrammer!";

            // 암호화할 문자열
            String target = "Java 마스터!";


            // AES로 암호화 =================================================

            // AES Cipher 객체 생성
            Cipher cipher = Cipher.getInstance("AES");

            // 암호화 Chipher 초기화
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(),"AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // 암호화 완료
            byte[] encryptBytes = cipher.doFinal(target.getBytes("UTF-8"));
            System.out.println(new String(encryptBytes)); // => 똑같은 암호화키로 복호화


            // AES로 복호화 =================================================

            // 복호화 Chipher 초기화, 똑같은 암호화키로 복호화
            cipher.init(cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            System.out.println(new String(decryptBytes, "UTF-8"));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
