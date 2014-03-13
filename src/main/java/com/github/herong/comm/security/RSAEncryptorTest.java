/**
 * 
 */
package com.github.herong.comm.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.github.herong.comm.util.Util;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-5 下午02:21:32
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class RSAEncryptorTest implements IEncryptor {

    private String provider = "BC";

    public RSAEncryptorTest() {
        this("BC");
    }

    public RSAEncryptorTest(String provider) {
        if ("SunJCE".equals(provider)) {
            this.provider = provider;
        } else
            if ("BC".equals(provider)) {
                Security.addProvider(new BouncyCastleProvider());
            }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.security.IEncryptor#decrypt(byte[], byte[])
     */
    public byte[] decrypt(byte[] key, byte[] source) throws Exception {
        // privateKeyByte = Util.getBytes(privateKeyHexStr);
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(key);
            ois = new ObjectInputStream(bais);
            RSAPrivateKey privateKey = (RSAPrivateKey) ois.readObject();
            ois.close();
            if (privateKey != null) {
                Cipher cipher = Cipher.getInstance("RSA", this.provider);
                System.out.println("provider:" + cipher.getProvider().getName());
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] resultBytes = cipher.doFinal(source);
                return resultBytes;
            } else {
                throw new Exception("密钥不能为空!");
            }

        } catch (Exception ex) {
            throw new Exception("解密失败," + ex.getLocalizedMessage(), ex);
        } finally {
            if (ois != null) {
                ois.close();
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.security.IEncryptor#encrypt(byte[], byte[])
     */
    public byte[] encrypt(byte[] key, byte[] source) throws Exception {
        // privateKeyByte = Util.getBytes(privateKeyHexStr);
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(key);
            ois = new ObjectInputStream(bais);
            RSAPublicKey publicKey = (RSAPublicKey) ois.readObject();
            ois.close();
            if (publicKey != null) {
                Cipher cipher = Cipher.getInstance("RSA", this.provider);
                System.out.println("provider:" + cipher.getProvider().getName());
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] resultBytes = cipher.doFinal(source);
                return resultBytes;
            } else {
                throw new Exception("密钥不能为空!");
            }

        } catch (Exception ex) {
            throw new Exception("加密失败," + ex.getLocalizedMessage(), ex);
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }
    
    /**
     * 生成密钥
     * 
     * @return 返回密钥对， 公钥 私钥
     * @throws Exception
     *             生成失败
     */
    public List<byte[]> createKeyPair() throws Exception {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
            // 密钥大小1024位
            keyPairGen.initialize(1024);

            // 生成一个密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(privateKey);
            oos.close();
            byte[] privateKeyByte = bos.toByteArray();

            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(publicKey);
            oos.close();
            byte[] publicKeyByte = bos.toByteArray();

            List<byte[]> keys = new ArrayList<byte[]>(2);
            keys.add(publicKeyByte);
            keys.add(privateKeyByte);
            return keys;
        } catch (Exception ex) {
            throw new Exception("生成突密钥失败," + ex.getLocalizedMessage(), ex);
        }
    }
    
    /**
     * @param args
     * @throws Exception
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, Exception {

        RSAEncryptorTest rsa = new RSAEncryptorTest();
        String msg = "测试加密";
        System.out.println("原文:" + new String(msg.getBytes(), "UTF-8"));
        List<byte[]> keys = rsa.createKeyPair();
        System.out.println("公钥：" + Util.getString(keys.get(0)));
        System.out.println("私钥：" + Util.getString(keys.get(1)));
        byte[] enByte =  rsa.encrypt(keys.get(0), msg.getBytes("UTF-8"));
        System.out.println("密文:" + Util.getString(enByte));
        byte[] deByte = rsa.decrypt(keys.get(1), enByte);
        System.out.println("明文:" + new String(deByte, "UTF-8"));

    }

}
