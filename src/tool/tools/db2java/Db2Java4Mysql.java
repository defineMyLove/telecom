package tool.tools.db2java;

import com.company.modules.dao.BaseDao;
import com.company.modules.dao.SqlParameter;
import com.company.modules.utils.DateUtil;
import com.company.modules.utils.FreeMarkerUtil;
import com.company.modules.utils.JdbcUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/spring/spring_tx.xml"})
@Transactional
public class Db2Java4Mysql {
	
	@Autowired
	private BaseDao baseDao;
	
	public final static String TABLE_SCHEMA = JdbcUtil.getDbName();
	public final static String FILE_PATH = "d:\\db2java\\";
	public final static String PACKAGE_PATH = "";
	public final static String CLASS_FILE_PATH = FILE_PATH+"classes";
	private List<Map> freeMakerList;
	
	
	/**
	 * @return
	 */
	private List<Map> getFreeMakerList(){
		if (freeMakerList == null || freeMakerList.size() == 0) {
			String sql = "select TABLE_NAME,COLUMN_NAME,COLUMN_TYPE as TYPE,COLUMN_COMMENT from information_schema.columns where TABLE_SCHEMA = '"+ Db2Java4Mysql.TABLE_SCHEMA +"'";
			List<Map> list = baseDao.queryForList(sql,new SqlParameter());
			for (Map map : list) {
				String column_type = map.get("TYPE").toString().toLowerCase();
				if (StringUtils.startsWith(column_type, "char") || StringUtils.startsWith(column_type, "varchar") || StringUtils.startsWith(column_type, "text") || StringUtils.startsWith(column_type, "longtext")) {
					map.put("COLUMN_TYPE", "String");
				} else if (StringUtils.startsWith(column_type, "int") || StringUtils.startsWith(column_type, "tinyint") || StringUtils.startsWith(column_type, "smallint")) {
					map.put("COLUMN_TYPE", "Integer");
				} else if (StringUtils.startsWith(column_type, "bigint")) {
					map.put("COLUMN_TYPE", "Long");
				} else if (StringUtils.startsWith(column_type, "float") || StringUtils.startsWith(column_type, "double")) {
					map.put("COLUMN_TYPE", "Double");
				}
				map.put("COLUMN_LENGTH", !StringUtils.startsWith(column_type,"text") ?
								column_type.substring(column_type.indexOf("(")+1, column_type.length()-1)
								:new Integer(65535));
			}
			freeMakerList = list;
		}
		return this.freeMakerList;
	}
	
	private List<Map> getTableInfoList(){
		List<Map> resultList = new ArrayList<Map>();
		Map bwlMap = new HashMap();
		List<Map> list = getFreeMakerList();
		for (Map map : list) {
			if (!bwlMap.containsKey(map.get("TABLE_NAME"))) {
				resultList.add(map);
				bwlMap.put(map.get("TABLE_NAME"), map);
				List<Map> tempList = new ArrayList<Map>();
				map.put("columns", tempList);
			} else {
				List<Map> tempList = (List<Map>)(((Map)bwlMap.get(map.get("TABLE_NAME"))).get("columns"));
				tempList.add(map);
			}
		}
		return resultList;
	}
	
	/**
	 * ���
	 * @param root
	 * ���ߣ��
	 */
	private Map addPK(){
		
		Map root = new HashMap();
		
		String getPKSQL = "SELECT\n" +
		"	TABLE_NAME,\n" +
		"  COLUMN_NAME,\n" +
		"  REFERENCED_TABLE_NAME as R_TABLE_NAME,\n" +
		"  REFERENCED_COLUMN_NAME as R_COLUMN_NAME\n" +
		" FROM\n" +
		"	information_schema.KEY_COLUMN_USAGE\n" +
		" WHERE\n" +
		//" 	TABLE_NAME = 'ac_role_qx'\n" +
		" POSITION_IN_UNIQUE_CONSTRAINT = 1\n" +
		" and TABLE_SCHEMA = '"+ Db2Java4Mysql.TABLE_SCHEMA +"'";
		
		root.put("MYFK", baseDao.queryForList(getPKSQL,new SqlParameter()));
		return root;
	}
	
