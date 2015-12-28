<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<div class="pageContent">
	<form method="post" action="demo/common/ajaxDone.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="98">
			<p>
				<label>默认格式：</label>
				<input type="text" name="date1" class="date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd</span>
			</p>
			<p>
				<label>定义年份：</label>
				<input type="text" name="date2" class="date" yearstart="-80" yearend="5" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date3" class="date" format="yyyy/MM/dd" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy/MM/dd</span>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date4" class="date" format="dd/MM/yyyy" yearstart="-20" yearend="5" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">dd/MM/yyyy</span>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date5" class="date" format="dd/MM/yy" yearstart="-5" yearend="5"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">dd/MM/yy</span>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date6" class="date" format="yyyyMMdd" yearstart="-5" yearend="5"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyyMMdd</span>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date7" class="date" format="yyyy年MM月dd日" yearstart="-5" yearend="5"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy年MM月dd日</span>
			</p>
			<p>
				<label>自定义日期格式：</label>
				<input type="text" name="date8" class="date" format="y年M月d日" yearstart="-20" yearend="5"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">y年M月d日</span>
			</p>
			
			<div class="divider"></div>
			<h3>日期 + 时间</h3>
			<div class="unit">
				<label>自定义日期格式：</label>
				<input type="text" name="date10" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd HH:mm:ss</span>
			</div>
			<div class="unit">
				<label>自定义日期格式：</label>
				<input type="text" name="date11" class="date" format="yyyy-MM-dd HH:mm" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd HH:mm</span>
			</div>
			<div class="unit">
				<label>自定义日期格式：</label>
				<input type="text" name="date12" class="date" format="yyyy-MM-dd HH:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd HH:ss</span>
			</div>
				
			<div class="unit">
				<label>自定义日期格式：</label>
				<input type="text" name="date13" class="date" format="y年M月d日 H点" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">y年M月d日 H点</span>
			</div>
			<div class="unit">
				<label>自定义日期格式：</label>
				<input type="text" name="date14" class="date" format="EEE HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">EEE HH:mm:ss</span>
			</div>
			<div class="unit">
				<label>自定义只有时间：</label>
				<input type="text" name="date14" class="date" format="HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">HH:mm:ss</span>
			</div>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>