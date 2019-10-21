package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.FuturesDealData;
import com.sky.vo.SectorOrderStatic_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/21.
 */
public interface FuturesDealDataMapper extends BaseMapper<FuturesDealData> {

    List<SectorOrderStatic_VO> getFuturesOrderStaticList(@Param("orderType") String orderType , @Param("startDay") String startDay , @Param("endDay") String endDay);
}
