package com.github.herong.webservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceGroupContext;

import com.github.herong.comm.util.Util;
import com.github.herong.iface.IWSProcessor;
import com.github.herong.model.MsgParser;

public class WSTools {
    private final static Map<String, InitParam> YWLB_PARAMS = new ConcurrentHashMap<String, InitParam>(1);
    private static final ThreadLocal<Boolean> isAgentRequest = new ThreadLocal<Boolean>();

    public static Boolean isAgentRequest() {
        Boolean f = isAgentRequest.get();
        return f == null ? false : f;
    }

    public static void setAgentRequest(Boolean flag) {
        isAgentRequest.set(flag);
    }

    public static void addInitParam(InitParam initParam) throws Exception {
        if (initParam == null || Util.isEmpty(initParam.getYwlb())) {
            throw new Exception("请指定业务类别!");
        }

        YWLB_PARAMS.put(initParam.getYwlb(), initParam);
    }

    public static boolean isLogined(String sessionid) {
        MessageContext mc = MessageContext.getCurrentMessageContext();
        ServiceGroupContext sc = mc.getServiceGroupContext();

        if (sc.getProperty(sessionid) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isLoginChk(String ywlb) throws Exception {
        InitParam param = getInitParam(ywlb);
        return param.isLoginChk();
    }

    /**
     * 保存session相关信息
     * 
     * @param sessionid
     * @param o
     */
    public static void setSession(String sessionid, Object o) {
        MessageContext mc = MessageContext.getCurrentMessageContext();
        ServiceGroupContext sc = mc.getServiceGroupContext();
        synchronized (sc) {
            sc.setProperty(sessionid, o);
            sc.setLastTouchedTime(1000 * 60 * 60);
        }
    }

    /**
     * 获取session相关信息
     * 
     * @param sessionid
     * @param o
     */
    public static Object getSession(String sessionid) {
        MessageContext mc = MessageContext.getCurrentMessageContext();
        ServiceGroupContext sc = mc.getServiceGroupContext();
        synchronized (sc) {
            Object o = sc.getProperty(sessionid);
            return o;
        }
    }

    public static void jkjy(String dType, String ywfl) throws Exception {
        if (dType == null || "".equals(dType)) {
            throw new Exception("请指定双方约定的传输格式，通常是JSON或XML!");
        }

        if (ywfl == null || "".equals(ywfl)) {
            throw new Exception("请传入业务分类!");
        }
    }

    private static InitParam getInitParam(final String ywlb) throws Exception {
        InitParam initParam = YWLB_PARAMS.get(ywlb);
        if (initParam == null) {
            throw new Exception("业务类别为" + ywlb + "的参数未初始化!");

        }
        return initParam;
    }

    /**
     * 获取处理实例
     * 
     * @param ywlb
     *            业务分类
     * @return 处理实例
     * @throws Exception
     */
    public static IWSProcessor getProcessor(final String ywlb) throws Exception {
        InitParam initParam = getInitParam(ywlb);
        IWSProcessor ps = initParam.getProcessor();
        if (ps != null) {
            return ps;
        } else {
            throw new Exception("未设置业务处理器");
        }

    }

    /**
     * 获取处理实例
     * 
     * @param ywlb
     *            业务分类
     * @return 处理实例
     * @throws Exception
     */
    public static MsgParser getMsgParser(final String ywlb) throws Exception {
        InitParam initParam = getInitParam(ywlb);
        return initParam.getMsgParser();
    }

    public static void main(String[] args) throws Exception {
        MsgParser dd = getMsgParser("EXAMPLE");

    }
}
