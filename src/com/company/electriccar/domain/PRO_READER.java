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
public class PRO_READER extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("name", "String");
		KEYS.put("ip", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("address", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String name;
	private Boolean isSetted_name = false;
	private String ip;
	private Boolean isSetted_ip = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String address;
	private Boolean isSetted_address = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("name", null);
			BEAN_VALUES.put("ip", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("address", null);
	}
	
	public PRO_READER() {
		initBeanValues();
	}
	
	public PRO_READER(String id) {
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
	public PRO_READER setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update PRO_READER set ");
			if (isSetted_name) {
				sBuffer.append("name=:name,");
			}
			if (isSetted_ip) {
				sBuffer.append("ip=:ip,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_address) {
				sBuffer.append("address=:address,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into PRO_READER(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("name,");
			values.append(":name,");
			fileds.append("ip,");
			values.append(":ip,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("address,");
			values.append(":address,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getName() {
			return name;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_READER setName(String name) {
			this.name = name;
			this.isSetted_name = true;
			BEAN_VALUES.put("name",name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getIp() {
			return ip;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_READER setIp(String ip) {
			this.ip = ip;
			this.isSetted_ip = true;
			BEAN_VALUES.put("ip",ip);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_READER setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getAddress() {
			return address;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_READER setAddress(String address) {
			this.address = address;
			this.isSetted_address = true;
			BEAN_VALUES.put("address",address);
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
		public PRO_READER getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public PRO_READER queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from PRO_READER where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_name) {
					sBuffer.append("name=:name and ");
				}
				if (isSetted_ip) {
					sBuffer.append("ip=:ip and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_address) {
					sBuffer.append("address=:address and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "PRO_READER";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public PRO_READER insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public PRO_READER update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public PRO_READER insertOrUpdate(){
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
			
			return dao.queryForMap("select * from PRO_READER where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("NAME");
			BEAN_VALUES.put("name",obj);
				this.setName(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("IP");
			BEAN_VALUES.put("ip",obj);
				this.setIp(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("ADDRESS");
			BEAN_VALUES.put("address",obj);
				this.setAddress(ConvertUtil.obj2Str(obj));
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
		
		public PRO_READER newInstance(){
			return new PRO_READER();
		}



















}