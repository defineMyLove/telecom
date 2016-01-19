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
public class NEW_INFO extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("title", "String");
		KEYS.put("content", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("query_count", "Integer");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String title;
	private Boolean isSetted_title = false;
	private String content;
	private Boolean isSetted_content = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private Integer query_count;
	private Boolean isSetted_query_count = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("title", null);
			BEAN_VALUES.put("content", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("query_count", null);
	}
	
	public NEW_INFO() {
		initBeanValues();
	}
	
	public NEW_INFO(String id) {
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
	public NEW_INFO setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update NEW_INFO set ");
			if (isSetted_title) {
				sBuffer.append("title=:title,");
			}
			if (isSetted_content) {
				sBuffer.append("content=:content,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_query_count) {
				sBuffer.append("query_count=:query_count,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into NEW_INFO(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("title,");
			values.append(":title,");
			fileds.append("content,");
			values.append(":content,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("query_count,");
			values.append(":query_count,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取标题<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * 设置标题<BR/>
		 * 2014-48-09 hh:07
		 */
		public NEW_INFO setTitle(String title) {
			this.title = title;
			this.isSetted_title = true;
			BEAN_VALUES.put("title",title);
			return this;
		}
		/**
		 * 获取内容<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getContent() {
			return content;
		}
		/**
		 * 设置内容<BR/>
		 * 2014-48-09 hh:07
		 */
		public NEW_INFO setContent(String content) {
			this.content = content;
			this.isSetted_content = true;
			BEAN_VALUES.put("content",content);
			return this;
		}
		/**
		 * 获取创建时间<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置创建时间<BR/>
		 * 2014-48-09 hh:07
		 */
		public NEW_INFO setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取浏览次数<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public Integer getQuery_count() {
			return query_count;
		}
		/**
		 * 设置浏览次数<BR/>
		 * 2014-48-09 hh:07
		 */
		public NEW_INFO setQuery_count(Integer query_count) {
			this.query_count = query_count;
			this.isSetted_query_count = true;
			BEAN_VALUES.put("query_count",query_count);
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
		public NEW_INFO getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public NEW_INFO queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from NEW_INFO where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_title) {
					sBuffer.append("title=:title and ");
				}
				if (isSetted_content) {
					sBuffer.append("content=:content and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_query_count) {
					sBuffer.append("query_count=:query_count and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "NEW_INFO";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public NEW_INFO insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public NEW_INFO update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public NEW_INFO insertOrUpdate(){
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
			
			return dao.queryForMap("select * from NEW_INFO where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("TITLE");
			BEAN_VALUES.put("title",obj);
				this.setTitle(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CONTENT");
			BEAN_VALUES.put("content",obj);
				this.setContent(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("QUERY_COUNT");
			BEAN_VALUES.put("query_count",obj);
				this.setQuery_count(ConvertUtil.obj2Integer(obj));
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
		
		public NEW_INFO newInstance(){
			return new NEW_INFO();
		}



















}