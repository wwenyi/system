package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *封装磁盘信息的实体类
 */
@Data
public class Disc {
	/**
	 * 盘符路径
	 */
	private String dPath;
	/**
	 * 文件系统
	 */
	private String fSystem;
	/**
	 * 盘符类型
	 * 
	 */
	private String type;
	/**
	 * 总大小
	 * 单位:GB
	 */
	private String tSize;
	/**
	 * 可用大小
	 * 单位:GB
	 */
	private String aSize;
	/**
	 * 已用大小
	 * 单位:GB
	 */
	private String aUSize;
	/**
	 * 使用百分比
	 */
	private String proportion;
	@Override
	public String toString() {
		return "硬盘： [盘符：" + dPath + ", 文件系统：" + fSystem + ", 盘符类型：" + type + ", 总大小：" + tSize + ", 可用大小："
				+ aSize + ", 已用大小：" + aUSize + ", 使用率：" + proportion + "]";
	}
	
}
