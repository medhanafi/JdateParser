package com.comoressoft.keystore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.x509.X509V3CertificateGenerator;

public class GenerateKey {

	public static void main(String[] args) throws CertificateException, KeyStoreException, IOException, OperatorCreationException, InvalidKeyException, NoSuchProviderException, SignatureException {
		 try {
		 char[] pwdArray = "keyStorePassword".toCharArray();
		 JavaKeyStore jk=new JavaKeyStore("JKS", "keyStorePassword", "keyStoreName");
		 SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
		 KeyStore.SecretKeyEntry secretKeyEntry = new
		 KeyStore.SecretKeyEntry(secretKey);
		 KeyStore.ProtectionParameter protectionParameter = new
		 KeyStore.PasswordProtection(pwdArray);
		 jk.createEmptyKeyStore();
		 jk.loadKeyStore();
		 jk.setEntry("keyStoreName", secretKeyEntry, protectionParameter);
		
		
		
		
		 } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException
		 | IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		try {
			
			char[] pwdArray = "keyStorePassword".toCharArray();
			 JavaKeyStore jk=new JavaKeyStore("JKS", "keyStorePassword", "keyStoreName");
			 
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair KPair = keyPairGenerator.generateKeyPair();
			
			PrivateKey key = KPair.getPrivate();
			
			Calendar after = Calendar.getInstance();
			Calendar befor = Calendar.getInstance();
			befor.add(Calendar.YEAR, 1);
			
			 X500Principal owner = new X500Principal("CN=domaine.com, OU=None, O=None L=None, C=None");
			    X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(owner, BigInteger.valueOf(new SecureRandom().nextInt()), new Date(), new Date(), owner, KPair.getPublic());
			    ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(key);
			    X509CertificateHolder certHolder = builder.build(signer);
			    X509Certificate cert = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(certHolder);
			    cert.verify(KPair.getPublic());
			    
			    KeyStore.ProtectionParameter protectionParameter = new
			   		 KeyStore.PasswordProtection("password".toCharArray());
			    Entry entry = new KeyStore.PrivateKeyEntry(key, new Certificate[]{ cert });
				jk.setEntry("alias", entry, protectionParameter);
			
			
			X509V3CertificateGenerator v3CertGen = new X509V3CertificateGenerator();
			
			v3CertGen.setSerialNumber(BigInteger.valueOf(new SecureRandom().nextInt()));
	        v3CertGen.setIssuerDN(new X500Principal("CN=domaine.com, OU=None, O=None L=None, C=None"));
	        v3CertGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30));
	        v3CertGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365*10)));
	        v3CertGen.setSubjectDN(new X500Principal("CN=domain.com, OU=None, O=None L=None, C=None"));
	        
	        
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//https://www.programcreek.com/java-api-examples/?api=org.bouncycastle.cert.X509v3CertificateBuilder
	//http://www.bouncycastle.org/latest_releases.html
	//https://www.javatips.net/api/netty-master/netty-4.1/handler/src/main/java/io/netty/handler/ssl/util/BouncyCastleSelfSignedCertGenerator.java

	//BEST //http://blog.thilinamb.com/2010/01/how-to-generate-self-signed.html
}
