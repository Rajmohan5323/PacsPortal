
package com.onward.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import java.net.URL;
import sun.misc.BASE64Encoder;
import java.security.Key;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import javax.crypto.Cipher;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class EncEncrypt {

    private static FileInputStream fis;
    private static BufferedInputStream bis;
    private static DataInputStream dis;
    Cipher ecipher;
    Cipher dcipher;
    byte[] salt;
    int iterationCount;
    private String secretKey;
    private String plainStr;

    public EncEncrypt() {
        
        this.salt = new byte[]{-87, -101, -56, 50, 86, 52, -29, 3};
        this.iterationCount = 19;
        this.secretKey = "VFhiOKIrMT1OMUbmswra7A";
    }
    public String encrypt(String plainText) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
        URL location = UserEncrypt.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getFile();
        String filePath = new File(path).getParent();
        KeySpec keySpec = new PBEKeySpec(this.secretKey.toCharArray(), this.salt, this.iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
        (this.ecipher = Cipher.getInstance(key.getAlgorithm())).init(1, key, paramSpec);
        String charSet = "UTF-8";
        byte[] in = plainText.getBytes(charSet);
        byte[] out = this.ecipher.doFinal(in);
        String encStr = new BASE64Encoder().encode(out);
        return encStr;
    }

    static {
        EncEncrypt.fis = null;
        EncEncrypt.bis = null;
        EncEncrypt.dis = null;
    }
    
    
   
}
