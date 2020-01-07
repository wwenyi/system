package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @date 2019-11-7
 * 堆内存信息封装实体类
 *
 */
@Data
public class Heap {
	/**
	 * 堆内存初始大小
	 * 单位：GB
	 */
	private String hMISize;
	/**
	 * 堆内存最大大小
	 * 单位：GB
	 */
	private String hMMSize;
	/**
	 * 堆内存已使用内存
	 * 单位：MB
	 */
	private String hSMemory;
	/**
	 * 堆内存可使用内存
	 * 单位：GB
	 */
	private String hMUMemory;
	@Override
	public String toString() {
		return "堆内存： [堆内存初始大小：" + hMISize + ", 堆内存最大大小：" + hMMSize + ", 堆内存已使用：" + hSMemory + ",堆内存可使用："
				+ hMUMemory + "]";
	}
	
}
