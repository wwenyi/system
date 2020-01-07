package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @date 2019-11-7
 * 非堆内存参数封装实体类
 *
 */
@Data
public class UHeap {
	/**
	 * 非堆内存初始大小
	 * 单位：GB
	 */
	private String uHMISize;
	
	/**
	 * 非堆内存最大大小
	 * 单位：GB
	 */
	private String uHMMSize;
	
	/**
	 * 非堆内存已使用内存
	 * 单位：MB
	 */
	private String uHSMemory;
	
	/**
	 * 非堆内存可使用内存
	 * 单位：GB
	 */
	private String uHMUMemory;

	@Override
	public String toString() {
		return "非堆内存： [非堆内存初始容量：" + uHMISize + ", 非堆内存最大容量：" + uHMMSize + ", 非堆内存已使用：" + uHSMemory + 
				", 非堆内存可使用："
				+ uHMUMemory + "]";
	}
	
}
