package jasypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAEncrypt {
    public static void main(String[] args) throws NoSuchAlgorithmException
            , NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            , BadPaddingException, UnsupportedEncodingException {

        // 암호화할 문자열
        String target = "Java 마스터!";


        // RSA 로 암호화 =================================================

        // RSA 비밀키와 공개키를 생성
        KeyPairGenerator keypairgen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keypairgen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // Cipher 객체 생성과 비밀키 초기화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        // 암호화 완료
        byte[] encryptBytes = cipher.doFinal(target.getBytes());
        System.out.println(new String(encryptBytes)); // => 암호화되어 읽지못함

        // RSA로 복호화 =================================================

        // 복호화 Chipher 초기화, 비밀키와 쌍인 공개키로 복호화함.
        cipher.init(cipher.DECRYPT_MODE, publicKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println(new String(decryptBytes));
    }

}
