package com.wwy.util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.wwy.entry.CPU;
import com.wwy.entry.Disc;
import com.wwy.entry.GetEntry;
import com.wwy.entry.Heap;
import com.wwy.entry.JVM;
import com.wwy.entry.JVMMemory;
import com.wwy.entry.Memory;
import com.wwy.entry.Platform;
import com.wwy.entry.RenovateEntry;
import com.wwy.entry.Server;
import com.wwy.entry.UHeap;

/**
 * 
 * @author wwy
 * @Date 2019-11-5
 *查询系统监控信息的工具类
 */
@Component
public class SystemUtil {
	@Autowired
	private SigarUtils s;

	/**
	 * 定义内存单位转换的进制为1000（或者1024）
	 */
	@Value("${bin.conversion}")
	private int conversion;
	@Value("${file.logpath}")
	private String logpath;
	@Value("${file.filepath}")
	private String filepath;

	/**
	 * 
	 * @throws SigarException 
	 * 将所需要的动态信息封装到实体类中
	 */
	public RenovateEntry Renovate() throws SigarException {
		//初始化
		s.initSigar();
		RenovateEntry entry=new RenovateEntry();
		CPU cpu=cpu();	
		Memory memory=memory();
		
		entry.setCpu(cpu);
		entry.setMemory(memory);
		return entry;
		}
	/**
	 * 
	 * @throws Exception 
	 * 将所需要的固定信息封装到实体类中
	 */
	public GetEntry get() throws Exception {
		//初始化
		s.initSigar();
		//新建entry对象
		GetEntry entry =new GetEntry();
		//执行方法获取相应数据
		List<Disc> discs=disc();
		Platform platform=platform();
		JVM jvm=jvm();
		Server server=server();
		//将数据封装到entry对象中
		entry.setJvm(jvm);
		entry.setDiscs(discs);
		entry.setPlatform(platform);
		entry.setServer(server);
		return entry;
	}
	/**
	 * @return 平台相关信息 
	 * @throws UnknownHostException
	 */
	public Platform platform() {
		
		Platform platform=new Platform();
		Properties props = System.getProperties();
		//文件路径
		platform.setWorkPath(props.getProperty("user.dir"));
		//日志存放路径(暂时写死)
		platform.setLogPath(logpath);
		//文件上传路径（暂时写死）
		platform.setFilePath(filepath);
		return platform;
	}
	/**
	 * 
	 * @throws UnknownHostException
	 */
	public  JVM jvm() throws UnknownHostException {
		JVM jvm=new JVM();
		//封装虚拟机信息的接口
		RuntimeMXBean runtime=ManagementFactory.getRuntimeMXBean();		
		
		Properties props = System.getProperties();
		  
		/////////////////封装数据(jvm相关)/////////////////////
		//jvm名称
		jvm.setJName(runtime.getVmName());
		//java版本
		jvm.setJEdition(props.getProperty("java.version")+",供应商"+props.getProperty("java.vendor"));
		//java的安装路径
		jvm.setJPath(System.getProperty("java.home"));
		//运行时长
		Long t=runtime.getUptime();
		jvm.setJRuntime(new SimpleDateFormat("HH小时mm分ss秒").format(new Date(t)));
		//启动时间
		Long st=runtime.getStartTime();
		jvm.setJSTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(st)));
		//启动参数
		List<String> list=runtime.getInputArguments();
		
		jvm.setJSParameter(list);;
		
		return jvm;
	}

	/**
	 * 
	 * @return 内存信息
	 * @throws SigarException
	 */
	public  Memory memory() throws SigarException {
		Memory memory=new Memory();
		JVMMemory jm=new JVMMemory();
		Heap h=new Heap();
		UHeap uh=new UHeap();
		memory.setJvmMemory(jm);
		memory.setHeap(h);
		memory.setUHeap(uh);
		Sigar sigar = new Sigar();
		Mem mem = sigar.getMem();
		//堆内存使用情况
				MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
				//非堆内存使用情况
				MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
				Runtime r = Runtime.getRuntime();
		////////////////////封装数据//////////////////////
		//总内存
		memory.setTMemory(kbToGBString(mem.getTotal()));
		//剩余内存
		memory.setSMemory(kbToGBString(mem.getFree()));
		//已使用内存
		memory.setAUMemory(kbToGBString(mem.getUsed()));
		//内存使用率
		memory.setProportion(retain2((mem.getUsed()/(mem.getTotal()*1.0))*100)+"%");
		
		//JVM总内存数
		memory.getJvmMemory().setJTMemory(kbToMBString(r.totalMemory()));
				//JVM剩余内存
		memory.getJvmMemory().setJSMemory(kbToMBString(r.freeMemory()));
				//JVM已使用内存
		memory.getJvmMemory().setJAUMemory(kbToMBString(r.totalMemory()-r.freeMemory()));
				//jvm内存使用率
		memory.getJvmMemory().setJProportion(retain2(((r.totalMemory()-r.freeMemory())/(r.totalMemory()*1.0))*100)+"%");
				/////////////////////////////封装数据（堆内存及非堆内存）////////////////////////////////////
				//堆内存初始大小
		memory.getHeap().setHMISize(kbToMBString(heapMemoryUsage.getInit()));
				//非堆内存初始大小
		memory.getUHeap().setUHMISize(kbToMBString(nonHeapMemoryUsage.getInit()));
				//堆内存最大大小
		memory.getHeap().setHMMSize(kbToGBString(heapMemoryUsage.getMax()));
				//非堆内存最大大小
		memory.getUHeap().setUHMMSize(kbToGBString(nonHeapMemoryUsage.getMax()));
				//堆内存已使用大小
		memory.getHeap().setHSMemory(kbToMBString(heapMemoryUsage.getUsed()));
				//非堆内存已使用大小
		memory.getUHeap().setUHSMemory(kbToMBString(nonHeapMemoryUsage.getUsed()));
				//堆内存可使用大小
		memory.getHeap().setHMUMemory(kbToGBString(heapMemoryUsage.getCommitted()));
				//非堆内存可使用大小
		memory.getUHeap().setUHMUMemory(kbToGBString(nonHeapMemoryUsage.getCommitted()));		
		return memory;

	}
	/**
	 * 
	 * @return CPU相关信息
	 * @throws SigarException
	 */
	public  CPU cpu() throws SigarException {
		///////////////定义变量/////////////////////////
		//cpu的总频率,单位m
		Integer hz=0;
		//cpu实时频率，单位m
		Integer usedMhz=0;
		//cpu使用率
		Double combined=0.0;

		//////////////计算cpu数据///////////////////////
		CPU cpu=new CPU();
		Sigar sigar = new Sigar();
		CpuInfo infos[] = sigar.getCpuInfoList();
		CpuPerc cpuList[] = null;
		cpuList = sigar.getCpuPercList();
		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
			CpuInfo info = infos[i];
			hz+=info.getMhz();
			combined+=cpuList[i].getCombined();
			int used =(int)((double)info.getMhz()*(double)cpuList[i].getCombined());
			usedMhz+=used;
		}
		/////////////////封装数据///////////////////////////////
		//cpu核心数
		cpu.setInfos(sigar.getCpuInfoList().length);
		//cpu总频率
		cpu.setMGhz(retain2(hz/(conversion*1.0*sigar.getCpuInfoList().length))+"GHZ");
		//cpu实时频率 
		cpu.setGhz(retain2(usedMhz/(conversion*1.0))+"GHZ");
		//cpu使用率
		cpu.setProportion(CpuPerc.format(combined/sigar.getCpuInfoList().length));
		return cpu;  
	} 
	/**
	 * 
	 * @return 服务器信息
	 * @throws UnknownHostException
	 */
	public Server server() throws UnknownHostException {
		//获取服务器ip
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		//获取服务器名
		Map<String, String> map = System.getenv();
		String computerName = map.get("COMPUTERNAME");// 获取计算机名
		///////////////////////封装数据/////////////////////////
		Server server=new Server();
		//计算机名
		server.setSName(computerName);
		//操作系统名
		server.setOs(System.getProperty("os.name")+",版本"+System.getProperty("os.version"));
		//服务器ip
		server.setSIP(ip);
		//架构
		server.setSystemArchitecture(System.getProperty("os.arch"));
		return server;
	}
	/**
	 * 
	 * @return 磁盘信息的list集合
	 * @throws Exception
	 */
	public List<Disc> disc() throws Exception {
		List<Disc> list=new ArrayList<>();
		Sigar sigar = new Sigar();
		//获取所有磁盘
		FileSystem fslist[] = sigar.getFileSystemList();
		//遍历获取每个磁盘的信息并封装的list集合中
		for (int i = 0; i < fslist.length; i++) {
			Disc disc=new Disc();
			FileSystem fs = fslist[i];
			/////////////////////////////封装数据//////////////////////
			//盘符名称
			disc.setDPath(fs.getDevName());
			//文件系统（如：FAT32、NTFS）
			disc.setFSystem(fs.getSysTypeName());
			//盘符类型（如：本地硬盘、光驱、网络文件系统）
			disc.setType(fs.getTypeName());
			FileSystemUsage usage = null;
			usage = sigar.getFileSystemUsage(fs.getDirName());
			switch (fs.getType()) {
			case 0: // TYPE_UNKNOWN ：未知
				break;
			case 1: // TYPE_NONE
				break;
			case 2: // TYPE_LOCAL_DISK : 本地硬盘	
				//总大小
				disc.setTSize(mbToGBString(usage.getTotal()));
				//可用大小
				disc.setASize(mbToGBString(usage.getAvail()));
				//已用大小
				disc.setAUSize(mbToGBString(usage.getUsed()));
				//使用比例
				disc.setProportion(retain2(usage.getUsePercent() * 100D)+"%");
				break;
			case 3:// TYPE_NETWORK ：网络
				break;
			case 4:// TYPE_RAM_DISK ：闪存
				break;
			case 5:// TYPE_CDROM ：光驱
				break;
			case 6:// TYPE_SWAP ：页面交换
				break;
			}
			//将磁盘信息封装到list集合中
			list.add(disc);
		}

		return list;
	}
	/**
	 * 
	 * @param kb传递的以kb为单位的内存数量
	 * @return 返回的String类型的以GB为单位的内存量
	 * 将单位为kb的数据转换为单位为GB，并保留两位小数以String类型返回
	 */
	public String kbToGBString(long kb) {
		double gb=kb/(conversion*conversion*conversion*1.0);
		return String.format("%.2f", gb)+"GB";
	}
	/**
	 * 
	 * @param mb传递的以mb为单位的内存数量
	 * @return 返回的String类型的以GB为单位的内存量
	 * 将单位为mb的数据转换为单位为GB，并保留两位小数以String类型返回
	 */
	public String mbToGBString(long mb) {
		double gb=mb/(conversion*conversion*1.0);
		return String.format("%.2f", gb)+"GB";
	}
	/**
	 * 
	 * @param Kb传递的以Kb为单位的内存数量
	 * @return 返回的String类型的以MB为单位的内存量
	 * 将单位为Kb的数据转换为单位为MB，并保留两位小数以String类型返回
	 */
	public String kbToMBString(long Kb) {
		double gb=Kb/(conversion*conversion*1.0);
		return String.format("%.2f", gb)+"MB";
	}
	
	public String retain2(double n) {
		return String.format("%.2f", n);
	}
	
}
