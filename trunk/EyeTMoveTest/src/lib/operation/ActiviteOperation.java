package lib.operation;

import java.util.List;

import lib.model.entity.Activite;

import com.eyet.framework.util.Page;

public interface ActiviteOperation {
	
	/**
	 * 带查询条件
	 * @param page
	 * @param title  查询条件1
	 * @return
	 */
	public List<Activite> getActiviteList(Page page , String title);
	
	public boolean addActivite(Activite activite);
	
	/**
	 * 通过id获得activite对象
	 * @param id
	 * @return
	 */
	public Activite getActivite(int id);
	
	/**
	 * @param activite 
	 * @param activite 活动对象  
	 * @return
	 */
	public boolean editActivite(Activite activite);
	
	/**
	 * @param id 需要删除的对象id
	 * @return
	 */
	public boolean delActivite(Object[] id);
	
}
