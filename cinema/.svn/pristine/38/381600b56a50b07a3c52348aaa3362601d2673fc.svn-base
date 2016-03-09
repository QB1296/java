/**
 * 文件名： BaiduRequestParam.java
 *  
 * 版本信息：  
 * 日期：2015年4月13日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;
/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月13日 下午2:05:34
 */
/**
 * @author Administrator
 *
 */
public class BaiduRequestParam {

	/**
	 * 
	 */
	public final static String OUTPUT_JSON ="json";
	public final static String OUTPUT_XML ="xml";
	/**
	 * 必须：是
	 * 默认值：默认JSON
	 * 格式举例：json或xml
	 * 含义：输出格式为json或者xml
	 */
	private String output = OUTPUT_JSON;
	
	/**
	 * 必须：是
	 * 默认值：无
	 * 格式举例：E4805d16520de693a3fe707cdc962045
	 * 含义：用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key”
	 */
	private String ak = "2H4PbMKfZXMb4NUBdqHqrpKy";
	
	/**
	 * 必须：否
	 * 默认值：无
	 * 格式举例：无
	 * 含义：若用户所用ak的校验方式为sn校验时该参数必须。
	 */
	private String sn;
	
	/**
	 * 必须：否
	 * 默认值：无
	 * 格式举例：callback=showLocation(JavaScript函数名)
	 * 含义：将json格式的返回值通过callback函数返回以实现jsonp功能
	 */
	private String callback;
	
	/**
	 * 必须：是
	 * 默认值：无
	 * 格式举例：北京市海淀区上地十街10号
	 * 含义：根据指定地址进行坐标的反定向解析
		该参数是地理编码的必填项，可以输入三种样式的值，分别是： 
		•标准的地址信息，如北京市海淀区上地十街十号; 
		•名胜古迹、标志性建筑物，如天安门，百度大厦; 
		• 支持 “*路与*路交叉口”描述方式，如北一环路和阜阳路的交叉路口 
		注意：后两种方式并不总是有返回结果，只有当地址库中存在该地址描述时才有返回。
	 * 备注：对于address字段可能会出现中文或其它一些特殊字符（如：空格），对于类似的字符要进行编码处理，
	 * 编码成 UTF-8 字符的二字符十六进制值，凡是不在下表中的字符都要进行编码。
	 */
	private String address;
	
	/**
	 * 必须：否
	 * 默认值：无
	 * 格式举例：callback=showLocation(JavaScript函数名)
	 * 含义：地址所在的城市名
		该参数是可选项，用于指定上述地址所在的城市，当多个城市都有上述地址时，该参数起到过滤作用。
	 */
	private String city;

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("output=").append(output);
		buf.append("&ak=").append(ak);
		if(sn != null){
			buf.append("&sn=").append(sn);
		}
		if(callback != null)
		{
			buf.append("&callback=").append(callback);
		}
		if(address != null){
			buf.append("&address=").append(address);
		}
		if(city != null){
			buf.append("&city=").append(city);
		}
		return buf.toString();
	}
	
	
}
