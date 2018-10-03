package com.comoressoft.keystore;

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

public class MainKey {

	public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, OperatorCreationException, InvalidKeyException, NoSuchProviderException, SignatureException {
		KeyStoreGenerator jks=new KeyStoreGenerator("JKS", "keyStorePassword", "keyStoreName");
		jks.createEmptyKeyStore();
		
		char[] pwd="keyStorePassword".toCharArray();
		//generate private key from paireKey
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair KPair = keyPairGenerator.generateKeyPair();
		
		PrivateKey key = KPair.getPrivate();
		
		
		Calendar after = Calendar.getInstance();
		Calendar befor = Calendar.getInstance();
		befor.add(Calendar.YEAR, 25);
		
		 X500Principal owner = new X500Principal("CN=domaine.com, OU=None, O=None L=None, C=None");
		    X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(owner, BigInteger.valueOf(new SecureRandom().nextInt()), after.getTime(), befor.getTime(), owner, KPair.getPublic());
		    ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(key);
		    X509CertificateHolder certHolder = builder.build(signer);
		   
		    X509Certificate cert = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(certHolder);
		    cert.verify(KPair.getPublic());
		    
		    KeyStore.ProtectionParameter protectionParameter = new
		   		 KeyStore.PasswordProtection(pwd);
		    Entry entry = new KeyStore.PrivateKeyEntry(key, new Certificate[]{ cert });
			jks.setEntry("alias", entry, protectionParameter);
			
			jks.saveKeyStore(pwd, jks.getKeyStorPath());
	}
}
