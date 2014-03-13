/**
 * 
 */
package com.github.herong.model.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-17 上午10:32:42
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Body implements Serializable {
    private List<Param> params;
    private List<Rows> dataset;

    /**
     * @return the params
     */
    public List<Param> getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(List<Param> params) {
        this.params = params;
    }

    /**
     * 替换参数值
     * @param key 参数名
     * @param value 参数值
     */
    public void replaceParam(String key,String value) {
        for (Param p : this.params) {
            if (key.equalsIgnoreCase(p.getKey())) {
                 p.setVal(value);
                 return;
            }
        }
        return ;
    }
    
    public void addParams(String key, String val) {
        if (params == null) {
            params = new ArrayList<Param>(1);
        }
        Param param = new Param();
        param.setKey(key);
        param.setVal(val);
        params.add(param);
    }

    /**
     * @return the dataset
     */
    public List<Rows> getDataset() {
        return dataset;
    }

    /**
     * @param dataset
     *            the dataset to set
     */
    public void setDataset(List<Rows> dataset) {
        this.dataset = dataset;
    }

    /**
     * 查找节点值
     * 
     * @param hnisi
     *            数据对象
     * @param keyName
     *            参数名
     * @return 参数值
     */
    public String getParamVal(String key) {
        for (Param p : this.params) {
            if (key.equalsIgnoreCase(p.getKey())) {
                return p.getVal();
            }
        }
        return "";
    }

}
