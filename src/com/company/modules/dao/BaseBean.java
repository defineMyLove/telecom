package com.company.modules.dao;

import com.company.modules.utils.SpringBeanUtil;
import org.springframework.jdbc.core.RowMapper;

import java.util.Map;


public abstract class BaseBean  implements  RowMapper {
	
	public final static BaseDao dao = SpringBeanUtil.getBean(BaseDao.class);

	public abstract String getId();
	
	public abstract <T> T setId(String id);
	
	public abstract String getTableName();
	
	public abstract String getUpdateSql();
	
	public abstract String getInsertSql();
	
	
	public abstract <T> T insert();
	
	public abstract <T> T update();
	
	public abstract <T> T queryForBean();
	
	public abstract Map getBeanValues();
	
	
	
	public abstract <T> T getInstanceById();
	
	/**
	 * 通过ID获取该条信息的Map
	 * @return
	 * @version V1.0.0
	 * @author
	 * @date Dec 21, 2013 9:59:00 PM
	 */
	public abstract Map getBeanMapById();
	
	
	
	public abstract void deleteById();
	
	public abstract <T> T insertOrUpdate();
	
	public abstract BaseBean newInstance();
}
