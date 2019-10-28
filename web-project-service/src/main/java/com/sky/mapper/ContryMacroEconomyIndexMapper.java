package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.ContryMacroEconomyIndex;
import com.sky.vo.MacroEconomy_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/26.
 */
public interface ContryMacroEconomyIndexMapper extends BaseMapper<ContryMacroEconomyIndex> {

    List<MacroEconomy_VO> getContryMacroEconomy(@Param("contry") String contry ,@Param("indexCode")  String indexCode ,@Param("startDay")  String startDay ,@Param("endDay")  String endDay);
}
