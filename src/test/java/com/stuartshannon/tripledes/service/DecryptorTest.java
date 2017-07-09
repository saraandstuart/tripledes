package com.stuartshannon.tripledes.service;

import com.stuartshannon.tripledes.encryption.IEncryption;
import com.stuartshannon.tripledes.encryption.TripleDes;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by stuartshannon on 09/07/2017.
 */
public class DecryptorTest
{
    private static final String UNECRYPTED = "textToEncrypt";
    private static final String ECRYPTED = "nkYvPcZZjyeTTj3Kw6+5Pg==";

    private IDecryptor decryptor;

    @Before
    public void setup()
    {
        IEncryption encryption = new TripleDes();
        decryptor = new Decryptor(encryption);
    }

    @Test
    public void correctlyDecrypts() throws Exception
    {
        //given
        String expected = UNECRYPTED;

        //when
        String actual = decryptor.decrypt(ECRYPTED);

        //then
        assertThat(actual, is(expected));
    }
}
