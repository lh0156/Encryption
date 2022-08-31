package jasypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyPairGeneratorDemo {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator keypair = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keypair.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        System.out.println(privateKey.toString());

        privateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println(privateKey.toString());


        SecureRandom secureRandom = new SecureRandom();
        keypair = KeyPairGenerator.getInstance("RSA");
        keypair.initialize(1024, secureRandom);
        keyPair = keypair.genKeyPair();

        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();

        System.out.println(privateKey.toString());

    }
}
