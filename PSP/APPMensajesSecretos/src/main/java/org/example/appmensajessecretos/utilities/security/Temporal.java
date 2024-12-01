//package org.example.appmensajessecretos.utilities.security;
//
//import io.vavr.control.Either;
//import org.bouncycastle.asn1.x500.X500Name;
//import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
//import org.bouncycastle.cert.X509CertificateHolder;
//import org.bouncycastle.cert.X509v3CertificateBuilder;
//import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
//import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.jce.spec.IESParameterSpec;
//import org.bouncycastle.operator.ContentSigner;
//import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
//import org.example.appmensajessecretos.Configuration;
//import org.example.appmensajessecretos.domain.error.DataBaseError;
//import org.example.appmensajessecretos.domain.error.DataInputError;
//import org.example.appmensajessecretos.domain.error.Error;
//import org.example.appmensajessecretos.domain.error.ServiceError;
//import org.example.appmensajessecretos.domain.model.Usuario;
//import org.example.appmensajessecretos.utilities.Constantes;
//
//import javax.crypto.Cipher;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.math.BigInteger;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.cert.Certificate;
//import java.security.cert.X509Certificate;
//import java.security.spec.ECGenParameterSpec;
//import java.util.Base64;
//import java.util.Date;
//
//public class Temporal {
//
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }
//    private final Configuration configuration;
//
//    public Temporal(Configuration configuration) {
//        this.configuration = configuration;
//    }
//
//
//    public static void main(String[] args) {
////        String text = "hola";
////
////        try {
////            Cipher cipher = Cipher.getInstance(Constantes.ECIES, Constantes.BC);
////            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
////            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
////            System.out.println(Base64.getEncoder().encodeToString(cipher.doFinal(byteText)));
////        } catch (Exception e) {
////            System.out.println("Error");
////        }
////        try {
////            Cipher cipher = Cipher.getInstance(Constantes.ECIES, Constantes.BC);
////            cipher.init(Cipher.DECRYPT_MODE, privateKey);
////            byte[] byteText = Base64.getDecoder().decode(text);
////            System.out.println(new String(cipher.doFinal(byteText), StandardCharsets.UTF_8));
////        } catch (Exception e) {
////            System.out.println("Error");
////        }
//    }
//
//    private IESParameterSpec provideParams() {
//        byte[] derivation = new byte[16];
//        byte[] encoding = new byte[16];
//        new SecureRandom().nextBytes(derivation);
//        new SecureRandom().nextBytes(encoding);
//        return new IESParameterSpec(derivation, encoding, 128, 128, null);
//    }
//    public Either<Error, PublicKey> getPublicKey(Usuario user) {
//        try (FileInputStream fis = new FileInputStream(configuration.getPathKeyStore())) {
//            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
//            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
//            keyStore.load(fis, keyStorePassword);
//            return Either.right(keyStore.getCertificate(user.getName()).getPublicKey());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public Either<Error, PrivateKey> getPrivateKey(Usuario user) {
//        try (FileInputStream fis = new FileInputStream(configuration.getPathKeyStore())) {
//            KeyStore keyStore = KeyStore.getInstance(Constantes.KEY_STORE_TYPE);
//            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
//            keyStore.load(fis, keyStorePassword);
//            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(
//                    user.getName(), new KeyStore.PasswordProtection(user.getPassword().toCharArray()));
//            return Either.right(pkEntry.getPrivateKey());
//        } catch (UnrecoverableKeyException e) {
//            log.error(e.getMessage(),e);
//            return Either.left(DataInputError.INCORRECT_PASSWORD);
//        } catch (IOException e) {
//            log.error(e.getMessage(),e);
//            return Either.left(DataBaseError.ERROR_READING_FILE);
//        } catch (Exception e) {
//            log.error(e.getMessage(),e);
//            return Either.left(ServiceError.ERROR_GETTING_PRIVATE_KEY);
//        }
//    }
//}
//
///*// Generar un par de claves usando ECDSA
//KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
//            keyPairGenerator.
//
//initialize(new ECGenParameterSpec("secp521r1"));
//KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//// Generar el certificado autofirmado
//X509Certificate certificate = generateSelfSignedCertificate(keyPair);
//
//// Crear un keystore de tipo JKS
//KeyStore keyStore = KeyStore.getInstance("JKS");
//char[] password = "accesoKeyStore".toCharArray(); // Contraseña para proteger el keystore
//            keyStore.
//
//load(null,password);
//
//// Guardar la clave privada y el certificado en el keystore
//            keyStore.
//
//setKeyEntry("server",keyPair.getPrivate(),password, new Certificate[]{certificate});
//
//        // Guardar el keystore en un archivo
//        try(
//FileOutputStream fos = new FileOutputStream("keystore.jks")){
//        keyStore.
//
//store(fos, password);
//            }
//
//                    System.out.
//
//println("Keystore creado con éxito, incluyendo clave privada y certificado.");
//        }catch(
//Exception e){
//        e.
//
//printStackTrace();
//        }
//                }
//
//// Método para generar un certificado autofirmado usando X509v3CertificateBuilder
//private static X509Certificate generateSelfSignedCertificate(KeyPair keyPair)
//        throws Exception {
//    // Configurar el DN del emisor y el sujeto
//    X500Name issuer = new X500Name("CN=Test Certificate");
//    X500Name subject = issuer;
//
//    // Definir el periodo de validez del certificado
//    Date notBefore = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24); // Un día antes
//    Date notAfter = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365); // Válido por un año
//
//    // Número de serie único
//    BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());
//
//    // Crear el constructor del certificado
//    SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());
//    X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
//            issuer, serialNumber, notBefore, notAfter, subject, subjectPublicKeyInfo
//    );
//
//    // Crear el firmante de contenido para firmar el certificado con la clave privada
//    ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256withECDSA")
//            .setProvider("BC")
//            .build(keyPair.getPrivate());
//
//    // Generar el certificado
//    X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);
//
//    // Convertir el certificado de BouncyCastle a X509Certificate (clase estándar de Java)
//    return new JcaX509CertificateConverter()
//            .setProvider("BC")
//            .getCertificate(certificateHolder);*/