package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.SystemParam;
import com.sky.vo.SystemParam_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
public interface SystemParamMapper extends BaseMapper<SystemParam> {

    List<SystemParam_VO> getParamListByIdentity(@Param("paramIdentity") String paramIdentity);

    List<SystemParam> getSystemParamList(Pagination page ,@Param("paramIdentity") String  paramIdentity,@Param("paramName") String paramName ,@Param("parentCode") String  parentCode);

    List<SystemParam> getSystemParamList(@Param("paramIdentity") String  paramIdentity,@Param("paramName") String paramName ,@Param("parentCode") String  parentCode);

}
