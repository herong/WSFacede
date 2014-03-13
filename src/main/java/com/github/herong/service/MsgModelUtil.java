package com.github.herong.service;

import com.github.herong.iface.IDTO.DataType;
import com.github.herong.iface.IMsgModelUtil;
import com.github.herong.iface.IMsgParser;
import com.github.herong.model.MsgParser;
import com.github.herong.model.msg.Hnisi;

public class MsgModelUtil implements IMsgModelUtil {

    public MsgParser msgParser;

    MsgModelUtil(MsgParser msgParser) {
        this.msgParser = msgParser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.framework.iface.IMsgModelUtil#model2msg(cn.sinobest.framework
     * .model.msg.Hnisi, java.lang.String)
     */
    public String model2msg(Hnisi model, String format) throws Exception {
        IMsgParser parser = this.msgParser.getMsgParser(DataType.valueOf(format.toUpperCase()));
        if (parser != null) {
            return parser.model2Str(model);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.framework.iface.IMsgModelUtil#msg2model(java.lang.String,
     * java.lang.String)
     */
    public Hnisi msg2model(String msg, String format) throws Exception {
        IMsgParser parser = this.msgParser.getMsgParser(DataType.valueOf(format.toUpperCase()));
        if (parser != null) {
            return parser.str2Model(msg);
        }
        return null;
    }

}
