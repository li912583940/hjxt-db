package com.slxt.rs.svc;

import java.util.List;
import java.util.Map;

import com.slxt.rs.util.Acd;
@SuppressWarnings("unchecked")
public interface Rsservice {	
	public void save(Object empty);
	public void update(Object empty);
	public void delete(Class cla,Integer id);
	public void delete(Class cla,Long id);
	public void delete(Object ob) ;
	public void deleteByHql(String hql,Object... param);
	public Object findById(Class cla,Integer id);
	public Object findById(Class cla,Long id);
	public Object findById(Class cla,String id);
	/**
	 * ���з�ҳ�Ĳ�ѯ��ѯ
	 * @param sql         Ҫִ�е�sql
	 * @param pageIndex   ��ʼҳ��
	 * @param pageSize    ÿҳ�Ĵ�С
	 * @param param       ������б�
	 * @return            ��װ�˲�ѯ�����ܼ�¼���map����
	 */
	public Map searchToPage(String sql, Integer pageIndex, Integer pageSize,String mark,
			Object[] param) ;
	public Map searchToPage(final String sql, Integer pageIndex, Integer pageSize,final Object[] param );
	/**
	 * ���з�ҳ��ԭʼsql�Ĳ�ѯ
	 * @param sql         Ҫִ�е�sql
	 * @param pageIndex   ��ʼҳ��
	 * @param pageSize    ÿҳ�Ĵ�С
	 * @param param       ������б�
	 * @return            ��װ�˲�ѯ�����ܼ�¼���map����
	 */
	public Map searchToPageBySql(final String sql, Integer pageIndex, Integer pageSize,final Object[] param );

	/**
	 * ������ȫ����Ϣ��ѯ
	 * @param sql    Ҫִ�е�sql
	 * @param param  �����б�
	 * @return		 sqlִ�к��ѯ����ȫ�����
	 */
	public List searchAll(String sql,Object... param) ;
	/**
	 * ��������ȫ����Ϣ��ѯ
	 * @param sql   Ҫִ�е�sql
	 * @return      sqlִ�к��ѯ����ȫ�����
	 */
	public List searchAllBySql(String sql) ;
	public List searchAll(String hql) ;
	public void updates(String hql, Object... param);	
	public List search(Object... param) ;
	public List login(String username,String password);
	public void saveProcess(String saveProcessName);
	public void saveProcess(String saveProcessName,String groupNo);
	public int getAcdNo(String saveProcessName,int windowsIndex);
	public int getOneOutPut(String saveProcessName);
	public int getLineNo(String saveProcessName,String frNo);
	public String getString(String saveProcessName,int id);
	public String getString(String saveProcessName,int id,String jy);
	public int fw(String saveProcessName);
	public Long getIndex(String saveProcessName,String frNo);
	public int getAcdNo1(String saveProcessName,int windowsIndex,String serverName);
	public int rgfpZw(String saveProcessName,String frNo,String jy,int lineNo);
	public int getTypeZw(String saveProcessName,String jy,int lineNo);
	public String getZw(String saveProcessName,String frNo,int lineType);
	public void executeUpdate(String sql);
	public Acd getAcdNo2(String saveProcessName,int windowsIndex,String serverName);
	public String getZw1(String saveProcessName,long hjId);
}
