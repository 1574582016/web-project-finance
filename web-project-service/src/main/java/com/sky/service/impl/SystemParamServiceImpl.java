package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.SystemParamMapper;
import com.sky.model.SystemParam;
import com.sky.service.SystemParamService;
import com.sky.vo.SystemParam_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
@Service
@Transactional
public class SystemParamServiceImpl extends ServiceImpl<SystemParamMapper ,SystemParam> implements SystemParamService {
    @Override
    public List<SystemParam_VO> getParamListByIdentity(String paramIdentity) {
        return baseMapper.getParamListByIdentity( paramIdentity);
    }

    @Override
    public Page<SystemParam> getSystemParamList(Integer page, Integer size, String  paramIdentity,String paramName , String  parentCode) {
        Page<SystemParam> pageInfo = new Page<SystemParam>(page, size);
        List<SystemParam> list = baseMapper.getSystemParamList(pageInfo,paramIdentity, paramName ,parentCode);
        for(SystemParam systemParam : list){
            List<SystemParam> sublist = baseMapper.getSystemParamList(null, null ,systemParam.getParamCode());
            systemParam.setChildParam(sublist);
        }
        pageInfo.setRecords(list);
        return pageInfo;
    }

    @Override
    public List<SystemParam> getSystemParamList(String  paramIdentity,String paramName , String  parentCode) {
        return baseMapper.getSystemParamList(  paramIdentity, paramName ,   parentCode);
    }
}
