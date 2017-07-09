package com.stuartshannon.tripledes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stuartshannon.tripledes.module.DecryptorModule;
import com.stuartshannon.tripledes.service.IDecryptor;

/**
 * Created by stuartshannon on 09/07/2017.
 */
public class App 
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new DecryptorModule());
        IDecryptor decryptor = injector.getInstance(IDecryptor.class);

        String input = "nkYvPcZZjyeTTj3Kw6+5Pg==";
        String result = decryptor.decrypt(input);

        System.out.println("expectedResult=textToEncrypt, actualResult=" + result);
    }
}
