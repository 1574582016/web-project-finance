package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.CompanyBaseInformationMapper;
import com.sky.model.CompanyBaseInformation;
import com.sky.service.CompanyBaseInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@Service
@Transactional
public class CompanyBaseInformationServiceImpl extends ServiceImpl<CompanyBaseInformationMapper,CompanyBaseInformation> implements CompanyBaseInformationService {

    @Override
    public Page<CompanyBaseInformation> getCompanyBaseInformationList(Integer page, Integer size, String listCode, String listName) {
        Page<CompanyBaseInformation> pageInfo = new Page<CompanyBaseInformation>(page, size);
        List<CompanyBaseInformation> list = baseMapper.getCompanyBaseInformationList( pageInfo,listCode, listName);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
