package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *封装服务器信息的实体类
 */
@Data
public class Server {
	/**
	 * 服务器名称
	 */
	private String sName;
	/**
	 * 操作系统
	 */
	private String os;
	/**
	 * 服务器IP
	 */
	private String sIP;
	/**
	 * 系统架构
	 */
	private String systemArchitecture;
	@Override
	public String toString() {
		return "服务器： [服务器名称：" + sName + ", 操作系统：" + os + ", 服务器IP：" + sIP + ", 系统架构：" + systemArchitecture
				+ "]";
	}
	
}
