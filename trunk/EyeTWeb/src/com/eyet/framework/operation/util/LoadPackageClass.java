package com.eyet.framework.operation.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * 
 * @author Hexleo
 * 
 * 仅在类初始化时调用，不使用static模式
 *
 * 仅在固定包路径下寻找，无需迭代子目录
 */
public class LoadPackageClass {
	
	/**
	 * 传入包路径，获取完整的类路径
	 * @param pack
	 * @return
	 */
	public String[] getClassesName(String pack){
		String[] result = null;
        String packagePath = pack.replace('.', '/');  
        Enumeration<URL> dirs = null;
        try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
			while(dirs.hasMoreElements()){
				URL url = dirs.nextElement();
				// 如果是以文件的形式保存在服务器上  
                if ("file".equals(url.getProtocol())) {
                	String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                	File packageDir = new File(filePath);
                	if (!packageDir.exists() || !packageDir.isDirectory()) {  
        	            break;  
        	        }
                	//获得所有class的文件名
                	File[] fileClasses = packageDir.listFiles(new FileFilter(){
						public boolean accept(File pathname) {
							return pathname.getName().endsWith(".class");
						}
                	});
                	int len = fileClasses.length;
                	result = new String[len];
                	//获取.class前的类名
                	for(int i=0 ; i<len ; i++){
                		result[i] = fileClasses[i].getName()
                				.substring(0, fileClasses[i].getName().length() - 6);
                	}
                }
			}
			
		} catch (IOException e) {
			System.out.println("LoadPackageClass -> getClassesName");
			e.printStackTrace();
		}
		return result;
	}
}
