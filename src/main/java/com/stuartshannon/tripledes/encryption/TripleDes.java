package com.stuartshannon.tripledes.encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by stuartshannon on 08/07/2017.
 */
public class TripleDes implements IEncryption
{
    private static final String UTF_8 = "utf-8";
    private static final String SHA_1 = "SHA-1";
    private static final String DES_EDE = "DESede";
    private static final int NEW_ARRAY_LENGTH = 24;

    private static final String DEFAULT_SECRET_KEY = "SecretKey";

    private String secretKey;

    public TripleDes()
    {
        this(DEFAULT_SECRET_KEY);
    }

    public TripleDes(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public String encrypt(String input) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance(SHA_1);
        byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, NEW_ARRAY_LENGTH);

        SecretKey key = new SecretKeySpec(keyBytes, DES_EDE);
        Cipher cipher = Cipher.getInstance(DES_EDE);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainTextBytes = input.getBytes(UTF_8);

        byte[] buf = cipher.doFinal(plainTextBytes);
        byte[] base64Bytes = Base64.encodeBase64(buf);

        return new String(base64Bytes, UTF_8);
    }

    public String decrypt(String input) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance(SHA_1);
        byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, NEW_ARRAY_LENGTH);

        SecretKey key = new SecretKeySpec(keyBytes, DES_EDE);
        Cipher cipher = Cipher.getInstance(DES_EDE);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] encryptedBytes = input.getBytes(UTF_8);

        byte[] plainTextBytes = Base64.decodeBase64(encryptedBytes);
        byte[] plainText = cipher.doFinal(plainTextBytes);

        return new String(plainText, UTF_8);
    }

}
