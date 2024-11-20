package org.example.appmensajessecretos.utilities.security;

import io.vavr.control.Either;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.example.appmensajessecretos.config.ConfigurationFicheros;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.utilities.Constantes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class Asymmetric {

    private final ConfigurationFicheros configuration;

    public Asymmetric(ConfigurationFicheros configuration) {
        this.configuration = configuration;
    }

    public CompletableFuture<Either<Error, Void>> saveUserKeys(Usuario user) {
        try {
            FileInputStream fis = new FileInputStream(configuration.getPathKeyStore());
            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
            char[] keyStorePassword = configuration.getServerKey().toCharArray();
            keyStore.load(fis, keyStorePassword);
            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(Constantes.SERVER, entryPassword);
            PrivateKey serverPrivateKey = privateKeyEntry.getPrivateKey();


            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp521r1"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();
            cert1.setSerialNumber(BigInteger.valueOf(1));   //or generate a random number
            cert1.setSubjectDN(new X509Principal("CN=Oscar"));  //see examples to add O,OU etc
            cert1.setIssuerDN(new X509Principal("CN=Oscar")); //same since it is self-signed
            cert1.setPublicKey(clavesRSA.getPublic());
            cert1.setNotBefore(
                    Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm("SHA256WithRSAEncryption");
            PrivateKey signingKey = clavesRSA.getPrivate();


            X509Certificate cert =  cert1.generate(signingKey);


        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
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
        }

        // Generar el certificado autofirmado
        X509Certificate certificate = generateSignedCertificate(keyPair);

        // Crear un keystore de tipo JKS
        KeyStore keyStore = KeyStore.getInstance("JKS");
        char[] password = "password".toCharArray(); // Contraseña para proteger el keystore
        keyStore.load(null, password);

        // Guardar la clave privada y el certificado en el keystore
        keyStore.setKeyEntry("alias2", keyPair.getPrivate(), password, new Certificate[]{certificate});

        // Guardar el keystore en un archivo
        try (FileOutputStream fos = new FileOutputStream("keystore.jks")) {
            keyStore.store(fos, password);
        }
    }
}
