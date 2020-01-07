package com.wwy.service.impl;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wwy.entry.GetEntry;
import com.wwy.entry.RenovateEntry;
import com.wwy.service.SystemService;
import com.wwy.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author wwy
 * @date 2019-11-6
 *获取系统监控的业务层接口实现类
 */
@Service
@Slf4j
public class SystenServiceImpl implements SystemService{
	@Autowired
	private SystemUtil SystemUtil;//=new SystemUtil();
	@Value("${server.port}")
	private String port;
	/**
	 * 
	 * @return 封装固定的系统监控信息的实体类
	 * @throws Exception 
	 */
	@Override
	public GetEntry get() {
		try {
			GetEntry entry=SystemUtil.get();
			log.info("\n执行正常\n"+"\nIP为"+InetAddress.getLocalHost().getHostAddress()+"，端口号为："+port+"的服务器信息："+
					"\n完整请求路径：\n"+InetAddress.getLocalHost().getHostAddress()+":"+port+"/system/get"+
					"\n---------------------------start----------------------------------\n"
					+ entry.toString()
					+"\n----------------------------end---------------------------------");
			return entry;
			
		}catch(Exception e) {
			log.error("系统错误");
			log.error(e.toString());
			return null;
		}


	}
	/**
	 * 
	 * @return 封装动态的的系统监控信息的实体类
	 * @throws Exception 
	 */
	@Override
	public RenovateEntry renovate() {
		try {
			RenovateEntry entry=SystemUtil.Renovate();
			log.info("\n执行正常\n"+"\nIP为"+InetAddress.getLocalHost().getHostAddress()+"，端口号为："+port+"的服务器信息："+
					"\n完整请求路径：\n"+InetAddress.getLocalHost().getHostAddress()+":"+port+"/system/renovate"+
					"\n---------------------------start----------------------------------\n"
					+ entry.toString()
					+"\n----------------------------end---------------------------------");
			return entry;
		}catch(Exception e) {
			log.error("系统错误");
			log.error(e.toString());
			return null;
		}
	}

}
