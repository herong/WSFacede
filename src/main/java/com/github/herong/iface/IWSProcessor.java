package com.github.herong.iface;

import com.github.herong.model.msg.Hnisi;

public interface IWSProcessor {
    public static final String FHZ = "FHZ";
    public static final String MSG = "MSG";
    public static final String FHZ_SUCCESS = "1";
    public static final String FHZ_FAILURE = "-1";

    /**
     * 校验用户登录
     */
    public Hnisi login(Hnisi model);

    /**
     * 进行业务处理
     */
    public Hnisi process(Hnisi model);

}
