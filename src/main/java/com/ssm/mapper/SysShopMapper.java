package com.ssm.mapper;

import com.ssm.domain.SysShop;

public interface SysShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(SysShop record);

    int insertSelective(SysShop record);

    SysShop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(SysShop record);

    int updateByPrimaryKey(SysShop record);
}