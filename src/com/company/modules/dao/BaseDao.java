package com.company.modules.dao;

import com.company.modules.utils.ConvertUtil;
import com.company.modules.web.SplitPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;


public class BaseDao {
	
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate paraJdbcTemplate;
	

	/**
	 * GG_USER user = baseDao.queryForBean("select * from GG_USER where id=:id", new GG_USER().setId('aasaaa'));
	 * @param <T>
	 * @param sql
	 * @param bean
	 * @return
	 */
	public <T> T queryForBean(String sql,BaseBean bean) {
		List<T> list = this.paraJdbcTemplate.query(sql, bean.getBeanValues(), bean);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	
	/**
	 * 
	 * GG_USER user = baseDao.queryForBean("select * from GG_USER where id=:id",
				new SqlParameter("id","1dcd7d8483c2434dabbb142a76003df9"),
				new GG_USER());
	 * @param <T>
	 * @param sql
	 * @param param
	 * @param bean
	 * @return
	 */
	public <T> T queryForBean(String sql,Map param,BaseBean bean) {
		List<T> list = this.paraJdbcTemplate.query(sql, param, bean);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 只提供了对bean中set方法的注入
	 * @param bean
	 * @param map
	 * @return
	 */
	private BaseBean map2Bean(BaseBean bean,Map map){
		Iterator iterator = map.keySet().iterator();
		Class<?> c = bean.getClass();
		Method[] methods = c.getDeclaredMethods();
		try {
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				for (Method method : methods) {
					if (StringUtils.equals(method.getName().toLowerCase(), "set"+key.toLowerCase())) {
						String parameterType = method.getParameterTypes()[0].getSimpleName();
						if (StringUtils.equals("String", parameterType)) {
							method.invoke(bean, ConvertUtil.obj2Str(map.get(key)));
						} else if (StringUtils.equals("Integer", parameterType)) {
							method.invoke(bean, ConvertUtil.obj2Integer(map.get(key)));
						} else if (StringUtils.equals("Long", parameterType)) {
							method.invoke(bean, ConvertUtil.obj2Long(map.get(key)));
						} else if (StringUtils.equals("Double", parameterType)) {
							method.invoke(bean, ConvertUtil.obj2Double(map.get(key)));
						} else {
							System.out.println(method.getName() + "方法的参数类型为：" + parameterType + "该类型暂时没有处理，请联系");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("com.zzsb.core.utils.ConvertUtil.map2Bean(BaseBean, Map)方法执行失败");
		}
		return bean;
	}
	
	public <T> T queryForList(String sql,BaseBean baseBean){
		List<Map> list = this.queryForList(sql, baseBean.getBeanValues());
		if (list != null && list.size() > 0) {
			List<BaseBean> beanList = new ArrayList<BaseBean>();
			for (Map map : list) {
				beanList.add(map2Bean(baseBean.newInstance(), map));
			}
			return (T)beanList;
		}
		return (T)new ArrayList<BaseBean>();
	}
	
	
	/**
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Map> queryForList(String sql,Map param) {
		List<Map<String,Object>> list = this.paraJdbcTemplate.queryForList(sql, param);
		if (list == null) {
			list = new ArrayList<Map<String,Object>>();
		}
		return (List)list;
	}
	
	public List<Map> queryForList(String sql) {
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		if (list == null) {
			list = new ArrayList<Map<String,Object>>();
		}
		return (List)list;
	}
	
	
	/**
	 * ��ҳ��ѯ
	 * @param sql
	 * @param param
	 * @param splitPage
	 * @return
	 * @version V1.0.0
	 * @author �
	 * @date Sep 19, 2013 8:21:07 AM
	 */
	public List<Map> queryForSplit(String sql,SqlParameter param,SplitPage splitPage){
		String infoCountSql = "select count(*) from ("+ sql +") as infoCount"; 
		int infoCount = ConvertUtil.obj2Integer(this.queryForString(infoCountSql,param));
		splitPage.setInfoCount(infoCount);
		String infoListSql = sql +  " limit " + (splitPage.getCurrentPageIndex()-1)*splitPage.getPageSize() +"," + splitPage.getPageSize();
		return queryForList(infoListSql,param);
	}
	
	
	public Map queryForDataGrid(HttpServletRequest request,String sql,SqlParameter param){
		int pageindex = ConvertUtil.obj2Integer(request.getParameter("page")==null?1:request.getParameter("page")); //当前页码
		int pagesize = ConvertUtil.obj2Integer(request.getParameter("pageSize")==null?10:request.getParameter("pageSize"));; //每页显示条数
		List<Map> list = queryForList(sql,param);//TODO 此处分页采用的是将所有数据加载至内存后，使用JAVA分页，需优化
		int infoCount = list.size();
		int bin = (pageindex-1)* pagesize ;
		int end = (pageindex-1)* pagesize + pagesize;
		if(end>infoCount){
			end=infoCount;
		}
		List<Map> subList = list.subList(bin,end);
		
		Map rootMap = new HashMap();
        rootMap.put("total", infoCount); //信息总数
        rootMap.put("rows", subList);
        rootMap.put("page", pageindex);
        rootMap.put("pageSize", pagesize);
        rootMap.put("totalPage", Math.ceil((float)infoCount/(float)pagesize));
		return rootMap;
	}

	
	public Map queryForMap(String sql,Map paramMap){
		List<Map> list  = queryForList(sql, paramMap);
		if (list == null || list.size() == 0) {
			return  new HashMap();
		}
		return list.get(0);
	}
	
	
	public Map queryForMap(String sql){
		Map map = this.queryForMap(sql,new SqlParameter());
		if (map == null) {
			map = new HashMap();
		}
		return map;
	}
	
	
	public String queryForString(String sql,Map paramMap){
		Map map = queryForMap(sql, paramMap);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Str(map.get(map.keySet().iterator().next()));
		}
	}
	
	public String queryForString(String sql){
		Map map = queryForMap(sql);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Str(map.get(map.keySet().iterator().next()));
		}
	}
	
	
	public Integer queryForInteger(String sql){
		Map map = queryForMap(sql);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Integer(map.get(map.keySet().iterator().next()));
		}
	}
	
	public Long queryForLong(String sql){
		Map map = queryForMap(sql);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Long(map.get(map.keySet().iterator().next()));
		}
	}
	
	public Double queryForDouble(String sql){
		Map map = queryForMap(sql);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Double(map.get(map.keySet().iterator().next()));
		}
	}
	
