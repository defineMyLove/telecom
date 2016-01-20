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
public class SALEMAN extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("name", "String");
		KEYS.put("tel", "String");
		KEYS.put("address", "String");
		KEYS.put("create_user", "String");
		KEYS.put("create_user_name", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("card_no", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String name;
	private Boolean isSetted_name = false;
	private String tel;
	private Boolean isSetted_tel = false;
	private String address;
	private Boolean isSetted_address = false;
	private String create_user;
	private Boolean isSetted_create_user = false;
	private String create_user_name;
	private Boolean isSetted_create_user_name = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String card_no;
	private Boolean isSetted_card_no = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("name", null);
			BEAN_VALUES.put("tel", null);
			BEAN_VALUES.put("address", null);
			BEAN_VALUES.put("create_user", null);
			BEAN_VALUES.put("create_user_name", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("card_no", null);
	}
	
	public SALEMAN() {
		initBeanValues();
	}
	
	public SALEMAN(String id) {
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
	public SALEMAN setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update SALEMAN set ");
			if (isSetted_name) {
				sBuffer.append("name=:name,");
			}
			if (isSetted_tel) {
				sBuffer.append("tel=:tel,");
			}
			if (isSetted_address) {
				sBuffer.append("address=:address,");
			}
			if (isSetted_create_user) {
				sBuffer.append("create_user=:create_user,");
			}
			if (isSetted_create_user_name) {
				sBuffer.append("create_user_name=:create_user_name,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_card_no) {
				sBuffer.append("card_no=:card_no,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into SALEMAN(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("name,");
			values.append(":name,");
			fileds.append("tel,");
			values.append(":tel,");
			fileds.append("address,");
			values.append(":address,");
			fileds.append("create_user,");
			values.append(":create_user,");
			fileds.append("create_user_name,");
			values.append(":create_user_name,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("card_no,");
			values.append(":card_no,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getName() {
			return name;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setName(String name) {
			this.name = name;
			this.isSetted_name = true;
			BEAN_VALUES.put("name",name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getTel() {
			return tel;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setTel(String tel) {
			this.tel = tel;
			this.isSetted_tel = true;
			BEAN_VALUES.put("tel",tel);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getAddress() {
			return address;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setAddress(String address) {
			this.address = address;
			this.isSetted_address = true;
			BEAN_VALUES.put("address",address);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getCreate_user() {
			return create_user;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setCreate_user(String create_user) {
			this.create_user = create_user;
			this.isSetted_create_user = true;
			BEAN_VALUES.put("create_user",create_user);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getCreate_user_name() {
			return create_user_name;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setCreate_user_name(String create_user_name) {
			this.create_user_name = create_user_name;
			this.isSetted_create_user_name = true;
			BEAN_VALUES.put("create_user_name",create_user_name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-05-20 hh:01
		 */
		public String getCard_no() {
			return card_no;
		}
		/**
		 * 设置<BR/>
		 * 2016-05-20 hh:01
		 */
		public SALEMAN setCard_no(String card_no) {
			this.card_no = card_no;
			this.isSetted_card_no = true;
			BEAN_VALUES.put("card_no",card_no);
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
		public SALEMAN getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public SALEMAN queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from SALEMAN where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_name) {
					sBuffer.append("name=:name and ");
				}
				if (isSetted_tel) {
					sBuffer.append("tel=:tel and ");
				}
				if (isSetted_address) {
					sBuffer.append("address=:address and ");
				}
				if (isSetted_create_user) {
					sBuffer.append("create_user=:create_user and ");
				}
				if (isSetted_create_user_name) {
					sBuffer.append("create_user_name=:create_user_name and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_card_no) {
					sBuffer.append("card_no=:card_no and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "SALEMAN";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public SALEMAN insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public SALEMAN update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public SALEMAN insertOrUpdate(){
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
			
			return dao.queryForMap("select * from SALEMAN where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("NAME");
			BEAN_VALUES.put("name",obj);
				this.setName(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("TEL");
			BEAN_VALUES.put("tel",obj);
				this.setTel(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("ADDRESS");
			BEAN_VALUES.put("address",obj);
				this.setAddress(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_USER");
			BEAN_VALUES.put("create_user",obj);
				this.setCreate_user(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_USER_NAME");
			BEAN_VALUES.put("create_user_name",obj);
				this.setCreate_user_name(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("CARD_NO");
			BEAN_VALUES.put("card_no",obj);
				this.setCard_no(ConvertUtil.obj2Str(obj));
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
		
		public SALEMAN newInstance(){
			return new SALEMAN();
		}



















}