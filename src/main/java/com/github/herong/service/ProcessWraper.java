/**
 * 
 */
package com.github.herong.service;

import com.github.herong.comm.compress.GzipCompressor;
import com.github.herong.comm.security.MessageDigestUtil;
import com.github.herong.comm.security.RSAEncryptor;
import com.github.herong.comm.util.DateUtil;
import com.github.herong.comm.util.SecurityUtil;
import com.github.herong.comm.util.Util;
import com.github.herong.iface.IDTO;
import com.github.herong.model.DTO;
import com.github.herong.webservice.WSTools;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-17 上午08:44:25
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class ProcessWraper {

    private static GzipCompressor gzip = new GzipCompressor();
    private static RSAEncryptor rsa = new RSAEncryptor();
    private static String rsaDeKey = "CJoQsVqxvVSqxStSTUuSWxsXMxxwWTUqWWVWxxuSPxwsxswxWuvqwUSmWttVxTWVUXTtTTSXWuVDekoXxQPmNXmpuWXXXttUuwTuXTVOpwVwXTQWsvXpSwwXsUvWWTrsqmtwuUVUXsTXssTTwnxqRSnOWSxSWSsutStSvSwrXNMXoOsUvXTuvQtOrsmnUSXUXvsMSrqxNOvTwuVuwxVUWxuvxMXqPSuoRWxsrtTSwxvXwxUuwmtWSXtsMUwXXxwWTnVXTsSSstWsPTuWQSsXUUXtuXVUUTvXUGQhMJvNROrXonstvxTvwVNtroVOrvvUSvUwVttvsWwvWOxnmTXUWQnwxuUnSTXwRSwswVSXSuvAldqEriNpNOvNMvUVVTxSTWmQUvSutxvvWSomwvWTwuStSvvXTXuwWxuTOMxSVtUXuWXTxxwnpxSTvuutqPUwtSsvSwWToPUSUUNmxSVuqVtxUnsSXXswcfcOREhEpRrttHjiGkhIijilJgHIlmopMwXqovXfGriPRxJqmNSUpOMAdBkXmtHsvUfUBMJcaRHhLRomFDWHpbRtrVWaDsdFDfPHPKDkaMjIPqoKmNxawcwvgAaircAvujjDqWXuPxFSEInqtVkOCeeluPwmpRdeAuqgxeOvsfuftjRicXaOwharVWvrtookiIJkJgjKiKLLKKimnrPSSNWpoprQEQDmdtGoBoSuHppLBpgspwaTSLpMLiHuEKXkWKBWPpcbxMghePrNMTsjuBhlDKqLeGcaWXxKNgjesTAoPqcLDLkDXboaNQFihjdiNHiwbQXUkiHQiixpSlmxVrxMAhHrqsxVWMXOMhIJglJKGJikgLkglrqnoUwmTRonOrUXdQKjkkuwtFrrGkwhCunhfejFORAuUUaNcKjcnFfUEmujJURVmqHiAoWxaITkGAwwXSnVNrJiJjHIlJkKkiHkLKONMNxuoTRNnOovvmXVMwrsJBWNvsTFfHOSSApMBcdforIoUoCFbiNIUTmSOBNwnwtmjLaivkUnkSpVWXVNuRrlIHggJIGKhiJILLLrQMoTWPSNrnppWSLGbxDtrBOavQjrTbFrftfEfXQLstJFSSeCspWNbMbLfVJFAFECGOGnibnpQhiBbWVvQsOMHlIJHJhGHLglKklgNmpnwvmWmrRrrUancAwICaojnCbEIEQWiqEaxreSJltIOxMGKGAkmXNxlrVeFnKKirHudfmigMqIvMXstnwNogLKgGHHhlhiLJkJLQROmuWpUrQmNQubnSihOhQXTQJjaKketKpQRNEgMAmOplHdJULncNQGeqEbMVXNPXraMPBUalApLRwwusRtoQhhLkliIIhiHgIglirrMMwsMURNoRqprqPsij05po02lodkYlrkZvZXpqibkgZXgZbkmolsfabokXpvjjbqofZkopXk23IJ1GofsXqb3oqBbviw7E2pLk206C0EZoq3lbccfZfbkqq0MCgXsXljXqel2fd9kqbdboxC0Emofjb5umlkbkqGn0<01C0Emofjb5umlkbkqHn0<01C06mofjbGn0<01C06mofjbHn0<01C0EmrYifZ5umlkbkqn0<01uo0>lodkYlrkZvZXpqibkgZXgZbkmolsfabokXpvjjbqofZkopXk23IJ1GofsXqbBbv6h907F1S302C07jlarirpn0<01C0FmofsXqb5umlkbkqn0<01umpo0KgXsXkjXqek2fd9kqbdboCyVVfxxT306908Yfq3lrkq909YfqCbkdqe90JcfopqElkwbol2vqbErj90CiltbpqJbq2fq906pfdkrjR09jXdkfqrabq02R2uo0GgXsXkiXkdkErjYbo6iLTBKWB200um>>>>>>>>>>><>>><0001ro02R2ipNu68KW200um000037nUsdFQtrQMfz9BYwJDRaXB5aCn78X>3TYWxqcwdSxoIlEGqugW9pGW2AYxYUEmJIK89tRkZx97qr7YHcPJOfdwEezzo>bEerU9h<UcMFlrRoBFEoIw8sLONMHw8tpXupn0<04>>>>>>>>>>><>>><0001rn0<0800003a1aFnpcNaZl<Zzkb1dNjNag3ws<yiXmuGVd>8DDXIB<vJpg7p4TBZarkO6<LDunGUjtAUYJbqdlK1fX<iYi0yrwYErmxGrtLw6vXYMlKmjJ9mfQJB4w0vKSXhRVccxXuupn0<04>>>>>>>>>>><>>><0001rn0<080000FGiwgcu9bD5isAh42lDiDnRsbaPcjG<A2OKkJ2h2pIdnI2SG1C54DK5vy5T7wJyUupn0<04>>>>>>>>>>><>>><0001rn0<080000XJuXUUE<dj8oYdnTRSP17afWJXHWH6rNsgioJ5gEaZL75gcqMpZ9Rk5GBAEdVqKjupn0<04>>>>>>>>>>><>>><0001rn0<080000ACSku5P0neP0BLpT9>2tqk5XKiWurL>8vvZe<h6BhwWCOXotd7dOxp4j4f9kTWvzupn0<04>>>>>>>>>>><>>><0001rn0<080000>cTBE<5iPeZ>0pK1R<APUsG3B1<xToGFt9F0ViDff6WgBrxDVx43M>z9D72jP24bupn0<04>>>>>>>>>>><>>><0001rn0<080000mdTwz6iertLLIxQuojuqPAX9FPDwAUlyCfntzi<Ew2qgZBDBco>u>v3sEiSzxaaDupn0<04>>>>>>>>>>><>>><0001rn0<080003101u";
    private static byte[] rsaDeKeyByte = Util.getBytes(rsaDeKey);

    /**
     * 解压
     * 
     * @param data
     * @return
     * @throws Exception
     */
    private static String decompress(String data) throws Exception {
        byte[] deData = gzip.decompress(Util.hexStr2ByteArr(data));
        return new String(deData, "UTF-8");
    }

    /**
     * 验证
     * 
     * @param digest
     * @param data
     * @return
     * @throws Exception
     */
    private static boolean digestVerify(String digest, String data) throws Exception {
        String newDigest = MessageDigestUtil.createDigestAsHexStr(data.getBytes("UTF-8"));
        if (newDigest.equals(digest)) {
            return true;
        }
        return false;
    }

    /**
     * 解密
     * 
     * @param digest
     * @param data
     * @return
     * @throws Exception
     */
    private static String decrypt(byte[] key, byte[] data) throws Exception {
        byte[] deData = rsa.decrypt(key, data);
        return Util.byteArr2HexStr(deData);
    }

    /**
     * 密码解密
     * 
     * @param data
     *            密文
     * @return 明文
     * @throws Exception
     */
    public static String pwdDecryptRsa(String data) throws Exception {
        String val = SecurityUtil.decryptTwo(DateUtil.CurDate.YYYYMMDD.getDate().getBytes(), rsaDeKeyByte,
                Util.hexStr2ByteArr(data));
     
        return val;
    }

    /**
     * 验证
     * 
     * @param digest
     *            摘要
     * @param data
     *            传入数据
     * @return 解压后数据
     */
    private static boolean verify(IDTO dto) throws Exception {

        // 解密摘要
        if (dto.isEncrypted()) {
            String deDigest = decrypt(rsaDeKeyByte, Util.hexStr2ByteArr(dto.getDigest()));
            dto.setDigest(deDigest);
        }

        // 摘要验证
        if (!digestVerify(dto.getDigest(), dto.getData())) {
            return false;
        }

        return true;
    }

    /**
     * 返回数据处理
     * 
     * @param data
     *            返回数据
     * @return 压缩后数据
     * @throws Exception
     */
    private static IDTO responeProcess(IDTO dto) throws Exception {

        // 1.生成摘要
        String digest = MessageDigestUtil.createDigestAsHexStr(dto.getData().getBytes("UTF-8"));
        // 2.摘要加密
        digest = Util.byteArr2HexStr(rsa.encrypt(rsaDeKeyByte, Util.hexStr2ByteArr(digest)));

        // 3.数据压缩
        String enData = Util.byteArr2HexStr(gzip.compress(dto.getData().getBytes("UTF-8")));
        IDTO respDto = new DTO();
        respDto.setCompressed(true);
        respDto.setEncrypted(true);
        respDto.setDigest(digest);
        respDto.setData(enData);
        return respDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.sinobest.agent.web.IProcess#process(cn.sinobest.agent.web.IProcessHandler
     * )
     */
    public static IDTO process(IDTO dto, IProcessHandler processHandler) throws Exception {

        // 1.解压数据
        if (dto.isCompressed()) {
            String deData = decompress(dto.getData());
            dto.setData(deData);
        }

        // 2.验证
        if (!verify(dto)) {
            throw new Exception("数据验证不通过，数据在传输过程中可能被篡改!");
        }

        // 说明是Agent请求
        WSTools.setAgentRequest(true);

        // 3.业务处理
        IDTO respDto = processHandler.process(dto);

        // 4.返回处理
        return responeProcess(respDto);
    }
    
    public static void main(String[] args) throws Exception {
        String enpwd = "09313e4762264b04952754221a2fd3c1fbe3a476c8648a63ecacc6600ebf2fbb856d70819f79ae58206417e4b10bdf07073a077b78c87c21e041e0f4e1ea7291a287debbe699734bdc4c06332c722476cb5d2443aa0674e4e685d8d667be619f02b24768dc85c7e99979db0b1d0210b89592754cd611555ea3a0f1e7d1972ac0";
        String pwd = pwdDecryptRsa(enpwd);
        System.out.println("解密后:"+pwd);
    }
}
