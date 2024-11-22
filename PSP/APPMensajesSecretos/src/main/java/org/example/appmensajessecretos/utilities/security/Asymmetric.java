package org.example.appmensajessecretos.utilities.security;

import io.vavr.control.Either;
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
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.example.appmensajessecretos.config.ConfigurationFicheros;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.PBEKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.*;
import java.util.Base64;
import java.util.Date;

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

            X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
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

            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKeyJava = keyFactory.generatePublic(publicKeySpec);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKeyJava = keyFactory.generatePrivate(privateKeySpec);

            X509Certificate certificate = generateCertificate(publicKeyJava, serverPrivateKey);

            keyStore.setKeyEntry(user.getName(), privateKeyJava, user.getPassword().toCharArray(), new Certificate[]{certificate});

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (OperatorCreationException e) {
            throw new RuntimeException(e);
        }
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Either<Error, PrivateKey> getPrivateKey(Usuario user) {
        try {
            FileInputStream fis = new FileInputStream(configuration.getPathKeyStore());
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getServerKey().toCharArray();
            keyStore.load(fis, keyStorePassword);
            return Either.right((PrivateKey) keyStore.getKey(user.getName(), user.getPassword().toCharArray()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public KeySpec getRandomKey() {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        byte[] password = new byte[16];
        sr.nextBytes(password);
        return new PBEKeySpec(Base64.getUrlEncoder().encodeToString(password).toCharArray(), salt, 100000, 256);
    }

    public Either<Error, String> cipher(String text, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            return Either.right(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public Either<Error, String> decipher(String text, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            return Either.right(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

}
