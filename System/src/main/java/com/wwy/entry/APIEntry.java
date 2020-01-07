package com.wwy.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author wwy
 * @date 2019-11-6
 * 封装返回前端的数据的实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIEntry {
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 状态信息
	 */
	private String msg;
	/**
	 * 查询结果数据
	 */
	private Object data;
}
