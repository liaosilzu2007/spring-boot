package com.lzumetal.springboot.apidoc.common;

/**
 * 公共错误码
 */
public enum EBaseResponseCode implements ResponseCode {
	
	C200("200","成功"),
	C201("201","数据不存在"),
	C500("500","服务器错误"),
	C502("502","网络异常"),
	C503("503","服务器繁忙"),
	C515("515","无权限"),
	C401("401","参数错误"),
	C404("404","找不到"),
	C403("403","FORBIDDEN"), //未登录
	C430("430","非法请求"),	// 被认定为XSS攻击
	;

	private String code;
	private String msg;

	private EBaseResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
