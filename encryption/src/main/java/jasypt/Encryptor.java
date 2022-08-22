package jasypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public static void main(String[] args) {

        try {

            String temp = "멋진 윤섭";
            String key = "천재 개발자";
            byte[] tempArray = temp.getBytes();
            byte[] keyArray = key.getBytes();

            byte[] isEncryptMessage = encryptMessage(tempArray, keyArray);

            System.out.println(isEncryptMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] encryptMessage(byte[] message, byte[] keyBytes) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec aes = new SecretKeySpec(keyBytes, "AES");
            SecretKey secretKey = aes;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new byte[4];

    }

    public static byte[] decryptMessage(byte[] encryptedMessage, byte[] keyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(encryptedMessage);
    }
}
