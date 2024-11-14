package org.example.appmensajessecretos.utilities.security;

import com.google.common.primitives.Bytes;
import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.EncryptingException;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
public class Encrypt {

    public Either<Error,String> encrypt(String text, String secretKey)º {
        try {

            byte[] iv = new byte[12];
            byte[] salt = new byte[16];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(iv);
            sr.nextBytes(salt);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);


            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constantes.CIPHER_ALGORITHM);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt, Constantes.ITERATIONS, Constantes.CIPHER_KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), Constantes.AES);

            Cipher cipher = Cipher.getInstance(Constantes.CIPHER_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameterSpec);
            return Either.right(Base64.getUrlEncoder().encodeToString(Bytes.concat(iv,salt,
                cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)))));
        } catch (Exception e) {
            return Either.left(ServiceError.ERROR_ENCRYPTING);
        }
    }

    public Either<Error,String> decrypt (String strToDecrypt, String secret) {
        try {
            byte[] decoded = Base64.getUrlDecoder().decode(strToDecrypt);
            byte[] iv = Arrays.copyOf(decoded, 12);
            byte[] salt = Arrays.copyOfRange(decoded, 12,28);

            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constantes.CIPHER_ALGORITHM);
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt, Constantes.ITERATIONS,Constantes.CIPHER_KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), Constantes.AES);

            Cipher cipher = Cipher.getInstance(Constantes.CIPHER_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
            return Either.right(new String(cipher.doFinal(Arrays.copyOfRange(decoded, 28, decoded.length)), StandardCharsets.UTF_8));
        } catch (Exception e) {
            return Either.left(ServiceError.ERROR_DECRYPTING);
        }
    }
}
