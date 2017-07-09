package com.stuartshannon.tripledes.module;

import com.google.inject.AbstractModule;
import com.stuartshannon.tripledes.encryption.IEncryption;
import com.stuartshannon.tripledes.encryption.TripleDes;
import com.stuartshannon.tripledes.service.Decryptor;
import com.stuartshannon.tripledes.service.IDecryptor;

/**
 * Created by stuartshannon on 09/07/2017.
 */
public class DecryptorModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IEncryption.class).to(TripleDes.class);

        bind(IDecryptor.class).to(Decryptor.class);
    }
}
