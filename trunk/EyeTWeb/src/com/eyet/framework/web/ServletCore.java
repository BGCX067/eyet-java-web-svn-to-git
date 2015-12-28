package com.eyet.framework.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eyet.framework.global.Global;
import com.eyet.framework.operation.OperationContainer;
import com.eyet.framework.orm.util.DBConfig;
import com.eyet.framework.orm.util.DBConnectionManager;
import com.eyet.framework.web.init.InitConfig;
import com.eyet.framework.web.util.EventUtil;
import com.eyet.framework.web.util.UriParseUtil;
import com.eyet.framework.web.util.VerifyUtil;

public class ServletCore extends HttpServlet {
	private String projectName;
	//存放js等公共文件路径  : /projectName/public
	private String publicPath;
	private String projectPath;
	
	private static final String DEFAULT_FORWARD_URI = "/tpl/";
	private static final String DEFAULT_VIEW_SUFFIX = ".jsp"; 
	private void doCroe(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		req.setAttribute("PUBLIC", this.publicPath);
		req.setAttribute("APP", this.projectPath);
		String uri = req.getRequestURI();
		if(projectName != null && projectName.length() != 0){
			uri = uri.substring(projectName.length()+1 , uri.length());//+1 for first /
		}
		String[] eventUri = UriParseUtil.parse(uri);
		if(eventUri == null){
			resp.getWriter().print("eventUri is null : 404+" + uri);
			return;
		}else{
			doVerify(req , resp , eventUri[0] , eventUri[1]);
		}
		
	}
	
	/**
	 * 执行Verify操作
	 * @param req
	 * @param resp
	 * @param eventName
	 * @param eventMethod
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doVerify(HttpServletRequest req, HttpServletResponse resp , 
			String eventName , String eventMethod) throws ServletException, IOException{
		
		VerifyUtil verifyUtil = new VerifyUtil(req , resp , eventName , eventMethod);
		if(verifyUtil.isPass()){
			doEvent(req , resp , eventName , eventMethod);
		}else{
			String redirectUri = verifyUtil.getFailLoction();
			redirect(resp , redirectUri);
		}
		
	}
	
	
	/**
	 * 执行Event操作
	 * @param req
	 * @param resp
	 * @param eventName
	 * @param eventMethod
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doEvent(HttpServletRequest req, HttpServletResponse resp , 
			String eventName , String eventMethod) throws ServletException, IOException{
		EventUtil eventUtil = new EventUtil(eventName , eventMethod , req);
		if(eventUtil.isNormal()){
			if(eventUtil.isJson()){
				resp.setHeader("Content-Type", "text/html;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.print(eventUtil.getJsonObject());
			}else if(eventUtil.isForward()){
				req.setAttribute("param", eventUtil.getResponseParameter());
				this.getServletContext()
					.getRequestDispatcher(DEFAULT_FORWARD_URI + eventName + "/" + eventMethod + DEFAULT_VIEW_SUFFIX)
					.forward(req, resp);
			}else{
				System.out.println(projectName + "---" +eventUtil.getRedirectLocation());
				String redirectUri = eventUtil.getRedirectLocation();
				redirect(resp , redirectUri);
			}
		}else{
			resp.getWriter().print("eventUtil is not normal:404");
		}
	}
	
	/**
	 * 重定向方法
	 * @param resp
	 * @param redirectUri
	 * @throws IOException
	 */
	private void redirect(HttpServletResponse resp , String redirectUri) throws IOException{
		
		if(redirectUri == null || redirectUri.length() == 0){
			resp.getWriter().print("redirectUri not found:404");
			return;
		}
		
		if(projectName == null || projectName.length() == 0){
			
			resp.sendRedirect(redirectUri);
		}else{
			resp.sendRedirect("/" + projectName + "/" + redirectUri);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doCroe(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doCroe(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		projectName = config.getInitParameter("projectName");
		if(projectName != null && projectName.length() != 0){
			projectPath = "/" + projectName;
			publicPath = "/" + projectName + "/public";
		}else{
			projectPath = "";
			publicPath = "/public";
		}
		
		Global.init();
		Global.set(Global.ABSOLUTE_PATH, this.getServletContext().getRealPath("/"));
		InitConfig.init(config);
		//加载Operation类
		OperationContainer.initInstance();
	}

	@Override
	public void destroy() {
		//释放连接池中的连接
		DBConnectionManager conMan = DBConnectionManager.getInstance();
		conMan.release();
		System.out.println("run destroy");
		super.destroy();
	}
	
	
}