	/**
	 * ��ע��
	 * @return
	 * ���ߣ��
	 */
	public Map getTableComments(){
		String sql = "SELECT\n" +
		"	TABLE_NAME,\n" +
		"	TABLE_COMMENT\n" +
		"FROM\n" +
		"	information_schema.`TABLES`\n" +
		"WHERE\n" +
		"	TABLE_SCHEMA = '"+ Db2Java4Mysql.TABLE_SCHEMA +"'";
		List<Map> list = baseDao.queryForList(sql,new SqlParameter());
		Map rootMap = new HashMap();
		for (Map map : list) {
			rootMap.put(map.get("TABLE_NAME").toString().toUpperCase(), map.get("TABLE_COMMENT"));
		}
		return rootMap;
	}
	
	private void table2Java(Configuration cfg) throws Exception {
		List<Map> list = this.getTableInfoList();

		Map fkMap = addPK();
		Map tableComments = getTableComments();
		
		for (Map map : list) {
			Template template = cfg.getTemplate("bean.ftl");
			
			Map rootMap = new HashMap();
			rootMap.put("DATE", DateUtil.format("yyyy-mm-dd hh:MM", DateUtil.getCurrnetDate12()));
			rootMap.put("TABLENAME", map.get("TABLE_NAME").toString().toUpperCase());
			rootMap.put("columns", map.get("columns"));
			rootMap.put("MYFK", fkMap.get("MYFK"));
			rootMap.put("COMMENTS", tableComments);
			
			File targetFile = new File(FILE_PATH+Db2Java4Mysql.PACKAGE_PATH+map.get("TABLE_NAME").toString().toUpperCase()+".java");
			if (!targetFile.exists()) {
				targetFile.getParentFile().mkdirs();
			}
			Writer writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
			template.process(rootMap, writer);
			
			writer.flush();
			writer.close();
		}
	}
	
	private static void loadCommonStaticClass(Configuration cfg) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
//			cfg.setSharedVariable("Constants", (TemplateHashModel) staticModels.get("com.zzsb.Constants"));
			cfg.setSharedVariable("ConvertUtil", (TemplateHashModel) staticModels.get("com.company.modules.utils.ConvertUtil"));
			cfg.setSharedVariable("FreeMarkerUtil", (TemplateHashModel) staticModels.get("com.company.modules.utils.FreeMarkerUtil"));
			// root.put("FileUtil", (TemplateHashModel)
			// staticModels.get("com.djwl.core.utils.FileUtil"));
			cfg.setSharedVariable("DateUtil", (TemplateHashModel) staticModels.get("com.company.modules.utils.DateUtil"));
			cfg.setSharedVariable("StringUtil", (TemplateHashModel) staticModels.get("com.company.modules.utils.StringUtil"));
			cfg.setSharedVariable("StringUtils", (TemplateHashModel) staticModels.get("org.apache.commons.lang.StringUtils"));
			cfg.setSharedVariable("BwlUtil", (TemplateHashModel) staticModels.get("com.company.modules.utils.BwlUtil"));
			cfg.setSharedVariable("Math", (TemplateHashModel) staticModels.get("java.lang.Math"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public  void create() throws Exception {
		
		System.out.println("db2java ��ʼ��....");
		

		Configuration cfg = FreeMarkerUtil.getConfiguration(this.getClass(), "template");
		loadCommonStaticClass(cfg);
		this.table2Java(cfg);
		
		//�������ļ���
		String path = FILE_PATH.replaceAll("/", "\\\\");
        String cmd[]={"explorer.exe",path};   
        Process p = Runtime.getRuntime().exec(cmd);
		//�������ļ���
//        String cmd[]={"cmd /c cd /d " + FILE_PATH,"jar cf bean.jar " + PACKAGE_PATH};   
//		String cmd = "cmd /c cd /d " + FILE_PATH + 
//		" && " +
//		"javac -classpath "+ System.getProperty("java.class.path") +" "+ FILE_PATH+PACKAGE_PATH + "*.java " + 
//		" && " +
//		"jar cf bean-"+TABLE_SCHEMA+"-"+ DateUtil.getCurrentDate14() +".jar " + PACKAGE_PATH+"*.class" +
//		" && " +
//		"jar cf bean-source-"+TABLE_SCHEMA+"-"+ DateUtil.getCurrentDate14() +".jar " + PACKAGE_PATH+"*.java" +
//		" && " +
//		"jar cf bean-all-"+TABLE_SCHEMA+"-"+ DateUtil.getCurrentDate14() +".jar " + PACKAGE_PATH+"*.*" 
//		;
//		System.out.println(cmd);
//        Process p = Runtime.getRuntime().exec(cmd);
//        
//        System.out.println("db2java ִ�гɹ�");
	}
}
