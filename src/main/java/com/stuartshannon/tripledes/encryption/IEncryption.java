package com.stuartshannon.tripledes.encryption;

/**
 * Created by stuartshannon on 08/07/2017.
 */
public interface IEncryption
{
    String encrypt(String input) throws Exception;

    String decrypt(String input) throws Exception;
}
