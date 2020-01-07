package com.wwy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.entry.GetEntry;
import com.wwy.entry.RenovateEntry;
import com.wwy.service.SystemService;
/**
 * 
 * @author wwy
 * @date 2019-11-6
 *获取系统监控的controller
 */
@CrossOrigin
@RestController
@RequestMapping("system")
public class SystemController {
	@Autowired
	private SystemService systemService;
	/**
	 * 
	 * @return 封装系统监不变的控信息的实体类
	 */
@GetMapping("get")
	public APIEntry get() {

		GetEntry entry=systemService.get();

		return (entry==null)?new APIEntry(500,"数据获取有误",null):new APIEntry(200,"OK",entry);
	}
/**
 * 
 * @return 封装系统监固定的的控信息的实体类
 */
@GetMapping("renovate")
public APIEntry renovate() {

	RenovateEntry entry=systemService.renovate();

	return (entry==null)?new APIEntry(500,"数据获取有误",null):new APIEntry(200,"OK",entry);
}
@GetMapping("test")
public String test() {
	return "服务2";
}
}

