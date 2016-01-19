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
public class PRO_FLAG_RECORD extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("flagid", "String");
		KEYS.put("raltid", "String");
		KEYS.put("starttime", "Long");
		KEYS.put("endtime", "Long");
		KEYS.put("personcount", "Integer");
		KEYS.put("stantime", "Integer");
		KEYS.put("note", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String flagid;
	private Boolean isSetted_flagid = false;
	private String raltid;
	private Boolean isSetted_raltid = false;
	private Long starttime;
	private Boolean isSetted_starttime = false;
	private Long endtime;
	private Boolean isSetted_endtime = false;
	private Integer personcount;
	private Boolean isSetted_personcount = false;
	private Integer stantime;
	private Boolean isSetted_stantime = false;
	private String note;
	private Boolean isSetted_note = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("flagid", null);
			BEAN_VALUES.put("raltid", null);
			BEAN_VALUES.put("starttime", null);
			BEAN_VALUES.put("endtime", null);
			BEAN_VALUES.put("personcount", null);
			BEAN_VALUES.put("stantime", null);
			BEAN_VALUES.put("note", null);
	}
	
	public PRO_FLAG_RECORD() {
		initBeanValues();
	}
	
	public PRO_FLAG_RECORD(String id) {
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
	public PRO_FLAG_RECORD setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update PRO_FLAG_RECORD set ");
			if (isSetted_flagid) {
				sBuffer.append("flagid=:flagid,");
			}
			if (isSetted_raltid) {
				sBuffer.append("raltid=:raltid,");
			}
			if (isSetted_starttime) {
				sBuffer.append("starttime=:starttime,");
			}
			if (isSetted_endtime) {
				sBuffer.append("endtime=:endtime,");
			}
			if (isSetted_personcount) {
				sBuffer.append("personcount=:personcount,");
			}
			if (isSetted_stantime) {
				sBuffer.append("stantime=:stantime,");
			}
			if (isSetted_note) {
				sBuffer.append("note=:note,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into PRO_FLAG_RECORD(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("flagid,");
			values.append(":flagid,");
			fileds.append("raltid,");
			values.append(":raltid,");
			fileds.append("starttime,");
			values.append(":starttime,");
			fileds.append("endtime,");
			values.append(":endtime,");
			fileds.append("personcount,");
			values.append(":personcount,");
			fileds.append("stantime,");
			values.append(":stantime,");
			fileds.append("note,");
			values.append(":note,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getFlagid() {
			return flagid;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setFlagid(String flagid) {
			this.flagid = flagid;
			this.isSetted_flagid = true;
			BEAN_VALUES.put("flagid",flagid);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getRaltid() {
			return raltid;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setRaltid(String raltid) {
			this.raltid = raltid;
			this.isSetted_raltid = true;
			BEAN_VALUES.put("raltid",raltid);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public Long getStarttime() {
			return starttime;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setStarttime(Long starttime) {
			this.starttime = starttime;
			this.isSetted_starttime = true;
			BEAN_VALUES.put("starttime",starttime);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public Long getEndtime() {
			return endtime;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setEndtime(Long endtime) {
			this.endtime = endtime;
			this.isSetted_endtime = true;
			BEAN_VALUES.put("endtime",endtime);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public Integer getPersoncount() {
			return personcount;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setPersoncount(Integer personcount) {
			this.personcount = personcount;
			this.isSetted_personcount = true;
			BEAN_VALUES.put("personcount",personcount);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public Integer getStantime() {
			return stantime;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setStantime(Integer stantime) {
			this.stantime = stantime;
			this.isSetted_stantime = true;
			BEAN_VALUES.put("stantime",stantime);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-55-09 hh:01
		 */
		public String getNote() {
			return note;
		}
		/**
		 * 设置<BR/>
		 * 2016-55-09 hh:01
		 */
		public PRO_FLAG_RECORD setNote(String note) {
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
		public PRO_FLAG_RECORD getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public PRO_FLAG_RECORD queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from PRO_FLAG_RECORD where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_flagid) {
					sBuffer.append("flagid=:flagid and ");
				}
				if (isSetted_raltid) {
					sBuffer.append("raltid=:raltid and ");
				}
				if (isSetted_starttime) {
					sBuffer.append("starttime=:starttime and ");
				}
				if (isSetted_endtime) {
					sBuffer.append("endtime=:endtime and ");
				}
				if (isSetted_personcount) {
					sBuffer.append("personcount=:personcount and ");
				}
				if (isSetted_stantime) {
					sBuffer.append("stantime=:stantime and ");
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
			return "PRO_FLAG_RECORD";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public PRO_FLAG_RECORD insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public PRO_FLAG_RECORD update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public PRO_FLAG_RECORD insertOrUpdate(){
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
			
			return dao.queryForMap("select * from PRO_FLAG_RECORD where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("FLAGID");
			BEAN_VALUES.put("flagid",obj);
				this.setFlagid(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("RALTID");
			BEAN_VALUES.put("raltid",obj);
				this.setRaltid(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("STARTTIME");
			BEAN_VALUES.put("starttime",obj);
				this.setStarttime(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("ENDTIME");
			BEAN_VALUES.put("endtime",obj);
				this.setEndtime(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("PERSONCOUNT");
			BEAN_VALUES.put("personcount",obj);
				this.setPersoncount(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("STANTIME");
			BEAN_VALUES.put("stantime",obj);
				this.setStantime(ConvertUtil.obj2Integer(obj));
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
		
		public PRO_FLAG_RECORD newInstance(){
			return new PRO_FLAG_RECORD();
		}



















}