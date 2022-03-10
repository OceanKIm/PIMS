package com.pims.api.utils;

import com.pims.api.cont.Const;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncryptUtil
 * : 암호화 사용 유틸 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-03-10
**/
@Log4j2
@Component
public class EncryptUtil {

    @Value("${SERVER.PASSWORD.ENCRYPT}")
    private String mEncryptType;

    /**
     * MD5 암호화
     *
     * @param message 암호화 될 메시지
     * @return String MD5 암호화 데이터
     * @throws NoSuchAlgorithmException throws
     */
    public String makeMD5(String message) throws NoSuchAlgorithmException {

        String md5Text = "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes());

        try {
            byte byteData[] = md.digest();
            StringBuffer strBuf = new StringBuffer();
            for (byte item : byteData) {
                strBuf.append(Integer.toString((item & 0xFF) + 0x100, 16).substring(1));
            }
            md5Text = strBuf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5Text;
    }

    /**
     * SHA-256 암호화 메소드
     *
     * @param message 암호화 할 메시지
     * @return SHA-256 암호화 메시지
     * @throws NoSuchAlgorithmException throws
     */
    public String makeSHA256(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(message.getBytes());

        return bytesToHex(md.digest());
    }


    /**
     * SHA-512 암호화 메소드
     *
     * @param message 암호화 할 메시지
     * @return SHA-512 암호화 메시지
     * @throws NoSuchAlgorithmException throws
     */
    public String makeSHA512(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(message.getBytes());
        return bytesToHex(md.digest());
    }

    /**
     * bytes to hex
     *
     * @param bytes 
     * @return  String
    */
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * SHA256 암호 체크
     *
     * @param message 
     * @return  boolean
    */
    public boolean isSHA256(String message) {
        return message.length() == 64;
    }

    /**
     * isMD5 암호 체크
     *
     * @param message 
     * @return  boolean
    */
    public boolean isMD5(String message) {
        return message.length() == 32;
    }

}
