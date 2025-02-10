package utilities.security_ex3;

import com.google.common.primitives.Bytes;
import config.ConfigurationFicheros;
import domain.model.Usuario;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import utilities.Constantes;

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
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Log4j2
public class Asymmetric {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final ConfigurationFicheros configuration;

    public Asymmetric(ConfigurationFicheros configuration) {
        this.configuration = configuration;
    }

    public Either<String, Void> saveUserKeys(Usuario user) {
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
            keyStore.store(fos, configuration.getKeyStorePassword().toCharArray());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.left("No se ha podido dar de alta al usuario");
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
}