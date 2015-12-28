package lib.operation.impl;

import java.util.List;

import lib.model.ActiviteModel;
import lib.model.entity.Activite;
import lib.operation.ActiviteOperation;

import com.eyet.framework.orm.Inquery;
import com.eyet.framework.util.Page;

public class ActiviteOperationImpl implements ActiviteOperation {

	public boolean addActivite(Activite activite) {
		ActiviteModel activiteModel = new ActiviteModel();
		return activiteModel.save(activite);
	}

	public boolean delActivite(Object[] id) {
		ActiviteModel activiteModel = new ActiviteModel();
		Inquery query = new Inquery();
		query.setIn("id", id);
		return activiteModel.where(query).delete() >= 0 ? true : false;
	}

	public boolean editActivite(Activite activite) {
		
		ActiviteModel activiteModel = new ActiviteModel();
		return activiteModel.update(activite);
	}

	public List<Activite> getActiviteList(Page page , String title) {
		
		ActiviteModel activiteModel = new ActiviteModel();
		
		//当没有查询条件时返回为空
		Inquery query = new Inquery();
		if(title != null && !"".equals(title)){
			query.setString("title", title);
		}
		
		if(page == null){//选择全部
			return activiteModel.where(query).select();
		}else{
			page.setCount(activiteModel.where(query).count());  //设置总数
			return activiteModel.where(query).limit(page.getEveryPage(), page.getCurrentPage()).select();
		}
		
	}

	public Activite getActivite(int id) {
		
		ActiviteModel activiteModel = new ActiviteModel();
		return activiteModel.selectById(id);
		
	}

}
