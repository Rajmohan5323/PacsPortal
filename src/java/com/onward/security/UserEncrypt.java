package com.onward.security;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UserEncrypt
{
  private static FileInputStream fis = null;
  private static BufferedInputStream bis = null;
  private static DataInputStream dis = null;
  Cipher ecipher;
  Cipher dcipher;
  byte[] salt = { -87, -101, -56, 50, 86, 53, -29, 3 };
  int iterationCount = 19;
  private String secretKey = "ezeon8547";

  public String decrypt(String encryptedText)
    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException
  {
    KeySpec keySpec = new PBEKeySpec(this.secretKey.toCharArray(), this.salt, this.iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
    this.dcipher = Cipher.getInstance(key.getAlgorithm());
    this.dcipher.init(2, key, paramSpec);
    byte[] enc = new BASE64Decoder().decodeBuffer(encryptedText);
    byte[] utf8 = this.dcipher.doFinal(enc);
    String charSet = "UTF-8";
    String plainStr = new String(utf8, charSet);
    return plainStr;
  }

  public String encrypt(String content)
    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
  {
    KeySpec keySpec = new PBEKeySpec(this.secretKey.toCharArray(), this.salt, this.iterationCount);
    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
    String encStr = "";
    try
    {
      this.ecipher = Cipher.getInstance(key.getAlgorithm());
      this.ecipher.init(1, key, paramSpec);
      String charSet = "UTF-8";
      byte[] in = content.getBytes(charSet);
      byte[] out = this.ecipher.doFinal(in);
      encStr = new BASE64Encoder().encode(out);
     } catch (Exception e) {
      e.getStackTrace();
    }
     return encStr;
  }
}