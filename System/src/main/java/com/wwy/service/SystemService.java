package com.wwy.service;

import com.wwy.entry.GetEntry;
import com.wwy.entry.RenovateEntry;
/**
 * 
 * @author wwy
 * @date 2019-11-6
 *获取系统监控的业务层接口
 */
public interface SystemService {
	/**
	 * 
	 * @return 封装系统监控信息的实体类
	 * @throws Exception 
	 */
	GetEntry get();

	RenovateEntry renovate();

}
