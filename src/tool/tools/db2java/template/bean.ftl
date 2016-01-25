package cn.ksource.beans;

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

<#assign comments = FreeMarkerUtil.simpleHash2Map(COMMENTS) />
<#if comments.containsKey(TABLENAME?upper_case)>
/**
 * ${comments.get(TABLENAME?upper_case)}
 */
 </#if>
public class ${TABLENAME?upper_case} extends BaseBean{

	public final static Map<String, String> KEYS = new HashMap<String, String>();
	private Map BEAN_VALUES = null;
	
	static {
		KEYS.put("id", "String");
		<#list columns as column>
		KEYS.put("${column.COLUMN_NAME?lower_case}", "${column.COLUMN_TYPE}");
		</#list>
	}
	public Map getColumnMap(){
		return KEYS;
	}

	private String id;
	private Boolean isSetted_id = false;;
	
	<#list columns as column>
	private ${column.COLUMN_TYPE} ${column.COLUMN_NAME?lower_case};
	private Boolean isSetted_${column.COLUMN_NAME?lower_case} = false;
	</#list>

	private void initBeanValues(){
		BEAN_VALUES = new HashMap<String, Object>();
		BEAN_VALUES.put("id",id);
		<#list columns as column>
			BEAN_VALUES.put("${column.COLUMN_NAME?lower_case}", null);
		</#list>
	}
	
	public ${TABLENAME}() {
		initBeanValues();
	}
	
