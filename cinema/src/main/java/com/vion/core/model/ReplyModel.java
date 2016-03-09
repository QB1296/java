package com.vion.core.model;
/**
 * <b>功能描述</b> <br>
 * 操作成功与否的数据传输对象
 * @author YUJB
 * @date 2014年5月12日 下午5:51:23
 */
public abstract class ReplyModel {
	
	
	/** 是否操作成功  */
	private boolean success;
	
	/** 描述  */
	private String describe;

	
	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param describe the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
}
