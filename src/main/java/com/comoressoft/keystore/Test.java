package com.comoressoft.keystore;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    public static void main(String[] args) {

        writeContent("userName", "userPasswd");
    }

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void writeContent(final String keyStoreName, final String keyStorePassword) {
        JKSGenerator jksGenerator = new JKSGenerator();
        String jksFile = jksGenerator.generateJKS(keyStoreName, keyStorePassword);

        LOGGER.info("FILE : {}", jksFile);

    }

}
