/**
 * 
 */
package com.github.herong.model.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.herong.iface.IMsgParser;
import com.github.herong.model.XmlMsgParser;
/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-17 下午02:44:04
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class MsgHelper {

    /**
     * 查找节点值
     * 
     * @param hnisi
     *            数据对象
     * @param keyName
     *            参数名
     * @return 参数值
     */
    public static String findParamVal(Hnisi hnisi, String keyName) {
        for (Param p : hnisi.getBody().getParams()) {
            if (keyName.equalsIgnoreCase(p.getKey())) {
                return p.getVal();
            }
        }
        return "";
    }

    /**
     * 设置节点值
     * 
     * @param hnisi
     *            数据对象
     * @param keyName
     *            参数名
     * @param val
     *            新值
     * @return true:成功，false：失败。
     */
    public static boolean setParamVal(Hnisi hnisi, String keyName, String val) {
        for (Param p : hnisi.getBody().getParams()) {
            if (keyName.equalsIgnoreCase(p.getKey())) {
                p.setVal(val);
                return true;
            }
        }
        return false;
    }

    public static Hnisi createModelParams() {
        Hnisi hnisi = new Hnisi();
        Body body = hnisi.getBody();
        if (body == null) {
            body = new Body();
            hnisi.setBody(body);
        }
        return hnisi;
    }

    public static Hnisi addModelParams(Hnisi hnisi, String key, String value) {
        Hnisi hnisiTmp = hnisi;
        if (hnisiTmp == null) {
            hnisiTmp = createModelParams();
        }
        hnisiTmp.getBody().addParams(key, value);
        return hnisiTmp;
    }

    public static Hnisi createModelParams(Hnisi hnisi, Map<String, Object> params) {
        Hnisi hnisiTmp = hnisi;
        if (hnisiTmp == null) {
            hnisiTmp = createModelParams();
        }
        for (Entry<String, Object> valMap : params.entrySet()) {
            addModelParams(hnisiTmp, valMap.getKey(), valMap.getValue() == null ? "" : String.valueOf(valMap.getValue()));
        }
        return hnisiTmp;
    }

    public static Hnisi createModelParams(Map<String, Object> params) {
        Hnisi hnisi = createModelParams();
        for (Entry<String, Object> valMap : params.entrySet()) {
            addModelParams(hnisi, valMap.getKey(), valMap.getValue() == null ? "" : String.valueOf(valMap.getValue()));
        }
        return hnisi;
    }

    public static Hnisi setDataset(String dsName, List<Map<String, Object>> rowsList) {

        Rows rows = new Rows();
        int i = 1;
        rows.setKey(dsName);
        for (Map<String, Object> entry : rowsList) {
            Row row = new Row();
            row.setId(String.valueOf(i++));
            for (Entry<String, Object> colMap : entry.entrySet()) {
                Col col = new Col();
                col.setKey(colMap.getKey());
                col.setVal(colMap.getValue() == null ? "" : String.valueOf(colMap.getValue()));
                row.addCol(col);
            }
            rows.addRow(row);
        }

        Hnisi hnisi = new Hnisi();
        Body body = hnisi.getBody();
        if (body == null) {
            body = new Body();
            hnisi.setBody(body);
        }

        List<Rows> dataset = hnisi.getBody().getDataset();
        if (dataset == null) {
            dataset = new ArrayList<Rows>(1);
            hnisi.getBody().setDataset(dataset);
        }

        hnisi.getBody().getDataset().add(rows);

        return hnisi;
    }
    
    public static void main(String[] args) throws Exception {
        Hnisi hnisi = addModelParams(null, "YWLB", "111111");
        addModelParams(hnisi, "USERID", "admin");
        addModelParams(hnisi, "PASSWORD", "aaaaaa");
        IMsgParser parser = new XmlMsgParser();
        String xml = parser.model2Str(hnisi);
        System.out.println(xml);
    }
}
