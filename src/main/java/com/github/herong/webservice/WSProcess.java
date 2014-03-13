package com.github.herong.webservice;

import org.apache.log4j.Logger;

import com.github.herong.comm.IdGenerate;
import com.github.herong.comm.util.Util;
import com.github.herong.iface.IDTO;
import com.github.herong.iface.IDTO.DataType;
import com.github.herong.iface.IMsgParser;
import com.github.herong.iface.IWSProcessor;
import com.github.herong.model.MsgParser;
import com.github.herong.model.msg.Hnisi;
import com.github.herong.model.msg.MsgHelper;
import com.github.herong.service.ProcessWraper;

public class WSProcess {

    private static final Logger LOGGER = Logger.getLogger(WSProcess.class);

    public String login(String dType, String ywfl, String fn, String args) throws Exception {
        Hnisi respModel = null;
        IMsgParser parser = null;
        String resp = "";
        String requestCharset = "";
        try {
            WSTools.jkjy(dType, ywfl);
            MsgParser msgParser = WSTools.getMsgParser(ywfl);
            parser = msgParser.getMsgParser(DataType.valueOf(dType.toUpperCase()));
            if (parser == null) {
                throw new Exception("未指定" + DataType.valueOf(dType.toUpperCase()) + "解析器!");
            }
            /*
             * requestCharset = WSTools.getCharset(ywfl); String msg = args; if
             * (!Util.isEmpty(requestCharset)) { msg = new
             * String(args.getBytes("UTF-8"), requestCharset);
             * System.out.println("GBK-msg------"+msg); }
             */
            Hnisi hnisi = parser.str2Model(args);

            // String ywlb = hnisi.getBody().getParamVal("YWLB");
            // String userid = hnisi.getBody().getParamVal("USERID");

            // 如果不是通过代理请求，而必须要求HIS调用des.dll加密密码传输
            String password = hnisi.getBody().getParamVal("PASSWORD");
            if (!WSTools.isAgentRequest()) {
                password = Util.pwdDecrypt(password);// 解密
            } else {
                password = ProcessWraper.pwdDecryptRsa(password);
            }
            hnisi.getBody().replaceParam("PASSWORD", password);
            IWSProcessor processor = WSTools.getProcessor(ywfl);
            MsgHelper.addModelParams(hnisi, IDTO.FN, fn);
            respModel = processor.login(hnisi);
            String check = (String) respModel.getBody().getParamVal(IWSProcessor.FHZ);

            if (IWSProcessor.FHZ_SUCCESS.equals(check)) {

                /*
                 * respModel = MsgHelper.addModelParams(null, IWSProcessor.FHZ,
                 * IWSProcessor.FHZ_SUCCESS);
                 * MsgHelper.addModelParams(respModel, IWSProcessor.MSG,
                 * "登陆成功!");
                 */

                String key = IdGenerate.generateHisSessionid();
                /*
                 * result.put("DTYPE", dType); result.put("YWFL", ywfl);
                 */// 不依赖登录时指定，每次调用转比较灵活
                MsgHelper.addModelParams(respModel, "SESSIONID", key);
                hnisi.getBody().replaceParam("PASSWORD", "");
                MsgHelper.addModelParams(hnisi, "SESSIONID", key);
                WSTools.setSession(key, hnisi);

            } /*
               * else { respModel = MsgHelper.addModelParams(null,
               * IWSProcessor.FHZ, IWSProcessor.FHZ_FAILURE);
               * MsgHelper.addModelParams(respModel, IWSProcessor.MSG,
               * respModel.getBody().getParamVal(IWSProcessor.MSG)); }
               */

        } catch (Exception e) {
            LOGGER.error("登录处理失败," + e.getLocalizedMessage(), e);
            respModel = MsgHelper.addModelParams(null, IWSProcessor.FHZ, IWSProcessor.FHZ_FAILURE);
            MsgHelper.addModelParams(respModel, IWSProcessor.MSG, Util.exception2String(e, 30));
        }
        resp = parser.model2Str(respModel);
        /*
         * if (!Util.isEmpty(requestCharset)) { resp = new
         * String(resp.getBytes(requestCharset), "UTF-8"); }
         */
        return resp;
    }

    public String process(String dType, String ywfl, String fn, String data) throws Exception {
        Hnisi respModel = null;
        IMsgParser parser = null;
        String resp = "";
        String requestCharset = "";
        try {
            WSTools.jkjy(dType, ywfl);
            IWSProcessor processor = WSTools.getProcessor(ywfl);
            MsgParser msgParser = WSTools.getMsgParser(ywfl);
            parser = msgParser.getMsgParser(DataType.valueOf(dType.toUpperCase()));
            if (parser == null) {
                throw new Exception("未指定" + DataType.valueOf(dType.toUpperCase()) + "解析器!");
            }
            /*
             * requestCharset = WSTools.getCharset(ywfl); String msg = data; if
             * (!Util.isEmpty(requestCharset)) { msg = new
             * String(data.getBytes("UTF-8"), requestCharset); }
             */
            Hnisi hnisi = parser.str2Model(data);
            boolean flag = false;
            if (WSTools.isLoginChk(ywfl)) {
                String sessionid = MsgHelper.findParamVal(hnisi, "SESSIONID");
                flag = WSTools.isLogined(sessionid);
            } else {
                flag = true;
            }

            if (flag) {
                MsgHelper.addModelParams(hnisi, IDTO.FN, fn);
                respModel = processor.process(hnisi);
            } else {
                respModel = MsgHelper.addModelParams(null, IWSProcessor.FHZ, IWSProcessor.FHZ_FAILURE);
                MsgHelper.addModelParams(respModel, IWSProcessor.MSG, "请先登录!");
            }

        } catch (Exception e) {
            LOGGER.error("功能调用失败," + e.getLocalizedMessage(), e);
            respModel = MsgHelper.addModelParams(null, IWSProcessor.FHZ, IWSProcessor.FHZ_FAILURE);
            MsgHelper.addModelParams(respModel, IWSProcessor.MSG, Util.exception2String(e, 30));
        }

        resp = parser.model2Str(respModel);
        /*
         * if (!Util.isEmpty(requestCharset)) { resp = new
         * String(resp.getBytes(requestCharset), "UTF-8"); }
         */
        return resp;
    }

}
