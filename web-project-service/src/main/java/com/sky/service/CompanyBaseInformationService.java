package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.CompanyBaseInformation;

/**
 * Created by ThinkPad on 2019/5/10.
 */
public interface CompanyBaseInformationService extends IService<CompanyBaseInformation> {

    Page<CompanyBaseInformation> getCompanyBaseInformationList(Integer page, Integer size, String listCode, String listName );
}
