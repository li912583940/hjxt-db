package com.slxt.rs.idao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.slxt.rs.util.Acd;
/**
 * �ܽӿ�
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
public interface IdaoSupport<T,ID extends Serializable> {	
		public void save(T empty);
		public void update(T empty);
	    @SuppressWarnings("unchecked")
		public void delete(Class cla,ID id);
	    public void delete(T t) ;
		public void deleteByHql(String hql,Object... param);
	    @SuppressWarnings("unchecked")
		public T findById(Class cla,ID id);
	    /**
		 * ���з�ҳ�Ĳ�ѯ��ѯ
		 * @param sql         Ҫִ�е�sql
		 * @param pageIndex   ��ʼҳ��
		 * @param pageSize    ÿҳ�Ĵ�С
		 * @param param       ������б�
		 * @param mark        ȥ���ظ���¼���ֶβ�����б�
		 * @return            ��װ�˲�ѯ�����ܼ�¼���map����
		 */
	    @SuppressWarnings("unchecked")
		public Map searchToPage(final String sql, Integer pageIndex, Integer pageSize,String mark,final Object[] param );
		/**
		 * ���з�ҳ�Ĳ�ѯ��ѯ
		 * @param sql         Ҫִ�е�sql
		 * @param pageIndex   ��ʼҳ��
		 * @param pageSize    ÿҳ�Ĵ�С
		 * @param param       ������б�
		 * @return            ��װ�˲�ѯ�����ܼ�¼���map����
		 */
	    @SuppressWarnings("unchecked")
		public Map searchToPage(final String sql, Integer pageIndex, Integer pageSize,final Object[] param );

		/**
		 * ������ȫ����Ϣ��ѯ
		 * @param sql    Ҫִ�е�sql
		 * @param param  �����б�
		 * @return		 sqlִ�к��ѯ����ȫ�����
		 */
		public List<T> searchAll(String sql,Object... param) ;
		/**
		 * ��������ȫ����Ϣ��ѯ
		 * @param sql   Ҫִ�е�sql
		 * @return      sqlִ�к��ѯ����ȫ�����
		 */
		public List<T> searchAllBySql(String sql) ;
		public Map searchToPageBySql(final String sql, Integer pageIndex, Integer pageSize,final Object[] param );
		public List<T> searchAll(String hql) ;
//		public Object get(Class cla,Serializable id);//�ظ�
		public void updates(String hql, Object... param);
		public List login(String username,String password);
//		public List loginYj(String username,String password);
		public void saveProcess(String saveProcessName);
		public void saveProcess(String saveProcessName,String groupNo);
//		public void updata(String entityName, Object entity);//��
//		public long saveObject(Object entity) ;//����ֵ��ͬҲ���ظ�
		public int getAcdNo(String saveProcessName,int windowsIndex);
		public int getOneOutPut(String saveProcessName);
		public int getLineNo(String saveProcessName,String frNo);
		public Long getIndex(String saveProcessName,String frNo);
		public String getString(String saveProcessName,int id);
		public String getString(String saveProcessName,int id,String jy);
		public int fw(String saveProcessName);
		public int getAcdNo1(String saveProcessName,int windowsIndex,String serverName);
		public int rgfpZw(String saveProcessName,String frNo,String jy,int lineNo);
		public String getZw(String saveProcessName,String jy,int lineNo);
		public int getTypeZw(String saveProcessName,String frNo,int lineType);
		public void executeUpdate(String sql);
		public Acd getAcdNo2(String saveProcessName,int windowsIndex,String serverName);
		public String getZw1(String saveProcessName,long hjId);
		public int getsptj(String saveProcessName,long hjId);
//		public void saveBySql(String sql);//��
}	