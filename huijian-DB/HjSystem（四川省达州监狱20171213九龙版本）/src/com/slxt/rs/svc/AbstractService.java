package com.slxt.rs.svc;

import java.util.List;
import java.util.Map;
import com.slxt.rs.idao.IdaoSupport;
import com.slxt.rs.util.Acd;
public abstract class AbstractService implements Rsservice {
	private IdaoSupport rsDAO;

	public IdaoSupport getrsDAO() {
		return rsDAO;
	}

	public void setrsDAO(IdaoSupport rsDAO) {
		this.rsDAO = rsDAO;
	}

	public void delete(Class cla, Integer id) {
		// TODO Auto-generated method stub
		this.rsDAO.delete(cla, id) ;
	}
	public void delete(Class cla, Long id) {
		// TODO Auto-generated method stub
		this.rsDAO.delete(cla, id) ;
	}
	public void delete(Object ob){
		this.rsDAO.delete(ob) ;
	}
	public void deleteByHql(String hql, Object... param) {
		// TODO Auto-generated method stub
		this.rsDAO.deleteByHql(hql, param) ;
	}

	public Object findById(Class cla, Integer id) {
		// TODO Auto-generated method stub
		return this.rsDAO.findById(cla, id);
	}
	public Object findById(Class cla,Long id){
		return this.rsDAO.findById(cla, id);
	}
	public Object findByIdLong(Class cla, Long id) {
		// TODO Auto-generated method stub
		return this.rsDAO.findById(cla, id);
	}
	public void save(Object empty) {
		// TODO Auto-generated method stub
		 this.rsDAO.save(empty);
	}

	public List searchAll(String sql, Object... param) {
		// TODO Auto-generated method stub
		return this.rsDAO.searchAll(sql, param);
	}

	public List searchAll(String hql) {
		// TODO Auto-generated method stub
		return this.rsDAO.searchAll(hql);
	}

	public List searchAllBySql(String sql) {
		// TODO Auto-generated method stub
		return this.rsDAO.searchAllBySql(sql);
	}

	public Map searchToPage(String sql, Integer pageIndex, Integer pageSize,
			Object[] param) {
		// TODO Auto-generated method stub
		return this.rsDAO.searchToPage(sql, pageIndex, pageSize, param);
	}
	public Map searchToPage(String sql, Integer pageIndex, Integer pageSize,String mark,
			Object[] param) {
		
		// TODO Auto-generated method stub
		return this.rsDAO.searchToPage(sql, pageIndex, pageSize,mark, param);
	}

	public void update(Object empty) {
		// TODO Auto-generated method stub
		this.rsDAO.update(empty) ;
	}

	public void updates(String hql, Object... param) {
		// TODO Auto-generated method stub
		this.rsDAO.updates(hql, param) ;
	}
	public List search(Object... param){
		return null;
	}

	public Map searchToPageBySql(String sql, Integer pageIndex,
			Integer pageSize, Object[] param) {
		// TODO Auto-generated method stub
		return this.rsDAO.searchToPageBySql(sql, pageIndex, pageSize, param) ;
	}

	public Object findById(Class cla,String id){
		return this.rsDAO.findById(cla, id) ;
	}
	public List login(String username,String password){
		return this.rsDAO.login(username, password);
	}
	public void saveProcess(String saveProcessName){
		 this.rsDAO.saveProcess(saveProcessName);
	}
	public void saveProcess(String saveProcessName,String groupNo){
		this.rsDAO.saveProcess(saveProcessName, groupNo);
	}
	public int getAcdNo(String saveProcessName,int windowsIndex){
		return this.rsDAO.getAcdNo(saveProcessName, windowsIndex);
	}
	public int getOneOutPut(String saveProcessName){
		return this.rsDAO.getOneOutPut(saveProcessName);
	}
	public int getLineNo(String saveProcessName,String frNo){
		return this.rsDAO.getLineNo( saveProcessName, frNo);
	}
	public String getString(String saveProcessName,int id){
		return this.rsDAO.getString(saveProcessName, id);
	}
	public int fw(String saveProcessName){
		return this.rsDAO.fw(saveProcessName);
	}
	public String getString(String saveProcessName,int id,String jy){
		return this.rsDAO.getString(saveProcessName, id, jy);
	}
	public Long getIndex(String saveProcessName,String frNo){
		return this.rsDAO.getIndex(saveProcessName, frNo);
	}
	public int getAcdNo1(String saveProcessName,int windowsIndex,String serverName){
		return this.rsDAO.getAcdNo1(saveProcessName, windowsIndex, serverName);
	}
	public int rgfpZw(String saveProcessName,String frNo,String jy,int lineNo){
		return this.rsDAO.rgfpZw(saveProcessName, frNo, jy, lineNo);
	}
	public String getZw(String saveProcessName,String jy,int lineNo){
		return this.rsDAO.getZw(saveProcessName, jy, lineNo);
	}
	public int getTypeZw(String saveProcessName,String frNo,int lineType){
		return this.rsDAO.getTypeZw(saveProcessName,frNo, lineType);
	}
	public void executeUpdate(String sql){
		this.rsDAO.executeUpdate(sql);
	}
	public Acd getAcdNo2(String saveProcessName,int windowsIndex,String serverName){
		return this.rsDAO.getAcdNo2(saveProcessName, windowsIndex, serverName);
	}
	public String getZw1(String saveProcessName,long hjId){
		return this.rsDAO.getZw1(saveProcessName, hjId);
	}
	public int getsptj(String saveProcessName,long hjId){
		return this.rsDAO.getsptj(saveProcessName, hjId);
	}
}
