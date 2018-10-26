package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SystemParam;
import com.sky.vo.SystemParam_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
public interface SystemParamService extends IService<SystemParam> {

    List<SystemParam_VO> getParamListByIdentity(String paramIdentity);

    Page<SystemParam> getSystemParamList(Integer page , Integer size , String  paramIdentity,String paramName , String  parentCode);

    List<SystemParam> getSystemParamList( String  paramIdentity,String paramName , String  parentCode);
}
