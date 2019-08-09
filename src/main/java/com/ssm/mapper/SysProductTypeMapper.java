package com.ssm.mapper;

import com.ssm.domain.SysProductType;

public interface SysProductTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(SysProductType record);

    int insertSelective(SysProductType record);

    SysProductType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(SysProductType record);

    int updateByPrimaryKey(SysProductType record);
}