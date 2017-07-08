package com.stuartshannon.scratch.encryption;

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

    public String encrypt(String message) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance(SHA_1);
        byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, NEW_ARRAY_LENGTH);

        SecretKey key = new SecretKeySpec(keyBytes, DES_EDE);
        Cipher cipher = Cipher.getInstance(DES_EDE);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainTextBytes = message.getBytes(UTF_8);
        byte[] buf = cipher.doFinal(plainTextBytes);

        byte [] base64Bytes = Base64.encodeBase64(buf);
        String base64EncryptedString = new String(base64Bytes, UTF_8);

        return base64EncryptedString;
    }

    public String decrypt(String encryptedText) throws Exception
    {
        byte[] bytes = encryptedText.getBytes(UTF_8);
        byte[] message = Base64.decodeBase64(bytes);

        MessageDigest md = MessageDigest.getInstance(SHA_1);
        byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, NEW_ARRAY_LENGTH);

        SecretKey key = new SecretKeySpec(keyBytes, DES_EDE);
        Cipher decipher = Cipher.getInstance(DES_EDE);
        decipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = decipher.doFinal(message);
        String decryptedString = new String(plainText, UTF_8);

        return decryptedString;
    }

}
