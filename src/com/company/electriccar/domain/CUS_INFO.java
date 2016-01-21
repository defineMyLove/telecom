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
public class CUS_INFO extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("sale_id", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("cus_name", "String");
		KEYS.put("cus_tel", "String");
		KEYS.put("cus_address", "String");
		KEYS.put("cus_card_id", "String");
		KEYS.put("state", "Integer");
		KEYS.put("chanpin_id", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String sale_id;
	private Boolean isSetted_sale_id = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String cus_name;
	private Boolean isSetted_cus_name = false;
	private String cus_tel;
	private Boolean isSetted_cus_tel = false;
	private String cus_address;
	private Boolean isSetted_cus_address = false;
	private String cus_card_id;
	private Boolean isSetted_cus_card_id = false;
	private Integer state;
	private Boolean isSetted_state = false;
	private String chanpin_id;
	private Boolean isSetted_chanpin_id = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("sale_id", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("cus_name", null);
			BEAN_VALUES.put("cus_tel", null);
			BEAN_VALUES.put("cus_address", null);
			BEAN_VALUES.put("cus_card_id", null);
			BEAN_VALUES.put("state", null);
			BEAN_VALUES.put("chanpin_id", null);
	}
	
	public CUS_INFO() {
		initBeanValues();
	}
	
	public CUS_INFO(String id) {
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
	public CUS_INFO setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update CUS_INFO set ");
			if (isSetted_sale_id) {
				sBuffer.append("sale_id=:sale_id,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_cus_name) {
				sBuffer.append("cus_name=:cus_name,");
			}
			if (isSetted_cus_tel) {
				sBuffer.append("cus_tel=:cus_tel,");
			}
			if (isSetted_cus_address) {
				sBuffer.append("cus_address=:cus_address,");
			}
			if (isSetted_cus_card_id) {
				sBuffer.append("cus_card_id=:cus_card_id,");
			}
			if (isSetted_state) {
				sBuffer.append("state=:state,");
			}
			if (isSetted_chanpin_id) {
				sBuffer.append("chanpin_id=:chanpin_id,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into CUS_INFO(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("sale_id,");
			values.append(":sale_id,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("cus_name,");
			values.append(":cus_name,");
			fileds.append("cus_tel,");
			values.append(":cus_tel,");
			fileds.append("cus_address,");
			values.append(":cus_address,");
			fileds.append("cus_card_id,");
			values.append(":cus_card_id,");
			fileds.append("state,");
			values.append(":state,");
			fileds.append("chanpin_id,");
			values.append(":chanpin_id,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getSale_id() {
			return sale_id;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setSale_id(String sale_id) {
			this.sale_id = sale_id;
			this.isSetted_sale_id = true;
			BEAN_VALUES.put("sale_id",sale_id);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getCus_name() {
			return cus_name;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setCus_name(String cus_name) {
			this.cus_name = cus_name;
			this.isSetted_cus_name = true;
			BEAN_VALUES.put("cus_name",cus_name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getCus_tel() {
			return cus_tel;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setCus_tel(String cus_tel) {
			this.cus_tel = cus_tel;
			this.isSetted_cus_tel = true;
			BEAN_VALUES.put("cus_tel",cus_tel);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getCus_address() {
			return cus_address;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setCus_address(String cus_address) {
			this.cus_address = cus_address;
			this.isSetted_cus_address = true;
			BEAN_VALUES.put("cus_address",cus_address);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getCus_card_id() {
			return cus_card_id;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setCus_card_id(String cus_card_id) {
			this.cus_card_id = cus_card_id;
			this.isSetted_cus_card_id = true;
			BEAN_VALUES.put("cus_card_id",cus_card_id);
			return this;
		}
		/**
		 * 获取(0:新入库1:暂不处理2:完善数据,3:办理套餐4:归档)<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 设置(0:新入库1:暂不处理2:完善数据,3:办理套餐4:归档)<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setState(Integer state) {
			this.state = state;
			this.isSetted_state = true;
			BEAN_VALUES.put("state",state);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-16-21 hh:01
		 */
		public String getChanpin_id() {
			return chanpin_id;
		}
		/**
		 * 设置<BR/>
		 * 2016-16-21 hh:01
		 */
		public CUS_INFO setChanpin_id(String chanpin_id) {
			this.chanpin_id = chanpin_id;
			this.isSetted_chanpin_id = true;
			BEAN_VALUES.put("chanpin_id",chanpin_id);
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
		public CUS_INFO getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public CUS_INFO queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from CUS_INFO where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_sale_id) {
					sBuffer.append("sale_id=:sale_id and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_cus_name) {
					sBuffer.append("cus_name=:cus_name and ");
				}
				if (isSetted_cus_tel) {
					sBuffer.append("cus_tel=:cus_tel and ");
				}
				if (isSetted_cus_address) {
					sBuffer.append("cus_address=:cus_address and ");
				}
				if (isSetted_cus_card_id) {
					sBuffer.append("cus_card_id=:cus_card_id and ");
				}
				if (isSetted_state) {
					sBuffer.append("state=:state and ");
				}
				if (isSetted_chanpin_id) {
					sBuffer.append("chanpin_id=:chanpin_id and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "CUS_INFO";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public CUS_INFO insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public CUS_INFO update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public CUS_INFO insertOrUpdate(){
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
			
			return dao.queryForMap("select * from CUS_INFO where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("SALE_ID");
			BEAN_VALUES.put("sale_id",obj);
				this.setSale_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("CUS_NAME");
			BEAN_VALUES.put("cus_name",obj);
				this.setCus_name(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CUS_TEL");
			BEAN_VALUES.put("cus_tel",obj);
				this.setCus_tel(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CUS_ADDRESS");
			BEAN_VALUES.put("cus_address",obj);
				this.setCus_address(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CUS_CARD_ID");
			BEAN_VALUES.put("cus_card_id",obj);
				this.setCus_card_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("STATE");
			BEAN_VALUES.put("state",obj);
				this.setState(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("CHANPIN_ID");
			BEAN_VALUES.put("chanpin_id",obj);
				this.setChanpin_id(ConvertUtil.obj2Str(obj));
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
		
		public CUS_INFO newInstance(){
			return new CUS_INFO();
		}



















}