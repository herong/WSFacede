/**
 * 
 */
package com.github.herong.service;

import com.github.herong.iface.IDTO;

/**
 * TODO 这里用文字描述这个类的主要作用
 * @author herong
 * @createTime 2013-7-17 上午08:39:12
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public interface IProcessHandler {
    public IDTO process(IDTO dto) throws Exception;
}
