package com.comoressoft.keystore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateKey {

	public static void main(String[] args) {
		try {
			char[] pwdArray = "keyStorePassword".toCharArray();
			JavaKeyStore jk=new JavaKeyStore("JKS", "keyStorePassword", "keyStoreName");
			SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
			KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
			KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(pwdArray);
			jk.createEmptyKeyStore();
			jk.loadKeyStore();
			jk.setEntry("keyStoreName", secretKeyEntry, protectionParameter);
			
			
			

		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
