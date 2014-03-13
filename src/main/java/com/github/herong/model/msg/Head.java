/**
 * 
 */
package com.github.herong.model.msg;

import java.io.Serializable;

/**
 * TODO 这里用文字描述这个类的主要作用
 * @author herong
 * @createTime 2013-7-17 上午10:32:31
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Head implements Serializable {
    private String version = "1.0";

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
}
