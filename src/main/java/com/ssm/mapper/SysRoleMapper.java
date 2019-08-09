package com.ssm.mapper;

import com.ssm.domain.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}