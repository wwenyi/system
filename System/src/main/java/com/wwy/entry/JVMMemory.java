package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 *@date 2019-11-7
 *jvm内存信息封装实体类
 */
@Data
public class JVMMemory {
	/**
	 * JVM总内存数
	 * 单位：MB
	 */
	private String jTMemory;
	/**
	 * JVM剩余内存
	 * 单位：MB
	 */
	private String jSMemory;
	/**
	 * JVM已使用内存
	 * 单位：GB
	 */
	private String jAUMemory;
	/**
	 * JVM内存使用率
	 * 百分比
	 */
	private String jProportion;
	@Override
	public String toString() {
		return "JVM内存 [jvm总内存：" + jTMemory + ", jvm剩余内存：" + jSMemory + ", jvm已使用内存：" + jAUMemory
				+ ", jvm内存使用率：" + jProportion + "]";
	}
	
}
