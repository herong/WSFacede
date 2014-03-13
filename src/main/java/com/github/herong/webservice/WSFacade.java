package com.github.herong.webservice;

import com.github.herong.iface.IDTO;
import com.github.herong.model.DTO;
import com.github.herong.service.IProcessHandler;
import com.github.herong.service.ProcessWraper;
import com.google.gson.Gson;

/**
 * webservice传输前后处理
 * 
 * @author herong
 * @createTime 2013-7-18 下午06:12:22
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */
public class WSFacade {
    private static Gson gson = new Gson();
    private static WSProcess wfProcess = new WSProcess();

    /**
     * 登录
     * 
     * @param args
     *            传输内容
     * @return 处理后内容
     * @throws Exception
     */
    public String login(final String dType, final String ywfl, final String fn, String data) throws Exception {

        IDTO dto = gson.fromJson(data, DTO.class);

        IDTO respDto = ProcessWraper.process(dto, new IProcessHandler() {

            public IDTO process(IDTO dto) throws Exception {
                String resp = wfProcess.login(dType, ywfl, fn, dto.getData());
                IDTO respDto = new DTO();
                respDto.setDataType(IDTO.DataType.JSON);
                respDto.setData(resp);
                return respDto;
            }
        });
        return gson.toJson(respDto);

    }

    /**
     * 业务功能调用
     * 
     * @param ywlb
     *            业务分类
     * @param args
     *            入参
     * @return 处理返回信息
     * @throws Exception
     */
    public String process(final String dType, final String ywfl, final String fn, String data) throws Exception {

        IDTO dto = gson.fromJson(data, DTO.class);

        IDTO respDto = ProcessWraper.process(dto, new IProcessHandler() {

            public IDTO process(IDTO dto) throws Exception {
                String resp = wfProcess.process(dType, ywfl, fn, dto.getData());
                IDTO respDto = new DTO();
                respDto.setDataType(IDTO.DataType.JSON);
                respDto.setData(resp);
                return respDto;
            }
        });
        return gson.toJson(respDto);
    }

}
