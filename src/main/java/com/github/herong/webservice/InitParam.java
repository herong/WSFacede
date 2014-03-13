/**
 * 
 */
package com.github.herong.webservice;

import com.github.herong.iface.IMsgParser;
import com.github.herong.iface.IWSProcessor;
import com.github.herong.model.MsgParser;

/**
 * 初始参数类
 * 
 * @author herong
 * @createTime 2014-2-24 上午11:17:11
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class InitParam {

    /**
     * 业务处理类
     */
    private IWSProcessor processor;

    /**
     * 编码解码器
     */
    private MsgParser msgParser;
    /**
     * 业务类型
     */
    private String ywlb;

    /**
     * 是否需要登录检查
     */
    private boolean isLoginChk = true;

    private InitParam() {
        this.msgParser = new MsgParser();
    }

    /**
     * @return the msgParser
     */
    public MsgParser getMsgParser() {
        return msgParser;
    }

    /**
     * 构造方法
     * 
     * @param ywlb
     *            业务类别
     * @param processor
     *            业务处理器
     * @param xmlMsgParser
     *            xml格式信息编码解码器
     * @param jsonMsgParser
     *            json格式信息编码解码器
     */
    public InitParam(String ywlb, IWSProcessor processor, IMsgParser xmlMsgParser, IMsgParser jsonMsgParser) {
        this();
        this.ywlb = ywlb;
        this.processor = processor;
        if (jsonMsgParser != null) {
            this.msgParser.setJsonMsgParser(jsonMsgParser);
        }

        if (xmlMsgParser != null) {
            this.msgParser.setXmlMsgParser(xmlMsgParser);
        }
    }

    /**
     * 构造方法
     * 
     * @param ywlb
     *            业务类别
     * @param processor
     *            业务处理器
     * @param xmlMsgParser
     *            xml格式信息编码解码器
     * @param jsonMsgParser
     *            json格式信息编码解码器
     * @param isLoginChk
     *            是否登录检查
     */
    public InitParam(String ywlb, IWSProcessor processor, IMsgParser xmlMsgParser, IMsgParser jsonMsgParser, boolean isLoginChk) {
        this(ywlb, processor, xmlMsgParser, jsonMsgParser);
        this.isLoginChk = isLoginChk;
    }

    /**
     * 构造方法
     * 
     * @param ywlb
     *            业务类别
     * @param processor
     *            业务处理器
     */
    public InitParam(String ywlb, IWSProcessor processor) {
        this(ywlb, processor, null, null);
    }

    /**
     * 构造方法
     * 
     * @param ywlb
     *            业务类别
     * @param processor
     *            业务处理器
     * @param isLoginChk
     *            是否登录检查
     */
    public InitParam(String ywlb, IWSProcessor processor, boolean isLoginChk) {
        this(ywlb, processor, null, null, isLoginChk);
    }

    /**
     * @return the processor
     */
    public IWSProcessor getProcessor() {
        return processor;
    }

    /**
     * @param processor
     *            the processor to set
     */
    public void setProcessor(IWSProcessor processor) {
        this.processor = processor;
    }

    /**
     * @return the ywlb
     */
    public String getYwlb() {
        return ywlb;
    }

    /**
     * @param ywlb
     *            the ywlb to set
     */
    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    /**
     * @return the isLoginChk
     */
    public boolean isLoginChk() {
        return isLoginChk;
    }

    /**
     * @param isLoginChk
     *            the isLoginChk to set
     */
    public void setLoginChk(boolean isLoginChk) {
        this.isLoginChk = isLoginChk;
    }

}
