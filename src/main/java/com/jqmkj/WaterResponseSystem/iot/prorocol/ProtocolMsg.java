package com.jqmkj.WaterResponseSystem.iot.prorocol;

/**
 * 说明：消息对象
 *
 */
/**
 * 数据内容例子：
 * ##0494ST=21;CN=2011;PW=123456;MN=6C611136H;CP=&&DataTime=20180315164206;
 * w01018-Rtd=1.31,w01018-Flag=N;w21003-Rtd=79,w21003-Flag=N;
 * w01019-Rtd=31,w01019-Flag=N;w01001-Rtd=51.5,w01001-Flag=N;
 * w01009-Rtd=8,w01009-Flag=N;w01014-Rtd=67,w01014-Flag=N;
 * a01021-Rtd=99,a01021-Flag=N;w01010-Rtd=6.18,w01010-Flag=N;
 * w21011-Rtd=14,w21011-Flag=N;w21001-Rtd=2,w21001-Flag=N;
 * w20141-Rtd=8,w20141-Flag=N;w20142-Rtd=0.96,w20142-Flag=N;
 * w20143-Rtd=1,w20143-Flag=Nw20144-Rtd=10,w20144-Flag=Nw20117-Rtd=5,w20117-Flag=N&&9901
 */
public class ProtocolMsg {

//	//
//	private short head;//固定为2位##
//	private short contentSize;//数据段长度-4位
//	//数据段- 0≤n≤1024
//	private String QN;	//	请求编码QN 字符 20 精确到毫秒的时间戳:QN=YYYYMMDDhhmmsszzz，用来唯一标识一次命令交互
//	private String ST;	//	系统编码ST 字符 5 ST=系统编码
//	private String CN;	//	命令编码CN 字符 7 CN=命令编码
//	private String PW;	//	访问密码PW 字符 9 PW=访问密码
//	private String MN;	//	设备唯一标识MN 字符 27 MN=设备唯一标识
//	private String Flag;	//	拆分包及应答标志Flag 整数（0-255）8 Flag=标志位
//	private String PNUM;	//	总包数PNUM 字符 9 PNUM 指示本次通讯中总共包含的包数注：不分包时可以没有本字段，与标志位有关
//	private String PNO;	//	包号PNO 字符 8 PNO 指示当前数据包的包号注：不分包时可以没有本字段，与标志位有关
//	private String CP;	//	指令参数CP 字符 0≤n≤950 CP=&&数据区&&
//	private String crc;//4位16进制
//	private String tail;//2位固定为回车换行


	 
	private ProtocolHeader protocolHeader = new ProtocolHeader();
 	private String body;

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ProtocolHeader getProtocolHeader() {return protocolHeader;}
	public void setProtocolHeader(ProtocolHeader protocolHeader) {
		this.protocolHeader = protocolHeader;
	}

	/**
	 * 
	 */
	public ProtocolMsg() {}



	@Override
	public String toString() {
		return "ProtocolMsg{" +
				"protocolHeader=" + protocolHeader +
				", body='" + body + '\'' +
				'}';
	}
}