	public Double queryForDouble(String sql,Map paramMap){
		Map map = queryForMap(sql,paramMap);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Double(map.get(map.keySet().iterator().next()));
		}
	}
	
	public Long queryForLong(String sql,Map paramMap){
		Map map = queryForMap(sql,paramMap);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Long(map.get(map.keySet().iterator().next()));
		}
	}
	
	
	public Integer queryForInteger(String sql,Map paramMap){
		Map map = queryForMap(sql,paramMap);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			return ConvertUtil.obj2Integer(map.get(map.keySet().iterator().next()));
		}
	}
	
	
	
	
	
	/**
	 * @param sql
	 */
	public Integer execute(String sql,Map paramMap) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource source = new MapSqlParameterSource(paramMap);
		this.paraJdbcTemplate.update(sql, source, keyHolder);
		if (keyHolder.getKey() == null) {
			return null;
		}
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * 
	 * 在执行insert操作之后获取最新的ID
	 * @return
	 * @version V1.0.0
	 * @author
	 * @date Dec 3, 2013 1:35:52 PM
	 */
	public int getCurrentPK(){
		return queryForInteger("SELECT @@IDENTITY");
	}
	
	
	public void executeBatch(String sql,List<SqlParameter> paramList){
		if (paramList == null || paramList.size() == 0) {
			return;
		}
		Map[] maps = new Map[paramList.size()];
		for (int i = 0; i < paramList.size(); i++) {
			maps[i] = paramList.get(i);
		}
		this.paraJdbcTemplate.batchUpdate(sql, maps);
	}
	
	public void executeBatch(List<String> sqlList){
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		this.jdbcTemplate.batchUpdate(sqls);
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getParaJdbcTemplate() {
		return paraJdbcTemplate;
	}

	public void setParaJdbcTemplate(NamedParameterJdbcTemplate paraJdbcTemplate) {
		this.paraJdbcTemplate = paraJdbcTemplate;
	}

    public Map queryForDataGrid(Integer pageindex, Integer pagesize, String sql, SqlParameter sqlParameter) {
        if(pageindex==null){
            pageindex=1;
        }
        if(pagesize==null){
            pagesize=10;//默认
        }
        //TODO 此处分页采用的是将所有数据加载至内存后，使用JAVA分页，需优化
        List<Map> list = queryForList(sql,sqlParameter);
        int infoCount = list.size();
        int bin = (pageindex-1)* pagesize ;
        int end = (pageindex-1)* pagesize + pagesize;
        if(end>infoCount){
            end=infoCount;
        }
        List<Map> subList = list.subList(bin,end);

        Map rootMap = new HashMap();
        rootMap.put("total", infoCount); //信息总数
        rootMap.put("rows", subList);
        rootMap.put("page",pageindex);
        return rootMap;
    }
}