	public ${TABLENAME}(String id) {
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
	public ${TABLENAME?upper_case} setId(String id) {
		this.id = id;
		this.isSetted_id = true;
		BEAN_VALUES.put("id",id);
		return this;
	}
	
	@Override
	public String getUpdateSql() {
		StringBuffer sBuffer = new StringBuffer("update "+getTableName() +" set ");
		<#list columns as column>
			if (isSetted_${column.COLUMN_NAME?lower_case}) {
				sBuffer.append("${column.COLUMN_NAME?lower_case}=:${column.COLUMN_NAME?lower_case},");
			}
		</#list>
		String sql = sBuffer.toString();
		return StringUtils.removeEnd(sql, ",") + " where id=:id";
	}
	
	
	@Override
	public String getInsertSql() {
		StringBuffer sBuffer = new StringBuffer("insert into "+getTableName() +"(");
		StringBuffer fileds = new StringBuffer("id,");
		StringBuffer values = new StringBuffer(":id,");		
		<#list columns as column>
			fileds.append("${column.COLUMN_NAME?lower_case},");
			values.append(":${column.COLUMN_NAME?lower_case},");
		</#list>
		sBuffer.append(StringUtils.removeEnd(fileds.toString(), ",") + ") values("+StringUtils.removeEnd(values.toString(), ",")+")");
		return sBuffer.toString();
	}
	

	<#list columns as column>
		/**
		 * 获取${column.COLUMN_COMMENT}<BR/>
		 * 䣺${DATE}
		 */
		public ${column.COLUMN_TYPE} get${column.COLUMN_NAME?lower_case?cap_first}() {
			return ${column.COLUMN_NAME?lower_case};
		}
		/**
		 * 设置${column.COLUMN_COMMENT}<BR/>
		 * ${DATE}
		 */
		public ${TABLENAME?upper_case} set${column.COLUMN_NAME?lower_case?cap_first}(${column.COLUMN_TYPE} ${column.COLUMN_NAME?lower_case}) {
			this.${column.COLUMN_NAME?lower_case} = ${column.COLUMN_NAME?lower_case};
			this.isSetted_${column.COLUMN_NAME?lower_case} = true;
			BEAN_VALUES.put("${column.COLUMN_NAME?lower_case}",${column.COLUMN_NAME?lower_case});
			return this;
		}
	</#list>

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
		public ${TABLENAME} getInstanceById() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("获取Bean时ID不能为空");
			}
			return dao.queryForBean("select * from " + getTableName() + " where id=:id", BEAN_VALUES, this);
		}
	
	   <#list MYFK as pk>
		<#if pk.TABLE_NAME?lower_case == TABLENAME?lower_case>
		public ${pk.R_TABLE_NAME?upper_case} get${pk.R_TABLE_NAME?upper_case}() {         
			if(null == get${pk.COLUMN_NAME?lower_case?cap_first}()){
				throw new RuntimeException("${pk.COLUMN_NAME}ֵΪnull");
			}
			return new ${pk.R_TABLE_NAME?upper_case}(get${pk.COLUMN_NAME?lower_case?cap_first}()).getInstanceById();
			//return dao.queryForBean("select * from ${pk.R_TABLE_NAME} where ${pk.R_COLUMN_NAME}=:${pk.COLUMN_NAME?lower_case}", new ${pk.R_TABLE_NAME?upper_case}(this.get${pk.COLUMN_NAME?lower_case?cap_first}()));
		}
		</#if>
		</#list>
		
		<#list MYFK as pk>
		<#if pk.R_TABLE_NAME?lower_case == TABLENAME?lower_case>
		public List<${pk.TABLE_NAME?upper_case}> get${pk.TABLE_NAME?upper_case}List() {         
			if(null == get${pk.R_COLUMN_NAME?lower_case?cap_first}()){
				throw new RuntimeException("${pk.R_COLUMN_NAME}ֵΪnull");
			}
			return dao.queryForList("select * from ${pk.TABLE_NAME} where ${pk.COLUMN_NAME}=:${pk.COLUMN_NAME?lower_case}",this);
		}
		</#if>
		</#list>
		
		@Override
		public ${TABLENAME} queryForBean() {
			StringBuffer sBuffer = new StringBuffer("select * from "+getTableName() +" where ");
			if(isSetted_id){
				sBuffer.append("id=:id and ");
			}
			<#list columns as column>
				if (isSetted_${column.COLUMN_NAME?lower_case}) {
					sBuffer.append("${column.COLUMN_NAME?lower_case}=:${column.COLUMN_NAME?lower_case} and ");
				}
			</#list>
			String sql = sBuffer.toString();
			sql = StringUtils.removeEnd(sql, " and ");
			return dao.queryForBean(sql,this);
		}
	
		@Override
		public String getTableName() {
			return "${TABLENAME?lower_case}";
		}
		
		
		public Map getBeanValues(){
			return this.BEAN_VALUES;
		}
	
		@Override
		public ${TABLENAME} insert() {
			if (StringUtils.isBlank(id)) {
				this.setId(StringUtil.getUUID());
			}
			dao.execute(getInsertSql(),BEAN_VALUES);
			return this;
		}
	
		@Override
		public ${TABLENAME} update() {
			if (StringUtils.isBlank(id)) {
				throw new RuntimeException("更新Bean时ID不能为空");
			}
			dao.execute(getUpdateSql(),BEAN_VALUES);
			return this;
		}  
		
		public ${TABLENAME} insertOrUpdate(){
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
			
			return dao.queryForMap("select * from ${TABLENAME} where id=:id",BEAN_VALUES);
		}

		public Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Object id = rs.getObject("ID");
			this.setId(ConvertUtil.obj2Str(id));
			BEAN_VALUES.put("id",id);
			Object obj = null;
			<#list columns as column>
			obj = rs.getObject("${column.COLUMN_NAME?upper_case}");
			BEAN_VALUES.put("${column.COLUMN_NAME?lower_case}",obj);
			<#if column.COLUMN_TYPE == "String">
				this.set${column.COLUMN_NAME?lower_case?cap_first}(ConvertUtil.obj2Str(obj));
			</#if>
			<#if column.COLUMN_TYPE == "Integer">
				this.set${column.COLUMN_NAME?lower_case?cap_first}(ConvertUtil.obj2Integer(obj));
			</#if>
			<#if column.COLUMN_TYPE == "Long">
				this.set${column.COLUMN_NAME?lower_case?cap_first}(ConvertUtil.obj2Long(obj));
			</#if>
			<#if column.COLUMN_TYPE == "Double">
				this.set${column.COLUMN_NAME?lower_case?cap_first}(ConvertUtil.obj2Double(obj));
			</#if>
			</#list>
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
		
		public ${TABLENAME?upper_case} newInstance(){
			return new ${TABLENAME?upper_case}();
		}



















}