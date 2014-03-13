/**
 * 
 */
package com.github.herong.comm.security;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.github.herong.comm.util.Util;

/**
 * DES加解密
 * 
 * @author herong
 * @createTime 2013-7-11 上午10:41:55
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class DESEncryptor implements IEncryptor {

   
    /* (non-Javadoc)
     * @see cn.sinobest.comm.security.IEncryptor#decrypt(byte[], byte[])
     */
    public byte[] decrypt( byte[] key,byte[] encryptedData) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte decryptedData[] = cipher.doFinal(encryptedData);
            return decryptedData;
        } catch (Exception e) {
            throw new Exception("解密出错," + e.getLocalizedMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see cn.sinobest.comm.security.IEncryptor#encrypt(byte[], byte[])
     */
    public byte[] encrypt(byte[] key,byte[] sourceData) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte encryptedData[] = cipher.doFinal(sourceData);
        return encryptedData;
    }

    public static void test() throws UnsupportedEncodingException, Exception{
        String data = "aaaaaa";
        String key = "hnsibmex";//8位
        DESEncryptor des = new DESEncryptor();
        System.out.println(data);
        byte[] enByte = des.encrypt(key.getBytes(),"rational".getBytes("UTF-8"));
        enByte = des.encrypt(enByte,data.getBytes("UTF-8"));
        String enStr = Util.byteArr2HexStr(enByte);
        System.out.println(enStr);

        /*String deStr = new String(des.decrypt(key.getBytes(),Util.hexStr2ByteArr(enStr)), "UTF-8");
        System.out.println(deStr);*/
    }
    
    public static void test0() throws UnsupportedEncodingException, Exception {
        String data = "测试加解密!";
        String key = "密钥密钥密钥";//8位
        DESEncryptor des = new DESEncryptor();
        System.out.println(data);
        byte[] enByte = des.encrypt(key.getBytes(),data.getBytes("UTF-8"));
        String enStr = Util.byteArr2HexStr(enByte);
        System.out.println(enStr);

        String deStr = new String(des.decrypt(key.getBytes(),Util.hexStr2ByteArr(enStr)), "UTF-8");
        System.out.println(deStr);
    }
    public static void main(String[] args) throws UnsupportedEncodingException, Exception {
        
        test0();
        test();

    }
}
