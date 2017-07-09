package com.stuartshannon.tripledes.service;

import com.google.inject.Inject;
import com.stuartshannon.tripledes.encryption.IEncryption;

/**
 * Created by stuartshannon on 09/07/2017.
 */
public class Decryptor implements IDecryptor
{
    private IEncryption encryption;

    @Inject
    public Decryptor(IEncryption encryption)
    {
        this.encryption = encryption;
    }

    public String decrypt(String input)
    {
        try
        {
            return encryption.decrypt(input);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
