/**
 * 
 */
package com.github.herong.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.herong.iface.IDTO;
import com.github.herong.iface.IDTO.Charset;
import com.github.herong.iface.IDTO.DataType;




/**
 * 数据传输对象
 * 
 * @author herong
 * @createTime 2013-7-5 下午12:57:29
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class DTO implements IDTO {

    /**
     * 扩展参数
     */
    private Map<String, String> extParams = null;

    /**
     * 是否登录
     */
    private boolean isLogin = true;
    /**
     * 业务分类
     */
    private String ywfl;
    /**
     * 数据编码
     */
    private Charset charset;

    /**
     * 数据格式类型
     */
    private DataType dataType;

    /**
     * 数据摘要
     */
    private String digest;

    /**
     * 
     */
    private String data;

    /**
     * 是否被压缩
     */
    private boolean isCompressed;
    /**
     * 是否被加密
     */
    private boolean isEncrypted;

    /**
     * 添加额外参数
     * @param key 参数名
     * @param value 参数值
     */
    public void addParam(String key, String value) {
        if (this.extParams == null) {
            this.extParams = new HashMap<String, String>(1);
        }
        this.extParams.put(key, value);
    }
    
    /**
     * 获取额外参数
     * @param key
     * @return
     */
    public String getParam(String key) {
        if (this.extParams != null) {
            return this.extParams.get(key);
        }
        return "";
    }
    
    /**
     *  获取额外参数
     * @return
     */
    public Map<String,String> getParams() {
        if (this.extParams != null) {
            return this.extParams;
        }
        return Collections.EMPTY_MAP;
    }
    
    /**
     * 添加额外参数
     * @param params 额外参数
     */
    public void addParams(Map<String,String> params) {
        if (this.extParams == null) {
            this.extParams = new HashMap<String, String>(1);
        }
        this.extParams.putAll(params);
    }

    
    /**
     * @return the isLogin
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * @param isLogin
     *            the isLogin to set
     */
    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#getData()
     */
    public String getData() {
        return this.data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#setData(java.lang.String)
     */
    public void setData(String data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#setYwfl(java.lang.String)
     */
    public void setYwfl(String ywfl) {
        this.ywfl = ywfl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#getYwfl(java.lang.String)
     */
    public String getYwfl() {
        return this.ywfl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#getDigest()
     */
    public String getDigest() {
        return this.digest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#setDigest()
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#getCharset()
     */
    public Charset getCharset() {
        return charset;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.comm.model.IDTO#setCharset(cn.sinobest.comm.model.IDTO.Charset
     * )
     */
    public void setCharset(Charset charet) {
        this.charset = charet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.agent.model.IDTO#toJson()
     */
    public String toJson() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.agent.model.IDTO#toXml()
     */
    public String toXml() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.comm.model.IDTO#getDataType()
     */
    public DataType getDataType() {
        return this.dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.comm.model.IDTO#setDataType(cn.sinobest.comm.model.IDTO.DataType
     * )
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the isCompressed
     */
    public boolean isCompressed() {
        return isCompressed;
    }

    /**
     * @param isCompressed
     *            the isCompressed to set
     */
    public void setCompressed(boolean isCompressed) {
        this.isCompressed = isCompressed;
    }

    /**
     * @return the isEncrypted
     */
    public boolean isEncrypted() {
        return isEncrypted;
    }

    /**
     * @param isEncrypted
     *            the isEncrypted to set
     */
    public void setEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

}
