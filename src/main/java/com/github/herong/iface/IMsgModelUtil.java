package com.github.herong.iface;

import com.github.herong.model.msg.Hnisi;

public interface IMsgModelUtil {
	/**
     * 将报文转化为原型
	 * @throws Exception 
     */	
	public Hnisi msg2model(String msg ,String format) throws Exception;

	/**
     * 将消息原型转化为报文
	 * @throws Exception 
     */
	public String model2msg(Hnisi model ,String format) throws Exception;
	
}
