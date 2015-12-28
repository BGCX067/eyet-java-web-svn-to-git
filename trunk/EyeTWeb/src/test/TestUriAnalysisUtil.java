package test;

import com.eyet.framework.web.util.UriParseUtil;

public class TestUriAnalysisUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		print(UriParseUtil.parse("/index.move"));
		print(UriParseUtil.parse("/a.move"));
		print(UriParseUtil.parse("/abc.move"));
		print(UriParseUtil.parse("/Abc/abc.move"));
		print(UriParseUtil.parse("/aBC/Abc.move"));
	}
	
	public static void print(String[] str){
		System.out.println(str[0] + " : " + str[1]);
	}
}
