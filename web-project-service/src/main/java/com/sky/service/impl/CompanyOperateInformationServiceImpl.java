package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.CompanyOperateInformationMapper;
import com.sky.model.CompanyOperateInformation;
import com.sky.service.CompanyOperateInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/11.
 */
@Service
@Transactional
public class CompanyOperateInformationServiceImpl extends ServiceImpl<CompanyOperateInformationMapper,CompanyOperateInformation> implements CompanyOperateInformationService {

    @Override
    public Page<CompanyOperateInformation> getCompanyOperateInformationList(Integer page, Integer size, String listCode) {
        Page<CompanyOperateInformation> pageInfo = new Page<CompanyOperateInformation>(page, size);
        List<CompanyOperateInformation> list = baseMapper.getCompanyOperateInformationList( pageInfo,listCode);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
