package org.example.appmensajessecretos.utilities.security;

import com.google.common.primitives.Bytes;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.example.appmensajessecretos.config.ConfigurationFicheros;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Log4j2
@Component
public class Asymmetric {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final ConfigurationFicheros configuration;

    public Asymmetric(ConfigurationFicheros configuration) {
        this.configuration = configuration;
    }

    public Either<Error, Void> saveUserKeys(Usuario user) {
        try {
            FileInputStream fis = new FileInputStream(configuration.getPathKeyStore());
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
            keyStore.load(fis, keyStorePassword);
            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(Constantes.SERVER, entryPassword);
            PrivateKey serverPrivateKey = privateKeyEntry.getPrivateKey();

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Constantes.EC, Constantes.BC);
            keyPairGenerator.initialize(new ECGenParameterSpec(Constantes.secp521r1));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            X509Certificate certificate = generateCertificate(keyPair.getPublic(), serverPrivateKey);

            keyStore.setKeyEntry(user.getName(), keyPair.getPrivate(), user.getPassword().toCharArray(), new Certificate[]{certificate});

            FileOutputStream fos = new FileOutputStream(configuration.getPathKeyStore());
            keyStore.store(fos,configuration.getKeyStorePassword().toCharArray());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_READING_FILE);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_GENERATING_KEYS);
        }
        return Either.right(null);
    }

    private X509Certificate generateCertificate(PublicKey userPublicKey, PrivateKey serverPrivateKey) throws OperatorCreationException, CertificateException {

        X500Name issuer = new X500Name(Constantes.GENERATING_CERTIFICATE_COMMON_NAME);
        X500Name subject = issuer;

        Date notBefore = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24);
        Date notAfter = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365);

        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());

        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(userPublicKey.getEncoded());
        X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
                issuer, serialNumber, notBefore, notAfter, subject, subjectPublicKeyInfo
        );

        ContentSigner contentSigner = new JcaContentSignerBuilder(Constantes.SHA256withECDSA)
                .setProvider(Constantes.BC)
                .build(serverPrivateKey);

        X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);

        return new JcaX509CertificateConverter()
                .setProvider(Constantes.BC)
                .getCertificate(certificateHolder);
    }

    public Either<Error, PublicKey> getPublicKey(Usuario user) {
        try (FileInputStream fis = new FileInputStream(configuration.getPathKeyStore())) {
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
            keyStore.load(fis, keyStorePassword);
            return Either.right(keyStore.getCertificate(user.getName()).getPublicKey());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_READING_FILE);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_GETTING_PUBLIC_KEY);
        }
    }

    public Either<Error, PrivateKey> getPrivateKey(Usuario user) {
        try (FileInputStream fis = new FileInputStream(configuration.getPathKeyStore())) {
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
            keyStore.load(fis, keyStorePassword);
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(
                    user.getName(), new KeyStore.PasswordProtection(user.getPassword().toCharArray()));
            return Either.right(pkEntry.getPrivateKey());
        } catch (UnrecoverableKeyException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataInputError.INCORRECT_PASSWORD);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_READING_FILE);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_GETTING_PRIVATE_KEY);
        }
    }

    public String getRandomKey() {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        byte[] password = new byte[16];
        sr.nextBytes(password);
        return Base64.getUrlEncoder().encodeToString(password);
    }

    public Either<Error, String> cipher(String text, PublicKey publicKey) {
        try {
            byte[] derivation = new byte[16];
            byte[] encoding = new byte[16];
        new SecureRandom().nextBytes(derivation);
        new SecureRandom().nextBytes(encoding);
            IESParameterSpec params = new IESParameterSpec(derivation, encoding, 128, 128, null);

           Cipher cipher = Cipher.getInstance(Constantes.ECIES, Constantes.BC);
//            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey,params);
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            return Either.right(Base64.getUrlEncoder().encodeToString(Bytes.concat(derivation,encoding,cipher.doFinal(byteText))));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_ENCRYPTING);
        }
    }

    public static byte[] derivation = new byte[16];
    public static byte[] encoding = new byte[16];
    static {
        new SecureRandom().nextBytes(derivation);
        new SecureRandom().nextBytes(encoding);
    }

    private IESParameterSpec provideParams() {
        //byte[] derivation = new byte[16];
        //byte[] encoding = new byte[16];
//        new SecureRandom().nextBytes(derivation);
//        new SecureRandom().nextBytes(encoding);
        return new IESParameterSpec(derivation, encoding, 128, 128, null);
    }

    public Either<Error, String> decipher(String text, PrivateKey privateKey) {
        try {
            byte[] decoded = Base64.getUrlDecoder().decode(text);
            byte[] derivation = Arrays.copyOf(decoded, 16);
            byte[] encoding = Arrays.copyOfRange(decoded, 16,32);



            Cipher cipher = Cipher.getInstance(Constantes.ECIES,Constantes.BC);
//            Cipher cipher = Cipher.getInstance("RSA");

            IESParameterSpec params = new IESParameterSpec(derivation, encoding, 128, 128, null);

            cipher.init(Cipher.DECRYPT_MODE, privateKey,params);
            byte[] byteText =Arrays.copyOfRange(decoded, 32, decoded.length);
            return Either.right(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_ENCRYPTING);
        }
    }
}
