/**
 * 
 */
package com.github.herong.comm.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.github.herong.comm.util.Util;

/**
 * 摘要生成工具类
 * 
 * @author herong
 * @createTime 2013-7-5 下午02:25:48
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class MessageDigestUtil {

    public static final String ALGORITHM_SHA = "SHA-1";
    public static final String ALGORITHM_MD5 = "MD5";

    /**
     * 内容生成摘要
     * 
     * @param data
     *            内容
     * @return 摘要
     * @throws Exception
     *             if no Provider supports a MessageDigestSpi implementation for
     *             the specified algorithm.
     */
    public static byte[] createDigest(byte[] data, String algorithm) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance(algorithm);
            sha.reset();
            sha.update(data);
            return sha.digest();
        } catch (Exception e) {
            throw new Exception("生成摘要失败," + e.getLocalizedMessage(), e);
        }

    }
    
    /**
     * 使用SHA算法生成摘要
     * @param data 内容
     * @return 摘要
     * @throws Exception
     */
    public static byte[] createDigest(byte[] data) throws Exception {
        return createDigest(data,ALGORITHM_SHA);

    }
    
    /**
     * 使用SHA算法生成摘要，以16进制返回
     * @param data 内容
     * @return 摘要
     * @throws Exception
     */
    public static String createDigestAsHexStr(byte[] data) throws Exception {
        byte[] b = createDigest(data,ALGORITHM_SHA);
        return Util.byteArr2HexStr(b);

    }
    
    
    /**
     * 文件生成摘要
     * 
     * @param fileName
     *            文件路径
     * @return 摘要
     */
    public static String createFileDigest(String fileName) {
        return null;

    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, Exception {
        String data = "";
        byte[] digest = MessageDigestUtil.createDigest(data.getBytes("UTF-8"));
        System.out.println("data:"+data+"\n digest:"+Util.byteArr2HexStr(digest));
    }
}
