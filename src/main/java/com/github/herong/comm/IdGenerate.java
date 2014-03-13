package com.github.herong.comm;

import java.util.UUID;

import com.github.herong.comm.util.Util;

/**
 * 生成ID
 * 
 * @author herong
 * @createTime 2013-7-24 下午03:47:33
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */
public class IdGenerate {

    private IdGenerate() {
    }

    /**
     * 以UUID的策略生成一个32长度的字符串，在同一时空中保持唯一。
     * 
     * @return UUID128位长度字符串。
     */
    public static String getUUIDString() {
        String id = UUID.randomUUID().toString();
        id = id.replace("-", "");
        return id;
    }

    /**
     * 产生SESSIONID
     * 
     * @return
     */
    public static String generateSessionid(String prefix) {
        String idPrefix = prefix;
        if (!Util.isEmpty(prefix)) {
            idPrefix = idPrefix + "-";
        }
        String sessionid = idPrefix + getUUIDString();
        return sessionid.toUpperCase();

    }
    
    /**
     * 产生HIS SESSIONID
     * 
     * @return
     */
    public static String generateHisSessionid() {
        return generateSessionid("HISID");

    }

    public static void main(String[] args) {
        System.out.println(generateSessionid("hisid"));
    }
}
