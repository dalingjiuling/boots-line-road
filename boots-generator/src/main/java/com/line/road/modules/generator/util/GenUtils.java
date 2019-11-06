package com.line.road.modules.generator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.line.road.common.config.Global;
import com.line.road.common.mapper.JaxbMapper;
import com.line.road.common.template.FreeMarkers;
import com.line.road.common.utils.DateUtils;
import com.line.road.common.utils.FileUtils;
import com.line.road.common.utils.StringUtils;
import com.line.road.modules.generator.entity.GenConfig;
import com.line.road.modules.generator.entity.GenTemplate;
import com.line.road.modules.generator.entity.GenTplCategory;
import com.line.road.modules.generator.entity.TableColumn;
import com.line.road.modules.generator.entity.TableSchema;
import com.line.road.modules.system.utils.UserUtils;

public class GenUtils {
	private static Logger LOG = LoggerFactory.getLogger(GenUtils.class);

	public static GenConfig getConfig() {
		// 将config.xml解析成javabean
		return (GenConfig) fileToObject("config.xml", GenConfig.class);
	}

	/**
	 * 给表的字段初始化值
	 * 
	 * @param table
	 */
	public void initColumnField(TableSchema table) {
		for (TableColumn column : table.getColumnList()) {
			if (StringUtils.isBlank(column.getComments())) {
				column.setComments(column.getColumnName());
			}
			if (StringUtils.startsWithIgnoreCase(column.getColumnType(), "date")
					|| (StringUtils.startsWithIgnoreCase(column.getColumnType(), "timestamp"))) {
				column.setJavaType(Date.class.getName());
				column.setShowType("dateselect");
			} else if ((StringUtils.startsWithIgnoreCase(column.getColumnType(), "float"))
					|| (StringUtils.startsWithIgnoreCase(column.getColumnType(), "double"))) {
				column.setJavaType(Double.class.getSimpleName());
			} else if ((StringUtils.containsIgnoreCase(column.getColumnType(), "int"))
					|| (StringUtils.startsWithIgnoreCase(column.getColumnType(), "number"))
					|| (StringUtils.startsWithIgnoreCase(column.getColumnType(), "decimal"))
					|| (StringUtils.startsWithIgnoreCase(column.getColumnType(), "numeric"))) {
				String[] ss = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");

				if ((ss != null) && (ss.length == 2) && (Integer.parseInt(ss[1]) > 0)) {
					column.setJavaType(Double.class.getSimpleName());
				} else if ((ss != null) && (ss.length >= 1) && (Integer.parseInt(ss[0]) <= 10)) {
					column.setJavaType(Integer.class.getSimpleName());
				} else {
					column.setJavaType(Long.class.getSimpleName());
				}
			} else {
				column.setJavaType(Long.class.getSimpleName());
			}

			column.setJavaField(StringUtils.toCamelCase(column.getColumnName()));

			for (TableColumn pk : table.getPkList()) {
				if (column.getColumnName().equals(pk.getColumnName())) {
					column.setIsPk("1");
					column.setIsEdit("1");
					column.setShowType("hidden");
				}
			}

			column.setIsInsert("1");

			if ((!"1".equals(column.getIsPk())) && (!StringUtils.equalsIgnoreCase(column.getColumnName(), "create_by"))
					&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "create_date"))
					&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "status"))
					&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "corp_code"))
					&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "corp_name"))) {
				column.setIsUpdate("1");

				if ((!StringUtils.equalsIgnoreCase(column.getColumnName(), "update_by"))
						&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "update_date"))
						&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "hasnext"))
						&& (!StringUtils.equalsIgnoreCase(column.getColumnName(), "sort_grade"))) {
					column.setIsEdit("1");
				}
			}

			if ((StringUtils.equalsIgnoreCase(column.getColumnName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "title"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "remarks"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "update_date"))) {
				column.setIsList("1");
			}

			if ((StringUtils.equalsIgnoreCase(column.getColumnName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "title"))) {
				column.setIsQuery("1");
			}

			if ((StringUtils.equalsIgnoreCase(column.getColumnName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "title"))) {
				column.setQueryType("all_like");
			}

			if ((StringUtils.startsWithIgnoreCase(column.getColumnName(), "create_date"))
					|| (StringUtils.startsWithIgnoreCase(column.getColumnName(), "update_date"))) {
				column.setShowType("dateselect");
			} else if ((StringUtils.equalsIgnoreCase(column.getColumnName(), "remarks"))
					|| (StringUtils.equalsIgnoreCase(column.getColumnName(), "content"))) {
				column.setShowType("textarea");
			} else if (StringUtils.equalsIgnoreCase(column.getColumnName(), "parent_code")) {
				column.setJavaType("This");
				column.setJavaField("parent.id|name");
				column.setShowType("treeselect");
			} else if (StringUtils.equalsIgnoreCase(column.getColumnName(), "parent_codes")) {
				column.setQueryType("like");
			} else if (StringUtils.equalsIgnoreCase(column.getColumnName(), "sorts")) {
				column.setJavaType(Integer.class.getSimpleName());
			} else if (StringUtils.equalsIgnoreCase(column.getColumnName(), "status")) {
				column.setShowType("select");
				column.setDictType("search_status");
			}
		}
	}

	/**
	 * 获取配置文件映射的实体中的内容
	 * 
	 * @param config
	 *            配置文件映射的实体
	 * @param category
	 *            类别
	 * @param isChildTable
	 *            是否有子表
	 * @return
	 */
	public static List<GenTemplate> getTemplateList(GenConfig config, String category, boolean isChildTable) {
		List<GenTemplate> templateList = new ArrayList<GenTemplate>();
		if ((null != config) && (null != config.getTplCategoryList()) && (null != category)) {
			// 遍历config.xml中的category节点
			for (GenTplCategory e : config.getTplCategoryList()) {
				// 遍历config.xml中的category节点value属性
				if (category.equals(e.getValue())) {
					List<String> list = null;
					if (!isChildTable)
						list = e.getTemplate();
					else {
						list = e.getChildTableTemplate();
					}
					if (list == null)
						break;
					for (String s : list) {
						// 如果包含category-ref:
						if (StringUtils.startsWith(s, GenTplCategory.CATEGORY_REF)) {
							// dao
							templateList.addAll(getTemplateList(config,
									StringUtils.replace(s, GenTplCategory.CATEGORY_REF, ""), false));
						} else {
							// 根据模板路径生成文件
							GenTemplate template = (GenTemplate) fileToObject(s, GenTemplate.class);
							if (template != null)
								templateList.add(template);
						}
					}
					break;
				}
			}

		}

		return templateList;
	}

	/**
	 * 配置文件映射成类
	 * 
	 * @param fileName
	 *            配置文件
	 * @param clazz
	 *            配置文件映射的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fileToObject(String fileName, Class<?> clazz) {
		BufferedReader br = null;
		String pathName = null;
		try {
			pathName = new StringBuilder().append("/templates/modules/gen/").append(fileName).toString();
			Resource resource = new ClassPathResource(fileName);
			File file = resource.getFile();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line).append("\r\n");
			}
			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (Exception e) {
			LOG.error("Read file [{}] exception ", pathName, e);
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error("After reading the file[{}], close the exception.", pathName, e);
				}
			}
		}
		return null;
	}

	public static Map<String, Object> getDataModel(TableSchema genTable) {
		if (genTable.getParentExists().booleanValue()) {
			TableSchema parentTable = genTable.getParent();
			genTable.setPackageName(parentTable.getPackageName());
			genTable.setModuleName(parentTable.getModuleName());
			genTable.setSubModuleName(parentTable.getSubModuleName());
			genTable.setFunctionName(parentTable.getFunctionName());
			genTable.setFunctionNameSimple(parentTable.getFunctionNameSimple());
			genTable.setFunctionAuthor(parentTable.getFunctionAuthor());
			genTable.setGenBaseDir(parentTable.getGenBaseDir());
			genTable.setOptions(parentTable.getOptions());
			genTable.setReplaceFile(parentTable.getReplaceFile());
			genTable.setFlag(parentTable.getFlag());
		}

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("packageName", StringUtils.lowerCase(genTable.getPackageName()));
		model.put("lastPackageName", StringUtils.substringAfterLast((String) model.get("packageName"), "."));
		model.put("moduleName", StringUtils.lowerCase(genTable.getModuleName()));
		model.put("subModuleName", StringUtils.lowerCase(genTable.getSubModuleName()));
		model.put("className", StringUtils.uncapitalize(genTable.getClassName()));
		model.put("ClassName", StringUtils.capitalize(genTable.getClassName()));

		model.put("functionName", genTable.getFunctionName());
		model.put("functionNameSimple", genTable.getFunctionNameSimple());
		model.put("functionAuthor", StringUtils.isNotBlank(genTable.getFunctionAuthor()) ? genTable.getFunctionAuthor()
				: UserUtils.getUser().getUserName());
		model.put("functionVersion", DateUtils.getDate());

		model.put("urlPrefix",
				StringUtils.replace(model.get("moduleName")
						+ (StringUtils.isNotBlank(genTable.getSubModuleName())
								? "/" + StringUtils.lowerCase(genTable.getSubModuleName()) : "")
						+ "/" + model.get("className"), ".", "/"));

		model.put("viewPrefix", model.get("urlPrefix"));

		model.put("permissionPrefix",
				StringUtils.replace(model.get("moduleName")
						+ (StringUtils.isNotBlank(genTable.getSubModuleName())
								? ":" + StringUtils.lowerCase(genTable.getSubModuleName()) : "")
						+ ":" + model.get("className"), ".", ":"));

		model.put("dbName", Global.getDbName());

		model.put("table", genTable);

		String pkField = "";
		if ((genTable != null) && (genTable.getPkList() != null) && (genTable.getPkList().size() == 1)) {
			pkField = ((TableColumn) genTable.getPkList().get(0)).getSimpleJavaField();
		}
		model.put("pkField", pkField);

		return model;
	}

	public static String generateToFile(GenTemplate tpl, Map<String, Object> model, TableSchema genTable) {
		if ((StringUtils.isBlank(genTable.getGenBaseDir())) || (tpl.getFileName().endsWith(".jsp"))) {
			genTable.setGenBaseDir(Global.getProjectPath());
		}

		String fileName = genTable.getGenBaseDir() + File.separator
				+ StringUtils.replaceEach(
						FreeMarkers.renderString(new StringBuilder().append(tpl.getFilePath()).append("/").toString(),
								model),
						new String[] { "//", "/", "." },
						new String[] { File.separator, File.separator, File.separator })
				+ FreeMarkers.renderString(tpl.getFileName(), model);

		LOG.debug("Generate File ==> \r\n" + fileName);

		String content = FreeMarkers.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);
		LOG.debug("File Content ==> \r\n" + content);

		if ("2".equals(genTable.getFlag())) {

			if (genTable.getReplaceFile().booleanValue()) {
				FileUtils.deleteFile(fileName);
			}

			if (FileUtils.createFile(fileName)) {
				FileUtils.writeToFile(fileName, content, true);
				LOG.debug("File Create ==> " + fileName);
				return "生成成功：" + fileName + "<br/>";
			}
			LOG.debug("File Extents ==> " + fileName);
			return "文件已存在：" + fileName + "<br/>";
		}

		return fileName + "<br/>";
	}
}
