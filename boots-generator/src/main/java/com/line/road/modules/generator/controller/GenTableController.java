package com.line.road.modules.generator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.line.road.framework.persistence.entity.Result;
import com.line.road.framework.persistence.entity.ResultFactory;
import com.line.road.framework.web.controller.BaseController;
import com.line.road.modules.generator.entity.TableColumn;
import com.line.road.modules.generator.entity.TableSchema;
import com.line.road.modules.generator.service.GenTableService;

@Controller
@RequestMapping("/gen/genTable")
public class GenTableController extends BaseController {

	@Autowired
	private GenTableService genTableService;

	/**
	 * 获取已经存在的生成表的数据
	 * 
	 * @param table
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public List<TableSchema> list(TableSchema table) {
		return genTableService.findAllList(table);
	}

	@RequestMapping("save")
	@ResponseBody
	public Result save(TableSchema table, Model model) {
		if (null != table) {
			boolean existsPk = false;
			boolean existsNull = false;
			boolean existsInsert = false;
			boolean existsUpdate = false;
			boolean existsList = false;
			boolean existsQuery = false;
			for (TableColumn c : table.getColumnList()) {
				if ("1".equals(c.getIsPk())) {
					existsPk = true;
				}
				if ("1".equals(c.getIsNull())) {
					existsNull = true;
				}
				if ("1".equals(c.getIsInsert())) {
					existsInsert = true;
				}
				if ("1".equals(c.getIsUpdate())) {
					existsUpdate = true;
				}
				if ("1".equals(c.getIsList())) {
					existsList = true;
				}
				if ("1".equals(c.getIsQuery())) {
					existsQuery = true;
				}
			}

			if ((!existsPk) || (!existsNull) || (!existsInsert) || (!existsUpdate) || (!existsList) || (!existsQuery)) {
				return ResultFactory.buildFailResult("每一列复选按钮必须选择一项！");
			}

			int count = genTableService.insert(table);
			String msg = new StringBuilder().append("保存表'").append(table.getTableName()).append("'成功").toString();
			// 生成代码
			genTableService.generateCode(table);
		}
		return null;
	}

	public String from(TableSchema table, Model model) {

		return null;
	}
}