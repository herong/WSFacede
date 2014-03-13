/**
 * 
 */
package com.github.herong.comm.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP压缩器
 * 
 * @author herong
 * @createTime 2013-7-5 下午02:13:51
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class GzipCompressor implements ICompressor {

    /**
     * 缓存大小
     */
    public final static int BUFFER_SZIE = 1024 * 512; // 512KB
    public final static String CHARACT = "UTF-8";// AgentConfig.INIT_PARAM.getCharset();

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.compress.ICompressor#compress(java.lang.String)
     */
    public byte[] compress(byte[] source) throws Exception {
        ByteArrayOutputStream baos = null;
        GZIPOutputStream gzip = null;
        try {
            if (source == null || source.length == 0) {
                return null;
            }
            // String bstr = new BASE64Encoder().encode(source);
            baos = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(baos, BUFFER_SZIE);
            gzip.write(source);
            gzip.flush();
            baos.flush();
            gzip.close();
            return baos.toByteArray();
        } catch (UnsupportedEncodingException e) {
            throw new Exception("内容压缩出错，系统不支持字符集" + CHARACT, e);
        } catch (IOException e) {
            throw new Exception("内容压缩出错," + e.getLocalizedMessage(), e);
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.compress.ICompressor#decompress(java.lang.String)
     */
    public byte[] decompress(byte[] source) throws Exception {
        if (source == null || source.length == 0) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        GZIPInputStream gzip = null;
        try {
            baos = new ByteArrayOutputStream(BUFFER_SZIE);
            bais = new ByteArrayInputStream(source);
            gzip = new GZIPInputStream(bais, BUFFER_SZIE);
            byte[] bf = new byte[BUFFER_SZIE];
            int i = 0;
            while ((i = gzip.read(bf)) != -1) {
                baos.write(bf, 0, i);
            }
            baos.flush();
            gzip.close();
            // byte[] dd = new BASE64Decoder().decodeBuffer(baos.toString());
            return baos.toByteArray();
        } catch (UnsupportedEncodingException e) {
            throw new Exception("内容解缩出错，系统不支持字符集" + CHARACT, e);
        } catch (IOException e) {
            throw new Exception("内容解缩出错," + e.getLocalizedMessage(), e);
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
        }
    }

 
   
    public static void main(String[] args) throws Exception {

        // testBase64();
       
    }

}
