package org.example.appmensajessecretos.domain.security;

import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Log4j2
@Component
public class MainAesTest {

    private final byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public String encrypt(String strToEncrypt, String secret) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constantes.CIPHER_ALGORITHM);
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), Constantes.CIPHER_SALT.getBytes(), 65536,256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), Constantes.AES);


            Cipher cipher = Cipher.getInstance(Constantes.CIPHER_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getUrlEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error(e,e);
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constantes.CIPHER_ALGORITHM);
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), Constantes.CIPHER_SALT.getBytes(), 65536,256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), Constantes.AES);


            Cipher cipher = Cipher.getInstance(Constantes.CIPHER_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getUrlDecoder().decode(strToDecrypt)),StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e,e);
        }
        return null;
    }
}
