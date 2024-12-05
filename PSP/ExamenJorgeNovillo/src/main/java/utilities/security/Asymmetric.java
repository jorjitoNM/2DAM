package utilities.security;

import domain.model.Mensaje;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import utilities.Constantes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.Date;

@Log4j2
public class Asymmetric {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public Asymmetric() {

    }

    public Either<String, KeyPair> saveUserKeys(String userName) {
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Constantes.EC, Constantes.BC);
            keyPairGenerator.initialize(new ECGenParameterSpec(Constantes.secp521r1));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return Either.right(keyPair);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.left("Error al generar claves");
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

        ContentSigner contentSigner = new JcaContentSignerBuilder(Constantes.SHA256withECDSA)
                .setProvider(Constantes.BC)
                .build(serverPrivateKey);

        X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);

        return new JcaX509CertificateConverter()
                .setProvider(Constantes.BC)
                .getCertificate(certificateHolder);
    }

    public Either<String, Mensaje> signMessage(Mensaje message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(Constantes.SHA256withECDSA, Constantes.BC);
            signature.initSign(privateKey);
            signature.update(message.getContenido().getBytes(StandardCharsets.UTF_8));

            String sign = Base64.getUrlEncoder().encodeToString(signature.sign());
            return Either.right(new Mensaje(message.getContenido(), message.getGroupName(), sign));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.left("Error firmando");
        }
    }

    public Either<String, Void> checkMessageSign(Mensaje message, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance(Constantes.SHA256withECDSA, Constantes.BC);
            signature.initVerify(publicKey);
            signature.update(message.getContenido().getBytes(StandardCharsets.UTF_8));

            byte[] decodedSign = Base64.getUrlDecoder().decode(message.getSign());
            if (signature.verify(decodedSign)) {
                return Either.right(null);
            } else {
                return Either.left("El mensaje no esta firmado correctamente o ha sido modificado");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.left("No se ha podido comprobar que el mensaje este firmado");
        }
    }
}