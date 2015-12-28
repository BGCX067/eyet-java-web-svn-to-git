package lib.operation.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.model.RoleModel;
import lib.model.UserModel;
import lib.model.entity.Role;
import lib.model.entity.User;
import lib.operation.MemberOperation;
import lib.util.IOUtil;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.eyet.framework.global.Global;
import com.eyet.framework.orm.Inquery;
import com.eyet.framework.util.Page;

public class MemberOperationImpl implements MemberOperation {

	public User dologin(String username, String passwd) {

		UserModel userModel = new UserModel();
		Inquery query = new Inquery();
		query.setString("username", username);
		query.setString("passwd", passwd);
		List<User> userlist = userModel.where(query).select();

		if (userlist != null && userlist.size() == 1) {
			return userlist.get(0);
		}
		return null;
	}

	public Map<String, List> getMemList(Page page) {

		Map<String, List> map = new HashMap<String, List>();
		UserModel userModel = new UserModel();
		RoleModel roleModel = new RoleModel();
		
		if(page == null){//选择全部
			map.put("userlist", userModel.select());
			map.put("rolelist", roleModel.select());
		}else{
			page.setCount(userModel.count());
			map.put("userlist", userModel.limit(page.getEveryPage(),
					page.getCurrentPage()).select());
			map.put("rolelist", roleModel.select());
		}
		
		

		return map;
	}

	public void useradd(User user) {
		UserModel userModel = new UserModel();
		userModel.save(user);

	}

	public void userdel(String[] id) {
		UserModel userModel = new UserModel();
		Inquery query = new Inquery();
		query.setIn("id", id);

		userModel.where(query).delete();

	}

	public User getuser(int id) {
		UserModel userModel = new UserModel();
		return userModel.selectById(id);
	}

	public boolean edituser(User user) {
		UserModel userModel = new UserModel();

		return userModel.update(user);
	}

	public ResultSet getMemAuth(Page page) {
		RoleModel model = new RoleModel();
		String sql = "SELECT r.rname,r.rdesc,res.resuri,res.resdesc FROM role r , resource res , auth a "
				+ "WHERE a.roleid = r.id and a.resourceid = res.id "
				+ "ORDER by r.id "
				+ "LIMIT "
				+ (page.getCurrentPage() - 1)* page.getEveryPage() + "," + page.getEveryPage();
		ResultSet result = (ResultSet)model.executeSQL(sql);
		
		sql = "SELECT count(*) num FROM role r , resource res , auth a "
			+ "WHERE a.roleid = r.id and a.resourceid = res.id "
			+ "ORDER by r.id ";
		
		ResultSet count = (ResultSet)model.executeSQL(sql);
		if(count != null){
			try {
				count.next();
				page.setCount(count.getInt("num"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
	
	public List<User> getUserList(){
		UserModel userModel = new UserModel();
		return userModel.select();
	}
	
	public String expusertoexcel() {
		// TODO Auto-generated method stub
		// 获取下载文件存放的临时目录
		String temp = Global.get(Global.ABSOLUTE_PATH)+"\\file\\temp\\";
		// 获取模板文件存放
		String templatePath = Global.get(Global.ABSOLUTE_PATH)+"\\file\\templates";
		// 创建导出文件名
		String fileName = "Eyet" + System.currentTimeMillis() + ".xls";
		String newFile = temp + fileName;
		String templateFile = templatePath + "/Eyet_UserTemplate.xls";
		FileOutputStream fos = null;
		InputStream is = null;
		List<User> userList = (new UserModel()).select();
		List<Role> roleList = (new RoleModel()).select();
		
		Map<Integer , Role> rolemap = new HashMap<Integer , Role>();		
		for(Role role : roleList){
			rolemap.put(role.getId() , role);
		}
			
		try {
			// 将模板文件复制为导出文件
			IOUtil.copyFile(templateFile, newFile);
			is = new FileInputStream(newFile);
			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);

			// 在导出文件中填充数据
			for (int i = 1, len = userList.size(); i <= len; i++) {
				Row row = sheet.createRow(i);
				
				User user = userList.get(i-1);
				
				// 构造行的每个单元格
				Cell idCell = row.createCell(0);
				Cell uesrnameCell = row.createCell(1);
				Cell roleCell = row.createCell(2);
				Cell rdescCell = row.createCell(3);
				
				idCell.setCellValue(user.getId());
				uesrnameCell.setCellValue(user.getUsername());
				roleCell.setCellValue(rolemap.get(user.getRoleid()).getRname());
				rdescCell.setCellValue(rolemap.get(user.getRoleid()).getRdesc());
				
			}
			// 自动设置列宽
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);

			// 输出文件
			fos = new FileOutputStream(newFile);
			wb.write(fos);
			//return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();// 一定要进行文件的关闭,否则在新文件会是空的!
					fos = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
		}
		
		return fileName;
	}
}
