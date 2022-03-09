package com.pims.api.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES256
 * : AES256 암복호화 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-09
**/
@Log4j2
@Component
public class AES256 {

    public static final String alg = "AES/CBC/PKCS5Padding";
    private static final String key = "98569856325412569857452365214585";

    /**
     * ASE256 암호화
     *
     * @param text 대상 문자열
     * @return String 암호화 문자열
    */
    public static String encrypt(String text) throws Exception {
        String iv = key.substring(0, 16);
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        log.debug("============================================================================================");
        log.debug("encrypt aes 215 text before : {}", text);
        log.debug("encrypt aes 215 text after  : {}", Base64.getEncoder().encodeToString(encrypted));
        log.debug("============================================================================================");
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * ASE256 복호화
     *
     * @param cipherText 대상 문자열
     * @return String 복호화 문자열
    */
    public static String decrypt(String cipherText) throws Exception {
        String iv = key.substring(0, 16);
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        String decryptText =  new String(decrypted, "UTF-8");
        log.debug("============================================================================================");
        log.debug("decrypt aes 215 text before : {}", cipherText);
        log.debug("decrypt aes 215 text after  : {}", decryptText);
        log.debug("============================================================================================");
        return decryptText;
    }

}