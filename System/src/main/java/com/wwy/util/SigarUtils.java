package com.wwy.util;

import java.io.File;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author wwy
 * @date 2019-11-5
 *sigar工具类
 */
@Slf4j
@Component
public class SigarUtils {
	/**
	 * 获取存放脚本的地址
	 */
	//将脚本存放位置配置在yml文件中，通过@value读取
	@Value("${sigar.path}")
	private  String path;
	/**
	 * 
	 * @return Sigar
	 * 初始化
	 */
	public  Sigar initSigar() {
		try {
			//如果yml配置中sigar.path为system则加载路径为当前项目根目录
			//如果yml中配置不是system则根据yml中的配置加载
			File classPath=null;
			classPath=	("system".equals(path)?
					new File(System.getProperty("user.dir")+"\\hyperic-sigar-1.6.4\\sigar-bin\\lib")
					:new File(path));
			String path = System.getProperty("java.library.path");
			String sigarLibPath = classPath.getCanonicalPath();
			//防止java.library.path重复加载
			if (!path.contains(sigarLibPath)) {		
				path += (isOSWin()?(";" + sigarLibPath): (":" + sigarLibPath));
				System.setProperty("java.library.path", path);
			}
			return new Sigar();
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	public  boolean isOSWin(){
		String OS = System.getProperty("os.name").toLowerCase();
		return OS.indexOf("win") >= 0? true:false;
	}
}