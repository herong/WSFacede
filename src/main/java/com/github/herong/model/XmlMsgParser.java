package com.github.herong.model;

import com.github.herong.iface.IMsgParser;
import com.github.herong.model.msg.Col;
import com.github.herong.model.msg.Hnisi;
import com.github.herong.model.msg.Param;
import com.github.herong.model.msg.Row;
import com.github.herong.model.msg.Rows;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlMsgParser implements IMsgParser {

    private static XStream xstream = new XStream(new StaxDriver());

    static {
        xstream.setClassLoader(XmlMsgParser.class.getClassLoader());
        xstream.alias("hnisi", Hnisi.class);
        xstream.alias("param", Param.class);
        xstream.alias("rows", Rows.class);
        xstream.alias("row", Row.class);
        xstream.alias("col", Col.class);
        xstream.addImplicitCollection(Rows.class, "rows");
        xstream.addImplicitCollection(Row.class, "cols");
        xstream.aliasAttribute(Param.class, "key", "key");
        xstream.aliasAttribute(Param.class, "val", "val");
        xstream.aliasAttribute(Rows.class, "key", "key");
        xstream.aliasAttribute(Row.class, "id", "id");
        xstream.aliasAttribute(Col.class, "key", "key");
        xstream.aliasAttribute(Col.class, "val", "val");
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.framework.iface.IMsgParser#str2Model(java.lang.String)
     */
    public Hnisi str2Model(String str) throws Exception {
        return (Hnisi) xstream.fromXML(str);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.framework.iface.IMsgParser#model2Str(cn.sinobest.framework
     * .model.msg.Hnisi)
     */
    public String model2Str(Hnisi model) throws Exception {
        return xstream.toXML(model);
    }

    public static void main(String[] args) throws Exception {
        String msg = "<?xml version='1.0' encoding='UTF-8'?>" +
                "<hnisi>" +
                "<head><version>1.0</version></head>" +
                "<body>" +
                "<params>" +
                "<param key=\"USERID\" val=\"Hello\" />" +
                "<param key=\"PASSWORD\" val=\"World\" />" +
                "</params>" +
                "</body>" +
                "</hnisi>";
        
        msg="<?xml version=\"1.0\" encoding=\"utf-8\" ?>                                               "
            +"<hnisi>                                                                                   "
            +"    <head>                                                                                "
            +"        <version>1.0</version>                                                            "
            +"    </head>                                                                               "
            +"    <body>                                                                                "
            +"       <!-- 数据主要包括离散数据 和 列表数据  -->                                         "
            +"       <!-- 离散数据节点 params ，所有离散数据放在此节点内  -->                           "
            +"        <params>                                                                          "
            +"            <!-- 用户名:系统参数,登录时才需要指定  -->                                    "
            +"            <param key=\"USERID\" val=\"\" />                                             "
            +"            <!-- 密码: 系统参数,登录时才需要指定  -->                                     "
            +"            <param key=\"PASSWORD\" val=\"\" />                                           "
            +"                                                                                          "
            +"            <!-- 会话ID:系统参数,登录时后返回的会话ID,业务功能调用时都需要指定  -->       "
            +"            <param key=\"SESSIONID\" val=\"\" />                                          "
            +"                                                                                          "
            +"            <!-- 业务传参, 业务调用需要传输的参数 。如：-->                               "
            +"            <param key=\"业务传参1\" val=\"\" />                                          "
            +"            <param key=\"业务传参2\" val=\"\" />                                          "
            +"        </params>                                                                         "
            +"                                                                                          "
            +"        <!-- 列表数据集 -->                                                               "
            +"        <dataset>                                                                         "
            +"            <!-- 列表数据,key指定数据集名称,可以有多个rows节点，但名称不能重复-->         "
            +"            <rows key=\"list1\">                                                          "
            +"                <!--行节点，可以有多行,但id不能重复-->                                    "
            +"                <row id=\"1\">                                                            "
            +"                <!--列接点,可以有多列，但key不能重复-->                                   "
            +"                    <col key=\"AAC003\" val=\"姓名\" />                                   "
            +"                    <col key=\"AAC058\" val=\"证件类型\" />                               "
            +"                    <col key=\"AAC002\" val=\"证件号码\" />                               "
            +"                </row>                                                                    "
            +"            </rows>                                                                       "
            +"        </dataset>                                                                        "
            +"    </body>                                                                               "
            +"</hnisi>                                                                                  ";
        
        IMsgParser xmlParser = new XmlMsgParser();
        Hnisi hnisi = xmlParser.str2Model(msg);
        System.out.println(hnisi.getBody().getParamVal("USERID"));
        System.out.println(hnisi.getBody().getParamVal("PASSWORD"));
        IMsgParser jsonParser = new JsonMsgParser();
        String jsonStr = jsonParser.model2Str(hnisi);
        System.out.println(jsonStr);
    }
}
