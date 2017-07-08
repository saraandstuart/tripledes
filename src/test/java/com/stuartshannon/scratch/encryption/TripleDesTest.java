package com.stuartshannon.scratch.encryption;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by stuartshannon on 08/07/2017.
 */
public class TripleDesTest
{
    private static final String UNECRYPTED = "textToEncrypt";
    private static final String ECRYPTED = "nkYvPcZZjyeTTj3Kw6+5Pg==";

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
        String expected = ECRYPTED;

        //when
        String actual = encryption.encrypt(UNECRYPTED);

        //then
        assertThat(actual, is(expected));
    }

    @Test
    public void correctlyDecrypts() throws Exception
    {
        //given
        String expected = UNECRYPTED;

        //when
        String actual = encryption.decrypt(ECRYPTED);

        //then
        assertThat(actual, is(expected));
    }
}
