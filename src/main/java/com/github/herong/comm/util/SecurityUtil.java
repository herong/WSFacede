/**
 * 
 */
package com.github.herong.comm.util;

import com.github.herong.comm.security.DESEncryptor;
import com.github.herong.comm.security.IEncryptor;
import com.github.herong.comm.security.RSAEncryptor;

/**
 * 安全相关功能
 * 
 * @author herong
 * @createTime 2013-7-12 上午09:20:33
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class SecurityUtil {


    /**
     * 二次解密
     * 
     * @param key1
     *            密钥
     * @param key2
     *            密钥
     * @param data
     *            密文
     * @return 明文
     * @throws Exception 
     */
    public static String decryptTwo(byte[] key1, byte[] key2, byte[] data) throws Exception {
        try {
            IEncryptor rsa = new RSAEncryptor();
            byte[] deStr2 = rsa.decrypt(key2, data);
            IEncryptor des = new DESEncryptor();
            byte[] deStr1 = des.decrypt(key1,deStr2);
            String deStr2String = new String(deStr1,"UTF-8");
            return deStr2String;
         } catch (Exception ex) {
            throw new Exception("解密失败," + ex.getLocalizedMessage(), ex);
        }
    }

   
}
