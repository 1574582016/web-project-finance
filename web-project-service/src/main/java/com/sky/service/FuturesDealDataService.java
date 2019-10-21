package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.FuturesDealData;
import com.sky.vo.SectorOrderStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/21.
 */
public interface FuturesDealDataService extends IService<FuturesDealData> {

    List<FuturesDealData> spiderFuturesDealData(Integer periodType , String futuresCode);

    List<SectorOrderStatic_VO> getFuturesOrderStaticList(String orderType , String startDay , String endDay);
}
