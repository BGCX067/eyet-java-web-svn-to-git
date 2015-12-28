package lib.event;

import java.util.HashMap;
import java.util.Map;

import com.eyet.framework.util.JsonObject;
import com.eyet.framework.web.event.EventSupport;

public class ErrorEvent extends EventSupport {
	
	public void adderror(){
		this.display();
	}
	
	public void errorjson(){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("statusCode",200);
		jsonMap.put("message","操作成功");
		JsonObject jobj = JsonObject.fromObject(jsonMap);
		this.displayJson(jobj.toString());
	}
}
