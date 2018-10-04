package com.comoressoft.keystore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.KeyStore.Entry;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JKSGenerator {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JKSGenerator.class);

    public String generateJKS(String keyStoreName, String keyStorePassword) {
        JKSService jks = null;
        try {
            jks = new JKSService("JKS", keyStorePassword, keyStoreName);

            jks.createEmptyKeyStore();

            char[] pwd = keyStorePassword.toCharArray();
            // generate private key from paireKey
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair KPair = keyPairGenerator.generateKeyPair();

            PrivateKey key = KPair.getPrivate();

            Calendar after = Calendar.getInstance();
            Calendar befor = Calendar.getInstance();
            befor.add(Calendar.YEAR, 25);

            X500Principal owner = new X500Principal("CN=gamo-byopen.com, OU=None, O=None L=None, C=None");
            X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(owner, BigInteger.valueOf(new SecureRandom().nextInt()),
                    after.getTime(), befor.getTime(), owner, KPair.getPublic());
            ContentSigner signer;
            try {
                signer = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(key);

                X509CertificateHolder certHolder = builder.build(signer);

                X509Certificate cert = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(certHolder);
                cert.verify(KPair.getPublic());

                KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(pwd);
                Entry entry = new KeyStore.PrivateKeyEntry(key, new Certificate[] {cert});
                String alias = keyStoreName ;
                jks.setEntry(alias, entry, protectionParameter);

                jks.saveKeyStore(pwd, jks.getKeyStorPath());
            } catch (OperatorCreationException | InvalidKeyException | NoSuchProviderException | SignatureException e) {
                e.printStackTrace();
                LOGGER.error("Errors occurred in ApiJKSControler#generateJKS()", e);
            }
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
            e.printStackTrace();
            LOGGER.error("Errors occurred in ApiJKSControler#generateJKS()", e);
        }
        return jks.getKeyStorPath();
    }

    public byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            // read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
}
