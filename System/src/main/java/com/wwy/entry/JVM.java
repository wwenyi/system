package com.wwy.entry;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author wwy
 * @data 2019-11-7
 *JVM不变的数据封装实体类
 */
@Data
public class JVM {
	/**
	 * java名称
	 */
	private String jName;
	/**
	 * java版本
	 */
	private String jEdition;
	/**
	 * 启动时间
	 */
	private String jSTime;
	/**
	 * 运行时长
	 */
	private String jRuntime;
	/**
	 * 安装路径
	 */
	private String jPath;
	/**
	 * 启动参数
	 */
	private List<String> jSParameter;
	@Override
	public String toString() {
		return "JVM [java名称" + jName + ", java版本" + jEdition + ", 启动时间：" + jSTime + ", 运行时长：" + jRuntime
				+ ", 安装路径：" + jPath + ", 启动参数：" + jSParameter + "]";
	}
	
	
}
