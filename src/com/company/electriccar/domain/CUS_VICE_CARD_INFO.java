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
public class CUS_VICE_CARD_INFO extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("main_card_id", "String");
		KEYS.put("vice_card_id", "String");
		KEYS.put("back_card_id", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("cus_id", "String");
		KEYS.put("cus_no", "Integer");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String main_card_id;
	private Boolean isSetted_main_card_id = false;
	private String vice_card_id;
	private Boolean isSetted_vice_card_id = false;
	private String back_card_id;
	private Boolean isSetted_back_card_id = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String cus_id;
	private Boolean isSetted_cus_id = false;
	private Integer cus_no;
	private Boolean isSetted_cus_no = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("main_card_id", null);
			BEAN_VALUES.put("vice_card_id", null);
			BEAN_VALUES.put("back_card_id", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("cus_id", null);
			BEAN_VALUES.put("cus_no", null);
	}
	
	public CUS_VICE_CARD_INFO() {
		initBeanValues();
	}
	
	public CUS_VICE_CARD_INFO(String id) {
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
	public CUS_VICE_CARD_INFO setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update CUS_VICE_CARD_INFO set ");
			if (isSetted_main_card_id) {
				sBuffer.append("main_card_id=:main_card_id,");
			}
			if (isSetted_vice_card_id) {
				sBuffer.append("vice_card_id=:vice_card_id,");
			}
			if (isSetted_back_card_id) {
				sBuffer.append("back_card_id=:back_card_id,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_cus_id) {
				sBuffer.append("cus_id=:cus_id,");
			}
			if (isSetted_cus_no) {
				sBuffer.append("cus_no=:cus_no,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into CUS_VICE_CARD_INFO(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("main_card_id,");
			values.append(":main_card_id,");
			fileds.append("vice_card_id,");
			values.append(":vice_card_id,");
			fileds.append("back_card_id,");
			values.append(":back_card_id,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("cus_id,");
			values.append(":cus_id,");
			fileds.append("cus_no,");
			values.append(":cus_no,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取主卡ID<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public String getMain_card_id() {
			return main_card_id;
		}
		/**
		 * 设置主卡ID<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setMain_card_id(String main_card_id) {
			this.main_card_id = main_card_id;
			this.isSetted_main_card_id = true;
			BEAN_VALUES.put("main_card_id",main_card_id);
			return this;
		}
		/**
		 * 获取副卡号<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public String getVice_card_id() {
			return vice_card_id;
		}
		/**
		 * 设置副卡号<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setVice_card_id(String vice_card_id) {
			this.vice_card_id = vice_card_id;
			this.isSetted_vice_card_id = true;
			BEAN_VALUES.put("vice_card_id",vice_card_id);
			return this;
		}
		/**
		 * 获取副卡背卡<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public String getBack_card_id() {
			return back_card_id;
		}
		/**
		 * 设置副卡背卡<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setBack_card_id(String back_card_id) {
			this.back_card_id = back_card_id;
			this.isSetted_back_card_id = true;
			BEAN_VALUES.put("back_card_id",back_card_id);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取客户ID<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public String getCus_id() {
			return cus_id;
		}
		/**
		 * 设置客户ID<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setCus_id(String cus_id) {
			this.cus_id = cus_id;
			this.isSetted_cus_id = true;
			BEAN_VALUES.put("cus_id",cus_id);
			return this;
		}
		/**
		 * 获取客户编号<BR/>
		 * 䣺2016-02-23 hh:01
		 */
		public Integer getCus_no() {
			return cus_no;
		}
		/**
		 * 设置客户编号<BR/>
		 * 2016-02-23 hh:01
		 */
		public CUS_VICE_CARD_INFO setCus_no(Integer cus_no) {
			this.cus_no = cus_no;
			this.isSetted_cus_no = true;
			BEAN_VALUES.put("cus_no",cus_no);
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
		public CUS_VICE_CARD_INFO getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public CUS_VICE_CARD_INFO queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from CUS_VICE_CARD_INFO where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_main_card_id) {
					sBuffer.append("main_card_id=:main_card_id and ");
				}
				if (isSetted_vice_card_id) {
					sBuffer.append("vice_card_id=:vice_card_id and ");
				}
				if (isSetted_back_card_id) {
					sBuffer.append("back_card_id=:back_card_id and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_cus_id) {
					sBuffer.append("cus_id=:cus_id and ");
				}
				if (isSetted_cus_no) {
					sBuffer.append("cus_no=:cus_no and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "CUS_VICE_CARD_INFO";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public CUS_VICE_CARD_INFO insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public CUS_VICE_CARD_INFO update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public CUS_VICE_CARD_INFO insertOrUpdate(){
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
			
			return dao.queryForMap("select * from CUS_VICE_CARD_INFO where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("MAIN_CARD_ID");
			BEAN_VALUES.put("main_card_id",obj);
				this.setMain_card_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("VICE_CARD_ID");
			BEAN_VALUES.put("vice_card_id",obj);
				this.setVice_card_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("BACK_CARD_ID");
			BEAN_VALUES.put("back_card_id",obj);
				this.setBack_card_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("CUS_ID");
			BEAN_VALUES.put("cus_id",obj);
				this.setCus_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CUS_NO");
			BEAN_VALUES.put("cus_no",obj);
				this.setCus_no(ConvertUtil.obj2Integer(obj));
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
		
		public CUS_VICE_CARD_INFO newInstance(){
			return new CUS_VICE_CARD_INFO();
		}



















}