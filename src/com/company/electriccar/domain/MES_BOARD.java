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
public class MES_BOARD extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("user_name", "String");
		KEYS.put("work_name", "String");
		KEYS.put("company_nam", "String");
		KEYS.put("company_address", "String");
		KEYS.put("company_youbian", "String");
		KEYS.put("email", "String");
		KEYS.put("company_tel", "String");
		KEYS.put("company_chuanzhen", "String");
		KEYS.put("content", "String");
		KEYS.put("create_time", "Long");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String user_name;
	private Boolean isSetted_user_name = false;
	private String work_name;
	private Boolean isSetted_work_name = false;
	private String company_nam;
	private Boolean isSetted_company_nam = false;
	private String company_address;
	private Boolean isSetted_company_address = false;
	private String company_youbian;
	private Boolean isSetted_company_youbian = false;
	private String email;
	private Boolean isSetted_email = false;
	private String company_tel;
	private Boolean isSetted_company_tel = false;
	private String company_chuanzhen;
	private Boolean isSetted_company_chuanzhen = false;
	private String content;
	private Boolean isSetted_content = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("user_name", null);
			BEAN_VALUES.put("work_name", null);
			BEAN_VALUES.put("company_nam", null);
			BEAN_VALUES.put("company_address", null);
			BEAN_VALUES.put("company_youbian", null);
			BEAN_VALUES.put("email", null);
			BEAN_VALUES.put("company_tel", null);
			BEAN_VALUES.put("company_chuanzhen", null);
			BEAN_VALUES.put("content", null);
			BEAN_VALUES.put("create_time", null);
	}
	
	public MES_BOARD() {
		initBeanValues();
	}
	
	public MES_BOARD(String id) {
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
	public MES_BOARD setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update MES_BOARD set ");
			if (isSetted_user_name) {
				sBuffer.append("user_name=:user_name,");
			}
			if (isSetted_work_name) {
				sBuffer.append("work_name=:work_name,");
			}
			if (isSetted_company_nam) {
				sBuffer.append("company_nam=:company_nam,");
			}
			if (isSetted_company_address) {
				sBuffer.append("company_address=:company_address,");
			}
			if (isSetted_company_youbian) {
				sBuffer.append("company_youbian=:company_youbian,");
			}
			if (isSetted_email) {
				sBuffer.append("email=:email,");
			}
			if (isSetted_company_tel) {
				sBuffer.append("company_tel=:company_tel,");
			}
			if (isSetted_company_chuanzhen) {
				sBuffer.append("company_chuanzhen=:company_chuanzhen,");
			}
			if (isSetted_content) {
				sBuffer.append("content=:content,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into MES_BOARD(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("user_name,");
			values.append(":user_name,");
			fileds.append("work_name,");
			values.append(":work_name,");
			fileds.append("company_nam,");
			values.append(":company_nam,");
			fileds.append("company_address,");
			values.append(":company_address,");
			fileds.append("company_youbian,");
			values.append(":company_youbian,");
			fileds.append("email,");
			values.append(":email,");
			fileds.append("company_tel,");
			values.append(":company_tel,");
			fileds.append("company_chuanzhen,");
			values.append(":company_chuanzhen,");
			fileds.append("content,");
			values.append(":content,");
			fileds.append("create_time,");
			values.append(":create_time,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getUser_name() {
			return user_name;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setUser_name(String user_name) {
			this.user_name = user_name;
			this.isSetted_user_name = true;
			BEAN_VALUES.put("user_name",user_name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getWork_name() {
			return work_name;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setWork_name(String work_name) {
			this.work_name = work_name;
			this.isSetted_work_name = true;
			BEAN_VALUES.put("work_name",work_name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getCompany_nam() {
			return company_nam;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCompany_nam(String company_nam) {
			this.company_nam = company_nam;
			this.isSetted_company_nam = true;
			BEAN_VALUES.put("company_nam",company_nam);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getCompany_address() {
			return company_address;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCompany_address(String company_address) {
			this.company_address = company_address;
			this.isSetted_company_address = true;
			BEAN_VALUES.put("company_address",company_address);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getCompany_youbian() {
			return company_youbian;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCompany_youbian(String company_youbian) {
			this.company_youbian = company_youbian;
			this.isSetted_company_youbian = true;
			BEAN_VALUES.put("company_youbian",company_youbian);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setEmail(String email) {
			this.email = email;
			this.isSetted_email = true;
			BEAN_VALUES.put("email",email);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getCompany_tel() {
			return company_tel;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCompany_tel(String company_tel) {
			this.company_tel = company_tel;
			this.isSetted_company_tel = true;
			BEAN_VALUES.put("company_tel",company_tel);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getCompany_chuanzhen() {
			return company_chuanzhen;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCompany_chuanzhen(String company_chuanzhen) {
			this.company_chuanzhen = company_chuanzhen;
			this.isSetted_company_chuanzhen = true;
			BEAN_VALUES.put("company_chuanzhen",company_chuanzhen);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public String getContent() {
			return content;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setContent(String content) {
			this.content = content;
			this.isSetted_content = true;
			BEAN_VALUES.put("content",content);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2014-48-09 hh:07
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2014-48-09 hh:07
		 */
		public MES_BOARD setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
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
		public MES_BOARD getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public MES_BOARD queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from MES_BOARD where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_user_name) {
					sBuffer.append("user_name=:user_name and ");
				}
				if (isSetted_work_name) {
					sBuffer.append("work_name=:work_name and ");
				}
				if (isSetted_company_nam) {
					sBuffer.append("company_nam=:company_nam and ");
				}
				if (isSetted_company_address) {
					sBuffer.append("company_address=:company_address and ");
				}
				if (isSetted_company_youbian) {
					sBuffer.append("company_youbian=:company_youbian and ");
				}
				if (isSetted_email) {
					sBuffer.append("email=:email and ");
				}
				if (isSetted_company_tel) {
					sBuffer.append("company_tel=:company_tel and ");
				}
				if (isSetted_company_chuanzhen) {
					sBuffer.append("company_chuanzhen=:company_chuanzhen and ");
				}
				if (isSetted_content) {
					sBuffer.append("content=:content and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "MES_BOARD";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public MES_BOARD insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public MES_BOARD update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public MES_BOARD insertOrUpdate(){
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
			
			return dao.queryForMap("select * from MES_BOARD where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("USER_NAME");
			BEAN_VALUES.put("user_name",obj);
				this.setUser_name(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("WORK_NAME");
			BEAN_VALUES.put("work_name",obj);
				this.setWork_name(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("COMPANY_NAM");
			BEAN_VALUES.put("company_nam",obj);
				this.setCompany_nam(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("COMPANY_ADDRESS");
			BEAN_VALUES.put("company_address",obj);
				this.setCompany_address(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("COMPANY_YOUBIAN");
			BEAN_VALUES.put("company_youbian",obj);
				this.setCompany_youbian(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("EMAIL");
			BEAN_VALUES.put("email",obj);
				this.setEmail(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("COMPANY_TEL");
			BEAN_VALUES.put("company_tel",obj);
				this.setCompany_tel(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("COMPANY_CHUANZHEN");
			BEAN_VALUES.put("company_chuanzhen",obj);
				this.setCompany_chuanzhen(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CONTENT");
			BEAN_VALUES.put("content",obj);
				this.setContent(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
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
		
		public MES_BOARD newInstance(){
			return new MES_BOARD();
		}



















}