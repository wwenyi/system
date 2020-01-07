package com.wwy.entry;



import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 * 封装返回动态的系统监控的实体类
 */
@Data
public class RenovateEntry {
	/**
	 * cpu信息
	 */
	private CPU cpu;
	/**
	 * 内存信息
	 */
	private Memory memory;
	@Override
	public String toString() {
		return "服务器实时数据如下：\n [\ncpu信息如下：\n" + cpu + ",\n 内存信息如下：\n" + memory + "]";
	}
	
}
