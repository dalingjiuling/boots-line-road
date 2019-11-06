package com.line.road.modules.generator.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.line.road.framework.service.CrudService;
import com.line.road.modules.generator.dao.TableColumnDao;
import com.line.road.modules.generator.dao.TableJoinDao;
import com.line.road.modules.generator.dao.TableSchemaDao;
import com.line.road.modules.generator.entity.GenConfig;
import com.line.road.modules.generator.entity.GenTemplate;
import com.line.road.modules.generator.entity.TableColumn;
import com.line.road.modules.generator.entity.TableJoin;
import com.line.road.modules.generator.entity.TableSchema;
import com.line.road.modules.generator.util.GenUtils;

@Service
@Transactional(readOnly = true)
public class GenTableService extends CrudService<TableSchema, TableSchemaDao> {

	@Autowired
	private TableColumnDao tableColumnDao;

	@Autowired
	private TableJoinDao tableJoinDao;

	@Autowired
	private TableSchemaDao tableSchemaDao;

	/**
	 * 生成代码
	 * 
	 * @param genTable
	 * @return
	 */
	public String generateCode(TableSchema genTable) {
		StringBuilder result = new StringBuilder();
		// 代码模板配置
		GenConfig config = GenUtils.getConfig();
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genTable.getTplCategory(), false);
		List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genTable.getTplCategory(), true);
		// 查看是否有子表
		if (childTableTemplateList.size() > 0) {
			TableSchema parentTable = new TableSchema();
			parentTable.setParentTableName(genTable.getTableName());
			genTable.setChildList(((TableSchemaDao) this.dao).findList(parentTable));
		}
		Iterator<TableSchema> i = null;
		if ((childTableTemplateList.size() > 0) && (genTable.getChildList().size() > 0))
			for (i = genTable.getChildList().iterator(); i.hasNext();) {
				TableSchema childTable = i.next();
				childTable.setParent(genTable);
				childTable.setColumnList(this.tableColumnDao.findList(new TableColumn(childTable)));
				childTable.setTableJoinList(this.tableJoinDao.findList(new TableJoin(childTable)));
				Map<String, Object> childTableModel = GenUtils.getDataModel(childTable);
				for (GenTemplate tpl : childTableTemplateList)
					result.append(GenUtils.generateToFile(tpl, childTableModel, childTable));
			}

		Map<String, Object> model = GenUtils.getDataModel(genTable);
		for (GenTemplate tpl : templateList) {
			result.append(GenUtils.generateToFile(tpl, model, genTable));
		}
		return result.toString();
	}

	public TableSchema getTableFormDb(TableSchema table) {
		if (StringUtils.isNotBlank(table.getTableName())) {
			List<TableSchema> tables = tableSchemaDao.findTableList(table);
			if (null != tables && !tables.isEmpty()) {
				// 查询表对应的字段
				List<TableColumn> columns = tableSchemaDao.findTableColumnList(table);
				for (TableColumn column : columns) {
					boolean b = false;
					for (TableColumn e : table.getColumnList()) {
						if (e.getColumnName().equals(column.getColumnName())) {
							b = true;
						}
					}
					if (!b) {
						table.getColumnList().add(column);
					}

				}

				for (TableColumn e : table.getColumnList()) {
					boolean b = false;
					for (TableColumn column : columns) {
						if (column.getColumnName().equals(e.getColumnName())) {
							b = true;
						}
					}
					if (!b) {
						e.setStatus("1");
					}
				}

				table.setPkList(tableSchemaDao.findTablePK(table));

			}
		}
		return table;
	}
}
