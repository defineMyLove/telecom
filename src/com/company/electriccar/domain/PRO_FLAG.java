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
public class PRO_FLAG extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("raft_id", "String");
		KEYS.put("state", "Integer");
		KEYS.put("bttery", "String");
		KEYS.put("tempetature", "String");
		KEYS.put("tagtype", "String");
		KEYS.put("flag_id", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private String raft_id;
	private Boolean isSetted_raft_id = false;
	private Integer state;
	private Boolean isSetted_state = false;
	private String bttery;
	private Boolean isSetted_bttery = false;
	private String tempetature;
	private Boolean isSetted_tempetature = false;
	private String tagtype;
	private Boolean isSetted_tagtype = false;
	private String flag_id;
	private Boolean isSetted_flag_id = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("raft_id", null);
			BEAN_VALUES.put("state", null);
			BEAN_VALUES.put("bttery", null);
			BEAN_VALUES.put("tempetature", null);
			BEAN_VALUES.put("tagtype", null);
			BEAN_VALUES.put("flag_id", null);
	}
	
	public PRO_FLAG() {
		initBeanValues();
	}
	
	public PRO_FLAG(String id) {
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
	public PRO_FLAG setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update PRO_FLAG set ");
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_raft_id) {
				sBuffer.append("raft_id=:raft_id,");
			}
			if (isSetted_state) {
				sBuffer.append("state=:state,");
			}
			if (isSetted_bttery) {
				sBuffer.append("bttery=:bttery,");
			}
			if (isSetted_tempetature) {
				sBuffer.append("tempetature=:tempetature,");
			}
			if (isSetted_tagtype) {
				sBuffer.append("tagtype=:tagtype,");
			}
			if (isSetted_flag_id) {
				sBuffer.append("flag_id=:flag_id,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into PRO_FLAG(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("raft_id,");
			values.append(":raft_id,");
			fileds.append("state,");
			values.append(":state,");
			fileds.append("bttery,");
			values.append(":bttery,");
			fileds.append("tempetature,");
			values.append(":tempetature,");
			fileds.append("tagtype,");
			values.append(":tagtype,");
			fileds.append("flag_id,");
			values.append(":flag_id,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取木筏ID<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public String getRaft_id() {
			return raft_id;
		}
		/**
		 * 设置木筏ID<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setRaft_id(String raft_id) {
			this.raft_id = raft_id;
			this.isSetted_raft_id = true;
			BEAN_VALUES.put("raft_id",raft_id);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 设置<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setState(Integer state) {
			this.state = state;
			this.isSetted_state = true;
			BEAN_VALUES.put("state",state);
			return this;
		}
		/**
		 * 获取电池状态<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public String getBttery() {
			return bttery;
		}
		/**
		 * 设置电池状态<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setBttery(String bttery) {
			this.bttery = bttery;
			this.isSetted_bttery = true;
			BEAN_VALUES.put("bttery",bttery);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public String getTempetature() {
			return tempetature;
		}
		/**
		 * 设置<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setTempetature(String tempetature) {
			this.tempetature = tempetature;
			this.isSetted_tempetature = true;
			BEAN_VALUES.put("tempetature",tempetature);
			return this;
		}
		/**
		 * 获取类型<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public String getTagtype() {
			return tagtype;
		}
		/**
		 * 设置类型<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setTagtype(String tagtype) {
			this.tagtype = tagtype;
			this.isSetted_tagtype = true;
			BEAN_VALUES.put("tagtype",tagtype);
			return this;
		}
		/**
		 * 获取<BR/>
		 * 䣺2016-40-09 hh:01
		 */
		public String getFlag_id() {
			return flag_id;
		}
		/**
		 * 设置<BR/>
		 * 2016-40-09 hh:01
		 */
		public PRO_FLAG setFlag_id(String flag_id) {
			this.flag_id = flag_id;
			this.isSetted_flag_id = true;
			BEAN_VALUES.put("flag_id",flag_id);
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
		public PRO_FLAG getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public PRO_FLAG queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from PRO_FLAG where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_raft_id) {
					sBuffer.append("raft_id=:raft_id and ");
				}
				if (isSetted_state) {
					sBuffer.append("state=:state and ");
				}
				if (isSetted_bttery) {
					sBuffer.append("bttery=:bttery and ");
				}
				if (isSetted_tempetature) {
					sBuffer.append("tempetature=:tempetature and ");
				}
				if (isSetted_tagtype) {
					sBuffer.append("tagtype=:tagtype and ");
				}
				if (isSetted_flag_id) {
					sBuffer.append("flag_id=:flag_id and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "PRO_FLAG";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public PRO_FLAG insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public PRO_FLAG update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public PRO_FLAG insertOrUpdate(){
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
			
			return dao.queryForMap("select * from PRO_FLAG where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("RAFT_ID");
			BEAN_VALUES.put("raft_id",obj);
				this.setRaft_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("STATE");
			BEAN_VALUES.put("state",obj);
				this.setState(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("BTTERY");
			BEAN_VALUES.put("bttery",obj);
				this.setBttery(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("TEMPETATURE");
			BEAN_VALUES.put("tempetature",obj);
				this.setTempetature(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("TAGTYPE");
			BEAN_VALUES.put("tagtype",obj);
				this.setTagtype(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("FLAG_ID");
			BEAN_VALUES.put("flag_id",obj);
				this.setFlag_id(ConvertUtil.obj2Str(obj));
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
		
		public PRO_FLAG newInstance(){
			return new PRO_FLAG();
		}



















}