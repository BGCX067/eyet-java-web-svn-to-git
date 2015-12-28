package lib.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.model.entity.Activite;
import lib.operation.ActiviteOperation;

import com.eyet.framework.util.JsonObject;
import com.eyet.framework.util.Page;
import com.eyet.framework.web.event.EventSupport;

public class ActiviteEvent extends EventSupport {
	
	
	public void activitelist(){
		
		/**
		 * 毫秒转字符串
		 *  java.text.SimpleDateFormat;
		 * SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd  HH-mm-ss");   
        *String a2=dateformat2.format(new Date());
        
       *字符串转毫秒
        public static Long getTimeStemp(String str){
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date d = null;
	        try {
	            d = sdf.parse(str);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        if(d != null) return d.getTime();
	        else return 0;
		}
		 */
		
		/**
		 * 翻页设置
		 */
		Page page = new Page(this);
		
		/**
		 * 查询条件设置, 注意空的设置
		 */
		String title = this.get("title");
		if(title == null || "".equals(title)){
			title="";
		}
		
		ActiviteOperation aop = (ActiviteOperation) this.getOperation("ActiviteOperation");
		List<Activite> list = aop.getActiviteList(page , title);
		
		/**
		 * 翻页选项
		 */
		this.set("pageNum", page.getCurrentPage());//当前页数
		this.set("totalCount", page.getTotalCount());//总数据量
		this.set("numPerPage", page.getEveryPage());//每页显示多少条
		
		/**
		 * 查询字段设置
		 */
		this.set("title" , title);
		
		this.set("list", list);
		this.display();
		
	}
	
	/**
	 * 进入添加页面
	 */
	public void toadd(){
		this.display();
	}
	public void add(){
		
		Activite activite = new Activite();
		
		this.fill(activite);
		activite.setPublishtime((int)(System.currentTimeMillis() / 1000));
		
		ActiviteOperation aop = (ActiviteOperation) this.getOperation("ActiviteOperation");
		boolean success = aop.addActivite(activite);
		
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("navTabId", "b1");
		map.put("callbackType", "");
		
		
		if(success){
			map.put("statusCode", "200");
			map.put("message", "操作成功");
		}else{
			map.put("statusCode", "300");
			map.put("message", "操作失败");
		}
		JsonObject json = JsonObject.fromObject(map);
		this.displayJson(json.toString());
		
	}
	
	/**
	 * 进入编辑页面
	 */
	public void toedit(){
		int id = this.getInt("id");
		ActiviteOperation aop = (ActiviteOperation) this.getOperation("ActiviteOperation");
		this.set("activite", aop.getActivite(id));
		this.display();
	}
	public void edit(){
		Activite activite = new Activite();
		this.fill(activite);
		ActiviteOperation aop = (ActiviteOperation) this.getOperation("ActiviteOperation");
		
		boolean success = aop.editActivite(activite);
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("navTabId", "b1");
		map.put("callbackType", "");
		
		
		if(success){
			map.put("statusCode", "200");
			map.put("message", "操作成功");
		}else{
			map.put("statusCode", "300");
			map.put("message", "操作失败");
		}
		JsonObject json = JsonObject.fromObject(map);
		this.displayJson(json.toString());
	}
	
	public void del(){
		String ids = this.get("ids");
		String[] id = ids.split(",");
		ActiviteOperation aop = (ActiviteOperation) this.getOperation("ActiviteOperation");
		
		boolean success = aop.delActivite(id);
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("navTabId", "b1");
		map.put("callbackType", "");
		
		
		if(success){
			map.put("statusCode", "200");
			map.put("message", "操作成功");
		}else{
			map.put("statusCode", "300");
			map.put("message", "操作失败");
		}
		JsonObject json = JsonObject.fromObject(map);
		this.displayJson(json.toString());
	}
	
	
	public static Long getTimeStemp(String str){
		long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d != null){
        	time = d.getTime();
        }
        return time / 1000;
	}
	
	public static void main(String[] args){
		System.out.println(getTimeStemp("2012-10-17 15:51:00"));
		System.out.println(System.currentTimeMillis() / 1000);
	}
}
