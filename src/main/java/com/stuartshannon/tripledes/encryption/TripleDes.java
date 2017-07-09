package com.stuartshannon.tripledes.encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.function.BiFunction;

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
        return templateMethod(input, Cipher.ENCRYPT_MODE, new Encryptor());
    }

    public String decrypt(String input) throws Exception
    {
        return templateMethod(input, Cipher.DECRYPT_MODE, new Decryptor());
    }

    private String templateMethod(String input, int cipherMode, BiFunction<byte[], Cipher, byte[]> converter) throws Exception
    {
        byte[] inputBytes = input.getBytes(UTF_8);

        byte[] keyBytes = keyBytes();
        Cipher cipher = initCipher(cipherMode, keyBytes);

        byte[] result = converter.apply(inputBytes, cipher);

        return new String(result, UTF_8);
    }

    private static class Decryptor implements BiFunction<byte[], Cipher, byte[]>
    {
        public byte[] apply(byte[] input, Cipher cipher)
        {
            try
            {
                byte[] interim = Base64.decodeBase64(input);
                return cipher.doFinal(interim);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private static class Encryptor implements BiFunction<byte[], Cipher, byte[]>
    {
        public byte[] apply(byte[] input, Cipher cipher)
        {
            try
            {
                byte[] interim = cipher.doFinal(input);
                return Base64.encodeBase64(interim);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private byte[] keyBytes() throws Exception
    {
        MessageDigest md = MessageDigest.getInstance(SHA_1);
        byte[] digestOfPassword = md.digest(secretKey.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, NEW_ARRAY_LENGTH);

        return keyBytes;
    }

    private Cipher initCipher(int cipherMode, byte[] keyBytes) throws Exception
    {
        SecretKey key = new SecretKeySpec(keyBytes, DES_EDE);
        Cipher cipher = Cipher.getInstance(DES_EDE);
        cipher.init(cipherMode, key);

        return cipher;
    }

}
