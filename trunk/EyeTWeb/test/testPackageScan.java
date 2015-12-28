import java.util.Set;

import junit.framework.TestCase;
import lib.operation.LoginOperation;

import com.eyet.framework.operation.OperationContainer;
import com.eyet.framework.operation.util.LoadPackageClass;


public class testPackageScan extends TestCase {

	public void testGetClasses() {
		findClassesInPackageByFile f  = new findClassesInPackageByFile();
		Set s = f.getClasses("lib.event.verify");
		for(int i=0;i<s.size();i++)
		System.out.println(s);
	}
	
	
	public void testGetClassesName(){
		LoadPackageClass lpc = new LoadPackageClass();
		String[] r = lpc.getClassesName("lib.operation.impl");
		for(String re : r){
			System.out.println(re);
		}
	}
	
	public void testOperationContainer(){
		OperationContainer oc = OperationContainer.getInstance();
		LoginOperation logOp = (LoginOperation) oc.getOperation("LoginOperaton");
		if(logOp != null){
			logOp.login("a", "b");
		}else{
			System.out.println("op == null");
		}
	}
}
