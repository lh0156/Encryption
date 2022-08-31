package jasypt;

import java.security.*;
import java.util.Base64;

import static java.lang.System.*;

public class RSADemo {
    public static void main(String[] args) throws Exception {

        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator gen;
        gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048, secureRandom);
        KeyPair keyPair = gen.genKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        out.println(privateKey);


    }


}
