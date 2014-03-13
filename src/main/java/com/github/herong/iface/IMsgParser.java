package com.github.herong.iface;

import com.github.herong.model.msg.Hnisi;


public interface IMsgParser {
    /**
     * 数据格式类型
     */
    public static enum DataType {
        JSON("JSON"),
        XML("XML");

        private String name;

        /**
         * 
         */
        private DataType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
    
	public Hnisi str2Model(String str) throws Exception;
	public String model2Str(Hnisi model) throws Exception;
}
