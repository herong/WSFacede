/**
 * 
 */
package com.github.herong.model;

import com.github.herong.iface.IDTO.DataType;
import com.github.herong.iface.IMsgParser;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-23 下午06:58:53
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class MsgParser {

    private IMsgParser jsonMsgParser;
    private IMsgParser xmlMsgParser;

    public MsgParser() {
        this.jsonMsgParser = new JsonMsgParser();
        this.xmlMsgParser = new XmlMsgParser();
    }

    /**
     * @return the jsonMsgParser
     */
    public IMsgParser getJsonMsgParser() {
        return jsonMsgParser;
    }

    /**
     * @param jsonMsgParser
     *            the jsonMsgParser to set
     */
    public void setJsonMsgParser(IMsgParser jsonMsgParser) {
        this.jsonMsgParser = jsonMsgParser;
    }

    /**
     * @return the xmlMsgParser
     */
    public IMsgParser getXmlMsgParser() {
        return xmlMsgParser;
    }

    /**
     * @param xmlMsgParser
     *            the xmlMsgParser to set
     */
    public void setXmlMsgParser(IMsgParser xmlMsgParser) {
        this.xmlMsgParser = xmlMsgParser;
    }

    public IMsgParser getMsgParser(DataType type) {
        if (type == DataType.JSON) {
            return getJsonMsgParser();
        } else
            if (type == DataType.XML) {
                return getXmlMsgParser();
            }
        return null;
    }

}
