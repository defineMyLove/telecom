package com.company.electriccar.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.company.modules.dao.BaseBean;
import com.company.modules.utils.ConvertUtil;
import com.company.modules.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public class YEJI_FILE extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("path", "String");
		KEYS.put("name", "String");
		KEYS.put("yeji_id", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String path;
	private Boolean isSetted_path = false;
	private String name;
	private Boolean isSetted_name = false;
	private String yeji_id;
	private Boolean isSetted_yeji_id = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("path", null);
			BEAN_VALUES.put("name", null);
			BEAN_VALUES.put("yeji_id", null);
	}
	
	public YEJI_FILE() {
		initBeanValues();
	}
	
	public YEJI_FILE(String id) {
		super();
		this.id = id;
		initBeanValues();
		BEAN_VALUES.put("id",id);
	}

    /**
     * 获取ID
     */
	public String getId() {
		return this.id;
	}
	/**
     * 设置ID
     */
	public YEJI_FILE setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update YEJI_FILE set ");
			if (isSetted_path) {
				sBuffer.append("path=:path,");
			}
			if (isSetted_name) {
				sBuffer.append("name=:name,");
			}
			if (isSetted_yeji_id) {
				sBuffer.append("yeji_id=:yeji_id,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into YEJI_FILE(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("path,");
			values.append(":path,");
			fileds.append("name,");
			values.append(":name,");
			fileds.append("yeji_id,");
			values.append(":yeji_id,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getPath() {
			return path;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public YEJI_FILE setPath(String path) {
			this.path = path;
			this.isSetted_path = true;
			BEAN_VALUES.put("path",path);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getName() {
			return name;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public YEJI_FILE setName(String name) {
			this.name = name;
			this.isSetted_name = true;
			BEAN_VALUES.put("name",name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getYeji_id() {
			return yeji_id;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public YEJI_FILE setYeji_id(String yeji_id) {
			this.yeji_id = yeji_id;
			this.isSetted_yeji_id = true;
			BEAN_VALUES.put("yeji_id",yeji_id);
			return this;
		}

		/**
		 * 使用ID删除Bean<BR/>
		 */
		public void deleteById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("删除bean时ID不能为空");
			}
			dao.execute("delete from " + getTableName() + " where id = :id", BEAN_VALUES);
		}
	
		@Override
		public YEJI_FILE getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public YEJI_FILE queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from YEJI_FILE where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_path) {
					sBuffer.append("path=:path and ");
				}
				if (isSetted_name) {
					sBuffer.append("name=:name and ");
				}
				if (isSetted_yeji_id) {
					sBuffer.append("yeji_id=:yeji_id and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "YEJI_FILE";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public YEJI_FILE insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public YEJI_FILE update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public YEJI_FILE insertOrUpdate(){
			if (StringUtils.isNotBlank(id)) {
				return update();
			} else {
				return insert();
			}
		}
		
		/**
		 * 通过ID获取该条信息的Map结构
		 */
		public Map getBeanMapById() {
			
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("ID不能为空!");
			}
			
			return dao.queryForMap("select * from YEJI_FILE where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("PATH");
			BEAN_VALUES.put("path",obj);
				this.setPath(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("NAME");
			BEAN_VALUES.put("name",obj);
				this.setName(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("YEJI_ID");
			BEAN_VALUES.put("yeji_id",obj);
				this.setYeji_id(ConvertUtil.obj2Str(obj));
			return this;
		}
		
		
		public String toString() {
			StringBuffer sb = new StringBuffer("[");
			for (Iterator iterator = KEYS.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				sb.append(key+"=" + BEAN_VALUES.get(key)+",");
			}
			sb.append("]");
			return sb.toString();
		}
		
		public YEJI_FILE newInstance(){
			return new YEJI_FILE();
		}



















}