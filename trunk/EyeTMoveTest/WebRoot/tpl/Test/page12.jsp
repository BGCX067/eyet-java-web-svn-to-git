<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<div class="pageContent">
	<form method="post" action="demo/common/ajaxDone.html" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>文件一：</label>
				<input name="file1" type="file" />
			</p>
			<p>
				<label>文件二：</label>
				<input name="file2" type="file" />
			</p>
			
			<p><label>多文件上传：</label>
				<a rel="w_uploadify" target="navTab" href="w_uploadify.html" class="button"><span>uploadify上传示例</span></a>
			</p>
		
		
		
			<fieldset>
				<legend>单行文本框</legend>
				<dl>
					<dt>普通输入框：</dt>
					<dd><input name="field1" type="text" /></dd>
				</dl>
				<dl>
					<dt>提示信息：</dt>
					<dd><input name="field2" type="text" alt="提示信息"/></dd>
				</dl>
				<dl>
					<dt>必填：</dt>
					<dd><input class="required" name="field3" type="text" /></dd>
				</dl>
				<dl>
					<dt>错误：</dt>
					<dd><input class="error" name="field4" type="text" /></dd>
				</dl>
				<dl>
					<dt>只读：</dt>
					<dd><input readonly="true" name="field5" type="text" /></dd>
				</dl>
				<dl>
					<dt>禁用：</dt>
					<dd><input disabled="true" name="field6" type="text" /></dd>
				</dl>
				<dl>
					<dt>密码：</dt>
					<dd><input name="password" type="text" /></dd>
				</dl>
			</fieldset>
			
			<fieldset>
				<legend>多行文本框(文本域)</legend>
				<dl class="nowrap">
					<dt>普通文本框：</dt>
					<dd><textarea name="textarea1" cols="80" rows="2"></textarea></dd>
				</dl>
				<dl class="nowrap">
					<dt>必填：</dt>
					<dd><textarea name="textarea2" cols="80" rows="2"></textarea></dd>
				</dl>
				<dl class="nowrap">
					<dt>错误：</dt>
					<dd><textarea name="textarea3" class="error" cols="80" rows="2"></textarea></dd>
				</dl>
				<dl class="nowrap">
					<dt>只读：</dt>
					<dd><textarea name="textarea4" readonly="true" cols="80" rows="2"></textarea></dd>
				</dl>
				<dl class="nowrap">
					<dt>禁用：</dt>
					<dd><textarea name="textarea5" disabled="true" cols="80" rows="2"></textarea></dd>
				</dl>
			</fieldset>
		</div>
		
		<p><label>弹出窗口:</label>
			<div style="float:left; display:block; overflow:hidden; width:400px; padding:0 10px; line-height:21px;">
				<a class="button" href="demo_page1.html" target="dialog" rel="dlg_page1" max="true" title="打开窗口1 [自定义标题]" width="800" height="480"><span>打开窗口1</span></a><br /><br />
				<a class="button" href="demo_page1.html" target="dialog" rel="dlg_page2"><span>打开窗口2</span></a><br /><br />
				<a class="button" href="demo_page2.html" target="dialog" rel="dlg_page2" width="645" height="370" fresh="false"><span>打开窗口3</span></a><br /><br />
				<div class="divider"></div>
				<a class="button" href="demo_page1.html" target="dialog" rel="dlg_page4" title="打开窗口4 [自定义标题]"><span>打开窗口4</span></a><br /><br />
				<a class="button" href="demo_page2.html" target="dialog" rel="dlg_page5" close="closedialog" param="{msg:'gogo'}"><span>打开窗口5</span></a><br /><br />
				<a class="button" href="demo_page2.html" target="dialog" rel="dlg_page5"><span>打开窗口6</span></a>
			</div>
			<div style="float:left; display:block; overflow:hidden; width:400px; padding:0 10px; line-height:21px;">
				<a class="button" href="demo_page1.html" target="dialog" rel="dlg_page7"><span>打开窗口7</span></a><br /><br />
				<a class="button" href="demo_page4.html" target="dialog" rel="dlg_page8"><span>打开窗口8</span></a><br /><br />
				<a class="button" href="demo_page4.html" target="dialog" rel="dlg_page9"><span>打开窗口9</span></a><br /><br />
				<div class="divider"></div>
				<a class="button" href="demo_page1.html" target="dialog" rel="dlg_page10" mask="true" title="模态窗口1 [自定义标题]"><span>模态窗口1</span></a><br /><br />
				<a class="button" href="demo_page2.html" target="dialog" rel="dlg_page11" mask="true"><span>模态窗口2</span></a><br /><br />
				<a class="button" href="demo_page2.html" target="dialog" rel="dlg_page12" mask="true"><span>模态窗口3</span></a><br /><br />
			</div>
		</p>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>