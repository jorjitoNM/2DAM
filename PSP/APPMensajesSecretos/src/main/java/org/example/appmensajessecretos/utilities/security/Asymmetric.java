package org.example.appmensajessecretos.utilities.security;

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
            char[] keyStorePassword = configuration.getServerKey().toCharArray();
            keyStore.load(fis, keyStorePassword);
            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(Constantes.SERVER, entryPassword);
            PrivateKey serverPrivateKey = privateKeyEntry.getPrivateKey();

            /*X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
            ECDomainParameters domainParams = new ECDomainParameters(ecp.getCurve(),
                    ecp.getG(), ecp.getN(), ecp.getH(),
                    ecp.getSeed());

            AsymmetricCipherKeyPair keyPair;
            ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(domainParams, new SecureRandom());
            ECKeyPairGenerator generator = new ECKeyPairGenerator();
            generator.init(keyGenParams);
            keyPair = generator.generateKeyPair();

            ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
            ECPublicKeyParameters publicKey = (ECPublicKeyParameters) keyPair.getPublic();
            byte[] privateKeyBytes = privateKey.getD().toByteArray();
            byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);

            KeyFactory keyFactory = KeyFactory.getInstance("EC","BC");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKeyJava = keyFactory.generatePublic(publicKeySpec);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKeyJava = keyFactory.generatePrivate(privateKeySpec);*/

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp521r1"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            X509Certificate certificate = generateCertificate(keyPair.getPublic(), serverPrivateKey);

            keyStore.setKeyEntry(user.getName(), keyPair.getPrivate(), user.getPassword().toCharArray(), new Certificate[]{certificate});

            FileOutputStream fos = new FileOutputStream(configuration.getPathKeyStore());
            keyStore.store(fos,configuration.getServerKey().toCharArray());
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

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256withECDSA")
                .setProvider("BC")
                .build(serverPrivateKey);

        X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);

        return new JcaX509CertificateConverter()
                .setProvider("BC")
                .getCertificate(certificateHolder);
    }

    public Either<Error, PublicKey> getPublicKey(Usuario user) {
        try {
            FileInputStream fis = new FileInputStream(configuration.getPathKeyStore());
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getServerKey().toCharArray();
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
        try {
            FileInputStream fis = new FileInputStream(configuration.getPathKeyStore());
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getServerKey().toCharArray();
            keyStore.load(fis, keyStorePassword);
            return Either.right((PrivateKey) keyStore.getKey(user.getName(), user.getPassword().toCharArray()));
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
            Cipher cipher = Cipher.getInstance("ECIES", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, provideParams());
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            return Either.right(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_ENCRYPTING);
        }
    }

    private IESParameterSpec provideParams() {
        byte[] derivation = new byte[16];
        byte[] encoding = new byte[16];
        new SecureRandom().nextBytes(derivation);
        new SecureRandom().nextBytes(encoding);
        return new IESParameterSpec(derivation, encoding, 128, 128, null);
    }

    public Either<Error, String> decipher(String text, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("ECIES", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey,provideParams());
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            return Either.right(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Either.left(ServiceError.ERROR_ENCRYPTING);
        }
    }
}
