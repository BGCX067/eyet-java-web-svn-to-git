<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<h2 class="contentTitle">表单验证</h2>

<div class="pageContent">
	
	<form method="post" action="demo/common/ajaxDone.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>客 户 号：</dt>
				<dd>
					<input type="text" name="name" maxlength="20" class="required" />
					<span class="info">class="required"</span>
				</dd>
			</dl>
			<dl>
				<dt>客 户 名：</dt>
				<dd>
					<input type="text" name="name" maxlength="20" class="required" />
					<span class="info">class="required"</span>
				</dd>
			</dl>
			<dl>
				<dt>必填+邮箱：</dt>
				<dd>
					<input type="text" name="email" class="required email" alt="请输入您的电子邮件"/>
					<span class="info">class="required email"</span>
				</dd>
			</dl>
			<dl>
				<dt>电话：</dt>
				<dd>
					<input type="text" name="phone" class="phone" alt="请输入您的电话"/>
					<span class="info">class="phone"</span>
				</dd>
			</dl>
			<dl>
				<dt>密码：</dt>
				<dd>
					<input id="w_validation_pwd" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
					<span class="info">class="required alphanumeric" minlength="6" maxlength="20"</span>
				</dd>
			</dl>
			<dl>
				<dt>确认密码：</dt>
				<dd>
					<input type="password" name="password" class="required" equalto="#w_validation_pwd"/>
					<span class="info">class="required" equalto="#xxxId"</span>
				</dd>
			</dl>
			
			<dl>
				<dt>注册资金</dt>
				<dd>
				    <select name="capital" class="required combox">
					<option value="">请选择</option>
					<option value="10">10</option>
					<option value="50" selected>50</option>
					<option value="100">100</option>
				    </select>
				    <span class="unit">万元</span>
				</dd>
			</dl>
			<dl>
				<dt>日期：</dt>
				<dd>
					<input type="text" name="startDate" class="date" size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>字母：</dt>
				<dd>
					<input type="text" name="lettersonly" class="lettersonly" />
					<span class="info">class="lettersonly"</span>
				</dd>
			</dl>
			<dl>
				<dt>网址：</dt>
				<dd>
					<input type="text" name="url" class="url" />
					<span class="info">class="url"</span>
				</dd>
			</dl>
			
			<dl>
				<dt>单选框：</dt>
				<dd>
					<label><input type="radio" name="r1" />选择1</label>
					<label><input type="radio" name="r1" />选择2</label>
					<label><input type="radio" name="r1" />选择3</label>
					<label><input type="radio" name="r1" />选择4</label>
				</dd>
			</dl>
			
			<dl>
				<dt>复选框：</dt>
				<dd>
					<label><input type="checkbox" name="c1" value="1" />选择1</label>
					<label><input type="checkbox" name="c1" value="2" />选择2</label>
					<label><input type="checkbox" name="c1" value="3" />选择3</label>
					<label><input type="checkbox" name="c1" value="4" />选择4</label>
				</dd>
			</dl>
			
				<dl>
				<dt>全选：</dt>
				<dd>
					<label style="float:left"><input type="checkbox" class="checkboxCtrl" group="c1" />全选</label>
				</dd>
			</dl>
			
			<dl class="nowrap">
				<dt>普通文本框：</dt>
				<dd><textarea name="textarea1" cols="50" rows="5"></textarea></dd>
		    </dl>
		    
		    <dl class="nowrap">
				<dt>编辑器：</dt>
				<dd><textarea class="editor" name="description" rows="10" cols="60" tools="mfull">mfull(多行完全)</textarea></dd>
		    </dl>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>