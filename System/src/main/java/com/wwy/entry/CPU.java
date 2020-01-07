
package com.wwy.entry;

import lombok.Data;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *封装cpu信息的实体类
 */
@Data
public class CPU {
	/**
	 * cpu核心数
	 */
	private Integer infos;
	/**
	 * cpu最大功率
	 * 单位是ghz
	 */
	private String MGhz;
	/**
	 * cpu实时频率
	 * 单位是ghz
	 */
	private String ghz; 
	/**
	 * cpu使用率
	 * 百分比
	 */
	private String proportion;
	@Override
	public String toString() {
		return "CPU：[核心数：" + infos + ",最大频率：" + MGhz + ", 实时频率：" + ghz + ", 使用率：" + proportion + "]";
	}
	
}
