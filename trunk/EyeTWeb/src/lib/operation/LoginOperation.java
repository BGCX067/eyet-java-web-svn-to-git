package lib.operation;


/**
 * 
 * @author Hexleo
 * 
 * 命名规则：
 * 操作名+Operation
 * 
 * 实现方法：
 * 接口+实现类impl
 * 
 * 包名固定：
 * lib.operation   存放接口
 * lib.operation.impl   存放实现
 *
 */
public interface LoginOperation {
	public boolean login(String username , String password);
	public boolean reg(String username , String password , int role);
}
