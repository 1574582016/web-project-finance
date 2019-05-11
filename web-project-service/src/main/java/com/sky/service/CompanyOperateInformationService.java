package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.CompanyOperateInformation;

/**
 * Created by ThinkPad on 2019/5/11.
 */
public interface CompanyOperateInformationService extends IService<CompanyOperateInformation> {

    Page<CompanyOperateInformation> getCompanyOperateInformationList(Integer page, Integer size, String listCode);
}
