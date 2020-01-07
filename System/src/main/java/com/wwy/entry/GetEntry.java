package com.wwy.entry;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 * 封装返回系统监控的实体类
 */
@Data
public class GetEntry {
	/**
	 * 服务器信息
	 */
	private Server server;
	/**
	 * 平台信息
	 */
	private Platform platform;
	/**
	 * 磁盘信息
	 */
	private List<Disc> discs;
	/**
	 * JVM
	 */
	private JVM jvm;
	@Override
	public String toString() {
		return "服务器固定数据如下：\n" + server + ", \n平台信息如下：\n" + platform + ", \n硬盘信息如下：\n" + discs + ", \njvm信息如下：\n" + jvm ;
	}
	
	
}
