package com.eyet.framework.operation;

import java.util.HashMap;
import java.util.Map;

import com.eyet.framework.operation.util.LoadPackageClass;
import com.eyet.framework.util.ClassUtil;

/**
 * 
 * @author Hexleo
 * 
 * Operation的容器
 * 负责实例化与获取Operation对象
 *
 */
public class OperationContainer {
	//operation实现类路径为固定路径
	private static final String LIB_OPERATION_IMPL = "lib.operation.impl";
	
	//全局转载operation对象容器，保证只在初始时进行设定，使用中禁止提供set接口
	private Map<String , Object> operations;
		
	//使用单例模式
	private static OperationContainer operationContainer = null;
	
	public OperationContainer(){
		this.initLoadClass();
	}
	
	/**
	 * servlet初始时实例化容器对象
	 */
	public static void initInstance(){
		getInstance();
	}
	
	/**
	 * 使用中获取operationContainer对象
	 * @return
	 */
	public static OperationContainer getInstance(){
		if(operationContainer == null){
			operationContainer = new OperationContainer();
		}
		return operationContainer;
	}
	
	/**
	 * 初始化转载Operation
	 */
	public void initLoadClass(){
		if(operations != null){
			operations.clear();
			operations = null;
		}
		operations = new HashMap<String , Object>(); 
		LoadPackageClass load = new LoadPackageClass();
		String[] classesName = load.getClassesName(LIB_OPERATION_IMPL);
		if(classesName == null){
			return;
		}
		for(String className : classesName){
			Object obj = ClassUtil.getInstance(LIB_OPERATION_IMPL + "." +className);
			//  ...Impl
			className = className.substring(0, className.length() - 4);
			operations.put(className, obj);
		}
		load = null;
	}
	
	public Object getOperation(String name){
		Object obj = null;
		if(operations != null){
			obj = operations.get(name);
		}
		return obj;
	}

}
