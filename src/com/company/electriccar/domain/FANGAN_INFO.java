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
public class FANGAN_INFO extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("name", "String");
		KEYS.put("pic_path", "String");
		KEYS.put("content", "String");
		KEYS.put("fenlei_id", "String");
		KEYS.put("order_no", "Integer");
		KEYS.put("create_time", "Long");
		KEYS.put("note", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String name;
	private Boolean isSetted_name = false;
	private String pic_path;
	private Boolean isSetted_pic_path = false;
	private String content;
	private Boolean isSetted_content = false;
	private String fenlei_id;
	private Boolean isSetted_fenlei_id = false;
	private Integer order_no;
	private Boolean isSetted_order_no = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String note;
	private Boolean isSetted_note = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("name", null);
			BEAN_VALUES.put("pic_path", null);
			BEAN_VALUES.put("content", null);
			BEAN_VALUES.put("fenlei_id", null);
			BEAN_VALUES.put("order_no", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("note", null);
	}
	
	public FANGAN_INFO() {
		initBeanValues();
	}
	
	public FANGAN_INFO(String id) {
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
	public FANGAN_INFO setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update "+getTableName() +" set ");
			if (isSetted_name) {
				sBuffer.append("name=:name,");
			}
			if (isSetted_pic_path) {
				sBuffer.append("pic_path=:pic_path,");
			}
			if (isSetted_content) {
				sBuffer.append("content=:content,");
			}
			if (isSetted_fenlei_id) {
				sBuffer.append("fenlei_id=:fenlei_id,");
			}
			if (isSetted_order_no) {
				sBuffer.append("order_no=:order_no,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_note) {
				sBuffer.append("note=:note,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into "+getTableName() +"(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("name,");
			values.append(":name,");
			fileds.append("pic_path,");
			values.append(":pic_path,");
			fileds.append("content,");
			values.append(":content,");
			fileds.append("fenlei_id,");
			values.append(":fenlei_id,");
			fileds.append("order_no,");
			values.append(":order_no,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("note,");
			values.append(":note,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getName() {
			return name;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setName(String name) {
			this.name = name;
			this.isSetted_name = true;
			BEAN_VALUES.put("name",name);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getPic_path() {
			return pic_path;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setPic_path(String pic_path) {
			this.pic_path = pic_path;
			this.isSetted_pic_path = true;
			BEAN_VALUES.put("pic_path",pic_path);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getContent() {
			return content;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setContent(String content) {
			this.content = content;
			this.isSetted_content = true;
			BEAN_VALUES.put("content",content);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getFenlei_id() {
			return fenlei_id;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setFenlei_id(String fenlei_id) {
			this.fenlei_id = fenlei_id;
			this.isSetted_fenlei_id = true;
			BEAN_VALUES.put("fenlei_id",fenlei_id);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getOrder_no() {
			return order_no;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setOrder_no(Integer order_no) {
			this.order_no = order_no;
			this.isSetted_order_no = true;
			BEAN_VALUES.put("order_no",order_no);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getNote() {
			return note;
		}
		/**
		 * 设置<BR/>
		 * 2016-56-25 hh:01
		 */
		public FANGAN_INFO setNote(String note) {
			this.note = note;
			this.isSetted_note = true;
			BEAN_VALUES.put("note",note);
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
		public FANGAN_INFO getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public FANGAN_INFO queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from "+getTableName() +" where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_name) {
					sBuffer.append("name=:name and ");
				}
				if (isSetted_pic_path) {
					sBuffer.append("pic_path=:pic_path and ");
				}
				if (isSetted_content) {
					sBuffer.append("content=:content and ");
				}
				if (isSetted_fenlei_id) {
					sBuffer.append("fenlei_id=:fenlei_id and ");
				}
				if (isSetted_order_no) {
					sBuffer.append("order_no=:order_no and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_note) {
					sBuffer.append("note=:note and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "fangan_info";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public FANGAN_INFO insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public FANGAN_INFO update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public FANGAN_INFO insertOrUpdate(){
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
			
			return dao.queryForMap("select * from FANGAN_INFO where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("NAME");
			BEAN_VALUES.put("name",obj);
				this.setName(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("PIC_PATH");
			BEAN_VALUES.put("pic_path",obj);
				this.setPic_path(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CONTENT");
			BEAN_VALUES.put("content",obj);
				this.setContent(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("FENLEI_ID");
			BEAN_VALUES.put("fenlei_id",obj);
				this.setFenlei_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("ORDER_NO");
			BEAN_VALUES.put("order_no",obj);
				this.setOrder_no(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("NOTE");
			BEAN_VALUES.put("note",obj);
				this.setNote(ConvertUtil.obj2Str(obj));
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
		
		public FANGAN_INFO newInstance(){
			return new FANGAN_INFO();
		}



















}