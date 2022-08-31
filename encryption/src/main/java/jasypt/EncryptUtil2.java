package jasypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class EncryptUtil2 {

    public static void main(String[] args) {
        /*
            1. 암호화 유틸 사용
            2. 파라미터는 각각 메세지, 암호화 타입, key이다.
                2.1. key는 AES를 사용할떄만 사용하고 반드시 16, 24, 32비트여야만 한다.
                2.2. 유효하지 않은 암호화 알고리즘 명이 들어갔을 경우 "해당하는 암호화 알고리즘이 없습니다"라는 값을 반환한다.

        */
        String RSATest = encrypt("하하", "RSA", "happyprogrammer!");
        System.out.println(RSATest);
    }

    public static String encrpty(String message, String algorithm) {
        return encrypt(message, algorithm, "");
    }



    public static String encrypt(String message, String algorithm, String key) {

        if (algorithm.equalsIgnoreCase("MD5")) {
            String MD5 = "";

            try {

                MessageDigest md = MessageDigest.getInstance(algorithm);

                md.update(message.getBytes());

                byte byteData[] = md.digest();

                StringBuffer sb = new StringBuffer();

                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }
                MD5 = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                MD5 = null;
            }
            return MD5;

        } else if (algorithm.equalsIgnoreCase("AES")) {
            try {
                // AES로 암호화

                // AES Cipher 객체 생성
                Cipher cipher = Cipher.getInstance(algorithm);

                // 암호화 Chipher 초기화
                SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                // 암호화 완료
                byte[] encryptBytes = cipher.doFinal(message.getBytes("UTF-8"));
                //System.out.println(new String(encryptBytes)); // => 똑같은 암호화키로 복호화


                //AES 복호화
                //복호화 Chipher 초기화, 똑같은 암호화키로 복호화
                //cipher.init(cipher.DECRYPT_MODE, secretKeySpec);
                //byte[] decryptBytes = cipher.doFinal(encryptBytes);
                //System.out.println(new String(decryptBytes, "UTF-8"));

                return new String(encryptBytes, "UTF-8");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if (algorithm.equalsIgnoreCase("RSA")) {

            // RSA 암호화
            // RSA 비밀키와 공개키를 생성
            try {
                KeyPairGenerator keypairgen = KeyPairGenerator.getInstance(algorithm);
                KeyPair keyPair = keypairgen.generateKeyPair();
                RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

                // Cipher 객체 생성과 비밀키 초기화
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);

                // 암호화 완료
                byte[] encryptBytes = cipher.doFinal(message.getBytes());
                //System.out.println(new String(encryptBytes)); // => 암호화되어 읽지못함

                // RSA로 복호화

                // 복호화 Chipher 초기화, 비밀키와 쌍인 공개키로 복호화함.
                //cipher.init(cipher.DECRYPT_MODE, publicKey);
                //byte[] decryptBytes = cipher.doFinal(encryptBytes);
                //System.out.println(new String(decryptBytes));

                return new String(encryptBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "해당하는 암호화 알고리즘이 없습니다";


    }
}
