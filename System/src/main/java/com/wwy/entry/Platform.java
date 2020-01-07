package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *封装平台信息的实体类
 */
@Data
public class Platform {
	/**
	 * 当前工作路径
	 */
	private String workPath;
	/**
	 * 日志存放路径
	 */
	private String logPath;
	/**
	 * 文件上传路径
	 */
	private String filePath;
	@Override
	public String toString() {
		return "平台信息： [当前工作路径：" + workPath + ", 日志存放路径：" + logPath + ", 文件上传路径：" + filePath + "]";
	}
	
}
