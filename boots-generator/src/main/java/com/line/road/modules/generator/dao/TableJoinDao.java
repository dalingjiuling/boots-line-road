package com.line.road.modules.generator.dao;

import com.line.road.framework.persistence.CrudDao;
import com.line.road.framework.persistence.annotion.MyBatisDao;
import com.line.road.modules.generator.entity.TableJoin;

@MyBatisDao
public interface TableJoinDao extends CrudDao<TableJoin> {

}
