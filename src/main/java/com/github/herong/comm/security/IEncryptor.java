/**
 * 
 */
package com.github.herong.comm.security;

/**
 * 加密器
 * @author herong
 * @createTime 2013-7-5 下午02:17:01
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public interface IEncryptor {
    /**
     * 加密
     * @param key 密钥
     * @param source 明文
     * @return 密文
     * @throws Exception 
     */
    public byte[] encrypt(byte[] key,byte[] source) throws Exception;
    /**
     * 解密
     * @param key 密钥
     * @param source 密文
     * @return 明文
     * @throws Exception 
     */
    public byte[] decrypt(byte[] key,byte[] source) throws Exception;
}
