package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *封装内存信息的实体类
 */
@Data
public class Memory {
	/**
	 * 总内存数
	 * 单位：GB
	 */
	private String tMemory;
	/**
	 * 剩余内存
	 * 单位：GB
	 */
	private String sMemory;
	/**
	 * 已使用内存
	 * 单位：GB
	 */
	private String aUMemory;
	/**
	 * 内存使用率
	 * 百分比
	 */
	private String proportion;
	/**
	 * jvm内存信息
	 */
	private JVMMemory jvmMemory;
	/**
	 * 堆内存信息
	 */
	private Heap heap;
	/**
	 * 非堆内存信息
	 */
	private UHeap uHeap;
	@Override
	public String toString() {
		return "内存： [总内存数：" + tMemory + ", 剩余内存数：" + sMemory + ", 已使用内存数：" + aUMemory + ", 内存使用率："
				+ proportion + ", \n其中jvm内存信息如下：\n" + jvmMemory + ", \n堆内存信息如下：\n" + heap + ", \n非堆内存信息如下：\n" + uHeap + "]";
	}
	
}
