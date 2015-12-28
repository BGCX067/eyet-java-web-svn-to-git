package test;

public class lyhTest {

	private static String formatSetter(String str){
		StringBuilder sb = new StringBuilder();
		sb.append("set");
		sb.append(String.valueOf(str.charAt(0)).toUpperCase());
		sb.append(str.substring(1, str.length()));
		return sb.toString();
		
	}
	/**
	 * 整理格式，获得getter方法名称
	 * @return
	 */
	private static String formatGetter(String str){
		StringBuilder sb = new StringBuilder();
		sb.append("get");
		sb.append(String.valueOf(str.charAt(0)).toUpperCase());
		sb.append(str.substring(1, str.length()));
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(formatSetter("user"));
		System.out.println(formatGetter("user"));
	}
}
