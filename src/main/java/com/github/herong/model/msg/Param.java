/**
 * 
 */
package com.github.herong.model.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 这里用文字描述这个类的主要作用
 * @author herong
 * @createTime 2013-7-17 上午10:33:29
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Param implements Serializable{
    private String key;
    private String val;
   
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return the val
     */
    public String getVal() {
        return val;
    }
    /**
     * @param val the val to set
     */
    public void setVal(String val) {
        this.val = val;
    }
    
}
