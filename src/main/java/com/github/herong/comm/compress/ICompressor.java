/**
 * 
 */
package com.github.herong.comm.compress;

import java.io.IOException;

/**
 * 压缩接口
 * @author herong
 * @createTime 2013-7-5 下午02:10:16
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public interface ICompressor {
 
    public static final int BUFFER_SIZE = 1024;
    public static final String CHARSET = "UTF-8";
    /**
     * 压缩文本
     * @param source 原内容
     * @return 压缩内容
     * @throws IOException 
     * @throws Exception 
     */
    public byte[] compress(byte[] source) throws Exception ;
    /**
     * 解缩文本
     * @param source 压缩内容
     * @return 原内容
     * @throws Exception 
     */
    public byte[] decompress(byte[] source) throws Exception;
}
