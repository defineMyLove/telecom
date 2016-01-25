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
public class CUS_CARD_INFO extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		KEYS.put("sale_id", "String");
		KEYS.put("product_order_no", "String");
		KEYS.put("create_time", "Long");
		KEYS.put("banli_time", "Long");
		KEYS.put("expire_time", "Long");
		KEYS.put("main_card_num", "Integer");
		KEYS.put("back_card_num", "Integer");
		KEYS.put("cus_id", "String");
		KEYS.put("cus_no", "Integer");
		KEYS.put("state", "Integer");
		KEYS.put("product_type", "Integer");
		KEYS.put("broadband_id", "String");
		KEYS.put("route_id", "String");
		KEYS.put("serial_num", "String");
		KEYS.put("product_id", "String");
		KEYS.put("product_no", "Integer");
		KEYS.put("product_name", "String");
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	private String sale_id;
	private Boolean isSetted_sale_id = false;
	private String product_order_no;
	private Boolean isSetted_product_order_no = false;
	private Long create_time;
	private Boolean isSetted_create_time = false;
	private Long banli_time;
	private Boolean isSetted_banli_time = false;
	private Long expire_time;
	private Boolean isSetted_expire_time = false;
	private Integer main_card_num;
	private Boolean isSetted_main_card_num = false;
	private Integer back_card_num;
	private Boolean isSetted_back_card_num = false;
	private String cus_id;
	private Boolean isSetted_cus_id = false;
	private Integer cus_no;
	private Boolean isSetted_cus_no = false;
	private Integer state;
	private Boolean isSetted_state = false;
	private Integer product_type;
	private Boolean isSetted_product_type = false;
	private String broadband_id;
	private Boolean isSetted_broadband_id = false;
	private String route_id;
	private Boolean isSetted_route_id = false;
	private String serial_num;
	private Boolean isSetted_serial_num = false;
	private String product_id;
	private Boolean isSetted_product_id = false;
	private Integer product_no;
	private Boolean isSetted_product_no = false;
	private String product_name;
	private Boolean isSetted_product_name = false;

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
			BEAN_VALUES.put("sale_id", null);
			BEAN_VALUES.put("product_order_no", null);
			BEAN_VALUES.put("create_time", null);
			BEAN_VALUES.put("banli_time", null);
			BEAN_VALUES.put("expire_time", null);
			BEAN_VALUES.put("main_card_num", null);
			BEAN_VALUES.put("back_card_num", null);
			BEAN_VALUES.put("cus_id", null);
			BEAN_VALUES.put("cus_no", null);
			BEAN_VALUES.put("state", null);
			BEAN_VALUES.put("product_type", null);
			BEAN_VALUES.put("broadband_id", null);
			BEAN_VALUES.put("route_id", null);
			BEAN_VALUES.put("serial_num", null);
			BEAN_VALUES.put("product_id", null);
			BEAN_VALUES.put("product_no", null);
			BEAN_VALUES.put("product_name", null);
	}
	
	public CUS_CARD_INFO() {
		initBeanValues();
	}
	
	public CUS_CARD_INFO(String id) {
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
	public CUS_CARD_INFO setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update "+getTableName() +" set ");
			if (isSetted_sale_id) {
				sBuffer.append("sale_id=:sale_id,");
			}
			if (isSetted_product_order_no) {
				sBuffer.append("product_order_no=:product_order_no,");
			}
			if (isSetted_create_time) {
				sBuffer.append("create_time=:create_time,");
			}
			if (isSetted_banli_time) {
				sBuffer.append("banli_time=:banli_time,");
			}
			if (isSetted_expire_time) {
				sBuffer.append("expire_time=:expire_time,");
			}
			if (isSetted_main_card_num) {
				sBuffer.append("main_card_num=:main_card_num,");
			}
			if (isSetted_back_card_num) {
				sBuffer.append("back_card_num=:back_card_num,");
			}
			if (isSetted_cus_id) {
				sBuffer.append("cus_id=:cus_id,");
			}
			if (isSetted_cus_no) {
				sBuffer.append("cus_no=:cus_no,");
			}
			if (isSetted_state) {
				sBuffer.append("state=:state,");
			}
			if (isSetted_product_type) {
				sBuffer.append("product_type=:product_type,");
			}
			if (isSetted_broadband_id) {
				sBuffer.append("broadband_id=:broadband_id,");
			}
			if (isSetted_route_id) {
				sBuffer.append("route_id=:route_id,");
			}
			if (isSetted_serial_num) {
				sBuffer.append("serial_num=:serial_num,");
			}
			if (isSetted_product_id) {
				sBuffer.append("product_id=:product_id,");
			}
			if (isSetted_product_no) {
				sBuffer.append("product_no=:product_no,");
			}
			if (isSetted_product_name) {
				sBuffer.append("product_name=:product_name,");
			}
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into "+getTableName() +"(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
			fileds.append("sale_id,");
			values.append(":sale_id,");
			fileds.append("product_order_no,");
			values.append(":product_order_no,");
			fileds.append("create_time,");
			values.append(":create_time,");
			fileds.append("banli_time,");
			values.append(":banli_time,");
			fileds.append("expire_time,");
			values.append(":expire_time,");
			fileds.append("main_card_num,");
			values.append(":main_card_num,");
			fileds.append("back_card_num,");
			values.append(":back_card_num,");
			fileds.append("cus_id,");
			values.append(":cus_id,");
			fileds.append("cus_no,");
			values.append(":cus_no,");
			fileds.append("state,");
			values.append(":state,");
			fileds.append("product_type,");
			values.append(":product_type,");
			fileds.append("broadband_id,");
			values.append(":broadband_id,");
			fileds.append("route_id,");
			values.append(":route_id,");
			fileds.append("serial_num,");
			values.append(":serial_num,");
			fileds.append("product_id,");
			values.append(":product_id,");
			fileds.append("product_no,");
			values.append(":product_no,");
			fileds.append("product_name,");
			values.append(":product_name,");
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

		/**
		 * 获取发展人ID<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getSale_id() {
			return sale_id;
		}
		/**
		 * 设置发展人ID<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setSale_id(String sale_id) {
			this.sale_id = sale_id;
			this.isSetted_sale_id = true;
			BEAN_VALUES.put("sale_id",sale_id);
			return this;
		}
		/**
		 * 获取套餐(产品)编号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getProduct_order_no() {
			return product_order_no;
		}
		/**
		 * 设置套餐(产品)编号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setProduct_order_no(String product_order_no) {
			this.product_order_no = product_order_no;
			this.isSetted_product_order_no = true;
			BEAN_VALUES.put("product_order_no",product_order_no);
			return this;
		}
		/**
		 * 获取创建时间<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Long getCreate_time() {
			return create_time;
		}
		/**
		 * 设置创建时间<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setCreate_time(Long create_time) {
			this.create_time = create_time;
			this.isSetted_create_time = true;
			BEAN_VALUES.put("create_time",create_time);
			return this;
		}
		/**
		 * 获取开卡时间<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Long getBanli_time() {
			return banli_time;
		}
		/**
		 * 设置开卡时间<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setBanli_time(Long banli_time) {
			this.banli_time = banli_time;
			this.isSetted_banli_time = true;
			BEAN_VALUES.put("banli_time",banli_time);
			return this;
		}
		/**
		 * 获取到期时间<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Long getExpire_time() {
			return expire_time;
		}
		/**
		 * 设置到期时间<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setExpire_time(Long expire_time) {
			this.expire_time = expire_time;
			this.isSetted_expire_time = true;
			BEAN_VALUES.put("expire_time",expire_time);
			return this;
		}
		/**
		 * 获取主卡号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getMain_card_num() {
			return main_card_num;
		}
		/**
		 * 设置主卡号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setMain_card_num(Integer main_card_num) {
			this.main_card_num = main_card_num;
			this.isSetted_main_card_num = true;
			BEAN_VALUES.put("main_card_num",main_card_num);
			return this;
		}
		/**
		 * 获取主卡背卡号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getBack_card_num() {
			return back_card_num;
		}
		/**
		 * 设置主卡背卡号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setBack_card_num(Integer back_card_num) {
			this.back_card_num = back_card_num;
			this.isSetted_back_card_num = true;
			BEAN_VALUES.put("back_card_num",back_card_num);
			return this;
		}
		/**
		 * 获取客户ID<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getCus_id() {
			return cus_id;
		}
		/**
		 * 设置客户ID<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setCus_id(String cus_id) {
			this.cus_id = cus_id;
			this.isSetted_cus_id = true;
			BEAN_VALUES.put("cus_id",cus_id);
			return this;
		}
		/**
		 * 获取客户编号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getCus_no() {
			return cus_no;
		}
		/**
		 * 设置客户编号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setCus_no(Integer cus_no) {
			this.cus_no = cus_no;
			this.isSetted_cus_no = true;
			BEAN_VALUES.put("cus_no",cus_no);
			return this;
		}
		/**
		 * 获取(0:未办理1:已办理2:归档)<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getState() {
			return state;
		}
		/**
		 * 设置(0:未办理1:已办理2:归档)<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setState(Integer state) {
			this.state = state;
			this.isSetted_state = true;
			BEAN_VALUES.put("state",state);
			return this;
		}
		/**
		 * 获取(产品类型0:宽带)<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getProduct_type() {
			return product_type;
		}
		/**
		 * 设置(产品类型0:宽带)<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setProduct_type(Integer product_type) {
			this.product_type = product_type;
			this.isSetted_product_type = true;
			BEAN_VALUES.put("product_type",product_type);
			return this;
		}
		/**
		 * 获取宽带<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getBroadband_id() {
			return broadband_id;
		}
		/**
		 * 设置宽带<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setBroadband_id(String broadband_id) {
			this.broadband_id = broadband_id;
			this.isSetted_broadband_id = true;
			BEAN_VALUES.put("broadband_id",broadband_id);
			return this;
		}
		/**
		 * 获取猫<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getRoute_id() {
			return route_id;
		}
		/**
		 * 设置猫<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setRoute_id(String route_id) {
			this.route_id = route_id;
			this.isSetted_route_id = true;
			BEAN_VALUES.put("route_id",route_id);
			return this;
		}
		/**
		 * 获取串号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getSerial_num() {
			return serial_num;
		}
		/**
		 * 设置串号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setSerial_num(String serial_num) {
			this.serial_num = serial_num;
			this.isSetted_serial_num = true;
			BEAN_VALUES.put("serial_num",serial_num);
			return this;
		}
		/**
		 * 获取产品ID<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getProduct_id() {
			return product_id;
		}
		/**
		 * 设置产品ID<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setProduct_id(String product_id) {
			this.product_id = product_id;
			this.isSetted_product_id = true;
			BEAN_VALUES.put("product_id",product_id);
			return this;
		}
		/**
		 * 获取产品编号<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public Integer getProduct_no() {
			return product_no;
		}
		/**
		 * 设置产品编号<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setProduct_no(Integer product_no) {
			this.product_no = product_no;
			this.isSetted_product_no = true;
			BEAN_VALUES.put("product_no",product_no);
			return this;
		}
		/**
		 * 获取套餐名称<BR/>
		 * 䣺2016-56-25 hh:01
		 */
		public String getProduct_name() {
			return product_name;
		}
		/**
		 * 设置套餐名称<BR/>
		 * 2016-56-25 hh:01
		 */
		public CUS_CARD_INFO setProduct_name(String product_name) {
			this.product_name = product_name;
			this.isSetted_product_name = true;
			BEAN_VALUES.put("product_name",product_name);
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
		public CUS_CARD_INFO getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
		
		
		@Override
		public CUS_CARD_INFO queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from "+getTableName() +" where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
				if (isSetted_sale_id) {
					sBuffer.append("sale_id=:sale_id and ");
				}
				if (isSetted_product_order_no) {
					sBuffer.append("product_order_no=:product_order_no and ");
				}
				if (isSetted_create_time) {
					sBuffer.append("create_time=:create_time and ");
				}
				if (isSetted_banli_time) {
					sBuffer.append("banli_time=:banli_time and ");
				}
				if (isSetted_expire_time) {
					sBuffer.append("expire_time=:expire_time and ");
				}
				if (isSetted_main_card_num) {
					sBuffer.append("main_card_num=:main_card_num and ");
				}
				if (isSetted_back_card_num) {
					sBuffer.append("back_card_num=:back_card_num and ");
				}
				if (isSetted_cus_id) {
					sBuffer.append("cus_id=:cus_id and ");
				}
				if (isSetted_cus_no) {
					sBuffer.append("cus_no=:cus_no and ");
				}
				if (isSetted_state) {
					sBuffer.append("state=:state and ");
				}
				if (isSetted_product_type) {
					sBuffer.append("product_type=:product_type and ");
				}
				if (isSetted_broadband_id) {
					sBuffer.append("broadband_id=:broadband_id and ");
				}
				if (isSetted_route_id) {
					sBuffer.append("route_id=:route_id and ");
				}
				if (isSetted_serial_num) {
					sBuffer.append("serial_num=:serial_num and ");
				}
				if (isSetted_product_id) {
					sBuffer.append("product_id=:product_id and ");
				}
				if (isSetted_product_no) {
					sBuffer.append("product_no=:product_no and ");
				}
				if (isSetted_product_name) {
					sBuffer.append("product_name=:product_name and ");
				}
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "cus_card_info";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public CUS_CARD_INFO insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public CUS_CARD_INFO update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public CUS_CARD_INFO insertOrUpdate(){
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
			
			return dao.queryForMap("select * from CUS_CARD_INFO where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			obj = rs.getObject("SALE_ID");
			BEAN_VALUES.put("sale_id",obj);
				this.setSale_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("PRODUCT_ORDER_NO");
			BEAN_VALUES.put("product_order_no",obj);
				this.setProduct_order_no(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CREATE_TIME");
			BEAN_VALUES.put("create_time",obj);
				this.setCreate_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("BANLI_TIME");
			BEAN_VALUES.put("banli_time",obj);
				this.setBanli_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("EXPIRE_TIME");
			BEAN_VALUES.put("expire_time",obj);
				this.setExpire_time(ConvertUtil.obj2Long(obj));
			obj = rs.getObject("MAIN_CARD_NUM");
			BEAN_VALUES.put("main_card_num",obj);
				this.setMain_card_num(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("BACK_CARD_NUM");
			BEAN_VALUES.put("back_card_num",obj);
				this.setBack_card_num(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("CUS_ID");
			BEAN_VALUES.put("cus_id",obj);
				this.setCus_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("CUS_NO");
			BEAN_VALUES.put("cus_no",obj);
				this.setCus_no(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("STATE");
			BEAN_VALUES.put("state",obj);
				this.setState(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("PRODUCT_TYPE");
			BEAN_VALUES.put("product_type",obj);
				this.setProduct_type(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("BROADBAND_ID");
			BEAN_VALUES.put("broadband_id",obj);
				this.setBroadband_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("ROUTE_ID");
			BEAN_VALUES.put("route_id",obj);
				this.setRoute_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("SERIAL_NUM");
			BEAN_VALUES.put("serial_num",obj);
				this.setSerial_num(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("PRODUCT_ID");
			BEAN_VALUES.put("product_id",obj);
				this.setProduct_id(ConvertUtil.obj2Str(obj));
			obj = rs.getObject("PRODUCT_NO");
			BEAN_VALUES.put("product_no",obj);
				this.setProduct_no(ConvertUtil.obj2Integer(obj));
			obj = rs.getObject("PRODUCT_NAME");
			BEAN_VALUES.put("product_name",obj);
				this.setProduct_name(ConvertUtil.obj2Str(obj));
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
		
		public CUS_CARD_INFO newInstance(){
			return new CUS_CARD_INFO();
		}



















}