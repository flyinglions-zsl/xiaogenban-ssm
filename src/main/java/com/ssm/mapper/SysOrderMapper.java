package com.ssm.mapper;

import com.ssm.domain.SysOrder;

public interface SysOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(SysOrder record);

    int insertSelective(SysOrder record);

    SysOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(SysOrder record);

    int updateByPrimaryKey(SysOrder record);
}