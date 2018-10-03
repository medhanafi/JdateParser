package com.comoressoft.keystore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeyStoreGenerator {

	private KeyStore keyStore;

	private String keyStoreType;
	private String keyStorePassword;
	private String keyStorPath;

	KeyStoreGenerator(String keyStoreType, String keyStorePassword, String keyStoreName)
			throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
		this.keyStoreType = keyStoreType;
		this.keyStorePassword = keyStorePassword;
		this.keyStorPath = keyStoreName + ".jks";
	}

	void createEmptyKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
		if (keyStoreType == null || keyStoreType.isEmpty()) {
			keyStoreType = KeyStore.getDefaultType();
		}
		keyStore = KeyStore.getInstance(keyStoreType);
		// load
		char[] pwdArray = keyStorePassword.toCharArray();
		keyStore.load(null, pwdArray);
		this.saveKeyStore(pwdArray, keyStorPath);

	}

	void saveKeyStore(char[] pwdArray, String keyStorPath)
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		// Save the keyStore
		FileOutputStream fos = new FileOutputStream(keyStorPath);
		keyStore.store(fos, pwdArray);
		fos.close();
	}

	void loadKeyStore() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
		char[] pwdArray = keyStorePassword.toCharArray();
		keyStore.load(new FileInputStream(keyStorPath), pwdArray);
	}

	 void setEntry(String alias, Entry entry, ProtectionParameter protectionParameter) throws KeyStoreException {
		keyStore.setEntry(alias, entry, protectionParameter);

	}
	
	 String getKeyStorPath() {
		 return this.keyStorPath;
	 }
	
}
