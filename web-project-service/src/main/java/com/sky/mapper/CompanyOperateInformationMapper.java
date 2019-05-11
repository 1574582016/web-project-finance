package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.CompanyOperateInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/11.
 */
public interface CompanyOperateInformationMapper extends BaseMapper<CompanyOperateInformation> {

    List<CompanyOperateInformation> getCompanyOperateInformationList(Pagination page, @Param("listCode") String listCode);
}
