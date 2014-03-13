package com.github.herong.model;

import com.github.herong.iface.IMsgParser;
import com.github.herong.model.msg.Hnisi;
import com.google.gson.Gson;

public class JsonMsgParser implements IMsgParser {

    private static Gson gson = new Gson();

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.framework.iface.IMsgParser#str2Model(java.lang.String)
     */
    public Hnisi str2Model(String str) throws Exception {
        return gson.fromJson(str, Hnisi.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.framework.iface.IMsgParser#model2Str(cn.sinobest.framework
     * .model.msg.Hnisi)
     */
    public String model2Str(Hnisi model) throws Exception {
        return gson.toJson(model);
    }

}
