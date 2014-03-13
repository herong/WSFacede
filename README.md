 【背景】 

       在各项目都基本上都需要提供webservice接口，此框架通过对webservice进行简化封装，提供一个统一的接口。制定一套通用的数据规范，并实现了基本用户口令认证（会话保持），业务检验接口，业务功能调用等。
 

 【描述】 


          封装实现了通用WebService功能，对接收的数据解压缩、数据指纹验证、解密等功能。目的是统一传输数据模型，方便移植到其它项目中，并使开发人员集成集力实现业务功能。

【特点】
      支持用户认证功能；支持数据解压缩；支持多个业务功能；
      统一标准数据准格式规范，提供标准格式转化器；
      支持JSON和XML数据格式，并支持相互转换。
      格式可以根据业务情况自定义。



【数据传输格式】
请求和返回格式一致，支持xml和json格式。
Xml格式：
<?xml version="1.0" encoding="utf-8" ?>
<hnisi>
    <head>
        <version>1.0</version>
    </head>
    <body>
       <!-- 数据主要包括离散数据 和 列表数据  -->
       <!-- 离散数据节点 params ，所有离散数据放在此节点内  -->
        <params>
            <!-- 用户名:系统参数,登录时才需要指定  -->
            <param key="USERID" val="" />
            <!-- 密码: 系统参数,登录时才需要指定  -->
            <param key="PASSWORD" val="" />
            
            <!-- 会话ID:系统参数,登录时后返回的会话ID,业务功能调用时都需要指定  -->
            <param key="SESSIONID" val="" />
            
            <!-- 业务传参, 业务调用需要传输的参数 。如：-->
            <param key="业务传参1" val="" />
            <param key="业务传参2" val="" />
        </params>
        
        <!-- 列表数据集 -->
        <dataset>
            <!-- 列表数据,key指定数据集名称,可以有多个rows节点，但名称不能重复-->
            <rows key="list1">
                <!--行节点，可以有多行,但id不能重复-->
                <row id="1"> 
                <!--列接点,可以有多列，但key不能重复-->
                    <col key="AAC003" val="姓名" />
                    <col key="AAC058" val="证件类型" />
                    <col key="AAC002" val="证件号码" />
                </row>
            </rows>
        </dataset>
    </body>
</hnisi>

Json格式：
{
	"head": {
		"version": "1.0"
	},
	"body": {
		"params": [{
			"key": "USERID",
			"val": ""
		},
		{
			"key": "PASSWORD",
			"val": ""
		},
		{
			"key": "SESSIONID",
			"val": ""
		},
		{
			"key": "业务传参1",
			"val": ""
		},
		{
			"key": "业务传参2",
			"val": ""
		}],
		"dataset": [{
			"key": "list1",
			"rows": [{
				"id": "1",
				"cols": [{
					"key": "AAC003",
					"val": "姓名"
				},
				{
					"key": "AAC058",
					"val": "证件类型"
				},
				{
					"key": "AAC002",
					"val": "证件号码"
				}]
			}]
		}]
	}
}


【开发方法】
　　    一、首先svn 获取创建一个动态web项目。
二、工程建好后，业务需要按以下步骤:

1. 实现自定义xml或json解析器（如果传输格式是采用框架标签格式则可以跳过此步）
   实现框架接口：cn.sinobest.framework.iface.IMsgParser
   框架默认支持JSON和xml格式，并提供了默认实现。如果框架提供的默认格式满足不了业务，则开发人员需自已实现解析器。所以，建议在定义传输格式时就按框架标准格式定义。

实现业务处理器
业务业务处理器类需要实现框架接口 cn.sinobest.framework.iface.IWSProcessor
    举例：cn.sinobest.example.processor.ExampleProcessor
    
设置初始参数
　　   
　　	需要在内网服务器的sysconfig.properties文件中增加以下参数：
　　	
#参数初始化，初始化类需要继续InitCommWSParamsAbstract类
app.comm.webservice.init=
	
　　举例：
　　app.comm.webservice.init=cn.sinobest.example.commws.params.InitParams
　　
public class InitParams extends InitCommWSParamsAbstract {

    /*
     * (non-Javadoc)
     * 
     * @see cn.sinobest.framework.comm.iface.InitCommWSParamsAbstract#init()
     */
    @Override
    public List<InitParam> init() {
        /**
         * @param ywlb
         *            指定业务分类，比如：his，图书管接口等。（必须指定）
         * @param processor
         *            指定业务处理器（必须指定）
         * @param xmlMsgParser
         *            xml格式信息编码解码器,若不指定则默认为框架提供的
         *            cn.sinobest.framework.model.XmlMsgParser
         * @param jsonMsgParser
         *            json格式信息编码解码器,若不指定则默认为框架提供的
         *            cn.sinobest.framework.model.JsonMsgParser
         * 
         */
        InitParam initParam = new InitParam("EXAMPLE", new ExampleProcessor(), new XmlMsgParser(), new JsonMsgParser());
        List<InitParam> respList = new ArrayList<InitParam>(1);
        respList.add(initParam);
        return respList;
    }
   
	
