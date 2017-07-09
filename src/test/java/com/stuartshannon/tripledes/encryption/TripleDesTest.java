package com.stuartshannon.tripledes.encryption;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by stuartshannon on 08/07/2017.
 */
public class TripleDesTest
{
    private static final String PLAIN_TEXT = "textToEncrypt";
    private static final String ENCRYPTED = "nkYvPcZZjyeTTj3Kw6+5Pg==";

    private IEncryption encryption;

    @Before
    public void setup()
    {
        encryption = new TripleDes();
    }

    @Test
    public void correctlyEncrypts() throws Exception
    {
        //given
        String expected = ENCRYPTED;

        //when
        String actual = encryption.encrypt(PLAIN_TEXT);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void correctlyDecrypts() throws Exception
    {
        //given
        String expected = PLAIN_TEXT;

        //when
        String actual = encryption.decrypt(ENCRYPTED);

        //then
        assertThat(actual, is(expected));
    }
}
