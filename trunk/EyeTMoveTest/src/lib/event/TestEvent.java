package lib.event;

import java.util.HashMap;
import java.util.Map;

import com.eyet.framework.util.JsonObject;
import com.eyet.framework.util.Page;
import com.eyet.framework.web.event.EventSupport;

public class TestEvent extends EventSupport {
	public void page1(){
		/*
		 * 页面1提交后提供的数据：
		 * 查询条件:keyword
		 * 当前页面:pageNum
		 * 每页显示多少条:numPerPage
		 */
		
		//进入页面1需要准备的数据
		//1、页面内容
		String msg = "信息....";
		int intmsg = 123456789;
		this.set("msg", msg);
		this.set("intmsg", intmsg);
		//2、分页数据
		Page page = new Page(this);
		page.setCount(100);
		this.set("pageNum", page.getCurrentPage());//当前页数
		this.set("totalCount", page.getTotalCount());//总数据量
		this.set("numPerPage", page.getEveryPage());//每页显示多少条
		this.display();
	}
	
	public void page2(){
		/*
		 * 页面2提交后提供的数据：
		 * 当前页面:pageNum
		 * 每页显示多少条:numPerPage
		 */
		
		//进入页面2需要准备的数据
		//1、页面内容
		String msg = "信息....";
		int intmsg = 123456789;
		this.set("msg", msg);
		this.set("intmsg", intmsg);
		//2、分页数据
		Page page = new Page(this);
		page.setCount(100);
		this.set("pageNum", page.getCurrentPage());//当前页数
		this.set("totalCount", page.getTotalCount());//总数据量
		this.set("numPerPage", page.getEveryPage());//每页显示多少条
		this.display();
	}
	
	public void page3(){
		this.display();
	}
	public void page5(){
		this.display();
	}
	/*
	 * 操作成功
	 */
	public void page6(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("statusCode", "200");
		map.put("message", "操作成功");
		map.put("navTabId", "mypage1");
		map.put("callbackType", "closeCurrent");
		
		JsonObject json = JsonObject.fromObject(map);

		this.displayJson(json.toString());
	}
	/*
	 * 操作失败
	 */
	public void page7(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("statusCode", "300");
		map.put("message", "操作失败");
		map.put("navTabId", "mypage7");
		map.put("callbackType", "closeCurrent");
		
		JsonObject json = JsonObject.fromObject(map);

		this.displayJson(json.toString());
	}
	/*
	 * 回话超时
	 */
	public void page8(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("statusCode", "301");
		map.put("message", "会话超时");
		map.put("navTabId", "mypage8");
		map.put("callbackType", "closeCurrent");
		
		JsonObject json = JsonObject.fromObject(map);

		this.displayJson(json.toString());
	}
	public void page9(){
		this.display();
	}
	public void page10(){
		this.display();
	}
	public void page11(){
		this.display();
	}
	public void page12(){
		this.display();
	}

}
