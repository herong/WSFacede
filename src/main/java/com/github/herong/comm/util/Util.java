/**
 * 
 */
package com.github.herong.comm.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Enumeration;

import org.omg.CORBA.Environment;

import com.github.herong.comm.security.Des;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2011-12-13 下午06:17:52
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Util {

    public static String nvl(Object o) {
        if (o == null) {
            return "";
        }
        return String.valueOf(o);
    }

    /**
     * Number 转换成 String 类型
     * 
     * <pre>
     *  e.g:
     *     String str = Util.number2String(999999998.066);
     * </pre>
     * 
     * @param n
     *            {@code Number} 类型值
     * @return 转换并格式化后的字符串,只保留两位小数
     * @see #number2String(Number,String)
     */
    public static String number2String(final Number n) {
        return number2String(n, null);
    }

    /**
     * Number 转换成 String 类型,自定格式
     * 
     * <pre>
     *  e.g:
     *     String str = Util.number2String(999999998.066,"0.##");
     * </pre>
     * 
     * @param n
     *            {@code Number} 类型值
     * @param fmt
     *            格式串，如不指定则默认为0.##(保留两位小数，并做四舍五入) 参考 {@code DecimalFormat}
     * @return 转换并格式化后的字符串
     * @see BigDecimal
     * @see DecimalFormat
     */
    public static String number2String(final Number n, final String fmt) {
        DecimalFormat df = new DecimalFormat();
        String format = fmt;
        if (fmt == null) {
            format = "0.####";
        }
        df.applyPattern(format);
        return df.format(n);
    }

    public static InputStream getResourceAsStream(String resource) throws Exception {
        String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

        InputStream stream = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResourceAsStream(stripped);
        }
        if (stream == null) {
            stream = Environment.class.getResourceAsStream(resource);
        }
        if (stream == null) {
            stream = Environment.class.getClassLoader().getResourceAsStream(stripped);
        }
        if (stream == null) {
            throw new Exception(resource + " not found");
        }
        return stream;
    }

    /**
     * 查找类路径获取文件的绝路径
     * 
     * @param resource
     * @return
     * @throws Exception
     */
    public static URL getResourcePath(String resource) throws Exception {
        String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

        URL stream = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResource(stripped);
        }
        if (stream == null) {
            stream = Environment.class.getResource(resource);
        }
        if (stream == null) {
            stream = Environment.class.getClassLoader().getResource(stripped);
        }
        if (stream == null) {
            throw new Exception(resource + " not found");
        }
        return stream;
    }

    /**
     * 判断是否为空、空中、空格串
     * 
     * @param str
     *            要判断的字符串对象
     * @return boolean true ： 空、空中、空格串，反之为false
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String getLocalIp() {
        String localIp = "";
        InetAddress ia;
        try {
            ia = InetAddress.getLocalHost();
            localIp = ia.getHostAddress();

        } catch (UnknownHostException e) {
        }

        return localIp;
    }

    /**
     * 获取本机IP地址(IPV4)
     * 
     * @return 本机IP地址
     * @throws Exception
     * @throws SocketException
     * 
     */
    public static String getLocalIp2() throws Exception {
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            InetAddress ip = null;
            String ips = "";
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces
                        .nextElement();
                // System.out.println(ni.getName());
                Enumeration<InetAddress> iptiems = ni.getInetAddresses();
                while (iptiems.hasMoreElements()) {
                    ip = (InetAddress) iptiems.nextElement();
                    ips = ip.getHostAddress();
                    // System.out.println(ips+","+ip.isSiteLocalAddress() +
                    // ","+ip.isLoopbackAddress());
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ips.indexOf(":") == -1) {
                        // System.out.println("本机的ip=" + ips);
                        return ips;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("获取服务器ip出错", e);
        }

        return "";
    }

    /**
     * 将异常堆栈转换成字符串
     * 
     * @param e
     *            异常
     * @param depth
     *            堆栈深度
     * @return 堆栈字符串
     */
    public static String exception2String(Throwable e, int depth) {
        StringBuffer ex = new StringBuffer("");
        int i = 1;
        if (e != null) {
            ex.append(e.toString()).append("\r\n");
            StackTraceElement[] st = e.getStackTrace();
            for (StackTraceElement ste : st) {
                i++;
                ex.append(ste.toString());
                if (depth != -1 && i > depth) {
                    break;
                }
            }
        }
        return ex.toString();
    }

    /**
     * 判断对象o实现的所有接口中是否有szInterface 2008-08-07 修正多继承中判断接口的功能， 以及修正接口继承后的判断功能
     * package test;
     * 
     * public interface ITest extends Serializable public class Test1 implements
     * ITest public class Test2 extends Test1 public class Test3 extends Test2
     * 
     * isInterface(Test3.class, "java.io.Serializable") = true
     * isInterface(Test3.class, "test.ITest") = true
     * 
     * @param c
     * @param szInterface
     * @return
     */
    public static boolean isInterface(Class c, String szInterface) {
        Class[] face = c.getInterfaces();
        for (int i = 0, j = face.length; i < j; i++) {
            if (face[i].getName().equals(szInterface)) {
                return true;
            } else {
                Class[] face1 = face[i].getInterfaces();
                for (int x = 0; x < face1.length; x++) {
                    if (face1[x].getName().equals(szInterface)) {
                        return true;
                    } else
                        if (isInterface(face1[x], szInterface)) {
                            return true;
                        }
                }
            }
        }
        if (null != c.getSuperclass()) {
            return isInterface(c.getSuperclass(), szInterface);
        }
        return false;
    }

    /**
     * 通过反射获取bean对象值
     * 
     * @param bean
     *            javabean对象
     * @param name
     *            属性
     * @param defVal
     *            默认值
     * @return 对应属性值
     * @throws Exception
     *             可能未找到属性，或对应的方法，或没有权限访问
     */
    public static String getBeanVal(Object bean, String name, String defVal) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        // 获取可访问的属性
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();

        String keyName = name;
        String value = defVal;

        // 遍历成员属性
        for (PropertyDescriptor prop : props) {
            if (!"class".equals(prop.getName()) && !(prop.getWriteMethod() == null)) {
                /*
                 * System.out.println(prop.getPropertyType() + "," +
                 * prop.getName() + "," + prop.getWriteMethod());
                 */

                if (prop.getPropertyType() == Boolean.TYPE || Boolean.class.isAssignableFrom(prop.getPropertyType())) {
                    if (keyName.startsWith("is")) {
                        keyName = keyName.substring(2);
                        String firstChar = String.valueOf(keyName.charAt(0)).toLowerCase();
                        keyName = firstChar + keyName.substring(1);
                    }

                }

                if (keyName.equals(prop.getName())) {
                    Object oVal = prop.getReadMethod().invoke(bean);
                    if (oVal == null) {
                        value = defVal;
                    } else {
                        value = String.valueOf(oVal);
                    }
                    return value;
                }
            }
        }
        return defVal;
    }

    /**
     * 通过反射设置bean对象值
     * 
     * @param bean
     *            javabean对象
     * @param name
     *            属性名
     * @param val
     *            值
     * @throws Exception
     *             可能未找到属性，或对应的方法，或没有权限访问
     */
    public static void setBeanVal(Object bean, String name, Object val) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        // 获取可访问的属性
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();

        String keyName = name;
        Object value = val;
        String valStr = String.valueOf(val);
        if (val instanceof Boolean || (val instanceof String && ("true".equalsIgnoreCase(valStr) || "false".equalsIgnoreCase(valStr)))) {

            if (keyName.startsWith("is")) {
                keyName = keyName.substring(2);
                String firstChar = String.valueOf(keyName.charAt(0)).toLowerCase();
                keyName = firstChar + keyName.substring(1);
            }

        }

        // 遍历成员属性
        for (PropertyDescriptor prop : props) {
            if (!"class".equals(prop.getName()) && !(prop.getWriteMethod() == null)) {
                // System.out.println(prop.getPropertyType() + "," +
                // prop.getName() + "," + prop.getWriteMethod());
                if (keyName.equals(prop.getName())) {
                    Class clazz = prop.getPropertyType();

                    // 非空时，需根据类型尝试转化
                    // 非继承关系的对象，增加处理Number子类的相关类型转化
                    // 用于（OracleJDBC)
                    if (clazz.isAssignableFrom(value.getClass())) {
                        // 对象存在继承关系，那么直接赋值
                        prop.getWriteMethod().invoke(bean, value);
                    } else
                        if ((Double.TYPE == clazz || Double.class == clazz)
                                && Number.class.isAssignableFrom(value
                                        .getClass())) {
                            // 双精度浮点类型处理
                            prop.getWriteMethod().invoke(bean,
                                    ((Number) value).doubleValue());
                        } else
                            if (Integer.TYPE == clazz || Integer.class == clazz) {
                                // 整形处理
                                if (Number.class.isAssignableFrom(value.getClass())) {
                                    prop.getWriteMethod().invoke(bean, Integer.parseInt((String) value));
                                } else
                                    if (value instanceof String) {
                                        prop.getWriteMethod().invoke(bean, Integer.parseInt((String) value));
                                    }
                            } else
                                if ((Long.TYPE == clazz || Long.class == clazz)) {
                                    // 长整形处理
                                    if (Number.class.isAssignableFrom(value.getClass())) {
                                        prop.getWriteMethod().invoke(bean,
                                                ((Number) value).longValue());
                                    } else
                                        if (value instanceof String) {
                                            prop.getWriteMethod().invoke(bean, Long.parseLong((String) value));
                                        }
                                } else
                                    if ((Byte.TYPE == clazz || Byte.class == clazz)
                                            && Number.class.isAssignableFrom(value
                                                    .getClass())) {
                                        // 字节处理
                                        prop.getWriteMethod().invoke(bean,
                                                ((Number) value).byteValue());
                                    } else
                                        if ((Short.TYPE == clazz || Short.class == clazz)
                                                && Number.class.isAssignableFrom(value
                                                        .getClass())) {
                                            // 短整形处理
                                            prop.getWriteMethod().invoke(bean,
                                                    ((Number) value).shortValue());
                                        } else
                                            if ((Float.TYPE == clazz || Float.class == clazz)
                                                    && Number.class.isAssignableFrom(value
                                                            .getClass())) {
                                                // 单精度浮点类型处理
                                                prop.getWriteMethod().invoke(bean,
                                                        ((Number) value).floatValue());
                                            } else
                                                if ((Boolean.TYPE == clazz || Boolean.class == clazz)) {
                                                    // Bolean类型处理
                                                    prop.getWriteMethod().invoke(bean, Boolean.parseBoolean(String.valueOf(value)));

                                                } else {
                                                    // 类型错误
                                                    prop.getWriteMethod().invoke(bean, value);
                                                }

                    break;
                }

            }

        }
    }

    /**
     * 将字节数组转换成16进制的字符串
     * 
     * @param b
     *            字节数组
     * @return 16进制的字符串 代码解析:一个字节内容高四位和低四位分别用一个16进制字符来表示，即是把一个byte拆成两个char
     */
    public static String byteArr2HexStr(byte[] b) {
        int bLen = b.length;
        int cLen = bLen << 1;
        char[] c = new char[cLen];
        for (int i = 0, j = 0; i < bLen; i++) {
            c[j++] = Character.forDigit((b[i] & 0xf0) >>> 4, 16);
            c[j++] = Character.forDigit(b[i] & 0x0f, 16);
        }
        return String.valueOf(c);
    }

    /**
     * 将16进制的字符串转换成字节数组
     * 
     * @param h
     *            16进制字符串
     * @return 字节数组 代码解析:将两个char合并成一个byte，相当于byteArr2HexStr的逆过程
     */
    public static byte[] hexStr2ByteArr(String h) {
        char[] c = h.toCharArray();
        int cLen = c.length;
        int blen = cLen >> 1;
        byte[] b = new byte[blen];
        for (int i = 0, j = 0; i < blen; i++) {
            int _b = Character.digit(c[j++], 16) << 4;
            _b = _b | Character.digit(c[j++], 16);
            b[i] = (byte) (_b & 0xff);
        }
        return b;
    }

    public static String getString(byte[] byteArray) {
        char[] charByte = new char[byteArray.length * 2];
        for (int i = 0; i < byteArray.length; i++) {
            byte aByte = byteArray[i];
            if ((-128 <= aByte) && (aByte < -64)) {
                charByte[i] = rndChar(0);
                charByte[(byteArray.length + i)] = getCharInRange(aByte + 128);
            } else
                if ((-64 <= aByte) && (aByte < 0)) {
                    charByte[i] = rndChar(1);
                    charByte[(byteArray.length + i)] = getCharInRange(aByte + 64);
                } else
                    if ((0 <= aByte) && (aByte < 64)) {
                        charByte[i] = rndChar(2);
                        charByte[(byteArray.length + i)] = getCharInRange(aByte);
                    } else
                        if ((64 <= aByte) && (aByte < 128)) {
                            charByte[i] = rndChar(3);
                            charByte[(byteArray.length + i)] = getCharInRange(aByte - 64);
                        } else {
                            System.out.println("Invalid Char in stream " + aByte);
                        }
        }
        String str = new String(charByte);
        return str;
    }

    private static char rndChar(int i) {
        int c = i * 6 + (int) (Math.random() * 6.0D);
        boolean u = (int) (Math.random() * 2.0D) < 1;
        return (char) (c + (u ? 97 : 65));
    }

    private static char getCharInRange(int c1) {
        if ((0 <= c1) && (c1 <= 9)) {
            return (char) (c1 + 48);
        }
        if ((10 <= c1) && (c1 <= 35)) {
            return (char) (c1 - 10 + 65);
        }
        if ((36 <= c1) && (c1 <= 61)) {
            return (char) (c1 - 36 + 97);
        }
        if (c1 == 62) {
            return '<';
        }
        if (c1 == 63) {
            return '>';
        }

        System.out.println("Invalid int in stream " + c1);
        return '\000';
    }

    private static byte getByteInRange(char c1) {
        if (('0' <= c1) && (c1 <= '9')) {
            return (byte) (c1 - '0');
        }
        if (('A' <= c1) && (c1 <= 'Z')) {
            return (byte) (c1 - 'A' + 10);
        }
        if (('a' <= c1) && (c1 <= 'z')) {
            return (byte) (c1 - 'a' + 36);
        }
        if (c1 == '<') {
            return 62;
        }
        if (c1 == '>') {
            return 63;
        }

        System.out.println("Incorrect character in stream " + c1);
        return 2;
    }

    public static byte[] getBytes(String string) {
        char[] charArray = string.toCharArray();

        byte[] bytes = new byte[charArray.length / 2];
        for (int i = 0; i < bytes.length; i++) {
            if (Character.toLowerCase(charArray[i]) < 'g') {
                bytes[i] = ((byte) (getByteInRange(charArray[(bytes.length + i)]) - 128));
            } else
                if (Character.toLowerCase(charArray[i]) < 'm') {
                    bytes[i] = ((byte) (getByteInRange(charArray[(bytes.length + i)]) - 64));
                } else
                    if (Character.toLowerCase(charArray[i]) < 's') {
                        bytes[i] = getByteInRange(charArray[(bytes.length + i)]);
                    } else
                        if (Character.toLowerCase(charArray[i]) < 'y') {
                            bytes[i] = ((byte) (getByteInRange(charArray[(bytes.length + i)]) + 64));
                        } else {
                            System.out.println("Invalid character in byte stream " + charArray[i]);
                        }
        }
        return bytes;
    }

    /**
     * 复杂对象克隆
     * 
     * @param <T>
     *            类型
     * @param oObj
     *            原对象
     * @return 新对象
     * 
     *         <pre>
     * e.g1:
     *   l1 为原对象 ，类型为List<Map<String,Object>>
     *   List<Map<String,Object>> l2 = Util.objectClone(l1);
     * 
     * e.g2:
     *   m1 为原对象 ，类型为<Map<String,Object>
     *    Map<String,Object> m2 = Util.objectClone(m1);
     * </pre>
     * @throws Exception 
     */
    public static <T> T objectClone(T oObj) throws Exception {
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        ObjectOutputStream objo = null;
        ObjectInputStream obji = null;

        try {
            bos = new ByteArrayOutputStream();
            objo = new ObjectOutputStream(bos);
            objo.writeObject(oObj);
            bis = new ByteArrayInputStream(bos.toByteArray());
            obji = new ObjectInputStream(bis);
            return (T) obji.readObject();
        } catch (IOException e) {
            throw new Exception(e.getLocalizedMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new Exception("类型未找到!", e);
        } finally {
            bos = null;
            bis = null;
            if (objo != null)
                try {
                    objo.close();
                } catch (IOException e) {
                }
            if (obji != null)
                try {
                    obji.close();
                } catch (IOException e) {
                }
        }
    }
    /**
     * HIS 解密 加密密码转换为明文密码
     * 
     * @param data
     *            密文
     * @return 明文
     */
    public static String pwdDecrypt(String data) {
        String hisCryptKey = "hnsibmex";
        String firstKey = new Des(hisCryptKey).encode("rational").substring(0, 8);
        return new Des(firstKey).decode(data).trim();
    }
   
    /**
     * HIS 解密 加密密码转换为明文密码
     * 
     * @param data
     *            密文
     * @return 明文
     */
    public static String pwdEncrypt(String data) {
        String hisCryptKey = "hnsibmex";
        String firstKey = new Des(hisCryptKey).encode("rational").substring(0, 8);
        return new Des(firstKey).encode(data).trim();
    }
    public static void main(String[] args) throws Exception {
        
        String str = "D7969A6DF5031778" ;
        String estr = pwdEncrypt(str);
        System.out.println("estr:"+estr+","+estr.length());
        String dstr = pwdDecrypt(estr);
        System.out.println("dstr:"+dstr);
        /* System.out.println(getLocalIp()); */
        //String val = getBeanVal(AgentConfig.INIT_PARAM, "threadPoolSize", "");
        //System.out.println(val);
        /*
         * System.out.println(AgentConfig.INIT_PARAM.isCompress());
         * setBeanVal(AgentConfig.INIT_PARAM, "isCompress", "false");
         * System.out.println(AgentConfig.INIT_PARAM.isCompress());
         */
    }

}
