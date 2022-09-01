package PojoJavaUtil;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.Random;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;


@Component
public class EncryptUtil {

    //--------------------RSA 시작--------------------
    /**
     * 1024비트 RSA 키쌍을 생성합니다.
     */
    public static KeyPair getRSAKeyPair() throws NoSuchAlgorithmException {

        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator gen;
        gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024, secureRandom);
        KeyPair keyPair = gen.genKeyPair();
        return keyPair;
    }

    /**
     * Public Key로 RSA 암호화를 수행합니다.
     * @param plainText 암호화할 평문입니다.
     * @param publicKey 공개키 입니다.
     * @return
     */
    public static String encryptRSA(String plainText, PublicKey publicKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);
        return encrypted;
    }

    /**
     * Private Key로 RAS 복호화를 수행합니다.
     *
     * @param encrypted 암호화된 이진데이터를 base64 인코딩한 문자열 입니다.
     * @param privateKey 복호화를 위한 개인키 입니다.
     * @return
     * @throws Exception
     */
    public static String decryptRSA(String encrypted, PrivateKey privateKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        String decrypted = new String(bytePlain, "utf-8");
        return decrypted;
    }

    //--------------------MD5--------------------
    public static String decryptMD5(String message) {
        String MD5 = "";
        try {

            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(message.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //--------------------SHA256--------------------
    public static String decryptSHA256(String message) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(message.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //--------------------DES--------------------
    public static Key getKey() throws Exception {

        try {

            DESKeySpec desKeySpec = new DESKeySpec("64bit-key".getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key key = keyFactory.generateSecret(desKeySpec);

            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String decryptDES(Key key, String message) throws Exception {

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            return cipher.doFinal(message.getBytes()).toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String decryptPBE(String message) throws Exception {

        try {
            char[] password = "DESTest".toCharArray();

            // 1. salt를 8byte 랜덤 생성
            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);

            // 2. 패스워드를 이용하여 PBEKeySpec 생성  
            PBEKeySpec keySpec = new PBEKeySpec(password);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");

            // 3. 비밀키 생성  
            SecretKey key = keyFactory.generateSecret(keySpec);

            // 4. salt, iteration count를 위한 PBEParameterSpec 생성
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);

            // 5. Cipher 생성 및 초기화
            Cipher cipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            // 6. 암호문 생성
            byte[] ciphertext = cipher.doFinal(message.getBytes());

            // 7. salt와 암호문을 Base64 인코딩 후 결합하여 최종 결과물 생성

            String saltString = java.util.Base64.getEncoder().encodeToString(salt);
            String ciphertextString = java.util.Base64.getEncoder().encodeToString(ciphertext);
            return saltString + ciphertextString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }

//    public static String decryptPBE2(String message, String password) throws Exception {
//        final int ITERATIONCOUNT = 100;
//
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithShAAndTwoFish-CBC");
//        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
//        SecretKey secretKey = keyFactory.generateSecret(keySpec);
//
//        SecureRandom random = new SecureRandom();
//        Key key = secretKey;
//        byte[] salt = random.generateSeed(8);
//
//        String saltStr = java.util.Base64.getEncoder().encodeToString(salt);
//
//        //  PBE
//        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,ITERATIONCOUNT);
//        //
//        Cipher cipher = Cipher.getInstance("PBEWithShAAndTwoFish-CBC");
//        //cipher            ，     ("          ","  ","  ")
//        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
//        //
//        byte encipheredData[] = cipher.doFinal(message.getBytes("UTF-8"));
//
//        return java.util.Base64.getEncoder().encodeToString(encipheredData);
//    }



}
