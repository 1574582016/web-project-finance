package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.CompanyBaseInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/10.
 */
public interface CompanyBaseInformationMapper extends BaseMapper<CompanyBaseInformation> {

    List<CompanyBaseInformation> getCompanyBaseInformationList(Pagination page, @Param("listCode") String listCode, @Param("listName") String listName);
}
