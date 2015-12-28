package com.eyet.framework.web.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eyet.framework.util.ClassUtil;
import com.eyet.framework.web.verify.Verify;

public class VerifyUtil {
	
	private Verify verify;
	
	private boolean isPass;
	
	public VerifyUtil(HttpServletRequest request , HttpServletResponse response 
			, String eventName , String eventMethod){
		verify = (Verify) ClassUtil.getVerifyInstance(eventName);
		//默认通过验证
		isPass = true;
		if(verify != null){
			verify.initVerify(request, response, eventName, eventMethod);
			isPass = verify.execute();
		}
	}
	
	public boolean isPass(){
		return isPass;
	}
	public String getFailLoction(){
		String url = null;
		if(verify != null){
			try {
				url = new String (verify.failLocation().getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				System.out.println("verifyUtil : getFailLoction");
			}
		}
		return url;
	}
}
