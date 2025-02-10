package utilities.security_ex3;

import com.google.common.primitives.Bytes;
import domain.model.Apuesta;
import io.vavr.control.Either;
import utilities.Constantes;

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

public class Symmetric {

    public Either<String,String> cipher (Apuesta apuesta, String secretKey) {
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
                cipher.doFinal(apuesta.toString().getBytes(StandardCharsets.UTF_8)))));
        } catch (Exception e) {
            return Either.left("Error al encriptar");
        }
    }

    public Either<String,String> decipher (String apuesta, String secret) {
        try {
            byte[] decoded = Base64.getUrlDecoder().decode(apuesta);
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
            return Either.left("Error al desencriptar");
        }
    }
}
