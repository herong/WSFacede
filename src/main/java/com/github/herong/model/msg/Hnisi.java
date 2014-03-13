/**
 * 
 */
package com.github.herong.model.msg;

import java.io.Serializable;


/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-17 上午10:31:38
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Hnisi implements Serializable {
    private Head head = new Head();
    private Body body;

    /**
     * @return the head
     */
    public Head getHead() {
        return head;
    }

    /**
     * @param head
     *            the head to set
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * @return the body
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(Body body) {
        this.body = body;
    }
    
    

}
