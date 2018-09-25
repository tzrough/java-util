package com.hongfei.util.enumation;

/**
 * @author hongfei
 * 关于时间格式的枚举类
 */
public enum DateEnum {

	/**
	 * 默认的时间格式：yyyy-MM-dd HH:mm:ss
	 */
	DEFAULT_FORMAT("yyyy-MM-dd HH:mm:ss"),
	/**
	 * 天的起始时间格式：yyyy-MM-dd 00:00:00
	 */
	START_DAY_FORAMT("yyyy-MM-dd 00:00:00"),
	/**
	 * 天的结束时间格式：yyyy-MM-dd 23:59:59
	 */
	END_DAY_FORMAT("yyyy-MM-dd 23:59:59"),
	/**
	 * 日期格式：yyyyMMdd
	 */
	YYYYMMDD_FORMAT("yyyyMMdd"),
	/**
	 * 时间格式：yyyyMMddHHmmss
	 */
	YYYYMMDDHHMMSS_FORMAT("yyyyMMddHHmmss");

	
	private String format;

	DateEnum(String format) {

		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
