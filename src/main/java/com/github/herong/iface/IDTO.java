/**
 * 
 */
package com.github.herong.iface;

import java.util.Map;


/**
 * 数据传数对象接口
 * 
 * @author herong
 * @createTime 2013-7-5 下午12:54:18
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public interface IDTO {
    public static final String FN = "FN";
    /**
     * 数据编码
     */
    public enum Charset {
        GBK("GBK"),
        UTF8("UTF-8"),
        UNICODE("UNICODE");

        private String name;

        private Charset(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * 数据格式类型
     */
    public enum DataType {
        JSON("json"),
        XML("xml");

        private String name;

        /**
         * 
         */
        private DataType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * 添加额外参数
     * 
     * @param key
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String key, String value);

    /**
     * 添加额外参数
     * 
     * @param params
     *            额外参数
     */
    public void addParams(Map<String, String> params);
    
    /**
    * 获取额外参数
    * @param key
    * @return
    */
   public String getParam(String key);
   
   /**
    *  获取额外参数
    * @return
    */
   public Map<String,String> getParams();
   
    /**
     * @return the isLogin
     */
    public boolean isLogin();

    /**
     * @param isLogin
     *            the isLogin to set
     */
    public void setLogin(boolean isLogin);

    /**
     * 获取数据类型
     * 
     * @return 数据类型
     */
    public DataType getDataType();

    /**
     * 设置数据格式类型
     * 
     * @param dataType
     *            格式类型
     */
    public void setDataType(DataType dataType);

    /**
     * 设置业务分类
     * 
     * @param ywfl
     * @return
     */
    public void setYwfl(String ywfl);

    /**
     * 获取业务分类
     * 
     * @param ywfl
     * @return
     */
    public String getYwfl();

    /**
     * 获取当前数据字符集
     * 
     * @return 当前数据字符集
     */
    public Charset getCharset();

    /**
     * 设置数据字符集
     * 
     * @param charet
     *            数据字符集
     */
    public void setCharset(Charset charet);

    /**
     * 获取数据摘要
     */
    public String getDigest();

    /**
     * 设置数据摘要
     */
    public void setDigest(String digest);

    /**
     * 获取原始数据
     * 
     * @return 原始数据
     */
    public String getData();

    /**
     * 设置原始数据
     * 
     * @param sourceString
     *            原始数据
     */
    public void setData(String data);

    /**
     * 数据以json格式返回
     * 
     * @return
     */
    public String toJson();

    /**
     * 数据以xml格式返回
     * 
     * @return
     */
    public String toXml();

    /**
     * @return the isCompressed
     */
    public boolean isCompressed();

    /**
     * @param isCompressed
     *            the isCompressed to set
     */
    public void setCompressed(boolean isCompressed);

    /**
     * @return the isEncrypted
     */
    public boolean isEncrypted();

    /**
     * @param isEncrypted
     *            the isEncrypted to set
     */
    public void setEncrypted(boolean isEncrypted);
}

