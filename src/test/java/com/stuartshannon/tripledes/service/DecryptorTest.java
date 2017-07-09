package com.stuartshannon.tripledes.service;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.stuartshannon.tripledes.encryption.IEncryption;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Created by stuartshannon on 09/07/2017.
 */
public class DecryptorTest
{
    private static final String PLAIN_TEXT = "textToEncrypt";
    private static final String ECRYPTED = "nkYvPcZZjyeTTj3Kw6+5Pg==";

    private IEncryption encryption;

    @Inject
    private IDecryptor decryptor;

    @Before
    public void setup()
    {
        encryption = mock(IEncryption.class);
        Guice.createInjector(getTestModule()).injectMembers(this);

        decryptor = new Decryptor(encryption);
    }

    private Module getTestModule() {
        return new AbstractModule() {
            @Override protected void configure()
            {
                bind(IEncryption.class).toInstance(encryption);
                bind(IDecryptor.class).to(Decryptor.class);
            }
        };
    }

    @Test
    public void correctlyDecrypts() throws Exception
    {
        //given
        String expected = PLAIN_TEXT;
        given(encryption.decrypt(anyString())).willReturn(expected);

        //when
        String actual = decryptor.decrypt(ECRYPTED);

        //then
        assertThat(actual, is(expected));
    }
}
